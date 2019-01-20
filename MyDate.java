import java.io.*;
import java.util.*;
import java.util.Calendar;


public class MyDate {
  private int day;
  private int month;
  private int year;
  private ArrayList<Copy> copiesDueToday;

  public MyDate(int newMonth, int newDay, int newYear) {
    day = newDay;
    month = newMonth;
    year = newYear;
    copiesDueToday = new ArrayList<Copy>();
  }

  public int getDay() {
    return day;
  }
  public int getMonth() {
    return month;
  }
  public ArrayList<Copy> getCopiesDue() {
    return copiesDueToday;
  }

  public void addCopy(Copy c) {
    copiesDueToday.add(c);
  }
  public String toString() {
    return month+"/"+day+"/"+year;
  }
  public static MyDate getFirstDateOfMonth(MyDate date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }






}
