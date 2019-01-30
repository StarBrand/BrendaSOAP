package attributes.functional_parameters;

import attributes.NumericalAttribute;
import entities.Literature;
import java.util.HashMap;

/**
 * RangeValue abstract class defined as the superclass
 * of the range values functional parameters attribute
 *
 * @author Juan Saez Hidalgo
 */
public abstract class SingleValue extends NumericalAttribute {

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public SingleValue(){

  }

  /**
   * The constructor given all parameters
   *
   * @param value       The value of the attribute
   * @param commentary  The commentary of the observation
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public SingleValue(double value, String commentary, Literature... reference){
    super(value, commentary, reference);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param value       The minimum value of the attribute
   * @param max_value   The maximum value of the attribute
   * @param commentary  The commentary of the observation
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public SingleValue(double value, double max_value, String commentary, Literature... reference){
    super(value, max_value, commentary, reference);
  }

  /**
   * Sest the value
   *
   * @param value Value
   */
  public void setValue(double value) {
    setMin_Value(value);
  }

  /**
   * Gets the value (or minimum value)
   *
   * @return Value
   */
  public double getValue() {
    return getMin_value();
  }

  /**
   * Gets the parameter for the SOAP query given the parameter who
   * extends (subclass) this class, to be used for the subclasses
   *
   * @param parameter The name of the Subclass parameter
   * @param extra     Any extra information
   * @return          The parameter for the SOAP query
   */
  protected String getParameter(String parameter, String... extra) {
    return super.getParameter(parameter, extra);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    super.setAttribute(resultOfQuery);
  }

  @Override
  public Object clone(){
    SingleValue cloned;
    try{
      cloned = (SingleValue) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }
}