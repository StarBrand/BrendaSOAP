package filters;

import attributes.Attribute;
import attributes.MoleculeDependentAttribute;
import entities.Entity;
import entities.Molecule;
import java.util.ArrayList;
import java.util.List;

/**
 * MoleculeDependentFilter class, filter a list of {@Link entities.Protein}
 * leaving the proteins that have the {@Link entities.Molecule} in common
 * with the criteria
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 * @see entities.Molecule
 */
public class MoleculeDependentFilter extends AbstractFilter{

  private List<Attribute> attributes;

  /**
   * Constructor
   */
  public MoleculeDependentFilter(){
    super();
    attributes = new ArrayList<Attribute>();
  }

  public void setCriteria(Attribute... attribute) {
    attributes = new ArrayList<Attribute>();
    for(Attribute a:attribute){
      attributes.add(a);
    }
  }

  public void addCriteria(Attribute... attribute) {
    for(Attribute a:attribute){
      attributes.add(a);
    }
  }

  public void clearCriteria() {
    attributes.clear();
  }

  public List<Entity> getFiltered() {
    int check;
    List<Entity> out = new ArrayList<Entity>();
    List<MoleculeDependentAttribute> moleculeDependentCriteria = getMoleculeDependentAttributes(this.attributes);
    for (Entity protein : entities) {
      check = 0;
      for(MoleculeDependentAttribute mDC:moleculeDependentCriteria){
        if(!mDC.getMolecule().getName().equals("")){
          List<MoleculeDependentAttribute> moleculeDependentParameter = getMoleculeDependentAttributes(protein.getAttribute());
          for(MoleculeDependentAttribute parameter:moleculeDependentParameter) {
            if (mDC.getMethod().equals(parameter.getMethod()) && mDC.getMolecule().getName().equals(parameter.getMolecule().getName())) {
              check++;
            }
          }
        }
        else{
          check++;
        }
      }
      if(check == moleculeDependentCriteria.size()){
        out.add(protein);
      }
    }
    return out;
  }

  private List<MoleculeDependentAttribute> getMoleculeDependentAttributes(List<Attribute> attributes){
    List<MoleculeDependentAttribute> out = new ArrayList<MoleculeDependentAttribute>();
    for(Attribute attribute:attributes){
      try {
        out.add((MoleculeDependentAttribute) attribute);
      } catch (Exception e){

      }
    }
    return out;
  }
}
