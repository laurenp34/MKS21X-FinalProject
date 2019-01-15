import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.*;

public class DownloadNYPLLocs{
  public static void main(String[] args)throws IOException{
    DownloadNYPLLocs runner = new DownloadNYPLLocs("https://refinery.nypl.org/api/nypl/locations/v1.0/locations");
    FileWriter fw = new FileWriter("NYPLLocations.json");
    BufferedWriter writer = new BufferedWriter(fw);
    runner.writeData();
    System.out.println("complete");
  }
  public DownloadNYPLLocs(String url){

  }
  public void writeData(){
    
  }

}
