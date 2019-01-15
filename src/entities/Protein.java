package entities;

import attributes.Attribute;
import attributes.organism_related_information.Organism;
import java.util.ArrayList;
import java.util.List;

public class Protein implements Entity{

  private Enzyme enzyme;
  private Organism organism;
  private String uniprot;
  private List<Attribute> attributes;
  private List<Literature> references;

  public Protein(Enzyme aEnzyme, Organism aOrganism, String sequence, Literature... reference){
    enzyme = aEnzyme;
    organism = aOrganism;
    uniprot = sequence;
    references = new ArrayList<Literature>();
    for (Literature literature:reference){
      references.add(literature);
    }
    attributes = new ArrayList<Attribute>();
  }

  public Enzyme getEnzyme(){
    return enzyme;
  }

  public Organism getOrganism() {
    return organism;
  }

  public String getUniprot(){
    return uniprot;
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public List<Literature> getReferences() {
    return references;
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
    return "ECNumber: "+enzyme.getEC().toString()+"/Organism: "+organism.getName()+"/Sequence: "+uniprot;
  }
}