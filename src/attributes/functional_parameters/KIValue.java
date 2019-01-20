package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import entities.NullMolecule;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * KIValue class, K_{I} value [ mM ]
 *
 * @author Juan Saez Hidalgo
 */
public class KIValue extends SingleValue implements MoleculeDependentAttribute {

  private Molecule inhibitor = new NullMolecule();

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public KIValue(){

  }

  /**
   * The constructor given all parameters
   *
   * @param kiValue     The value of the attribute
   * @param inhibitor   The name of the inhibitor
   * @param commentary  The commentary of the observation
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public KIValue(double kiValue, String inhibitor, String commentary, Literature... references){
    super(kiValue, commentary, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param kiValue     The minimum value of the attribute
   * @param kiMaxValue  The maximum value of the attribute
   * @param inhibitor   The name of the inhibitor
   * @param commentary  The commentary of the observation
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
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
    return getParameter("kiValue", "#inhibitor*", inhibitor.getName());
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
