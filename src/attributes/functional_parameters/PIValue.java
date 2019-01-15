package attributes.functional_parameters;

import entities.Literature;
import entities.NullMolecule;
import java.util.Arrays;
import java.util.List;

public class PIValue extends SingleValue{

  public PIValue(){

  }

  public PIValue(double piValue, Literature... references){
    super(piValue, references);
  }

  public PIValue(double piValue, double piMaxValue, Literature... references){
    super(piValue, piMaxValue, references);
  }

  public String getMethod() {
    return "getPiValue";
  }

  public String getParameter() {
    return getParameter("piValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","piValue","piValueMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}
