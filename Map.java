import java.util.*;
import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;


public class Map{
  public static void main(String[] args){
    Branch[] branches = BranchData.getBranches("NYPLLocations.json");
    Map m = new Map(branches);
  }
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
      if(b.getStoredCopies() != null){
        addPlaceMark(b,docElt,out);
      }
    }
    return out;
  }
  private Document initializeDocument(){
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
  private Element buildExterior(Document doc){
    Element kml = doc.createElement("kml");
    doc.appendChild(kml);
    kml.setAttribute("xmlns","http://www.opengis.net/kml/2.2");
    Element root = doc.createElement("Document");
    kml.appendChild(root);
    return root;
  }
  private void addPlaceMark(Branch b,Element root,Document doc){
    Element mark = doc.createElement("Placemark");
    mark.appendChild(textElement(doc,"name",b.getName()));
    mark.appendChild(textElement(doc,"description",b.toStringAvailables()));
    Element pt = doc.createElement("Point");
    pt.appendChild(textElement(doc,"coordinates",b.coordString()));
    mark.appendChild(pt);
    root.appendChild(mark);
  }
  private Element textElement(Document doc,String tag,String content){
    Element out = doc.createElement(tag);
    out.appendChild( doc.createTextNode(content) );
    return out;
  }
}
