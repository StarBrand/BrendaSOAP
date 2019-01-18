package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Km class, K_{M} value [ mM ]
 *
 * @author Juan Saez Hidalgo
 */
public class Km extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public Km(){ }

  /**
   * The constructor given all parameters
   *
   * @param km_value     The value of the attribute
   * @param commentary   The commentary of the observation
   * @param references   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public Km(double km_value, String substrate, String commentary, Literature... references){
    super(km_value, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param km_value      The minimum value of the attribute
   * @param km_max_value  The maximum value of the attribute
   * @param commentary    The commentary of the observation
   * @param references    The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
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
    return getParameter("kmValue", "#substrate*", substrate.getName());
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
