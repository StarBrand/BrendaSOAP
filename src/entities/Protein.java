package entities;

import static java.lang.Math.max;

import attributes.Attribute;
import attributes.organism_related_information.Organism;
import java.util.ArrayList;
import java.util.List;

/**
 * The protein class define as the principal entity as this query
 * differentiated by the {@Link entities.Enzyme} type (Ec Number) an the {@link attributes.organism_related_information.Organism} presented
 *
 * @author Juan Saez
 */
public class Protein implements Entity{

  private Enzyme enzyme;
  private Organism organism;
  private String uniprot;
  private List<Attribute> attributes;

  /**
   * Contructor of the protein given the Enzyme, Organism and code of the sequence
   *
   * @param enzyme The {@Link entities.Enzyme} class (or enzyme type)
   * @param organism The {@Link attributes.organism_related_information.Organism} class (the organism in witch the protein is)
   * @param sequence The code of the sequence in UniProt or another AASequence Source
   * @see Enzyme
   * @see Organism
   */
  public Protein(Enzyme enzyme, Organism organism, String sequence){
    this.enzyme = enzyme;
    this.organism = organism;
    uniprot = sequence;
    attributes = new ArrayList<Attribute>();
  }

  /**
   * Gets the {@Link entities.Enzyme}
   *
   * @return Enzyme
   * @see Enzyme
   */
  public Enzyme getEnzyme(){
    return enzyme;
  }

  /**
   * Gets the {@Link attributes.organism_related_information.Organism}
   *
   * @return Organism
   * @see Organism
   */
  public Organism getOrganism() {
    return organism;
  }

  /**
   * Gets the UniProt code sequence
   *
   * @return the uniport code sequence
   */
  public String getUniprot(){
    return uniprot;
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public void addAttributes(Attribute... attribute) {
    for(Attribute a:attribute){
      attributes.add(a);
    }
  }

  public String getParameter() {
    return "ecNumber*"+enzyme.getEC().toString()+"#organism*"+organism.getName();
  }

  @Override
  public String toString(){
    String reference = "";
    for (Literature r:organism.getReferences()){
      reference += r.getBrenda() + ", ";
    }
    reference = reference.substring(0, max(reference.length() - 2, 0));
    reference += ".";
    return "ECNumber: "+enzyme.getEC().toString()+"/Organism: "+organism.getName()+"/Commentary: "+organism.getCommentary()+"/Sequence: "+uniprot+"/References: "+reference;
  }
}