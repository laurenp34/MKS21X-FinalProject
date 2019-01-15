import java.io.IOException;
public class DownloadTester{
  public static void main(String[] args)throws IOException{
    Branch[] branches = DownloadNYPLLocs.getBranches("NYPLLocations.json");
    for(int i=0;i<branches.length;i++){
      System.out.println(branches[i].getID()+" "+branches[i].getName());
    }
  }
}
