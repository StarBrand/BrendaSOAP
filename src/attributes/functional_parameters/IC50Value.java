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
 * IC50 Value class, IC50 Value [ mM]
 *
 * @author Juan Saez Hidalgo
 */
public class IC50Value extends SingleValue implements MoleculeDependentAttribute {

  private Molecule inhibitor = new NullMolecule();

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public IC50Value(){

  }

  /**
   * The constructor given all parameters
   *
   * @param IC50value   The value of the attribute
   * @param inhibitor   The name of the inhibitor that defines the attribute
   * @param commentary  The commentary of the data
   * @param references  The literature of the data
   */
  public IC50Value(double IC50value, String inhibitor, String commentary, Literature... references){
    super(IC50value, commentary, references);
    this.inhibitor = new Inhibitor(inhibitor);
  }

  /**
   * The constructor given all parameters
   * and (exceptionally) the maximum value turning the single value
   * in a range
   *
   * @param IC50value     The minimum value of the attribute
   * @param IC50maximum   The maximum value of the attribute
   * @param inhibitor     The name of the inhibitor that defines the attribute
   * @param commentary    The commentary of the data
   * @param references    The literature of the data
   */
  public IC50Value(double IC50value, double IC50maximum, String inhibitor, String commentary, Literature... references){
    super(IC50value, IC50maximum, commentary, references);
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
    return "getIc50Value";
  }

  public String getParameter(){
    return getParameter("ic50Value", "#inhibitor*", inhibitor.getName());
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ic50Value","ic50ValueMaximum","inhibitor","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("inhibitor"));
    super.setAttribute(resultOfQuery);
  }
}