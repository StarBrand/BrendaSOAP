package attributes.enzyme_estrucuture;

import attributes.Attribute;
import entities.Literature;
import entities.Molecule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MolecularWeight implements Attribute{

  private int value;
  private List<Literature> references;

  public MolecularWeight(){

  }

  public MolecularWeight(int molecular_weight, Literature... reference){
    value = molecular_weight;
    references = new ArrayList<Literature>();
    for (Literature r:reference){
      references.add(r);
    }
  }

  public List<Literature> getReferences() {
    return references;
  }

  public void setReferences(List<Literature> references) {
    this.references = references;
  }

  public void addReference(Literature reference) {
    try {
      references.add(reference);
    } catch (Exception e) {
      references = new ArrayList<Literature>();
      references.add(reference);
    }
  }


  public String getMethod(){
    return "getMolecularWeight";
  }

  public String getParameter(){
    return "molecularWeight*" + String.valueOf(value);
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber","molecularWeight","molecularWeightMaximum","commentary","organism"};
    return Arrays.asList(columns);
  }

}
