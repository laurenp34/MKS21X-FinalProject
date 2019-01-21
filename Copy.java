import org.w3c.dom.*;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class Copy{
  private Branch location;
  private String  callnum;
  private String message;
  private String status;
  private boolean available;
  private MyDate dueDate;
  private int dueDay;
  private int dueMonth;
  private int dueYear;

  public Copy(Branch loc,String callnum,String status,String message){
    this.location = loc;
    this.callnum = callnum;
    this.status = status;
    this.message = message;
    available = status.equals("AVAILABLE");
    //ADD DATE CODE
  }
  public String toString(){
    if(available) return "  Available Copy @"+location.getName();
    else          return "Unavailable Copy @"+location.getName()+" "+status;
  }
  public Branch getBranch() {
    return location;
  }
  public boolean getAvail() {
    return available;
  }
  public String getMessage() {
    return message;
  }
  public String getStatus() {
    return status;
  }
  public int getDueD() {
    return dueDay;
  }
  public int getDueM() {
    return dueMonth;
  }
  public int getDueY() {
    return dueYear;
  }
  public MyDate getDueDate(){
    return dueDate;
  }

  public static void countCopies(Copy[] out, LibraryCalendar cal) {

    LocalDateTime now = LocalDateTime.now();
    int nowYear = now.getYear();
    int nowMonth = now.getMonthValue();
    int nowDay = now.getDayOfMonth();

    int countOverdue = 0;
    int countOnHold =0;
    int countInTrans = 0;
    int countAvailable = 0;
    int countUnavailable = 0;
    int countStorage = 0;
    int countLoaned = 0;

    for (Copy c: out) {
      //System.out.println(c.getAvail() + c.getMessage()+c.getStatus());
      if (c.getAvail()) {
        countAvailable ++;
      } else {
        countUnavailable ++;
        if (c.getStatus().contains("IN TRANSIT")) {
          countInTrans ++;
        } else if (c.getStatus().contains("ON HOLDSHELF")) {
          countOnHold ++;
        } else if (c.getStatus().contains("storage")) {
          countStorage ++;
        } else { // if the status contains a due date:
          if (c.getDueD() != 0) {
            countLoaned ++;
            if (c.getDueY() == nowYear) {
              c.updateDueDate(cal);
            } else  if ((c.getDueY() < nowYear) || (c.getDueD() < nowDay && c.getDueM() == nowMonth)){
              countOverdue ++;
            }
          }
        }
      }
    }
    System.out.println("Available copes: "+countAvailable);
    System.out.println("Unavailable copies: "+countUnavailable);
    System.out.println("\tCopies loaned out: "+countLoaned);
    System.out.println("\tCopies overdue: "+countOverdue);
    System.out.println("\tCopies on hold: "+countOnHold);
    System.out.println("\tCopies in transit: "+countInTrans);
    System.out.println("\tCopies in storage: "+countStorage);
  }

  public MyDate updateDueDMY() {
    if (!available && status.subSequence(0,3).equals("DUE")) {
      String month = (String) status.subSequence(4,6);
      dueMonth = Integer.parseInt(month);

      String day = (String) status.subSequence(7,9);
      dueDay = Integer.parseInt(day);

      String year = (String) status.subSequence(10,12);
      dueYear = Integer.parseInt(year) + 2000;
    }

    MyDate result = new MyDate(dueMonth,dueDay,dueYear);
    return result;
  }

  public void updateDueDate(LibraryCalendar libcal) {

    Month[] cal = libcal.getCal();
    int monthDiff = 0;

    Copy c = this;
    if (this.getDueY() == cal[0].getYear()) {
      monthDiff = this.getDueM() - cal[0].getNum(); // index for month.
    } /*else {    Code initially to work with books due before today. (incl. books due last yr)
      int countMon = 0;
      for (Month m: cal) {
        if (m.getYear() == cal[0].getYear()) {
          countMon ++; // countMOn counts how many months in the calendar are in the previous year.
        }
      }
      monthDiff = countMon + (this.getDueM() - 1);
    }*/

    Month mon = cal[monthDiff];
    dueDate = mon.getMonthArray()[dueDay-1];
    dueDate.addCopy(c);
  }
}
