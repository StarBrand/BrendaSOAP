package queries;

import attributes.Attribute;
import attributes.enzyme_estrucuture.ECNumber;
import attributes.organism_related_information.Organism;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProteinQuery implements Query {

  private User user;
  private SoapClient client;
  private List<Enzyme> enzymes;
  private List<Protein> proteins;
  private Organism organism;
  private ParserAnswer parserAnswer;

  public ProteinQuery(User aUser){
    user = aUser;
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
    Protein protein;
    client.makeCall();
    String result;
    for (Enzyme e:enzymes){
      ECNumber ec = e.getEC();
      organism = new Organism();
      result = client.getResult(ec.getParameter(), organism.getMethod());
      List<HashMap<String, String>> results = parserAnswer.getResult(result);
      for(HashMap<String, String> ans:results){
        protein = new Protein(e, new Organism( ans.get("organism") ), ans.get("sequenceCode"));
        proteins.add(protein);
      }
    }
    return proteins;
  }
}
