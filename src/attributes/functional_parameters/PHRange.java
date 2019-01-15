package attributes.functional_parameters;

import entities.Literature;
import entities.NullMolecule;
import java.util.Arrays;
import java.util.List;

public class PHRange extends RangeValue{

  public PHRange(){ }

  public PHRange(double phMinValue, double phMaxValue, Literature... references){
    super(phMinValue, phMaxValue, references);
  }

  public String getMethod() {
    return "getPhRange";
  }

  public String getParameter() {
    return getParameter("phOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","phRange","phRangeMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}