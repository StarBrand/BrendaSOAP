package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TemperatureRange extends RangeValue{

  public TemperatureRange(){

  }

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
