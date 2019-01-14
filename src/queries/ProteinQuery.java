package queries;

import attributes.enzyme_estrucuture.ECNumber;
import attributes.organism_related_information.Organism;
import client.SoapClient;
import client.User;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProteinQuery implements Query {

  private User user;
  private SoapClient client;
  private List<Protein> proteins;
  private Organism organism;
  private ParserAnswer parserAnswer;

  public ProteinQuery(User aUser){
    user = aUser;
    client = new SoapClient(user);
    proteins = new ArrayList<Protein>();
    parserAnswer = new ParserAnswer();
  }

  public List<Protein> getProteins(Enzyme enzyme) throws Exception{
    Protein protein;
    proteins.clear();
    client.makeCall();
    ECNumber ec = enzyme.getEC();
    organism = new Organism();
    String result = client.getResult(","+ec.getParameter(), organism.getMethod());
    List<HashMap<String, String>> results = parserAnswer.getResult(result);
    int i = 1;
    for(HashMap<String, String> ans:results){
      protein = new Protein(enzyme, new Organism( ans.get("organism") ), ans.get("sequenceCode"));
      proteins.add(protein);
      i++;
    }
    return proteins;
  }

  public List<Protein> getProteins(){
    return proteins;
  }

}
