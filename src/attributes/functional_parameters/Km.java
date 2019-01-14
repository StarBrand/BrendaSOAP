package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.analysis.function.Sin;

public class Km extends SingleValue {

  public Km(){ }

  public Km(double km_value, String substrate, Literature... references){
    super(km_value, new Substrate(substrate), references);
  }

  public Km(double km_value, double km_max_value, String substrate, Literature... references){
    super(km_value, km_max_value, new Substrate(substrate), references);
  }

  public void setSubstrate(Substrate substrate) {
    setMolecule(substrate);
  }

  public void setSubstrate(String substrate) {
    setMolecule(new Substrate(substrate));
  }

  public Substrate getSubstrate() {
    return (Substrate) getMolecule();
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
