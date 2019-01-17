package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PHRange extends RangeValue{

  public PHRange(){ }

  public PHRange(double phMinValue, double phMaxValue, String commentary, Literature... references){
    super(phMinValue, phMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getPhRange";
  }

  public String getParameter() {
    return getParameter("phOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"phRange","phRangeMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }
}