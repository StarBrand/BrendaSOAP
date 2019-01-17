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

public class ProteinQuery implements Query {

  private User user;
  private SoapClient client;
  private List<Enzyme> enzymes;
  private List<Protein> proteins;
  private Organism organism;
  private ParserAnswer parserAnswer;

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
    Protein protein;
    Literature reference;
    client.makeCall();
    String result;
    for (Enzyme e:enzymes){
      ECNumber ec = e.getEC();
      organism = new Organism();
      result = client.getResult(ec.getParameter(), organism.getMethod());
      List<HashMap<String, String>> results = parserAnswer.getResult(result);
      for(HashMap<String, String> ans:results){
        organism.setAttribute(ans);
        protein = new Protein(e, organism, ans.get("sequenceCode"));
        for (String brenda_reference:ans.get("literature").split(", ")){
          try {
            reference = new Literature(Integer.valueOf(brenda_reference));
            protein.getOrganism().addReference(reference);
          } catch (Exception ex) {

          }
        }
        proteins.add(protein);
      }
    }
    return proteins;
  }
}
