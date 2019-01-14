package attributes.functional_parameters;

import entities.Literature;
import entities.Substrate;
import java.util.Arrays;
import java.util.List;

public class TurnoverNumber extends SingleValue{

  public TurnoverNumber(){

  }

  public TurnoverNumber(double turnoverNumberValue, String substrate, Literature... references){
    super(turnoverNumberValue, new Substrate(substrate), references);
  }

  public TurnoverNumber(double turnoverNumberValue, double turnoverNumberMaxValue, String substrate, Literature... references){
    super(turnoverNumberValue, turnoverNumberMaxValue, new Substrate(substrate), references);
  }

  public void setSubstrate(Substrate substrate){
    setMolecule(substrate);
  }

  public void setSubstrate(String substrate){
    setMolecule(new Substrate(substrate));
  }

  public Substrate getSubstrate(){
    return (Substrate) getMolecule();
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
