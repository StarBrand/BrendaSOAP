package attributes.functional_parameters;

import entities.Literature;
import entities.NoMolecule;
import java.util.Arrays;
import java.util.List;

public class TemperatureOptimum extends SingleValue{

  public TemperatureOptimum(){

  }

  public TemperatureOptimum(double temperatureOptimumValue, Literature... references){
    super(temperatureOptimumValue, new NoMolecule(), references);
  }

  public TemperatureOptimum(double temperatureOptimumValue, double temperatureOptimumMaxValue, Literature... references){
    super(temperatureOptimumValue, temperatureOptimumMaxValue, new NoMolecule(), references);
  }

  public String getMethod() {
    return "getTemperatureOptimum";
  }

  public String getParameter() {
    return getParameter("temperatureOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","temperatureOptimum","temperatureOptimumMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}
