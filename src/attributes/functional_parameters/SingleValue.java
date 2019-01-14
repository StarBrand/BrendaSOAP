package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;

public abstract class SingleValue extends FunctionalParameter{

  private double value;
  private double max_value;
  private Molecule molecule;

  public SingleValue(){

  }

  public SingleValue(double value, Molecule molecule, Literature... reference){
    super(reference);
    this.value = value;
    max_value = Double.NaN;
    this.molecule = molecule;
  }

  public SingleValue(double value, double max_value, Molecule molecule, Literature... reference){
    super(reference);
    this.value = value;
    this.max_value = max_value;
    this.molecule = molecule;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public void setMax_value(double max_value) {
    this.max_value = max_value;
  }

  protected void setMolecule(Molecule molecule) {
    this.molecule = molecule;
  }

  public double getValue() {
    return value;
  }

  public double getMax_value() {
    return max_value;
  }

  protected Molecule getMolecule() {
    return molecule;
  }

  protected String getParameter(String parameter) {
    String out =  parameter + "*" + value;
    if (! Double.isNaN(max_value)){
      out += "#" + parameter + "Maximum*" + max_value;
    }
    return out;
  }

}
