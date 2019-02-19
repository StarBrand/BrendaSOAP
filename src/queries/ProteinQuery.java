package queries;

import attributes.Attribute;
import attributes.enzyme_structure.ECNumber;
import attributes.organism_related_information.Organism;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ProteinQuery class,
 * generates a list of {@Link entities.Protein} given a
 * list of {@Link entities.Enzyme}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 * @see entities.Enzyme
 */
public class ProteinQuery implements Query {

  private User user;
  private SoapClient client;
  private List<Enzyme> enzymes;
  private List<Protein> proteins;
  private ParserAnswer parserAnswer;

  /**
   * Constructor given a Brenda User
   *
   * @param user Brenda User
   */
  public ProteinQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    enzymes = new ArrayList<Enzyme>();
    proteins = new ArrayList<Protein>();
    parserAnswer = new ParserAnswer();
  }

  public void setEntities(Entity... entity) {
    try {
      for (Entity e : entity) {
        enzymes.add((Enzyme) e);
      }
    } catch (Exception e){

    }
  }

  public void addAttributes(Attribute... attribute) {

  }

  public int numberOfAttributes() {
    return 0;
  }

  public List<?> getResult() throws Exception {
    Literature reference;
    client.makeCall();
    String result;
    for (Enzyme enzyme:enzymes){
      ECNumber ec = enzyme.getEC();
      Organism organism = new Organism();
      result = client.getResult(ec.getParameter(), organism.getMethod());
      if(!result.equals("")) {
        List<HashMap<String, String>> results = parserAnswer.getResult(result);
        for (HashMap<String, String> ans : results) {
          Organism organism_found = new Organism();
          organism_found.setAttribute(ans);
          Protein protein = new Protein(enzyme, organism_found, ans.get("sequenceCode"));
          proteins.add(protein);
        }
      }
    }
    return proteins;
  }
}
