import com.google.gson.Gson;
import java.io.*;
import java.util.*;


public class JSONTest {
  //simply testing whether this will commit/push
  public static void main(String[] args) throws FileNotFoundException{
    Gson obj = new Gson();
    System.out.println("library access worked!");
    Scanner sca = new Scanner(new File("samplefile.json"));
    String inp = "";
    while(sca.hasNextLine()) inp += sca.nextLine()+"\n";
    System.out.println(inp);
  }
}
