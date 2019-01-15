import org.w3c.dom.*;

public class Copy{
  private Branch location;
  private String callnum;
  private String locName;
  private String message;
  private String status;
  private boolean available;
  //private Date dueDate;
  public Copy(Branch loc,String callnum,String status,String message){
    this.location = loc;
    locName = location.getName();
    this.callnum = callnum;
    this.status = status;
    this.message = message;
    available = status.equals("AVAILABLE");
    //ADD DATE CODE
  }
  public String toString(){
    if(available) return "  Available Copy @"+locName;
    else          return "Unavailable Copy @"+locName+" "+status;
  }
}
