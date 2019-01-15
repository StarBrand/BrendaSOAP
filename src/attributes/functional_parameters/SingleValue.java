package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;

public abstract class SingleValue extends FunctionalParameter{

  private double value;
  private double max_value;

  public SingleValue(){

  }

  public SingleValue(double value, Literature... reference){
    super(reference);
    this.value = value;
    max_value = Double.NaN;
  }

  public SingleValue(double value, double max_value, Literature... reference){
    super(reference);
    this.value = value;
    this.max_value = max_value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public void setMax_value(double max_value) {
    this.max_value = max_value;
  }

  public double getValue() {
    return value;
  }

  public double getMax_value() {
    return max_value;
  }

  protected String getParameter(String parameter) {
    String out =  parameter + "*" + value;
    if (! Double.isNaN(max_value)){
      out += "#" + parameter + "Maximum*" + max_value;
    }
    return out;
  }

}
