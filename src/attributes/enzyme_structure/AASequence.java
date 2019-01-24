package attributes.enzyme_structure;

import attributes.AbstractAttribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * AASequence class, which has the sequence (primary structure) of a protein
 *
 * @author Juan Saez Hidalgo
 */
public class AASequence extends AbstractAttribute {

  private String attributeName = "AA Sequence";
  private String sequence = "";
  private int numberOfAminoacids = 0;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public AASequence(){

  }

  /**
   * The constructor given every parameter
   *
   * @param sequence The aa sequence
   */
  public AASequence(String sequence){
    this.sequence = sequence;
    this.numberOfAminoacids = sequence.length();
  }

  /**
   * Sets the sequence
   *
   * @param sequence the aa sequence
   */
  public void setSequence(String sequence) {
    this.sequence = sequence;
    this.numberOfAminoacids = sequence.length();
  }

  public String getAttributeName() {
    return attributeName;
  }

  /**
   * Gets the sequence
   *
   * @return the aa sequence
   */
  public String getSequence() {
    return sequence;
  }

  /**
   * Gets the number of aminoacids
   * calculated for the sequence (just the length of the String)
   *
   * @return the number of amino acids
   */
  public int getNumberOfAminoacids() {
    return numberOfAminoacids;
  }

  public String getMethod() {
    return "getSequence";
  }

  public String getParameter() {
    return "sequence*" + sequence + "#noOfAminoAcids*" + numberOfAminoacids;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"sequence","noOfAminoAcids","firstAccessionCode","source","id","commentary","literature"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setSequence(resultOfQuery.get("sequence"));
  }

  @Override
  public Object clone(){
    AASequence cloned;
    try{
      cloned = (AASequence) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public HashMap<String, String> rowsToTable() {
    HashMap<String, String> out = super.rowsToTable(attributeName);
    out.put("Number of Aminoacids", String.valueOf(numberOfAminoacids));
    return out;
  }
}
