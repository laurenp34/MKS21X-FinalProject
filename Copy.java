import org.w3c.dom.*;

public class Copy{
  private Branch location;
  private String callnum;
  private String loc;
  private String message;
  private String status;
  private boolean available;
  //private Date dueDate;
  public Copy(String loc,String callnum,String status,String message){
    this.loc = loc;
    //ADD CODE FOR BRANCH WHEN LOCATION DATA DOWNLOADED
    this.callnum = callnum;
    this.status = status;
    this.message = message;
    available = status.equals("AVAILABLE");
    //ADD DATE CODE
  }
  public String toString(){
    if(available) return "  Available Copy @"+loc;
    else          return "Unavailable Copy @"+loc+" "+status;
  }
}
