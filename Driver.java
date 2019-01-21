import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class Driver{

  public static void seeAvailable(Branch[] NYPLBranches) {

    for (Branch b: NYPLBranches) {
      if (b.countAvailCopies() > 0) {
        System.out.println("\n"+b.getName());
        System.out.println("\tAvailable copies: "+b.countAvailCopies());
      }
    }

  }

  public static void seeUpcoming(LibraryCalendar cal) {
    for (int i=0;i<cal.getCal().length;i++) {
      Month mon = cal.getCal()[i];
      MyDate[] calArray = mon.getMonthArray();
      System.out.println("\n\n--------------------\n"+mon.getName()+", "+mon.getYear());
      for (int i2=0;i2<calArray.length;i2++) {
        MyDate date = calArray[i2];
        System.out.print("\n\n"+date.getDay());
        if (date.getCopiesDue().size() > 0) {
          System.out.println("   Copies due: "+date.getCopiesDue().size());
          for (Copy c: date.getCopiesDue()) {
            System.out.println("\t"+c.getBranch().getName());
          }
        }
      }
    }
    System.out.println("---------------------------");
  }

  public static void handleInput(Branch[] NYPLBranches, LibraryCalendar cal) {

    System.out.println("\n\n\nEnter a number to check out: ");
    System.out.println("\t1: Locations of available books");
    System.out.println("\t2: Upcoming due dates and locations");

    boolean searching = true;
    int input =0;
    while (searching) {

      System.out.println("\033[?25h"); //shwo cursor.
      Scanner sys = new Scanner(System.in);

      try {
        input = sys.nextInt();
        searching = false;
        System.out.print("\033[?25l"); // hide cursor.
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid integer.");
      }

      if (input < 1 || input > 2) {
        System.out.println("Please enter a valid input.");
        searching = true;
      } else {
        System.out.print("\033[H\033[2J");//clear screen.
        System.out.flush();
      }

    }

    if (input == 1) {
      Driver.seeAvailable(NYPLBranches);
    }
    if (input == 2) {
      Driver.seeUpcoming(cal);
    }

  }

  public static void runInputHandler(Branch[] NYPLBranches, Copy[] out, LibraryCalendar cal) {

    boolean cont = true;

    while (cont){

      Driver.handleInput(NYPLBranches,cal);

      boolean searching = true;
      System.out.println("\n\n\n\n\n");

      while (searching) {

        System.out.println("\nWould you like to continue? (yes / no)");
        System.out.println("\033[?25h"); //shwo cursor.

        Scanner sys = new Scanner(System.in);

        String ans = sys.nextLine();
        System.out.print("\033[?25l"); // hide cursor.

        if (ans.equals("no")) {
          cont = false;
          searching = false;
          System.out.println("Thanks for using the Library Database!! Have a great day.");
          System.out.println("\033[?25h"); //shwo cursor.

        } else if (ans.equals("yes")) {
          searching = false;
        } else {
          System.out.println("Please enter either yes or no.");
        }
      }

    }

  }


  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    Branch[] NYPLBranches = BranchData.getBranches("NYPLLocations.json");
    CatalogAccessor ny = new NYPLAccessor(NYPLBranches);
    Copy[] out = ny.getAllCopies(result);

    if (out.length > 0) {
      System.out.println("\n\n\nThere were "+out.length+" copies of "+result.getTitle()+" found in the NYPL database.\n\n");
    } else {
      System.out.println("\n\n\nSorry, there were no results found for "+result.getTitle()+" in the NYPL database.");
      System.out.println("\033[?25h"); //shwo cursor.
      System.exit(1);
    }

    LibraryCalendar.newLibraryCalendar(out);

    Copy.countCopies(out,cal);

    Driver.runInputHandler(NYPLBranches,out,cal);



/* debug print loop.
    for(Copy c : out) {
      System.out.println(c);
      if (c.getAvail()) {
        for (Branch b: NYPLBranches) {
          if (c.getBranch().getName().equals(b.getName())) {
            System.out.println("\tBranch: "+b.getName());
            System.out.println(b.getStoredCopies());
            Copy cc = new Copy(b,"test","test","test");
            b.addCopy(cc);
          }
        }
      }
    }
    */

/*
    System.out.println("complete");

    for (Branch b: NYPLBranches) {
      if(b.getStoredCopies() != null){
        System.out.print(b.getName());
        System.out.println(": "+b.numCopies());
        for (Copy c: b.getStoredCopies()) {
          System.out.println("\t"+c);
          System.out.println(c.getStatus());
          System.out.println(c.getDueM());
          //c.updatedueDate();
          //System.out.println("\t"+c.getDueM()+"/"+c.getDueD()+"/"+c.getDueY());
        }
      }
    }
    System.out.print("\033[?25h"); // show cursor.*/
  }
}
