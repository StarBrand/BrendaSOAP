package attributes;

import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.ArrayList;

/**
 * The type atribute for every parameter
 */
public interface Attribute {

  /**
   * Return the references of the parameter
   * @return a list of reference
   */
  ArrayList<Literature> getReferences();

  /**
   * Return the SOAP method of the attribute
   * @return the name of the SOAP method
   */
  String getMethod();

}
