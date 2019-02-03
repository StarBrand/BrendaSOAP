package output;

import attributes.Attribute;
import attributes.enzyme_structure.PDB;
import client.User;
import entities.Entity;
import entities.Protein;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import queries.ParserAnswer;

/**
 * PdbQuery class, look for the PDB ID
 * in a List of {@Link entities.Protein}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 */
public class PdbQuery extends EnzymeStructureQuery{

  private PDB pdb;
  private List<Entity> proteins;
  private List<Protein> result;
  private ParserAnswer parserAnswer;

  /**
   * Constructor
   *
   * @param user Brenda User
   */
  public PdbQuery(User user){
    super(user);
    pdb = new PDB();
    proteins = new ArrayList<Entity>();
    result = new ArrayList<Protein>();
    parserAnswer = new ParserAnswer();
  }

  /**
   * Set the list of {@Link entities.Protein} to get
   * the aa sequence
   *
   * @param entity entities.Protein
   */
  public void setEntities(Entity... entity) {
    for(Entity e:entity){
      proteins.add(e);
    }
  }

  public List<?> getResult() throws Exception {
    result.clear();
    proteins = clean_protein(proteins);
    List<String> result = super.getResult(true, false, pdb, proteins);
    int i = 0;
    for(String ans:result){
      if(!ans.equals("")){
        for(HashMap<String, String> value:parserAnswer.getResult(ans)){
          PDB new_pdb = new PDB();
          new_pdb.setAttribute(value);
          if(!proteins.get(i).getAttribute().contains(new_pdb)) proteins.get(i).addAttributes(new_pdb);
        }
      }
      this.result.add((Protein) proteins.get(i));
      i++;
    }
    return this.result;
  }

  /**
   * Gets whether two {@Link entities.Protein} has
   * the same organism and ecNumber
   *
   * @param protein1 A protein
   * @param protein2 Another protein
   * @return Whether they are equals or not
   * @see entities.Protein
   */
  private boolean compare(Protein protein1, Protein protein2){
    boolean ans = true;
    ans &= protein1.getOrganism().getName().equals(protein2.getOrganism().getName());
    ans &= protein1.getEnzyme().getEC().equals(protein2.getEnzyme().getEC());
    return ans;
  }

  /**
   * Gets whether two {@Link attributes.enzyme_structure.PDB}
   * are the same
   *
   * @param pdb1 One pdb
   * @param pdb2 Another pdb
   * @return
   * @see attributes.enzyme_structure.PDB
   */
  private boolean compare(PDB pdb1, PDB pdb2){
    return pdb1.getPdb().equals(pdb2.getPdb());
  }

  /**
   * Leave just one proteins with the same
   * organism and EC Number
   *
   * @param proteins A list of Protein
   * @return  The list leaving just the proteins that
   *          meet the condition
   */
  private List<Entity> clean_protein(List<Entity> proteins){
    List<Entity> out = new ArrayList<Entity>();
    for(Entity protein:proteins){
      boolean condition = true;
      for(Entity new_protein:out){
        condition &= !(compare((Protein) protein, (Protein) new_protein));
      }
      if(condition) out.add(protein);
    }
    return out;
  }

  /**
   * Generate the table with four attributes:
   * {@Link attributes.enzyme_structure.ECNumber}, the name of the
   * {@Link attributes.organism_related_information.Organism}, and
   * the PDB ID and Link from {@Link attributes.enzyme_structure.PDB}
   *
   * @see attributes.enzyme_structure.ECNumber
   * @see attributes.organism_related_information.Organism
   * @see attributes.enzyme_structure.PDB
   */
  public void generateFile() throws IOException {
    List<String> line = new ArrayList<String>();
    (new File(user.getMail() + "_results")).mkdirs();
    FileWriter file = new FileWriter(user.getMail() + "_results\\pdb_table.txt");
    BufferedWriter bw = new BufferedWriter(file);
    line.add("EC_Number");
    line.add("Organism");
    line.add("PDB");
    line.add("link");
    TableWriter.write(line, "\t", bw);
    for(Entity protein:result){
      String ecNumber = ((Protein) protein).getEnzyme().getEC().toString();
      String organism = ((Protein) protein).getOrganism().getName();
      for(Attribute pdb:protein.getAttribute()){
        line.clear();
        line.add(ecNumber);
        line.add(organism);
        line.add( ((PDB) pdb).getPdb() );
        line.add( ((PDB) pdb).getLink() );
        TableWriter.write(line, "\t", bw);
      }
    }
    bw.close();
  }

}
