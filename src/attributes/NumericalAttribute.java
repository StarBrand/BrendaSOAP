package attributes;

import entities.Literature;
import java.util.HashMap;

/**
 * NumericalAttribute abstract class defined as the superclass
 * of the numerical attribute
 *
 * @author Juan Saez Hidalgo
 */
public abstract class NumericalAttribute extends AbstractAttribute{

  private double min_value = Double.NaN;
  private double max_value = Double.NaN;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public NumericalAttribute(){

  }

  /**
   * The constructor given just the minimum value (or one value)
   * this is useful when Brenda database define a range to small
   * in just one value
   *
   * @param min_value   The minimum value (or value) of the attribute
   * @param commentary  The commentary of the observation
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public NumericalAttribute(double min_value, String commentary, Literature... reference){
    super(commentary, reference);
    this.min_value = min_value;
    max_value = Double.NaN;
  }

  /**
   * The constructor given all the parameters
   *
   * @param min_value   The minimum value (or value) of the range
   * @param max_value   The maximum value of the range
   * @param commentary  The commentary of the data
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public NumericalAttribute(double min_value, double max_value, String commentary, Literature... reference){
    super(commentary, reference);
    this.min_value = min_value;
    this.max_value = max_value;
  }

  /**
   * Sets the minimum value (or value) of the range
   *
   * @param min_value The minimum value (or value)
   */
  public void setMin_Value(double min_value) {
    this.min_value = min_value;
  }

  /**
   * Sets the maximum value of the range
   *
   * @param max_value The maximum value
   */
  public void setMax_value(double max_value) {
    this.max_value = max_value;
  }

  /**
   * Gets the (minimum) value
   *
   * @return (Minimum) value
   */
  public double getMin_value() {
    return min_value;
  }

  /**
   * Gets the maximum value
   *
   * @return Maximum value
   */
  public double getMax_value() {
    return max_value;
  }

  /**
   * Gets the parameter for the SOAP query given the parameter who
   * extends (subclass) this class, to be used for the subclasses
   *
   * @param parameter The name of the Subclass parameter
   * @param extra     Any extra information
   * @return The parameter for the SOAP query
   */
  protected String getParameter(String parameter, String... extra) {
    String out =  parameter + "*";
    if (! Double.isNaN(min_value)){
      out += min_value;
    }
    out += "#" + parameter + "Maximum*";
    if (! Double.isNaN(max_value)){
      out += max_value;
    }
    out += "#commentary*" + this.getCommentary();
    for (String e:extra) {
      out += e;
    }
    return out;
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    try {
      setMin_Value(Double.valueOf(resultOfQuery.get(this.getColumns().get(0))));
    } catch (Exception e){

    }
    try {
      setMax_value(Double.valueOf(resultOfQuery.get(this.getColumns().get(1))));
    } catch (Exception e){

    }
    super.setAttribute(resultOfQuery);
  }

  @Override
  public Object clone(){
    NumericalAttribute cloned;
    try{
      cloned = (NumericalAttribute) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  /**
   * rowsToTable for NumericalAttribute for default
   * to be used for the subclasses
   *
   * @param name The name of the Attribute
   * @return The row of the table
   */
  protected HashMap<String, String> rowsToTable(String name) {
    HashMap<String, String> out = super.rowsToTable(name);
    if (Double.isNaN(max_value) && !Double.isNaN(min_value)){
      out.put(name + " value", String.valueOf(min_value));
    }
    else if(Double.isNaN(min_value)){
      return null;
    }
    else{
      out.put(name + " value", String.valueOf(min_value) + "-" + String.valueOf(max_value));
    }
    return out;
  }
}