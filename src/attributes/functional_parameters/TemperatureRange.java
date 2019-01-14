package attributes.functional_parameters;

import entities.Literature;
import entities.NoMolecule;
import java.util.Arrays;
import java.util.List;

public class TemperatureRange extends RangeValue{

  public TemperatureRange(){

  }

  public TemperatureRange(double temperatureMinValue, double temperatureMaxValue, Literature... references){
    super(temperatureMinValue, temperatureMaxValue, new NoMolecule(), references);
  }

  public String getMethod() {
    return "getTemperatureRange";
  }

  public String getParameter() {
    return getParameter("temperatureRange");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","temperatureRange","temperatureRangeMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}
