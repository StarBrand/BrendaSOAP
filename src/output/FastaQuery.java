package output;

import attributes.enzyme_structure.AASequence;
import client.User;
import entities.Entity;
import entities.Protein;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import queries.ParserAnswer;

/**
 * FastaQuery class, generate a .txt file
 * with a lis of AA sequence in FASTA format
 *
 * @author Juan Saez Hidalgo
 */
public class FastaQuery extends EnzymeStructureQuery {

  private AASequence aaSequence;
  private List<Entity> proteins;
  private List<AASequence> result;
  private ParserAnswer parserAnswer;

  /**
   * FastaQuery constructor given a Brenda User
   *
   * @param user Brenda User
   */
  public FastaQuery(User user){
    super(user);
    aaSequence = new AASequence();
    proteins = new ArrayList<Entity>();
    result = new ArrayList<AASequence>();
    parserAnswer = new ParserAnswer();
  }

  /**
   * Set the list of {@Link entities.Protein} to get
   * the aa sequence
   *
   * @param entity entities.Protein
   */
  public void setEntities(Entity... entity) {
    for (Entity e:entity){
      proteins.add(e);
    }
  }

  public List<?> getResult() throws Exception {
    this.result.clear();
    List<Entity> new_proteins = new ArrayList<Entity>();
    for (Entity protein:proteins) {
      String result = ((Protein) protein).getUniprot();
      result = result.replace(" and ", " AND ");
      result = result.replace(" And ", " AND ");
      String[] uniprots = result.split(" AND ");
      for (String uniprot : uniprots) {
        if(!uniprot.equals("")) {
          uniprot = uniprot.trim();
          Protein aProtein = new Protein(
              ((Protein) protein).getEnzyme(),
              ((Protein) protein).getOrganism(),
              uniprot
          );
          new_proteins.add(aProtein);
        }
      }
    }
    proteins = new_proteins;
    List<String> result = super.getResult(false, true, aaSequence, proteins);
    for(String ans:result){
      AASequence aaSequence = new AASequence();
      if(ans.equals("")) this.result.add(aaSequence);
      else{
        aaSequence.setAttribute(
            parserAnswer.getResult(ans).get(0)
        );
        this.result.add(aaSequence);
      }
    }
    return this.result;
  }

  /**
   * Generate the file in FASTA format
   * with all the {@Link attributes.enzyme_structure.AASequence}
   * on the result
   *
   * @see attributes.enzyme_structure.AASequence
   */
  public void generateFile() throws IOException {
    List<String> line = new ArrayList<String>();
    List<String> line2 = new ArrayList<String>();
    String tempLine;
    int lines, rest;
    (new File(user.getMail() + "_results")).mkdirs();
    FileWriter file = new FileWriter(user.getMail() + "_results\\fasta_output.txt");
    FileWriter file2 = new FileWriter(user.getMail() + "_results\\report_fasta.txt");
    BufferedWriter bw = new BufferedWriter(file);
    BufferedWriter bw2 = new BufferedWriter(file2);
    line2.add("UniProt");
    line2.add("Enzyme");
    line2.add("Organism");
    line2.add("Source");
    line2.add("Found_using_Brenda");
    TableWriter.write(line2, "\t", bw2);
    for (int i = 0; i < result.size(); i++) {
      if (((Protein) proteins.get(i)).getUniprot() == "") {
      } else {
        lines = result.get(i).getNumberOfAminoacids() / 80;
        rest = result.get(i).getNumberOfAminoacids() % 80;
        line.clear();
        line2.clear();
        tempLine = ((Protein) proteins.get(i)).getUniprot();
        line.add(">" + tempLine);
        line2.add(tempLine);
        tempLine = ((Protein) proteins.get(i)).getEnzyme().getRecommended_name();
        line.add(tempLine);
        line2.add(tempLine);
        tempLine = ((Protein) proteins.get(i)).getOrganism().getName();
        line.add(tempLine);
        line2.add(tempLine);
        tempLine = result.get(i).getSource();
        line2.add(tempLine);
        if(result.get(i).getNumberOfAminoacids() == 0){
          line2.add("FALSE");
        }
        else{
          line2.add("TRUE");
          line.add("Source: Brenda->"+tempLine);
        }
        TableWriter.write(line, "|", bw);
        TableWriter.write(line2, "\t", bw2);
        line.clear();
        for (int j = 0; j < lines; j++) {
          line.add(result.get(i).getSequence().substring(j * 80, (j+1) * 80 ));
          TableWriter.write(line, "\t", bw);
          line.clear();
        }
        if (rest != 0) {
          line.add(result.get(i).getSequence().substring(lines * 80, lines * 80 + rest));
          TableWriter.write(line, "\t", bw);
          line.clear();
        }
        bw.newLine();
        bw.newLine();
      }
    }
    bw.close();
    bw2.close();
  }
}
