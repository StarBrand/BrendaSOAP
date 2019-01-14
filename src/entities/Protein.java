package entities;

import attributes.Attribute;
import attributes.organism_related_information.Organism;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.biojava.nbio.genome.uniprot.UniprotToFasta;

public class Protein implements Entity{

  private Enzyme enzyme;
  private Organism organism;
  private String uniprot;
  private String sequence_db;
  private List<Attribute> attributes;
  private List<Literature> references;
  private UniprotToFasta uniprotToFasta;

  public Protein(Enzyme aEnzyme, Organism aOrganism, String sequence, Literature... reference){
    enzyme = aEnzyme;
    organism = aOrganism;
    String[] sequence_database = sequence.split(" ");
    uniprot = sequence_database[0];
    try {
      sequence_db = sequence_database[1];
    } catch (Exception e){
      sequence_db = "";
    }
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

  public String getSequence_db() {
    return sequence_db;
  }

  public List<Literature> getReferences() {
    return references;
  }

  public void getSequence() throws Exception{
    uniprotToFasta = new UniprotToFasta();
    FileWriter write_fasta = new FileWriter("fastaFile.txt", false);
    FileWriter write_uniprot = new FileWriter("uniprotFile.txt", false);
    PrintWriter print_line = new PrintWriter(write_uniprot);
    print_line.printf("%s" + "%n", uniprot);
    print_line.close();
    print_line = new PrintWriter(write_fasta);
    print_line.printf("%s" + "%n", "");
    print_line.close();
    uniprotToFasta.process("uniprotFile.txt", "fastaFile.txt");
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }

  @Override
  public String toString(){
    return "ECNumber: "+enzyme.getEC().toString()+"/Organism: "+organism.getName();
  }
}