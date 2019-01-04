//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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


    Terminal terminal = TerminalFacade.createTerminal();
		terminal.enterPrivateMode();

		TerminalSize terminalSize = terminal.getTerminalSize();

    Screen scr = new Screen(terminal);
    GUIScreen gscr = new GUIScreen(scr);


    putString(1,2,terminal,"Search for your book using its ISBN: ");

    TextInputDialog.showTextInputBox(gscr, "Search box", "search by ISBN", "Please enter the ISBN");



  }


}
