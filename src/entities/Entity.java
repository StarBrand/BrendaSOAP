package entities;

import attributes.Attribute;
import java.util.HashMap;
import java.util.List;

/**
 * The type entity
 *
 * @author Juan Saez Hidalgo
 */
public interface Entity{

  /**
   * Return the {@Link attributes.Attribute} of an entity, for default is an empty list
   *
   * @return a List of the attributes
   * @see Attribute
   */
  List<Attribute> getAttribute();

  /**
   * Add an {@Link attributes.Attribute}, attributes, or nothing to an entity
   *
   * @param attribute a list of attribute, an empty argument or an attribute to an entity
   * @see Attribute
   */
  void addAttributes(Attribute... attribute);

  /**
   * Return the parameters for a main.BrendaSOAP query
   *
   * @return the parameters ready for a query
   */
  String getParameter();

}
