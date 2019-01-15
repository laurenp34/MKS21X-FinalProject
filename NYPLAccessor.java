import java.net.*;
import java.io.*;
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class NYPLAccessor implements CatalogAccessor{
  private Branch[] branches;
  public NYPLAccessor(Branch[] branches){
    this.branches = branches;
  }
  public Copy[] getAllCopies(Book bk){
    try{
      long startTime = System.currentTimeMillis();
      //System.out.println("start 0");
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      //System.out.println(searchUrl);
      URL catSearch = new URL(searchUrl);
      //System.out.println("begin download " + (System.currentTimeMillis()-startTime));
      InputStream stream = catSearch.openStream();
      //System.out.println("begin scanner build " + (System.currentTimeMillis()-startTime));
      Scanner sca = new Scanner(stream);
      //System.out.println("begin extraction" + (System.currentTimeMillis()-startTime));
      ArrayList<String> catalogIDs = getResultIDs(sca);
      //System.out.println("number of pages: "+catalogIDs.size());
      ArrayList<Copy> out = new ArrayList<Copy>();
      for(String bookID : catalogIDs){
        addCopiesFromPage(bookID,out);
      }
      //System.out.println("complete " + (System.currentTimeMillis()-startTime));
      Copy[] temp = new Copy[0];
      return out.toArray(temp);
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
    return null;
  }
  private String genSearch(String[] terms){
    return "%28"+String.join("%20%7C%20",terms)+"%29";
  }
  private ArrayList<String> getResultIDs(Scanner sca){
    ArrayList<String> out = new ArrayList<String>();
    while(sca.hasNextLine()){
      String line = sca.nextLine();
      if(line.contains("<div id=\"allitemsmax-")){
        line = line.substring((line.indexOf("<div id=\"allitemsmax-")+"<div id=\"allitemsmax-".length()),line.length());
        out.add(line.substring(0,line.indexOf("\"")));
      }
    }
    return out;
  }
  private String getCopyHTML(Scanner sca){
    ArrayList<String> out = new ArrayList<String>();
    while(sca.hasNextLine()){
      String line = sca.nextLine();
      if(line.contains("<div id=\"allitems-")){
        return getDiv(line,sca);
      }
    }
    return null;
  }
  private String getDiv(String first,Scanner sca){
    int divDepth = 2;
    String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+first+"\n";
    String currentLine = "";
    while(divDepth > 0){
      out  += "\n" + currentLine;
      currentLine = sca.nextLine();
      if(currentLine.contains("<div")) divDepth ++;
      if(currentLine.contains("</div>")) divDepth --;
    }
    return out+"\n";
  }

  private Document getDocument(String XMLStr){
    try{
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(new InputSource(
                            new ByteArrayInputStream(
                              XMLStr.getBytes("utf-8"))));
    }catch(ParserConfigurationException e){
      System.out.println(e.getClass().getName());
      e.printStackTrace();
      System.exit(1);
    }catch(SAXException e){
      System.out.println(e.getClass().getName());
      e.printStackTrace();
      System.exit(2);
    }catch(IOException e){
      System.out.println(e.getClass().getName());
      e.printStackTrace();
      System.exit(3);
    }
    return null;
  }
  private void addCopiesFromPage(String id,ArrayList<Copy> out)throws MalformedURLException,IOException{
    URL idPage = new URL("https://browse.nypl.org/iii/encore/record/C__R"+id+"?lang=eng");
    //System.out.println("begin download of "+id);
    //System.out.println("https://browse.nypl.org/iii/encore/record/C__R"+id+"?lang=eng");
    InputStream stream = idPage.openStream();
    //System.out.println("begin scanner build ");
    Scanner sca = new Scanner(stream);
    //System.out.println("begin extraction");
    String htmlBlock = getCopyHTML(sca);
    Document html = getDocument(htmlBlock);
    Element root = html.getDocumentElement();
    addCopiesFromBlock(root,out);
  }

  //(helper to addCopiesFromPage: takes a root element and AL to add copies to, adds all necessary copies)
  private void addCopiesFromBlock(Element root,ArrayList<Copy> out){
    Element table = traceDownFirsts(root,"div","table");//navigate to table
    NodeList rows = table.getElementsByTagName("tr");//NodeList of the rows
    for(int i=1;i<rows.getLength();i++){
      Element row = (Element)(rows.item(i));//typecast from more general Node to Element so that certain tools can be used
      out.add(buildCopy(row));
    }
  }
  private Element traceDownFirsts(Element root,String... tagNames){//follows XML tree down first element of each new layer with given tag
    Element out = root;
    for(String tagName : tagNames){
      //move pointer element to the first child node with given name
      out = (Element)(root.getElementsByTagName(tagName).item(0));
    }
    return out;
  }


  private Copy buildCopy(Element row){//takes a tr element, extracts the four columns and uses that data to construct a new Copy
    NodeList cells = row.getElementsByTagName("td");
    if(cells.getLength() < 4) throw new IllegalArgumentException("row length "+cells.getLength());
    //traces down to the internal link, then gets string out
    String locID = traceDownFirsts((Element)(cells.item(0)),"a").getAttributeNode("onclick").getValue().substring(23,25);
    Branch loc = BranchData.branchWithID(locID,branches);
    //similar to above, but also traces through a span
    String callnum = traceDownFirsts((Element)(cells.item(1)),"span","a").getChildNodes().item(0).getNodeValue().trim();
    //plain text tr cell, gets text child and then its value, and removes whitespace
    String status = cells.item(2).getChildNodes().item(0).getNodeValue().trim();
    String message = cells.item(3).getChildNodes().item(0).getNodeValue().trim();
    return new Copy(loc,callnum,status,message);
  }
}
