import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class LibraryCalendar {
  MyDate today;
  Month[] cal;

  public LibraryCalendar(Month firstMonth,Month lastMonth) {
    LocalDateTime now = LocalDateTime.now();
    int todayYear = now.getYear();
    int todayMonth = now.getMonthValue();
    int todayDay = now.getDayOfMonth();

    today = new MyDate(todayMonth,todayDay,todayYear);

    cal = new Month[(lastMonth.getNum()-todayMonth)+1]; // assumes they're in the same year.

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

  public static void newLibraryCalendar(Copy[] out) {

    LocalDateTime now = LocalDateTime.now();
    int nowYear = now.getYear();
    int nowMonth = now.getMonthValue();
    int nowDay = now.getDayOfMonth();

    Month firstMonth = new Month(nowMonth,nowYear);
    Month lastMonth = new Month(nowMonth,nowYear);

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

  }


}
