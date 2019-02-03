package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TemperaturOptimum class, temperature optimum value [ °C ]
 *
 * @author Juan Saez Hidalgo
 */
public class TemperatureOptimum extends SingleValue{

  private String attributeName = "Temperature Optimum";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public TemperatureOptimum(){

  }


  /**
   * The constructor given all parameters
   *
   * @param temperatureOptimumValue The value of the attribute
   * @param commentary              The commentary of the observation
   * @param references              The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TemperatureOptimum(double temperatureOptimumValue, String commentary, Literature... references){
    super(temperatureOptimumValue, commentary, references);
  }


  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param temperatureOptimumValue     The minimum value of the attribute
   * @param temperatureOptimumMaxValue  The maximum value of the attribute
   * @param commentary                  The commentary of the observation
   * @param references                  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TemperatureOptimum(double temperatureOptimumValue, double temperatureOptimumMaxValue, String commentary, Literature... references){
    super(temperatureOptimumValue, temperatureOptimumMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getTemperatureOptimum";
  }

  public String getParameter() {
    return getParameter("temperatureOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"temperatureOptimum","temperatureOptimumMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public Object clone(){
    TemperatureOptimum cloned;
    try{
      cloned = (TemperatureOptimum) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public String getAttributeName() {
    return attributeName;
  }
}
