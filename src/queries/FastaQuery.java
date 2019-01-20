package queries;

import attributes.Attribute;
import attributes.enzyme_structure.AASequence;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Protein;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FastaQuery class, generate a .txt file
 * with a lis of AA sequence in FASTA format
 *
 * @author Juan Saez Hidalgo
 */
public class FastaQuery implements Query {

  private SoapClient client;
  private User user;
  private List<Entity> proteins;
  private List<Attribute> attributes;
  private AASequence aaSequence;
  private ParserAnswer parserAnswer;
  private List<AASequence> result;


  /**
   * FastaQuery constructor given a Brenda User
   *
   * @param user Brenda User
   */
  public FastaQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    proteins = new ArrayList<Entity>();
    attributes = new ArrayList<Attribute>();
    aaSequence = new AASequence();
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

  public void addAttributes(Attribute... attribute) {

  }

  public int numberOfAttributes() {
    return 0;
  }

  public List<?> getResult() throws Exception {
    String result;
    client.makeCall();
    this.result = new ArrayList<AASequence>();
    for (Entity protein:proteins){
      AASequence aaSequence = new AASequence();
      result = client.getResult(
          protein.getParameter() +
              "#firstAccessionCode*" +
              ((Protein) protein).getUniprot()
          ,aaSequence.getMethod());
      if (result.equals("")){
        this.result.add(aaSequence);
      }
      else{
        aaSequence.setAttribute(
            parserAnswer.getResult(result).get(0)
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
    String line = "";
    int lines, rest;
    FileWriter file = new FileWriter("fasta_output.txt");
    BufferedWriter bf = new BufferedWriter(file);
    for (int i = 0; i < result.size(); i++){
      lines = result.get(i).getNumberOfAminoacids() / 80;
      rest = result.get(i).getNumberOfAminoacids() % 80;
      line = ">";
      line += ((Protein) proteins.get(i)).getUniprot() + " ";
      line += ((Protein) proteins.get(i)).getEnzyme().getRecommended_name() + " ";
      line += ((Protein) proteins.get(i)).getOrganism().getName();
      bf.write(line);
      bf.newLine();
      line = "";
      for (int j = 0; j < lines; j++){
        line += result.get(i).getSequence().substring(j*80, (j * 80) + 79);
        bf.write(line);
        bf.newLine();
        line = "";
      }
      if (rest != 0){
        line += result.get(i).getSequence().substring(lines*80, lines*80 + rest - 1);
        bf.write(line);
        bf.newLine();
        line = "";
      }
      bf.newLine();
      bf.newLine();
    }
    line += ">";
    bf.write(line);
    bf.newLine();
    bf.close();
  }
}
