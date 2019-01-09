import java.net.*;
import java.io.*;
import java.util.*;

public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    try{
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      System.out.println(searchUrl);
      URL catSearch = new URL(searchUrl);
      Scanner sca = new Scanner(catSearch.openStream());
      return null;
    }catch(IOException e){
      e.printStackTrace();
    }
    return null;
  }
  private String genSearch(String[] terms){
    return "%28"+String.join("%20%7C%20",terms)+"%29";
  }
  private String getDiv(Scanner sca){
    int divDepth = 1;
    String out = "";
    String currentLine = "";
    while(divDepth > 0){
      out  += currentLine;
      currentLine = sca.nextLine();
      if(currentLine.contains("<div")) divDepth ++;
      if(currentLine.contains("</div>")) divDepth --;
    }
    return out;
  }
}
