public class Driver{
  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    Branch[] NYPLBranches = BranchData.getBranches("NYPLLocations.json");
    CatalogAccessor ny = new NYPLAccessor(NYPLBranches);
    Copy[] out = ny.getAllCopies(result);
    System.out.println("\n\n");
    /*

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
*/
    System.out.println("complete");
    for (Branch b: NYPLBranches) {
      if(b.getStoredCopies() != null){
        System.out.print(b.getName());
        System.out.println(": "+b.numCopies());
        for (Copy c: b.getStoredCopies()) {
          System.out.println("\t"+c);
        }
      }
    }
    System.out.println("\n\n...");
    Map m = new Map(NYPLBranches);
    m.toFile("map.kml");
    System.out.println("KML File saved to /map.kml\nTo open file:\n1. Open https://mymaps.google.com/, signed into a Google account\n2. Choose the option \"Create a New Map\"\n3. Within the 'Untitled Layer' panel, click 'Import'\n4. Select the /map.kml file from your local files\n\tThis will display the map of where the book is currently available");
    System.out.print("\033[?25h"); // show cursor.
  }
}
