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


}
