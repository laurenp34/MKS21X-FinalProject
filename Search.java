
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
  public Search(String searchTerm){
    this.searchTerm = searchTerm;
    try{
      URL webpage = new URL("https://openlibrary.org/search.json?q="+searchTerm);
      Class[] temp = {String.class};
      String json = "";
      //code from oracle official tutorials
      BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
          json += inputLine + "\n";
      in.close();
      System.out.println(json);
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  public static void main(String[] args){//for testing purposes
    Search s = new Search(String.join(" ",args));
  }
}
