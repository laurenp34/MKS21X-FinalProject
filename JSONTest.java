import com.google.gson.Gson;
import com.google.gson.FieldAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.*;
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
    SearchDownload g = obj.fromJson(inp,SearchDownload.class);
    System.out.println(g.start);
    System.out.println(Arrays.toString(g.docs[0].isbn));
  }
  private class SearchDownload{
    String start;
    int numFound;
    Doc[] docs;
    private class Doc{
      String title_suggest;
      String[] isbn;
    }
  }
}
