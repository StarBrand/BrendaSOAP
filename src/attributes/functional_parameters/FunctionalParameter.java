package attributes.functional_parameters;

import attributes.Attribute;
import entities.Literature;
import entities.Molecule;
import java.util.ArrayList;
import java.util.List;

public abstract class FunctionalParameter implements Attribute {

  private List<Literature> references;

  public FunctionalParameter(Literature... literature){
    references = new ArrayList<Literature>();
    for(Literature l:literature){
      references.add(l);
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

}
