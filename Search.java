
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
//import com.googlecode.lanterna.screen.AbstractScreen ;
//import com.googlecode.lanterna.screen.TerminalScreen;
import java.io.*;
import java.util.*;
import java.net.*;

public class Search{
  private String searchTermInput;
  private int numFound;
  private Book[] docs;
  private String[] text;
  private String searchTerm;
  //private Terminal terminal;

  public Search() {
    searchTermInput = "";
    searchTerm = "";

  }

  public String getSearchTerm() {
    return searchTerm;
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void putStringColor(int r, int c,Terminal t, String s, Terminal.Color color){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.applyBackgroundColor(color);
      t.putCharacter(s.charAt(i));
    }
  }

  public void generateSearchTerm() {

    //initialize Screen
    Terminal terminal = TerminalFacade.createTerminal();
    terminal.enterPrivateMode();
    terminal.clearScreen();
    //TerminalFacade.createTextTerminal();

    TerminalSize terminalSize = terminal.getTerminalSize();

    putString(1,2,terminal,"Search for your book by entering its ISBN:");

    //run search until enter is pressed.
    boolean searching = true;
    while (searching) {

      Key key = terminal.readInput();

      if (key != null) {

        if (key.getKind().equals(Key.Kind.Enter)) {
          terminal.setCursorVisible(false);
          searching = false;


        } if (key.getKind().equals(Key.Kind.Escape)) {
          terminal.clearScreen();
          terminal.exitPrivateMode();
          System.exit(0);
        } else {
          searchTermInput += key.getCharacter();
        }


      }
    }

    System.out.println("your search term is: " + searchTermInput);
    terminal.setCursorVisible(false);
  }

  public String runSearch() {
    //initScreen();
    generateSearchTerm();
    buildSearch(searchTermInput);
    printSearchResults();

    return searchTerm;

  }


  public static Search buildSearch(String searchTerm){
    Search out = null;

    String formattedSearchTerm = "";

    //to add the search term to the URL, + needs to replace space.
    for (char c: searchTerm.toCharArray()) {
      if (c == ' ') {
        formattedSearchTerm += '+';
      } else {
        formattedSearchTerm += c;
      }
    }

    try{
      //URL object accesses webpage, InputStreamReader allows reading of its data
      URL webpage = new URL("https://openlibrary.org/search.json?q="+formattedSearchTerm);
      Reader json = new InputStreamReader(webpage.openStream());
      //for accessing nonstatic methods in Gson class
      Gson g = new Gson();
      //parses data from json file into an object
      out = g.fromJson(json,Search.class);
      out.searchTerm = formattedSearchTerm;
    }catch(MalformedURLException e){
      e.printStackTrace();
    }catch(IOException e){
      e.printStackTrace();
    }
    return out;
  }

  public void printSearchResults() {


    Terminal terminal = TerminalFacade.createTerminal();

    System.out.println(Arrays.toString(text));

    putString(1,4,terminal,docs[0].title);
    putString(1,5,terminal,docs[0].author_name[0]);

    putString(1,6,terminal,"" + docs.length);

    //count copies found.
    int countCopies;

    //this loop prints out various statements to check search.
    for(int i=0;i<docs.length;i++){
      System.out.println(docs[i].title);
      if(docs[i].author_name[0] != null) System.out.println(docs[i].author_name[0]);
      System.out.println(Arrays.toString(docs[i].isbn));
      System.out.println("\n\n");
    }


  }


  public static void main(String[] args){//for testing purposes
    // method used here effectively converts simplistic search terms from args
    // can be made more indepth and input can come from places other than shell args
    /*
    Search s = buildSearch(String.join("+",args));
    //printing of various variables to demonstrate successful conversion
    System.out.println(s.numFound);
    for(int i=0;i<s.docs.length;i++){
      System.out.println(s.docs[i].title);
      if(s.docs[i].author_name[0] != null) System.out.println(s.docs[i].author_name[0]);
      System.out.println(Arrays.toString(s.docs[i].isbn));
      System.out.println("\n\n");
    }
    */


    Search sea = new Search();

    sea.runSearch();
  }


}
