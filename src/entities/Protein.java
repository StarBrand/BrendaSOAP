package entities;

import java.util.ArrayList;

public class Protein {

  private Enzyme enzyme;
  private Organism organism;
  private String uniprot;
  private ArrayList<Literature> references;

  public Protein(Enzyme aEnzyme, Organism aOrganism, String aCode, Literature... referencesInput) {
    enzyme = aEnzyme;
    organism = aOrganism;
    uniprot = aCode;
    references = new ArrayList<Literature>();
    if (!referencesInput.equals(null)) {
      for (Literature reference : referencesInput) {
        references.add(reference);
      }
    }
  }

  public String getUniprot(){
    return uniprot;
  }

}