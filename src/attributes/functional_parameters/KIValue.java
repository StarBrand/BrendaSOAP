package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.Arrays;
import java.util.List;

public class KIValue extends SingleValue{

  private Molecule inhibitor;

  public KIValue(){

  }

  public KIValue(double kiValue, String inhibitor, Literature... references){
    super(kiValue, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  public KIValue(double kiValue, double kiMaxValue, String inhibitor, Literature... references){
    super(kiValue, kiMaxValue, references);
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
    String[] columns = new String[]{"ecNumber","kiValue","kiValueMaximum","inhibitor","commentary","organism","ligandStructureId"};
    return Arrays.asList(columns);
  }
}
