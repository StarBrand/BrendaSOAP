package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Kcat class, kcat/KM value [ (1/mMs^(-1) ]
 *
 */
public class Kcat extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;


  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public Kcat(){

  }

  /**
   * The constructor given all parameters
   *
   * @param kcat_value  The value of the attribute
   * @param commentary  The commentary of the observation
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public Kcat(double kcat_value, String substrate, String commentary, Literature... references){
    super(kcat_value, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param kcat_value      The minimum value of the attribute
   * @param kcat_max_value  The maximum value of the attribute
   * @param commentary      The commentary of the observation
   * @param references      The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
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
    return getParameter("kcatKmValue", "#substrate*", substrate.getName());
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
