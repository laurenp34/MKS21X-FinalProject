import java.util.*;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;


public class Map{
  private Branch[] branches;
  private Document kml;
  public Map(Branch[] branches){
    this.branches = branches;
    buildDocument();
  }
  private Document buildDocument(){
    Document out = initializeDocument();
    Element docElt = buildExterior(out);
    for(Branch b : branches){
      addPlaceMark(b,docElt);
    }
    return out;
  }
  public Document initializeDocument(){
    Document out = null;
    try{
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      out = builder.newDocument();
    }catch(ParserConfigurationException e){
      e.printStackTrace();
    }
    return out;
  }
  public Element buildExterior(Document doc){
    return null;
  }
  public void addPlaceMark(Branch b,Element root){

  }
}
