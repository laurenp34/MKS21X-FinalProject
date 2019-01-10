import java.net.*;
import java.io.*;
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    try{
      long startTime = System.currentTimeMillis();
      System.out.println("start 0");
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      System.out.println(searchUrl);
      URL catSearch = new URL(searchUrl);
      System.out.println("begin download " + (System.currentTimeMillis()-startTime));
      Scanner sca = new Scanner(catSearch.openStream());
      System.out.println("begin extraction" + (System.currentTimeMillis()-startTime));
      ArrayList<String> catalogIDs = getResultIDs(sca);
      /*
      //for(String s : htmlBlocks) System.out.println(s+"\n_______________________________________________________________________\n");
      ArrayList<Copy> out = new ArrayList<Copy>();
      for(int i=0;i<htmlBlocks.size();i++){
        Document d = getDocument(htmlBlocks.get(i));
        Element root = d.getDocumentElement();
        System.out.println(root.getAttribute("id"));
        addCopiesFromBlock(root,out);
      }
      */
      System.out.println("complete " + (System.currentTimeMillis()-startTime));
      return null;
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
  private ArrayList<String> getCopyHTML(Scanner sca){
    ArrayList<String> out = new ArrayList<String>();
    while(sca.hasNextLine()){
      String line = sca.nextLine();
      if(line.contains("<div id=\"allitemsmax-")){
        out.add(getDiv(line,sca));
      }
    }
    return out;
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
  private void addCopiesFromBlock(Element root,ArrayList<Copy> out){

  }
}
