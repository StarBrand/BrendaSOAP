package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class KIValue extends SingleValue implements MoleculeDependentAttribute {

  private Molecule inhibitor;

  public KIValue(){

  }

  public KIValue(double kiValue, String inhibitor, String commentary, Literature... references){
    super(kiValue, commentary, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public KIValue(double kiValue, double kiMaxValue, String inhibitor, String commentary, Literature... references){
    super(kiValue, kiMaxValue, commentary, references);
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
    return "getKiValue";
  }

  public String getParameter() {
    return getParameter("kiValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"kiValue","kiValueMaximum","inhibitor","commentary","literature"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("inhibitor"));
    super.setAttribute(resultOfQuery);
  }
}
