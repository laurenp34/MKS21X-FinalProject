import java.io.*;
import java.util.*;

public class Book{
  String title;
  String[] author_name;
  String[] isbn;
  String[] text;
  String[] subject;
  ArrayList<String> tags;
  public String[] getISBNs(){return isbn;}
  public String getTitle() {
    return title;
  }
}
