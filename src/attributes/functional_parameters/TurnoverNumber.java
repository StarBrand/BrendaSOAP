package attributes.functional_parameters;

import attributes.MoleculeDependentAttribute;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TurnoverNumber extends SingleValue implements MoleculeDependentAttribute {

  private Molecule substrate;

  public TurnoverNumber(){

  }

  public TurnoverNumber(double turnoverNumberValue, String substrate, String commentary, Literature... references){
    super(turnoverNumberValue, commentary, references);
    this.substrate = new Substrate(substrate);
  }

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
    return getParameter("turnoverNumber");
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
