package filters;

import attributes.Attribute;
import entities.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractFilter class, this abstract class has the standard
 * implementation of the most used method of the Filter interface
 *
 * @author Juan Saez Hidalgo
 */
public abstract class AbstractFilter implements Filter {

  protected List<Entity> entities;

  /**
   * Constructor
   */
  public AbstractFilter(){
    entities = new ArrayList<Entity>();
  }

  public void setEntities(Entity... entity) {
    entities = new ArrayList<Entity>();
    for (Entity e : entity) {
      entities.add(e);
    }
  }

  public void addEntities(Entity... entity) {
    for (Entity e: entity) {
      this.entities.add(e);
    }
  }

  public void clearEntities() {
    entities.clear();
  }

}