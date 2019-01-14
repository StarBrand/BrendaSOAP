package attributes.functional_parameters;

import entities.Literature;
import entities.NoMolecule;
import java.util.Arrays;
import java.util.List;

public class SpecificActivity extends SingleValue {

  public SpecificActivity(){

  }

  public SpecificActivity(double specificActivityValue, Literature... references){
    super(specificActivityValue, new NoMolecule(), references);
  }

  public SpecificActivity(double specificActivityValue, double specificActivityMaxValue, Literature... references){
    super(specificActivityValue, specificActivityMaxValue, new NoMolecule(), references);
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
