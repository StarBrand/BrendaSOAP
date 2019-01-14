package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IC50Value extends SingleValue {

  public IC50Value(){

  }

  public IC50Value(double IC50value, String inhibitor, Literature... references){
    super(IC50value, new Inhibitor(inhibitor), references);
  }

  public IC50Value(double IC50value, double IC50maximum, String inhibitor, Literature... references){
    super(IC50value, IC50maximum, new Inhibitor(inhibitor), references);
  }

  public void setInhibitor(Inhibitor inhibitor) {
    setMolecule(inhibitor);
  }

  public void setInhibitor(String inhibitor) {
    setMolecule(new Inhibitor(inhibitor));
  }

  public Inhibitor getInhibitor() {
    return (Inhibitor) getMolecule();
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