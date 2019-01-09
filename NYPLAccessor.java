import java.net.*;
import java.io.*;
import java.util.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    try{
      System.out.println("start");
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      System.out.println(searchUrl);
      URL catSearch = new URL(searchUrl);
      System.out.println("begin download");
      Scanner sca = new Scanner(catSearch.openStream());
      System.out.println("begin extraction");
      ArrayList<String> htmlBlocks = getCopyHTML(sca);
      //for(String s : htmlBlocks) System.out.println(s+"\n_______________________________________________________________________\n");
      for(int i=0;i<htmlBlocks.size();i++){
        Document d = getDocument(htmlBlocks.get(i));
        System.out.println(d);
      }
      System.out.println("complete");
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

  private ArrayList<String> getCopyHTML(Scanner sca){
    ArrayList<String> out = new ArrayList<String>();
    while(sca.hasNextLine()){
      if(sca.nextLine().contains("class=\"availableMaxItemsSection\"")){
        out.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<div>\n"+getDiv(sca));
      }
    }
    return out;
  }
  private String getDiv(Scanner sca){
    int divDepth = 1;
    String out = "";
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
}
