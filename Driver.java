public class Driver{
  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    Branch[] NYPLBranches = BranchData.getBranches("NYPLLocations.json");
    CatalogAccessor ny = new NYPLAccessor(NYPLBranches);
    Copy[] out = ny.getAllCopies(result);
    System.out.println("\n\n");

    for (Branch b: NYPLBranches) {
      b = new Branch(b.getLat(),b.getLon(),b.getUrl(),b.getID(),b.getName());
    }

    for(Copy c : out) {
      System.out.println(c);
      if (c.getAvail()) {
        for (Branch b: NYPLBranches) {
          if (c.getBranch().getName().equals(b.getName())) {
            System.out.println("\tBranch: "+b.getName());
            System.out.println(b.getStoredCopies());
            Copy cc = new Copy(b,"test","test","test");
            b.addCopy(cc);
          }
        }
      }
    }

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
