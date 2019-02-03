package attributes.enzyme_structure;

import attributes.AbstractAttribute;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * PDB class, which describes the PDB ID
 * and generates the link to the Protein
 * Data Base in RCSB
 *
 * @author Juan Saez Hidalgo
 */
public class PDB extends AbstractAttribute {

  private String attributeName = "PDB";
  private String pdb = "";
  private String link = "";

  /**
   * Empty constructor to be filled by default
   */
  public PDB(){

  }

  /**
   * Constructor given the PDB ID
   *
   * @param code the PDB ID
   */
  public PDB(String code){
    pdb = code;
    link = "https://www.rcsb.org/structure/" + code;
  }

  /**
   * Sets PDB ID
   *
   * @param pdb PDB ID
   */
  public void setPdb(String pdb) {
    this.pdb = pdb;
  }

  private void setLink(String pdb) {
    this.link = "https://www.rcsb.org/structure/" + pdb;
  }

  /**
   * Gets the PDB ID
   *
   * @return PDB ID
   */
  public String getPdb() {
    return pdb;
  }

  /**
   * Gets the link to RCSB
   *
   * @return Link to RCSB
   */
  public String getLink() {
    return link;
  }

  public String getMethod() {
    return "getPdb";
  }

  public String getParameter() {
    return "pdb*" + pdb;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"pdb"};
    return Arrays.asList(columns);
  }

  public String getAttributeName() {
    return attributeName;
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setPdb(resultOfQuery.get("pdb"));
    setLink(this.pdb);
  }

  @Override
  public Object clone(){
    PDB cloned;
    try{
      cloned = (PDB) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public HashMap<String, String> rowsToTable() {
    HashMap<String, String> out = new HashMap<String, String>();
    out.put("PDB", pdb);
    out.put("link", link);
    return out;
  }

  @Override
  public boolean equals(Object object){
    if(object instanceof PDB){
      return this.pdb.equals(((PDB) object).getPdb()) && this.link.equals(((PDB) object).getLink());
    }
    else{
      return false;
    }
  }
}
