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
  DataStructure dataStructure;
  Branch[] producedBranches;
  public DownloadNYPLLocs(String url)throws IOException{
    URL jsonPage = new URL(url);
    Reader r = new InputStreamReader(jsonPage.openStream());
    Gson g = new Gson();
    dataStructure = g.fromJson(r,DataStructure.class);

  }
  public void writeData(){

  }
  private class DataStructure{
    Location[] locations;
    private class Location{
      Link[] _links;
      GeoLoc geolocation;
      String id,name,slug,street_address;
      String[] synonyms;
      private class GeoLoc{
        double[] coordinates;
      }
      private class Link{
        LinkNest self;
        private class LinkNest{
          String about;
        }
      }
    }
  }
}
