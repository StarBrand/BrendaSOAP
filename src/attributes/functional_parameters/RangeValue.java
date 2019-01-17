package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;
import java.util.HashMap;

public abstract class RangeValue extends FunctionalParameter{

  private double min_value = Double.NaN;
  private double max_value = Double.NaN;

  public RangeValue(){

  }

  public RangeValue(double min_value, String commentary, Literature... reference){
    super(commentary, reference);
    this.min_value = min_value;
    max_value = Double.NaN;
  }

  public RangeValue(double value, double max_value, String commentary, Literature... reference){
    super(commentary, reference);
    this.min_value = min_value;
    this.max_value = max_value;
  }

  public void setMin_Value(double min_value) {
    this.min_value = min_value;
  }

  public void setMax_value(double max_value) {
    this.max_value = max_value;
  }

  public double getMin_value() {
    return min_value;
  }

  public double getMax_value() {
    return max_value;
  }

  protected String getParameter(String parameter) {
    return parameter + "*" + min_value + "#" + parameter + "Maximum*" + max_value;
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    try {
      setMin_Value(Integer.valueOf(resultOfQuery.get(this.getColumns().get(0))));
    } catch (Exception e){

    }
    try {
      setMax_value(Integer.valueOf(resultOfQuery.get(this.getColumns().get(1))));
    } catch (Exception e){

    }
    super.setAttribute(resultOfQuery);
  }
}
