package attributes.functional_parameters;

import attributes.AbstractAttribute;
import attributes.Attribute;
import entities.Literature;
import entities.Molecule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class FunctionalParameter extends AbstractAttribute {

  public FunctionalParameter(){

  }

  public FunctionalParameter(String commentary, Literature... literature){
    super(commentary, literature);
  }

  @Override
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    super.setAttribute(resultOfQuery);
  }
}
