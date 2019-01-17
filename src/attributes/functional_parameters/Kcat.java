package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Kcat extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;

  public Kcat(){

  }

  public Kcat(double kcat_value, String substrate, String commentary, Literature... references){
    super(kcat_value, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  public Kcat(double kcat_value, double kcat_max_value, String substrate, String commentary, Literature... references){
    super(kcat_value, kcat_max_value, commentary, references);
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

  public String getMethod(){
    return "getKcatKmValue";
  }

  public String getParameter(){
    return getParameter("kcatKmValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"kcatKmValue","kcatKmValueMaximum","substrate","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("substrate"));
    super.setAttribute(resultOfQuery);
  }
}
