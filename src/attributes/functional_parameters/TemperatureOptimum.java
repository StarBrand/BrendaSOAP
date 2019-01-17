package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TemperatureOptimum extends SingleValue{

  public TemperatureOptimum(){

  }

  public TemperatureOptimum(double temperatureOptimumValue, String commentary, Literature... references){
    super(temperatureOptimumValue, commentary, references);
  }

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
}
