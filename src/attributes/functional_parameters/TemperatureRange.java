package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TemperatureRange class, temperature range of activity [ °C ] - [ °C ]
 *
 * @author Juan Saez Hidalgo
 */
public class TemperatureRange extends RangeValue{

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public TemperatureRange(){

  }

  /**
   * The constructor given just the minimum value
   * this is useful when Brenda database define a range to small
   * in just one value
   *
   * @param temperatureMinValue The minimum value of the attribute
   * @param commentary          The commentary of the observation
   * @param references          The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TemperatureRange(double temperatureMinValue, String commentary, Literature... references){
    super(temperatureMinValue, commentary, references);
  }

  /**
   * The constructor given all the parameters
   *
   * @param temperatureMinValue The minimum value of the range
   * @param temperatureMaxValue The maximum value of the range
   * @param commentary          The commentary of the data
   * @param references          The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TemperatureRange(double temperatureMinValue, double temperatureMaxValue, String commentary, Literature... references){
    super(temperatureMinValue, temperatureMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getTemperatureRange";
  }

  public String getParameter() {
    return getParameter("temperatureRange");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"temperatureRange","temperatureRangeMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }
}
