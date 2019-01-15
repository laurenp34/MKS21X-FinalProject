import java.io.IOException;
public class DownloadTester{
  public static void main(String[] args)throws IOException{
    Branch[] branches = BranchData.getBranches("NYPLLocations.json");
    for(int i=0;i<branches.length;i++){
      if(branches[i].getID().length() != 2){
        System.out.println(i+" "+branches[i].getID()+" "+branches[i].getName());
      }
    }
    System.out.println(BranchData.branchWithID("mm",branches).getName());
  }
}
