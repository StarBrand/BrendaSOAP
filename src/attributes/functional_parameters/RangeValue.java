package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;

public abstract class RangeValue extends FunctionalParameter{

  private double min_value;
  private double max_value;

  public RangeValue(){

  }

  public RangeValue(double min_value, Literature... reference){
    super(reference);
    this.min_value = min_value;
    max_value = Double.NaN;
  }

  public RangeValue(double value, double max_value, Literature... reference){
    super(reference);
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

}
