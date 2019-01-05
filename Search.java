
import com.google.gson.Gson;
import com.google.gson.FieldAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Search{
  private String searchTerm;
  private int numFound;
  private Book[] docs;
  public static Search buildSearch(String searchTerm){
    Search out = null;
    try{
      URL webpage = new URL("https://openlibrary.org/search.json?q="+searchTerm);
      String json = "";
      //code from oracle official tutorials, https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
      BufferedReader in = new BufferedReader(new InputStreamReader(webpage.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
          json += inputLine + "\n";
      in.close();
      Gson g = new Gson();
      out = g.fromJson(json,Search.class);
      out.searchTerm = searchTerm;
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
    return out;
  }
  public Book getResult(int i){
    return docs[i];
  }
  public static void main(String[] args){//for testing purposes
    Search s = buildSearch(String.join("+",args));
    System.out.println(s.numFound);
    for(int i=0;i<s.docs.length;i++){
      System.out.println(s.docs[i].title);
      if(s.docs[i].author_name[0] != null) System.out.println(s.docs[i].author_name[0]);
      System.out.println(Arrays.toString(s.docs[i].isbn));
      System.out.println("\n\n");
    }
  }
}
