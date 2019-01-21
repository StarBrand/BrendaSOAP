package filters;

import attributes.Attribute;
import attributes.NumericalAttribute;
import entities.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * NumericalAttributeFilter class, filter a list of {@Link entities.Protein}
 * leaving the proteins that have a parameter between, more or less
 * value of a criterion parameter
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 */
public class NumericalAttributeFilter extends AbstractFilter{

  private List<Attribute> attributes;

  /**
   * Constructor
   */
  public NumericalAttributeFilter(){
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
    List<NumericalAttribute> numericalCriteria = getNumericalAttributes(this.attributes);
    for (Entity protein: entities){
      check = 0;
      List<NumericalAttribute> numericalParameter = getNumericalAttributes(protein.getAttribute());
      for(NumericalAttribute criteria:numericalCriteria){
        for(NumericalAttribute parameter:numericalParameter){
          if(criteria.getMethod().equals(parameter.getMethod())){
            if(minCriterion(criteria, parameter)){
              check++;
            }
            if(maxCriterion(criteria, parameter)){
              check++;
            }
          }
        }
      }
      if(check == 2*numericalCriteria.size()){
        out.add(protein);
      }
    }
    return out;
  }

  private boolean minCriterion(NumericalAttribute criteria, NumericalAttribute parameter){
    boolean out = true;
    out &= Double.isNaN(criteria.getMin_value()) || criteria.getMin_value() <= parameter.getMin_value();
    return out;
  }

  private boolean maxCriterion(NumericalAttribute criteria, NumericalAttribute parameter){
    boolean out = false;
    out |= Double.isNaN(criteria.getMax_value());
    if (Double.isNaN(parameter.getMax_value())){
      out |= criteria.getMax_value() >= parameter.getMin_value();
    }
    else {
      out |= criteria.getMax_value() >= parameter.getMax_value();
    }
    return out;
  }

  private List<NumericalAttribute> getNumericalAttributes(List<Attribute> attributes){
    List<NumericalAttribute> out = new ArrayList<NumericalAttribute>();
    for(Attribute attribute:attributes){
      try {
        out.add((NumericalAttribute) attribute);
      } catch (Exception e){

      }
    }
    return out;
  }
}
