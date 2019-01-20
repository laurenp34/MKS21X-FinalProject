import java.time.LocalDateTime;

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
  }


}
