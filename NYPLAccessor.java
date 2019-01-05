public class NYPLAccessor implements CatalogAccessor{
  public Copy[] getAllCopies(Book bk){
    String[] ISBNs = bk.getISBNs();
    String searchStr = genSearch(ISBNs);
    String searchUrl = "https://browse.nypl.org/iii/encore/search/C__S"+searchStr+"__Orightresult__U?lang=eng&suite=def";
    System.out.println(searchUrl);
    return null;
  }
  private String genSearch(String[] terms){
    return "%28"+String.join("%20%7C%20",terms)+"%29";
  }
}
