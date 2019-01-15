package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.List;

public class TurnoverNumber extends SingleValue{

  private Molecule substrate;

  public TurnoverNumber(){

  }

  public TurnoverNumber(double turnoverNumberValue, String substrate, Literature... references){
    super(turnoverNumberValue, references);
    this.substrate = new Substrate(substrate);
  }

  public TurnoverNumber(double turnoverNumberValue, double turnoverNumberMaxValue, String substrate, Literature... references){
    super(turnoverNumberValue, turnoverNumberMaxValue, references);
    this.substrate = new Substrate(substrate);
  }

  public void setMolecule(Substrate substrate){
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
    String[] columns = new String[]{"ecNumber","turnoverNumber","turnoverNumberMaximum","substrate","commentary","organism","ligandStructureId"};
    return Arrays.asList(columns);
  }
}
