package entities;

import attributes.Attribute;
import java.util.List;

/**
 * The type entity
 */
public interface Entity {

  List<Attribute> getAttribute();

  void addAttributes(Attribute... attribute);

  String getParameter();

}
