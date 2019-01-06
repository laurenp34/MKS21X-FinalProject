import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    Copy[] out = null;
    try{
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      System.out.println(searchUrl);
      URL searchPage = new URL(searchUrl);
      Document html = Jsoup.connect(searchUrl).get();

      return null;
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
    return out;
  }
  private String genSearch(String[] terms){
    return "%28"+String.join("%20%7C%20",terms)+"%29";
  }
}
