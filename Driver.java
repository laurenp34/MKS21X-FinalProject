public class Driver{
  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    Branch[] NYPLBranches = BranchData.getBranches("NYPLLocations.json");
    CatalogAccessor ny = new NYPLAccessor(NYPLBranches);
    Copy[] out = ny.getAllCopies(result);
    System.out.println("\n\n");
    for(Copy c : out) System.out.println(c);
    System.out.println("complete");
    for (Branch b: NYPLBranches) {
      System.out.println(b.getName());
      if(b.getStoredCopies() != null){
        System.out.println(b.numCopies());
      }
    }
    System.out.print("\033[?25h"); // show cursor.
  }
}
