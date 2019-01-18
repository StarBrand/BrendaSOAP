package queries;

import attributes.Attribute;
import entities.Entity;
import java.util.List;

/**
 * A query type for every model query
 *
 * @author Juan Saez Hidalgo
 */
public interface Query {

  /**
   * Set the {@Link entities.Entity) that are going to save
   * the results of the query
   *
   * @param entity None, one or more {@Link entities.Entity}
   * @see entities.Entity
   */
  void setEntities(Entity... entity);

  /**
   * Add {@Link attributes.Attribute} to the query,
   * they are going to be the parameters of the query
   *
   * @param attribute None, one or more {@Link attributes.Attribute}
   * @see attributes.Attribute
   */
  void addAttributes(Attribute... attribute);

  /**
   * Gets the number of {@Link attribute.Attribute} on the query
   *
   * @return The number of {@Link attribute.Attribute}
   * @see attributes.Attribute
   */
  int numberOfAttributes();

  /**
   * Gets the result of the query as a List of the
   * results
   *
   * @return A list of the results in the correspondent format
   * @throws Exception Call Exception
   */
  List<?> getResult() throws Exception;

}
