import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class Driver{
  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    Branch[] NYPLBranches = BranchData.getBranches("NYPLLocations.json");
    CatalogAccessor ny = new NYPLAccessor(NYPLBranches);
    Copy[] out = ny.getAllCopies(result);

/* -- LAUREN DEBUGGING WITH DATE and COPY */
    //create a calendar w jan-feb 2019 (each 2 31 days)
    /*
    MyDate[][] calendar = new MyDate[2][31]; // start out with only 2 months: jan-feb
    int dayIndex = 1;
    int monthIndex = 1;
    for (int i1=0;i1<calendar.length;i1++) {
      MyDate[] month = calendar[i1];
      dayIndex=1;
      for (int i2=0;i2<month.length;i2++) {
        month[i2] = new MyDate(monthIndex,dayIndex,2019);
        dayIndex++;
      }
      monthIndex++;
    }
    */

/* debug to make sure calenadr worked
    for (Date[] month: calendar) {
      System.out.println(Arrays.toString(month));
    }
    */

    //System.out.println(Arrays.deepToString(calendar));

    //Date dat = new Date(); // this date represents today
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
      System.out.println(d);

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
    //System.out.println(cal);

    Copy.countCopies(out,cal);

    for (int i=0;i<cal.getCal().length;i++) {
      Month mon = cal.getCal()[i];
      MyDate[] calArray = mon.getMonthArray();
      for (int i2=0;i2<calArray.length;i2++) {
        MyDate date = calArray[i2];
        System.out.println(date);
        if (date.getCopiesDue().size() > 0) {
          System.out.println("\t"+date.getCopiesDue());
        }
      }
    }

/*
    for (MyDate[] month: calendar) {
      for (MyDate d: month) {
        System.out.println(d+"\t"+d.getCopiesDue());
      }
    }
    */

    System.out.println("\n\n");


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
    System.out.print("\033[?25h"); // show cursor.
  }
}
