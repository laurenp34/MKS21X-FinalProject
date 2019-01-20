import org.w3c.dom.*;
import java.util.*;
import java.io.*;

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
  public MyDate updateDueDMY() {
    if (!available && status.subSequence(0,3).equals("DUE")) {
      String month = (String) status.subSequence(4,6);
      dueMonth = Integer.parseInt(month);

      String day = (String) status.subSequence(7,9);
      dueDay = Integer.parseInt(day);

      String year = (String) status.subSequence(10,12);
      dueYear = Integer.parseInt(year);
    }

    MyDate result = new MyDate(dueMonth,dueDay,dueYear);
    return result;
  }

  public void updateDueDate(MyDate[][] calendar) {

    Copy c = this;
    if (dueMonth >= 1 && dueMonth <= 2) { // for now calendar only has 2 months
      dueDate = calendar[dueMonth-1][dueDay-1];
      dueDate.addCopy(c);
    }
  }
}
