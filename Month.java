import java.io.*;
import java.util.*;


public class Month {
  private String name;
  private int num;
  private int year;
  private MyDate[] monthDays;
  private String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};

  public Month(int numm,int yearr) {
    num = numm;
    year = yearr;
    name = months[num-1];

    if (num == 1 || num == 3 || num == 5 || num == 7 || num == 8 || num == 10 || num == 12) {
      monthDays = new MyDate[31];
    } else if (num == 2) {
      if (yearr % 4 == 0) {
        monthDays = new MyDate[29];
      } else {
        monthDays = new MyDate[28];
      }
    } else {
      monthDays = new MyDate[30];
    }

    for (int i=0;i<monthDays.length;i++) {
      monthDays[i] = new MyDate(num,i+1,year);
    }
  }

  public Month(MyDate startDate) { // generates rest of month from start date.

    int startDay = startDate.getDay();

    num = startDate.getMonth();
    year = startDate.getYear();
    name = months[num-1];

    if (num == 1 || num == 3 || num == 5 || num == 7 || num == 8 || num == 10 || num == 12) {
      monthDays = new MyDate[31-startDay + 1 ];
    } else if (num == 2) {
      if (year % 4 == 0) {
        monthDays = new MyDate[29 - startDay + 1];
      } else {
        monthDays = new MyDate[28 - startDay + 1];
      }
    } else {
      monthDays = new MyDate[30 - startDay + 1];
    }

    for (int i=0;i<monthDays.length;i++) {
      monthDays[i] = new MyDate(num,i+startDay,year);
    }


  }

  public MyDate[] getMonthArray() {
    return monthDays;
  }

  public int getNum() {
    return num;
  }
  public int getYear(){
    return year;
  }
  public String getName(){
    return name;
  }

  public String toString(){
    return Arrays.toString(monthDays);
  }

  public static void main(String[] args) {
    Month jan = new Month(1,2019);
    System.out.println(Arrays.toString(jan.monthDays));

    MyDate hi = new MyDate(11,4,2019);
    Month nov = new Month(hi);
    System.out.println(Arrays.toString(nov.monthDays));
  }

}
