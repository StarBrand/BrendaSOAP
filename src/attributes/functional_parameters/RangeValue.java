package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;

public abstract class RangeValue extends FunctionalParameter{

  private double min_value;
  private double max_value;
  private Molecule molecule;

  public RangeValue(){

  }

  public RangeValue(double min_value, Molecule molecule, Literature... reference){
    super(reference);
    this.min_value = min_value;
    max_value = Double.NaN;
    this.molecule = molecule;
  }

  public RangeValue(double value, double max_value, Molecule molecule, Literature... reference){
    super(reference);
    this.min_value = min_value;
    this.max_value = max_value;
    this.molecule = molecule;
  }

  public void setMin_Value(double min_value) {
    this.min_value = min_value;
  }

  public void setMax_value(double max_value) {
    this.max_value = max_value;
  }

  protected void setMolecule(Molecule molecule) {
    this.molecule = molecule;
  }

  public double getMin_value() {
    return min_value;
  }

  public double getMax_value() {
    return max_value;
  }

  protected Molecule getMolecule() {
    return molecule;
  }

  protected String getParameter(String parameter) {
    return parameter + "*" + min_value + "#" + parameter + "Maximum*" + max_value;
  }

}
