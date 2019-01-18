package filters;

import attributes.Attribute;
import entities.Entity;
import java.util.List;

/**
 * A filter type for the filters that, given
 * a list, return another list with the specified
 * parameters
 *
 * @author Juan Saez Hidalgo
 */
public interface Filter {

  /**
   * Set the {@Link entities.Entity} that are
   * going to be filtered
   * this method erase the previous ones
   *
   * @param entity None, one or more {@Link entities.Entity}
   * @see entities.Entity
   */
  void setEntities(Entity... entity);

  /**
   * Add {@Link entities.Entity} that are
   * going to be filtered
   *
   * @param entity None, one or more {@Link entities.Entity}
   * @see entities.Entity
   */
  void addEntities(Entity... entity);

  /**
   * Clear the list of {@Link entities.Entity}
   *
   * @see entities.Entity
   */
  void clearEntities();

   /**
   * Set the {@Link attributes.Attribute} that are
   * going to be the criteria of the filters
   * this method erase the previous ones
   *
   * @param attribute None, one or more {@Link attributes.Attribute}
   * @see attributes.Attribute
   */
  void setCriteria(Attribute... attribute);

  /**
   * Add {@Link attributes.Attribute} that are
   * going to be the criteria of the filters
   *
   * @param attribute None, one or more {@Link attributes.Attribute}
   * @see attributes.Attribute
   */
  void addCriteria(Attribute... attribute);


  /**
   * Clear the list of {@Link attributes.Attribute} criteria
   *
   * @see attributes.Attribute
   */
  void clearCriteria();

  /**
   * Get the filtered list of {@Link entities.Entity}
   *
   * @return A list of filtered {@Link entities.Entity}
   * @see entities.Entity
   */
  List<Entity> getFiltered();

}
