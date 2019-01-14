package attributes.functional_parameters;

import entities.Inhibitor;
import entities.Literature;
import entities.Molecule;
import entities.Substrate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kcat extends SingleValue {

  public Kcat(){

  }

  public Kcat(double kcat_value, String substrate, Literature... references){
    super(kcat_value, new Substrate(substrate), references);
  }

  public Kcat(double kcat_value, double kcat_max_value, String substrate, Literature... references){
    super(kcat_value, kcat_max_value, new Substrate(substrate), references);
  }

  public void setSubstrate(Substrate substrate) {
    setMolecule(substrate);
  }

  public void setSubstrate(String substrate) {
    setMolecule(new Substrate(substrate));
  }


  public Substrate getSubstrate() {
    return (Substrate) getMolecule();
  }

  public String getMethod(){
    return "getKcatKmValue";
  }

  public String getParameter(){
    return getParameter("kcatKmValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","kcatKmValue","kcatKmValueMaximum","substrate","commentary","organism","ligandStructureId"};
    return Arrays.asList(columns);
  }
}
