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


}
