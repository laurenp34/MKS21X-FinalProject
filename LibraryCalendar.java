import java.time.LocalDateTime;

public class LibraryCalendar {
  MyDate today;

  public LibraryCalendar()
    LocalDateTime now = LocalDateTime.now();
    int todayYear = now.getYear();
    int todayMonth = now.getMonthValue();
    int todayDay = now.getDayOfMonth();

    today = new MyDate(todayMonth,todayDay,todayYear);
  }


}
