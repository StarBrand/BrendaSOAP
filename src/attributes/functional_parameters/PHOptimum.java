package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PHOptimum extends SingleValue{

  public PHOptimum(){ }

  public PHOptimum(double PHOptimum, String commentary, Literature... references){
    super(PHOptimum, commentary, references);
  }

  public PHOptimum(double PHOptimum_value, double maxPHOptimum, String commentary, Literature... references){
    super(PHOptimum_value, maxPHOptimum, commentary, references);
  }

  public String getMethod() {
    return "getPhOptimum";
  }

  public String getParameter() {
    return getParameter("phOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"phOptimum","phOptimumMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }
}
