package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.function.Sin;

public class Km extends SingleValue {

  private Molecule substrate;

  public Km(){ }

  public Km(double km_value, String substrate, Literature... references){
    super(km_value, references);
    this.substrate = new Substrate(substrate);
  }

  public Km(double km_value, double km_max_value, String substrate, Literature... references){
    super(km_value, km_max_value, references);
    this.substrate = new Substrate(substrate);
  }

  public void setMolecule(Molecule substrate) {
    this.substrate = substrate;
  }

  public void setSubstrate(String substrate) {
    this.substrate = new Substrate(substrate);
  }

  public Molecule getMolecule() {
    return substrate;
  }

  public String getMethod() {
    return "getKmValue";
  }

  public String getParameter() {
    return getParameter("kmValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","kmValue","kmValueMaximum","substrate","commentary","organism","ligandStructureId"};
    return Arrays.asList(columns);
  }
}
