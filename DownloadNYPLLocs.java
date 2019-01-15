import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.*;

public class DownloadNYPLLocs{
  public static void main(String[] args)throws IOException{
    DownloadNYPLLocs runner = new DownloadNYPLLocs("https://refinery.nypl.org/api/nypl/locations/v1.0/locations");
    FileWriter fw = new FileWriter("NYPLLocations.json");
    BufferedWriter writer = new BufferedWriter(fw);
    runner.writeData(writer);
    System.out.println("complete");
  }
  DataStructure dataStructure;
  Branch[] producedBranches;
  public DownloadNYPLLocs(String url)throws IOException{
    URL jsonPage = new URL(url);
    Reader r = new InputStreamReader(jsonPage.openStream());
    Gson g = new Gson();
    dataStructure = g.fromJson(r,DataStructure.class);
    producedBranches = buildBranches();
  }
  public Branch[] buildBranches(){
    int branchCount = dataStructure.locations.length;
    Branch[] out = new Branch[branchCount];
    for(int i=0;i<branchCount;i++){
      out[i] = dataStructure.locations[i].buildBranch();
    }
    return out;
  }
  public void writeData(BufferedWriter writer){
    Gson g = new Gson();
    g.toJson(producedBranches,writer);
  }
  private class DataStructure{
    Location[] locations;
    private class Location{
      Link[] _links;
      GeoLoc geolocation;
      String id,name;
      public Branch buildBranch(){
        String url = _links[0].self.about;
        double lat = geolocation.coordinates[0];
        double lon = geolocation.coordinates[1];
        return new Branch(lat,lon,url,id,name);
      }
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
