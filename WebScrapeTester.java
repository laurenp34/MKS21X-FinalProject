public class WebScrapeTester{
  public static void main(String[] args){
    Search s = Search.buildSearch(String.join("+",args));
    Book result = s.getResult(0);
    CatalogAccessor ny = new NYPLAccessor();
    ny.getAllCopies(result);
  }
}
