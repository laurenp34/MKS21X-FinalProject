import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.*;

public class BranchData{
  private static void mainNYPL(String[] args)throws IOException{
    DownloadNYPLLocs runner = new DownloadNYPLLocs("https://refinery.nypl.org/api/nypl/locations/v1.0/locations");
    runner.printData();
  }
  public static Branch[] getBranches(String fileName)throws IOException{
    FileReader reader = new FileReader(fileName);
    Gson g = new Gson();
    return g.fromJson(reader,Branch[].class);
  }
  public static Branch branchWithID(String id,Branch[] branches){
    id = id.toUpperCase();
    for(int i=0;i<branches.length;i++){
      if(branches[i].getID().equals(id)){
        return branches[i];
      }
    }
    return null;
  }
  DataStructure dataStructure;
  Branch[] producedBranches;
  private BranchData(String url)throws IOException{
    URL jsonPage = new URL(url);
    Reader r = new InputStreamReader(jsonPage.openStream());
    Gson g = new Gson();
    dataStructure = g.fromJson(r,DataStructure.class);
    producedBranches = buildBranches();
  }
  private Branch[] buildBranches(){
    int branchCount = dataStructure.locations.length;
    Branch[] out = new Branch[branchCount];
    for(int i=0;i<branchCount;i++){
      out[i] = dataStructure.locations[i].buildBranch();
    }
    return out;
  }
  private void printData(){
    Gson g = new Gson();
    System.out.println(g.toJson(producedBranches));
  }
  private class DataStructure{
    Location[] locations;
    private class Location{
      Link _links;
      GeoLoc geolocation;
      String id,name;
      public Branch buildBranch(){
        String url = _links.self.about;
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
