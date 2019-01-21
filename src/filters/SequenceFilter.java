package filters;

import attributes.Attribute;
import entities.Entity;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;

/**
 * SequenceFilter class, filter a list of {@Link entities.Protein}
 * leaving the proteins that have sequence
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 */
public class SequenceFilter extends AbstractFilter {

  /**
   * Constructor
   *
   */
  public SequenceFilter(){
    super();
  }

  public void setCriteria(Attribute... attribute) {

  }

  public void addCriteria(Attribute... attribute) {

  }

  public void clearCriteria() {

  }

  public List<Entity> getFiltered() {
    List<Entity> out = new ArrayList<Entity>();
    for(Entity entity:entities){
      if(!( (Protein) entity ).getUniprot().equals("")){
        out.add(entity);
      }
    }
    return out;
  }
}