package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * TurnoverNumber class, turnover number, kcat, k2 [ s^(-1) ]
 *
 */
public class TurnoverNumber extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public TurnoverNumber(){

  }

  /**
   * The constructor given all parameters
   *
   * @param turnoverNumberValue The value of the attribute
   * @param substrate           The name of the substrate
   * @param commentary          The commentary of the observation
   * @param references          The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TurnoverNumber(double turnoverNumberValue, String substrate, String commentary, Literature... references){
    super(turnoverNumberValue, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param turnoverNumberValue     The minimum value of the attribute
   * @param turnoverNumberMaxValue  The maximum value of the attribute
   * @param substrate               The name of the substrate
   * @param commentary              The commentary of the observation
   * @param references              The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public TurnoverNumber(double turnoverNumberValue, double turnoverNumberMaxValue, String substrate, String commentary, Literature... references){
    super(turnoverNumberValue, turnoverNumberMaxValue, commentary, references);
    this.substrate = new Substrate(substrate);
  }

  public void setMolecule(Molecule substrate){
    this.substrate = substrate;
  }

  public void setMolecule(String substrate){
    this.substrate = new Substrate(substrate);
  }

  public Molecule getMolecule(){
    return substrate;
  }

  public String getMethod() {
    return "getTurnoverNumber";
  }

  public String getParameter() {
    return getParameter("turnoverNumber", "#substrate*", substrate.getName());
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"turnoverNumber","turnoverNumberMaximum","substrate","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setMolecule(resultOfQuery.get("substrate"));
    super.setAttribute(resultOfQuery);
  }
}
