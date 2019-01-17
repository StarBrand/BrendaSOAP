package filters;

import attributes.Attribute;
import entities.Entity;
import java.util.List;

public interface Filter {

  public void setEntities(Entity... entiy);

  public void addEntities(Entity... entities);

  public void clearEntities();

  public void setCriterions(Attribute... attribute);

  public void addCriterions(Attribute... attribute);

  public void clearCriterions();

  public List<Entity> getFiltered();

}
