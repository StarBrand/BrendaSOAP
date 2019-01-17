package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PIValue extends SingleValue{

  public PIValue(){

  }

  public PIValue(double piValue, String commentary, Literature... references){
    super(piValue, commentary, references);
  }

  public PIValue(double piValue, double piMaxValue, String commentary, Literature... references){
    super(piValue, piMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getPiValue";
  }

  public String getParameter() {
    return getParameter("piValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"piValue","piValueMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }
}
