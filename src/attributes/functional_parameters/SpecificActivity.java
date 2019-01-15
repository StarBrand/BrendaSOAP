package attributes.functional_parameters;

import entities.Literature;
import entities.NullMolecule;
import java.util.Arrays;
import java.util.List;

public class SpecificActivity extends SingleValue {

  public SpecificActivity(){

  }

  public SpecificActivity(double specificActivityValue, Literature... references){
    super(specificActivityValue, references);
  }

  public SpecificActivity(double specificActivityValue, double specificActivityMaxValue, Literature... references){
    super(specificActivityValue, specificActivityMaxValue, references);
  }

  public String getMethod() {
    return "getSpecificActivity";
  }

  public String getParameter() {
    return getParameter("specificActivity");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","specificActivity","specificActivityMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}
