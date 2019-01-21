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

  public MyDate[] getMonthArray() {
    return monthDays;
  }

  public int getNum() {
    return num;
  }
  public int getYear(){
    return year;
  }

  public String toString(){
    return Arrays.toString(monthDays);
  }

  public static void main(String[] args) {
    Month jan = new Month(1,2019);
    System.out.println(Arrays.toString(jan.monthDays));
  }

}
