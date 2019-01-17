import java.util.ArrayList;
public class Branch{
  private double lat,lon;
  private String name;
  private String displayUrl;
  private String id;
  private ArrayList<Copy> storedCopies;
  public Branch(double lat,double lon,String url,String id,String name){
    this.lat = lat;
    this.lon = lon;
    displayUrl = url;
    this.id = id;
    this.name = name;
  }
  public void addCopy(Copy c){
    if(storedCopies==null) storedCopies = new ArrayList<Copy>();
    storedCopies.add(c);
  }
  public String getID(){
    return id;
  }
  public String getName(){
    return name;
  }
}
