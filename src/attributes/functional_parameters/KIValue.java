package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import java.util.Arrays;
import java.util.List;

public class KIValue extends SingleValue{

  public KIValue(){

  }

  public KIValue(double kiValue, String inhibitor, Literature... references){
    super(kiValue, new Inhibitor(inhibitor), references);
  }

  public KIValue(double kiValue, double kiMaxValue, String inhibitor, Literature... references){
    super(kiValue, kiMaxValue, new Inhibitor(inhibitor), references);
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
