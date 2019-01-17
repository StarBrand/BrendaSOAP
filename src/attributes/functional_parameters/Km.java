package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Km extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;

  public Km(){ }

  public Km(double km_value, String substrate, String commentary, Literature... references){
    super(km_value, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  public Km(double km_value, double km_max_value, String substrate, String commentary, Literature... references){
    super(km_value, km_max_value, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  public void setMolecule(Molecule substrate) {
    this.substrate = substrate;
  }

  public void setMolecule(String substrate) {
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
    String[] columns = new String[]{"kmValue","kmValueMaximum","substrate","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("substrate"));
    super.setAttribute(resultOfQuery);
  }
}
