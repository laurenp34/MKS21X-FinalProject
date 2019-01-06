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


  public static void main(String[] args) {
    boolean searching = true;


    Terminal terminal = TerminalFacade.createTerminal();
		terminal.enterPrivateMode();

		TerminalSize terminalSize = terminal.getTerminalSize();

    putString(1,2,terminal,"Search for your book using its ISBN: ");


    Key key = terminal.readInput();

    if (key != null) {

      if (key == Key.Kind.Enter) {
        terminal.setCursorVisible(false);


      }
      searchTerm += key.getCharacter();


    }



    //putString(1,2,terminal,"Search for your book using its ISBN: ");

    //TextInputDialog.showTextInputBox(gscr, "Search box", "search by ISBN", "Please enter the ISBN");

    //  example code from Stack Overflow
    /*
    Panel username = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
    username.addComponent(new Label("Username: "));
    username.addComponent(new TextBox(null, 15));
    addComponent(username);
    */


  }


}
