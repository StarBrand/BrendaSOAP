package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IC50Value extends SingleValue implements MoleculeDependentAttribute {

  private Molecule inhibitor;

  public IC50Value(){

  }

  public IC50Value(double IC50value, String inhibitor, String commentary, Literature... references){
    super(IC50value, commentary, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public IC50Value(double IC50value, double IC50maximum, String inhibitor, String commentary, Literature... references){
    super(IC50value, IC50maximum, commentary, references);
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
    String[] columns = new String[]{"ic50Value","ic50ValueMaximum","inhibitor","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("inhibitor"));
    super.setAttribute(resultOfQuery);
  }
}