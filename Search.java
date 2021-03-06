
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/*
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
*/
import java.io.*;
import java.util.*;
import java.net.*;

public class Search{
  private String searchTermInput;
  private int numFound;
  private String[] isbn;
  private Book[] docs;
  private String searchTerm;
  //private Terminal terminal;

  public Search() {
    searchTerm = "";
    ArrayList<Character> searchTermInput = new ArrayList<Character>();


  }
  public Book getResult(int i){
    return docs[i];
  }

  public String getSearchTerm() {
    return searchTerm;
  }

  /*

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
  */

  public void generateSearchTerm(boolean clearScreen) {


    /*
    //initialize Screen
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();
    //TerminalFacade.createTextTerminal();  //USE THIS COMMAND INSTEAD!
    if (clearScreen) {
      terminal.clearScreen();
    }
    TerminalSize terminalSize = terminal.getTerminalSize();
    */

    if (clearScreen) {
      System.out.print("\033[H\033[2J");
      System.out.flush();

    }

    System.out.println("\033[?25h"); //shwo cursor.

    System.out.print("Search for your book: ");
    Scanner sys = new Scanner(System.in);

    String searchTermInput = sys.nextLine();
    System.out.print("\033[?25l"); // hide cursor.

    //sys.close();
    //System.out.println("\nYou searched for: "+searchTermInput);
    System.out.println("\n\n\nLoading . . . ");

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
      */

    //to add the search term to the URL, + needs to replace space.
    for (char c: searchTermInput.toCharArray()) {
      if (c == ' ') {
        searchTerm += '+';
      } else {
        searchTerm += c;
      }
    }
  }

  public Book chooseResult() {



    boolean properIntFound = false;
    boolean searching = true;
    int bookChosen = 0;
    while (!properIntFound) {
      while (searching) {
        if (numFound > 1) {
          System.out.print("\n\n\nChoose a book (1-"+numFound+"): ");
        } else {
          System.out.print("\n\n\nChoose a book (1): ");
        }
        System.out.println("\033[?25h"); //shwo cursor.
        //System.out.print(u"\u001b[1000D");

        Scanner sys = new Scanner(System.in);

        try {
          bookChosen = sys.nextInt();
          searching = false;
          System.out.print("\033[?25l"); // hide cursor.
        } catch (InputMismatchException e) {
          System.out.println("Please enter a valid integer.");
        }

      }

      //will get here if valid integer entered.
      if (bookChosen < 1 || bookChosen > numFound) {
        System.out.println("Please enter an integer between 1 and "+numFound);
        searching = true;
      } else {
        properIntFound = true;
      }
  }

  System.out.print("\033[H\033[2J");
  System.out.flush();

  System.out.println("\nYou chose: ");
  System.out.print(docs[bookChosen-1].title);
  System.out.print(" by ");
  for (String name: docs[bookChosen-1].author_name) {
    System.out.print(name + " ");
  }

  System.out.println("\n\nLoading . . . ");

  return docs[bookChosen-1];

}

  public Book runSearch() {
    //initScreen();
    generateSearchTerm(true);
    buildSearch(searchTerm);
    printSearchResults();

    return chooseResult();

  }


  public Search buildSearch(String searchTerm){
    Search out = null;

    try{
      //URL object accesses webpage, InputStreamReader allows reading of its data
      URL webpage = new URL("https://openlibrary.org/search.json?q="+searchTerm);
      //System.out.println("\nhttps://openlibrary.org/search.json?q="+searchTerm);
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

    boolean bookFound = false;

    System.out.print("\033[H\033[2J");
    System.out.flush();

    while (!bookFound) {

    if (numFound == 0) {
      System.out.println("\nSorry! There were no books found.");
      System.out.println("Try your search again.\n");

      searchTerm = "";
      searchTermInput = "";

      generateSearchTerm(false);
      buildSearch(searchTerm);
      System.out.println(numFound);

    } else {

      bookFound = true;

      System.out.println("\nBook found!");
      removeBadResults();
      System.out.println("There were "+docs.length+" books that matched your search.");
      numFound = docs.length;

    for (int i=0;i<docs.length;i++) {
      System.out.println("\n\n("+(i+1)+")------------------");
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
    }
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

    Book reesult = sea.runSearch();

  }
  private void removeBadResults(){
    ArrayList<Book> out = new ArrayList<Book>();
    for(Book b : docs){
      if(!(b.getISBNs()==null || b.getISBNs().length==0)) out.add(b);
    }
    Book[] temp = new Book[0];
    docs = out.toArray(temp);
  }


}
