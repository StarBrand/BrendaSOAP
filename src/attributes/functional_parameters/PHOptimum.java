package attributes.functional_parameters;

import entities.Literature;
import entities.Molecule;
import entities.NoMolecule;
import entities.Substrate;
import java.util.Arrays;
import java.util.List;

public class PHOptimum extends SingleValue{

  public PHOptimum(){ }

  public PHOptimum(double PHOptimum, Literature... references){
    super(PHOptimum, new NoMolecule(), references);
  }

  public PHOptimum(double PHOptimum_value, double maxPHOptimum, Literature... references){
    super(PHOptimum_value, maxPHOptimum, new NoMolecule(), references);
  }

  public String getMethod() {
    return "getPhOptimum";
  }

  public String getParameter() {
    return getParameter("phOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","phOptimum","phOptimumMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }
}
