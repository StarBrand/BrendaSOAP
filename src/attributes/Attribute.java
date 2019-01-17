package attributes;

import entities.Literature;
import java.util.HashMap;
import java.util.List;

/**
 * The type Attribute for every parameter in the Brenda database
 *
 * @author Juan Saez
 */
public interface Attribute {

  /**
   * Return the references ({@Link entities.Literature}) of the parameter
   *
   * @return a list of reference
   * @see entities.Literature
   */
  List<Literature> getReferences();

  /**
   * Sets the commentary of an Attribute
   *
   * @param commentary the commentary of an Attribute
   */
  void setCommentary(String commentary);

  /**
   * Gets the commentary of an Attribute
   *
   * @return A commentary of an Attibute
   */
  String getCommentary();

  /**
   * Sets the reference of an Attribute, erasing the previous reference
   *
   * @param references the new list of reference
   */
  void setReferences(List<Literature> references);

  /**
   * Add a reference {@Link entities.Literature} to an Attribute
   *
   * @param reference A reference({@Link entities.Literature}), a list of reference, or an empty argument
   * @see entities.Literature
   */
  void addReference(Literature... reference);

  /**
   * Gets the SOAP method of the attribute
   *
   * @return the name of the SOAP method
   */
  String getMethod();

  /**
   * Gets the parameter for a SOAP query
   *
   * @return the parameter for a SOAP query
   */
  String getParameter();

  /**
   * A list of the columns that defines an Attribute
   *
   * @return List of String with the names of the useful attributes in the results of a SOAP query
   */
  List<String> getColumns();

  /**
   * Fill the parameters of an Attribute that are missing given the result
   * of a SOAP query accordly
   *
   * @param resultOfQuery A string with the result of a SOAP Query NOT EMPTY
   */
  void setAttribute(HashMap<String, String> resultOfQuery);

}
