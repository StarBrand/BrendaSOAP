package queries;

import attributes.Attribute;
import entities.Entity;
import java.util.List;

/**
 * A query type for every model query
 */
public interface Query {

  void setEntities(Entity... entity);

  void addAttributes(Attribute... attribute);

  int numberOfAttributes();

  List<?> getResult() throws Exception;

}
