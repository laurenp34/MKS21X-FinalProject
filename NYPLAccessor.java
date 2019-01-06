import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    Copy[] out = null;
    try{
      String[] ISBNs = bk.getISBNs();
      String searchStr = genSearch(ISBNs);
      String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
      URL searchPage = new URL(searchUrl);
      System.out.println("reached 15");
      DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      System.out.println("reached 17");
      InputStream is = searchPage.openStream();
      System.out.println("reached 18");
      Document html = db.parse(is);
      System.out.println("reached 19");
      return null;
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(SAXException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }catch(ParserConfigurationException e){
      e.printStackTrace();
    }
    return out;
  }
  private String genSearch(String[] terms){
    return "%28"+String.join("%20%7C%20",terms)+"%29";
  }
}
