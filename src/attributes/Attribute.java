package attributes;

import entities.Literature;
import java.util.List;

/**
 * The type atribute for every parameter
 */
public interface Attribute {

  /**
   * Return the references of the parameter
   * @return a list of reference
   */
  List<Literature> getReferences();

  void setReferences(List<Literature> references);

  void addReference(Literature reference);

  /**
   * Return the SOAP method of the attribute
   * @return the name of the SOAP method
   */
  String getMethod();

  String getParameter();

  List<String> getColumns();

}
