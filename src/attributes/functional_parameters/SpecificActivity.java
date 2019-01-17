package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SpecificActivity extends SingleValue {

  public SpecificActivity(){

  }

  public SpecificActivity(double specificActivityValue, String commentary, Literature... references){
    super(specificActivityValue, commentary, references);
  }

  public SpecificActivity(double specificActivityValue, double specificActivityMaxValue, String commentary, Literature... references){
    super(specificActivityValue, specificActivityMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getSpecificActivity";
  }

  public String getParameter() {
    return getParameter("specificActivity");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"specificActivity","specificActivityMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }
}
