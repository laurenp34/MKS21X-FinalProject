public class WebScrapeTester{
  public static void main(String[] args){
    Search s = new Search();
    Book result = s.runSearch();
    CatalogAccessor ny = new NYPLAccessor();
    Copy[] out = ny.getAllCopies(result);
    System.out.println("\n\n");
    for(Copy c : out) System.out.println(c);
  }
}
