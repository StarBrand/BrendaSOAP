package queries;

import attributes.Attribute;
import client.User;
import entities.Literature;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;

/**
 * FillLiterature class, automatically fill the Literature
 * {@Link entities.Literature} class from his Brenda reference
 * code in a list of {@Link entities.Protein}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Literature
 * @see entities.Protein
 */
public class FillLiterature {

  private User user;
  private boolean complete = true;
  private List<Protein> proteins;

  /**
   * Constructor given a Brenda User
   *
   * @param user Brenda User
   */
  public FillLiterature(User user, boolean... complete){
    this.user = user;
    proteins = new ArrayList<Protein>();
    for (boolean c:complete){
      this.complete = c;
    }
  }

  /**
   * Set the list of {@Link entities.Protein} which {@Link entities.Literature}
   * are going to be filled, this erases the previous protein
   *
   * @param protein None, one or more protein to set as the Proteins
   * @see entities.Protein
   * @see entities.Literature
   */
  public void setProteins(Protein... protein){
    this.proteins = new ArrayList<Protein>();
    for(Protein p:protein){
      this.proteins.add(p);
    }
  }

  /**
   * Add to the list of {@Link entities.Protein} which {@Link entities.Literature}
   * are going to be filled, this adds and not erases the previous protein
   *
   * @param protein None, one or more protein to set as the Proteins
   * @see entities.Protein
   * @see entities.Literature
   */
  public void addProteins(Protein... protein){
    for(Protein p:protein){
      this.proteins.add(p);
    }
  }

  /**
   * Fill the {@Link entities.Literature} of every {@Link attributes.Attribute}
   * of every {@Link entities.Protein} in the list, and return this list
   *
   * @return A list of Protein with Literature parameters completely filled
   */
  public List<Protein> fill() throws Exception {
    List<Protein> out = new ArrayList<Protein>();
    for(Protein p:proteins){
      for (Literature reference:p.getOrganism().getReferences()){
        if (this.complete) {
          reference.fill(p.getEnzyme().getEC().toString(), p.getOrganism().getName(), this.user);
        }
        else{
          reference.pubmedFiller(p.getEnzyme().getEC().toString(), p.getOrganism().getName(), this.user);
        }
      }
      Protein new_protein = new Protein(
          p.getEnzyme(),
          p.getOrganism(),
          p.getUniprot()
      );
      for (int i=0; i < p.getAttribute().size(); i++){
        Attribute new_attribute = p.getAttribute().get(i);
        for (int j=0; j < p.getAttribute().get(i).getReferences().size(); j++){
          if (this.complete) {
            new_attribute.getReferences().get(j)
                .fill(p.getEnzyme().getEC().toString(), p.getOrganism().getName(), this.user);
          }
          else{
            new_attribute.getReferences().get(j)
                .pubmedFiller(p.getEnzyme().getEC().toString(), p.getOrganism().getName(), this.user);
          }
        }
        new_protein.addAttributes(new_attribute);
      }
      out.add(new_protein);
    }
    return out;
  }
}