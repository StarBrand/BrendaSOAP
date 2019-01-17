package attributes.enzyme_structure;

import attributes.AbstractAttribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * AASequence class, which has the sequence (primary structure) of a protein
 *
 * @author Juan Saez
 */
public class AASequence extends AbstractAttribute {

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
   * @param commentary The commentary or observation
   * @param reference The list of reference
   */
  public AASequence(String sequence, String commentary, Literature... reference){
    super(commentary);
    for(Literature l:reference){
      super.addReference(l);
    }
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
    setCommentary(resultOfQuery.get("commentary"));
    setReference(resultOfQuery.get("literature"));
  }
}
