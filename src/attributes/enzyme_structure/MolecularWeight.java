package attributes.enzyme_structure;

import attributes.AbstractAttribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * MolecularWeight class, this attribute defines a number (with
 * few exceptions, a range) of molecular weight of a protein
 *
 * @author Juan Saez
 */
public class MolecularWeight extends AbstractAttribute{

  private int value = -1;
  private int max_value = -1;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public MolecularWeight(){

  }

  /**
   * The constructor given the molecular weight the commentary an (a list of) reference
   * ({@Link entities.Literature})
   *
   * @param molecular_weight the value of molecular weight
   * @param commentary       A commentary of the Attribute
   * @param reference       (A list of) reference of the Attribute
   * @see entities.Literature
   */
  public MolecularWeight(int molecular_weight, String commentary, Literature... reference){
    super(commentary, reference);
    value = molecular_weight;
  }

  /**
   * When the data is a range, the constructor is given the molecular weight(as the minimum
   * value of the range) and the maximum weight (as the maximum value) the commentary an (a
   * list of) reference({@Link entities.Literature})
   *
   * @param molecular_weight the value minimum value of molecular weight
   * @param molecular_weight the value maximum value of molecular weight
   * @param commentary       A commentary of the Attribute
   * @param reference       (A list of) reference of the Attribute
   * @see entities.Literature
   */
  public MolecularWeight(int molecular_weight, int molecular_weight_max, String commentary, Literature... reference){
    super(commentary, reference);
    value = molecular_weight;
    max_value = molecular_weight_max;
  }

  /**
   * Sets the molecular weight (or minimum value)
   *
   * @param value the molecular weight
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Sets the maximum molecular weigh
   *
   * @param max_value the maximum wieght
   */
  public void setMax_value(int max_value) {
    this.max_value = max_value;
  }

  /**
   * Gets the molecular weight (or minimum value)
   *
   * @return the molecular weight
   */
  public int getValue() {
    return value;
  }

  /**
   * Gets the maximum molecular weight
   *
   * @return the maximum molecular weight
   */
  public int getMax_value() {
    return max_value;
  }

  public String getMethod(){
    return "getMolecularWeight";
  }

  public String getParameter(){
    String out = "molecularWeight*";
    if (value != -1) {
      out += String.valueOf(value);
    }
    if (max_value != -1){
      out += "#molecularWeightMaximum*" + String.valueOf(max_value);
    }
    return out;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"molecularWeight","molecularWeightMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    try {
      setValue(Integer.valueOf(resultOfQuery.get("molecularWeight")));
    }
    catch (Exception e){

    }
    try {
      setMax_value(Integer.valueOf(resultOfQuery.get("molecularWeightMaximum")));
    }
    catch (Exception e){

    }
    setCommentary(resultOfQuery.get("commentary"));
    setReference(resultOfQuery.get("literature"));
  }

}