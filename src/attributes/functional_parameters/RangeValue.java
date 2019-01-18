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
public abstract class RangeValue extends NumericalAttribute {

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public RangeValue(){

  }

  /**
   * The constructor given just the minimum value
   * this is useful when Brenda database define a range to small
   * in just one value
   *
   * @param min_value   The minimum value of the attribute
   * @param commentary  The commentary of the observation
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public RangeValue(double min_value, String commentary, Literature... reference){
    super(min_value, commentary, reference);
  }

  /**
   * The constructor given all the parameters
   *
   * @param min_value   The minimum value of the range
   * @param max_value   The maximum value of the range
   * @param commentary  The commentary of the data
   * @param reference   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public RangeValue(double min_value, double max_value, String commentary, Literature... reference){
    super(min_value, max_value, commentary, reference);
  }

protected String getParameter(String parameter, String... extra) {
    return super.getParameter(parameter, extra);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    super.setAttribute(resultOfQuery);
  }

}