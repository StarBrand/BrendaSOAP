package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IC50Value extends SingleValue implements MoleculeDependentAttribute {

  private Molecule inhibitor;

  public IC50Value(){

  }

  public IC50Value(double IC50value, String inhibitor, Literature... references){
    super(IC50value, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public IC50Value(double IC50value, double IC50maximum, String inhibitor, Literature... references){
    super(IC50value, IC50maximum, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public void setMolecule(Molecule inhibitor) {
    this.inhibitor = inhibitor;
  }

  public void setMolecule(String inhibitor) {
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public Molecule getMolecule() {
    return inhibitor;
  }

  public String getMethod() {
    return "getIC50Value";
  }

  public String getParameter(){
    return getParameter("ic50Value");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","ic50Value","ic50ValueMaximum","inhibitor","commentary","organism","ligandStructureId"};
    return Arrays.asList(columns);
  }
}