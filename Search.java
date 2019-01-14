
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
  private ArrayList<Character> searchTermInput;
  private int numFound;
  private String[] isbn;
  private Book[] docs;
  private String searchTerm;
  private Terminal terminal;
  //private Terminal terminal;

  public Search() {
    searchTerm = "";
    ArrayList<Character> searchTermInput = new ArrayList<Character>();


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
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();
    //TerminalFacade.createTextTerminal();  //USE THIS COMMAND INSTEAD!
    terminal.clearScreen();
    TerminalSize terminalSize = terminal.getTerminalSize();

    System.out.print("Search for your book: ");
    Scanner sys = new Scanner(System.in);

    String searchTermInput = sys.next();
    sys.nextLine();


/*
    //run search until enter is pressed.
    boolean searching = true;
    while (searching) {
      Key key = terminal.readInput();


      if (key != null) {

        if (key.getKind().equals(Key.Kind.Enter)) {
          terminal.setCursorVisible(false);
          searching = false;

        } else if (key.getKind().equals(Key.Kind.Escape)) {
          terminal.clearScreen();
          terminal.exitPrivateMode();
          System.exit(0);
      //  } //if they press backspace (deletes)
        //  else if (key.getKind().equals(Key.Kind.Backspace) && searchTermInput.size() > 0) {
          //searchTermInput.remove(searchTermInput.size() - 1);
        } else {

          searchTermInput.add((Character) key.getCharacter());
        }
      }

    for (char c: searchTermInput) {
      System.out.print(c);
    }
    System.out.println("your search term is: " + searchTermInput);

    //to add the search term to the URL, + needs to replace space.
    for (char c: searchTermInput) {
      if (c == ' ') {
        searchTerm += '+';
      } else {
        searchTerm += c;
      }
    }
    */

    terminal.setCursorVisible(false);
  }

  public void chooseResult() {

    Terminal terminal = TerminalFacade.createTextTerminal();

    System.out.println("\n\n\nChoose a book (1-"+numFound+"): ");
    terminal.setCursorVisible(true);


  }

  public String runSearch() {
    //initScreen();
    generateSearchTerm();
    buildSearch(searchTerm);
    printSearchResults();
    chooseResult();

    return searchTerm;

  }


  public Search buildSearch(String searchTerm){
    Search out = null;

    try{
      //URL object accesses webpage, InputStreamReader allows reading of its data
      URL webpage = new URL("https://openlibrary.org/search.json?q="+searchTerm);
      System.out.println("https://openlibrary.org/search.json?q="+searchTerm);
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

    //since a new search object out was created to store the json data, now we will transfer this data
    // to the current object calling this method.
    this.docs = out.docs;
    this.numFound = out.numFound;



    return out;
  }

  public void printSearchResults() {


    //Terminal terminal = TerminalFacade.createTerminal();

    System.out.println("Book found!");
    System.out.println("There were "+docs.length+" books that matched your search.");

    for (int i=0;i<docs.length;i++) {
      System.out.println("\n\n------------------");
      System.out.println(docs[i].title);
      System.out.println(Arrays.toString(docs[i].author_name));
      //System.out.println(Arrays.toString(docs[0].text));

      //String[] text stores the isbns as well as a variety of tags (themes).
      //this loop differentiates the tags from the isbns.

      // this (below) is for if you change tags back from ArrayList<String> to String[]
        //String[] docs[0].tags = new String[100];
        //String[] docs[0].tags = new String[docs[0].text.length];
      docs[i].tags = new ArrayList<String>();
      //int idx=0;
      for (String s: docs[i].text) {
        if (s.contains(" ")) {
          docs[i].tags.add(s);
        } else {
          //check if the string contains a lowercase letter.
          for (int idx2=0;idx2<s.length();idx2++) {
            char c = s.charAt(idx2);
            if ((int) c >= 97 && (int) c <= 122) {
              docs[i].tags.add(s);
              idx2 = s.length();
            }
          }
        }
        //idx++;
        }

        System.out.println("tags: ");
        System.out.println(docs[i].tags);
      }
    //putString(1,4,terminal,docs[0].title);
    //putString(1,5,terminal,docs[0].author_name[0]);

    //putString(1,6,terminal,"" + docs.length);

    //count copies found.

    //this loop prints out various statements to check search.
    /*
    for(int i=0;i<docs.length;i++){
      System.out.println(docs[i].title);
      if(docs[i].author_name[0] != null) System.out.println(docs[i].author_name[0]);
      System.out.println(Arrays.toString(docs[i].isbn));
      System.out.println("\n\n");
    }
    */
  }


  public static void main(String[] args){
    //for testing purposes
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
