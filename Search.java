
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
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
      //URL object accesses webpage, InputStreamReader to get data
      URL webpage = new URL("https://openlibrary.org/search.json?q="+searchTerm);
      Reader json = new InputStreamReader(webpage.openStream());
      //for accessing nonstatic methods in Gson class
      Gson g = new Gson();
      //parses data from json file into an object
      out = g.fromJson(json,Search.class);
      out.searchTerm = searchTerm;
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
    return out;
  }
  public static void main(String[] args){//for testing purposes
    // method used here effectively converts simplistic search terms from args
    // can be made more indepth and input can come from places other than shell args
    Search s = buildSearch(String.join("+",args));
    //printing of various variables to demonstrate successful conversion
    System.out.println(s.numFound);
    for(int i=0;i<s.docs.length;i++){
      System.out.println(s.docs[i].title);
      if(s.docs[i].author_name[0] != null) System.out.println(s.docs[i].author_name[0]);
      System.out.println(Arrays.toString(s.docs[i].isbn));
      System.out.println("\n\n");
    }
  }
}
