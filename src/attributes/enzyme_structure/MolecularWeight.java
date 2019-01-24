package attributes.enzyme_structure;

import attributes.NumericalAttribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * MolecularWeight class, this attribute defines a number (with
 * few exceptions, a range) of molecular weight of a protein
 *
 * @author Juan Saez Hidalgo
 */
public class MolecularWeight extends NumericalAttribute {

  private String attributeName = "Molecular Weight";

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
  public MolecularWeight(double molecular_weight, String commentary, Literature... reference){
    super(molecular_weight, commentary, reference);
  }

  /**
   * When the data is a range, the constructor is given the molecular weight(as the minimum
   * value of the range) and the maximum weight (as the maximum value) the commentary an (a
   * list of) reference({@Link entities.Literature})
   *
   * @param molecular_weight     the value minimum value of molecular weight
   * @param molecular_weight_max the value maximum value of molecular weight
   * @param commentary           A commentary of the Attribute
   * @param reference            (A list of) reference of the Attribute
   * @see entities.Literature
   */
  public MolecularWeight(double molecular_weight, double molecular_weight_max, String commentary, Literature... reference){
    super(molecular_weight, molecular_weight_max, commentary, reference);
  }

  /**
   * Sets the molecular weight (or minimum value)
   *
   * @param value the molecular weight
   */
  public void setValue(double value) {
    super.setMin_Value(value);
  }

  public String getAttributeName() {
    return attributeName;
  }

  /**
   * Gets the molecular weight (or minimum value)
   *
   * @return the molecular weight
   */
  public double getValue() {
    return getMin_value();
  }

  public String getMethod(){
    return "getMolecularWeight";
  }

  public String getParameter(){
    return super.getParameter("molecularWeight");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"molecularWeight","molecularWeightMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    super.setAttribute(resultOfQuery);
  }

  @Override
  public Object clone(){
    MolecularWeight cloned;
    try{
      cloned = (MolecularWeight) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public HashMap<String, String> rowsToTable() {
    HashMap<String, String> out = super.rowsToTable(attributeName);
    return out;
  }
}