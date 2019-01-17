import org.w3c.dom.*;

public class Copy{
  private Branch location;
  private String callnum;
  private String message;
  private String status;
  private boolean available;
  private Date dueDate;
  private int dueDay;
  private int dueMonth;
  private int dueYear;

  public Copy(Branch loc,String callnum,String status,String message){
    this.location = loc;
    this.callnum = callnum;
    this.status = status;
    this.message = message;
    available = status.equals("AVAILABLE");
    //ADD DATE CODE
  }
  public String toString(){
    if(available) return "  Available Copy @"+location.getName();
    else          return "Unavailable Copy @"+location.getName()+" "+status;
  }
  public Branch getBranch() {
    return location;
  }
  public boolean getAvail() {
    return available;
  }
  public int getDueD() {
    return dueDay;
  }
  public int getDueM() {
    return dueMonth;
  }
  public int getDueY() {
    return dueYear;
  }
  public void updatedueDate() {
    if (!available && message.subSequence(0,3).equals("DUE")) {
      String month = message.subSequence(4,6);
      dueMonth = Integer.parseInt(month);

      String day = message.subSequence(7,9);
      dueDay = Integer.parseInt(day);

      String year = message.subSequence(10,12);
      dueYear = Integer.parseInt(year);
    }
  }
}
