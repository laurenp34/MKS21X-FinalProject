<<<<<<< HEAD

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
      //URL object accesses webpage, InputStreamReader allows reading of its data
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
=======
//API : http://mabe02.github.io/lanterna/apidocs/2.1/
/*
import com.googlecode.lanterna;

import com.googlecode.lanterna.bundle;

import com.googlecode.lanterna.graphics;

import com.googlecode.lanterna.gui2;

import com.googlecode.lanterna.gui2.dialogs;

import com.googlecode.lanterna.gui2.table;

import com.googlecode.lanterna.input;

import com.googlecode.lanterna.screen;

import com.googlecode.lanterna.terminal;

import com.googlecode.lanterna.terminal.ansi;

import com.googlecode.lanterna.terminal.swing;

import com.googlecode.lanterna.terminal.virtual;

//import com.googlecode.lanterna.gui;
//import com.googlecode.lanterna.terminal;
//import com.googlecode.lanterna.screen;
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
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.dialog.TextInputDialog;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.gui2;
//import com.googlecode.lanterna.gui2.TextBox;

//import com.googlecode.lanterna.gui.Panels;
//import com.googlecode.lanterna.gui2.AbstractComponent;
//import com.googlecode.lanterna.gui2.AbstractInteractableComponent;
import com.googlecode.lanterna.gui2.TextBox;
*/
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


public class Search {
  //private JSONObject searchResults;
  //private Book[] booksFound;
  private String searchTerm;

  public Search() {
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

  public static void initScreen() {
    //boolean searching = true;



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
          searchTerm += key.getCharacter();
        }


      }
    }

    System.out.println("your search term is: " + searchTerm);
    terminal.setCursorVisible(false);


  }

  public String runSearch() {
    //initScreen();
    generateSearchTerm();
    return searchTerm;

  }


  public static void main(String[] args) {

    Search s = new Search();

    s.runSearch();

  }


>>>>>>> Search
}
