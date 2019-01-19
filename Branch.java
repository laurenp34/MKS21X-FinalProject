import java.util.ArrayList;
public class Branch{
  private double lat,lon;
  private String name;
  private String displayUrl;
  private String id;
  private ArrayList<Copy> storedCopies = new ArrayList<Copy>();
  public Branch(double lat,double lon,String url,String id,String name){
    this.lat = lat;
    this.lon = lon;
    displayUrl = url;
    this.id = id;
    this.name = name;
    ArrayList<Copy> storedCopies = new ArrayList<Copy>();
  }
  public String getID(){
    return id;
  }
  public String getName(){
    return name;
  }
  public int numCopies(){
    return storedCopies.size();
  }
  public double getLat() {
    return lat;
  }
  public double getLon() {
    return lon;
  }
  public String getUrl() {
    return displayUrl;
  }

  public ArrayList<Copy> getStoredCopies() {
    return storedCopies;
  }
  public boolean hasAvailable(){
    if(storedCopies==null) return false;
    for(Copy c : storedCopies){
      if(c.getAvail()) return true;
    }
    return false;
  }
  public boolean addCopy(Copy copy) {
    if(storedCopies==null) storedCopies = new ArrayList<Copy>();
    storedCopies.add(copy);
    return true;
  }
  public String toStringAvailables(){
    if(storedCopies==null) return "";
    String out = "";
    for (Copy c : storedCopies){
      if(c.getAvail()) out += c+"\n";
    }
    return out;
  }
  public String coordString(){
    return lat+","+lon+",0";
  }
}
