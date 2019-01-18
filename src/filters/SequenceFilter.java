package filters;

import attributes.Attribute;
import entities.Entity;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;

public class SequenceFilter implements Filter {

  private List<Entity> proteins;

  public SequenceFilter(){
    proteins = new ArrayList<Entity>();
  }

  public void setEntities(Entity... entiy) {
    proteins = new ArrayList<Entity>();
    for(Entity e:entiy){
        proteins.add(e);
    }
  }

  public void addEntities(Entity... entities) {
    for(Entity entity:entities){
      proteins.add(entity);
    }
  }

  public void clearEntities() {
    proteins.clear();
  }

  public void setCriteria(Attribute... attribute) {

  }

  public void addCriteria(Attribute... attribute) {

  }

  public void clearCriteria() {

  }

  public List<Entity> getFiltered() {
    List<Entity> out = new ArrayList<Entity>();
    for(Entity entity:proteins){
      if(!( (Protein) entity ).getUniprot().equals("")){
        out.add(entity);
      }
    }
    return out;
  }
}
