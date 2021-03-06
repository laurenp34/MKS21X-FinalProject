import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class LibraryCalendar {
  MyDate today;
  Month[] cal;

  public LibraryCalendar(Month lastMonth) {
    LocalDateTime now = LocalDateTime.now();
    int todayYear = now.getYear();
    int todayMonth = now.getMonthValue();
    int todayDay = now.getDayOfMonth();

    today = new MyDate(todayMonth,todayDay,todayYear);

    cal = new Month[(lastMonth.getNum()-todayMonth)+1]; // assumes they're in the same year.

    if (cal.length == 1) {
      cal[0] = new Month(today);
    } else {
      cal[0] = new Month(today);

      for (int i=1;i<cal.length;i++) {
        Month current = new Month(todayMonth+i,todayYear);
        cal[i] = current;
      }

    }
  }


  public LibraryCalendar(Month firstMonth, Month lastMonth) {
    LocalDateTime now = LocalDateTime.now();
    int todayYear = now.getYear();
    int todayMonth = now.getMonthValue();
    int todayDay = now.getDayOfMonth();

    today = new MyDate(todayMonth,todayDay,todayYear);

    cal = new Month[(lastMonth.getNum()-firstMonth.getNum())+1]; // assumes they're in the same year.

      for (int i=0;i<cal.length;i++) {
        Month current = new Month(firstMonth.getNum()+i,todayYear);
        cal[i] = current;
      }

    }


  public String toString() {
    return Arrays.toString(cal);
  }

  public Month[] getCal() {
    return cal;
  }

  public static LibraryCalendar newLibraryCalendar(Copy[] out) {

    LocalDateTime now = LocalDateTime.now();
    int nowYear = now.getYear();
    int nowMonth = now.getMonthValue();
    int nowDay = now.getDayOfMonth();

    Month firstMonth = new Month(nowMonth,nowYear);
    Month lastMonth = new Month(nowMonth,nowYear);

    int dueLater = 0;

    for (int i=0;i<out.length;i++) {
      Copy c = out[i];
      MyDate d = c.updateDueDMY();
      int dDay = d.getDay();
      int dMonth = d.getMonth();
      int dYear = d.getYear();
      //System.out.println(d);

      /* no need to update first month b/c calendar won't span into the past.
      if (dDay !=0 && ((dMonth < firstMonth.getNum()  && dYear <= firstMonth.getYear()) || dYear < firstMonth.getYear())) {
        firstMonth = new Month(dMonth,dYear);
      }*/
      if (dDay !=0 && ((dMonth > lastMonth.getNum() && dYear >= lastMonth.getYear()) || dYear > lastMonth.getYear())) {
        lastMonth = new Month(dMonth,dYear);
      }
    }

    //System.out.println("first month: "+firstMonth.getNum()+" "+firstMonth.getYear());
    //System.out.println("last month: "+lastMonth.getNum()+" "+lastMonth.getYear());

    LibraryCalendar cal = new LibraryCalendar(firstMonth,lastMonth);
    return cal;

  }

  public static void main(String[] args) {
    Month mar = new Month(3,2019);
    Month may = new Month(5,2019);
    LibraryCalendar l = new LibraryCalendar(mar,may);
    System.out.println(l);
  }


}
