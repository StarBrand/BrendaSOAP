package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ParameterQuery, fill the parameters of a list
 * of empty (MUST BE EMPTY) {@Link attributes.Attribute}
 * and add this to a list of {@Link entities.Protein}
 * respectively
 *
 * @author Juan Saez Hidalgo
 * @see attributes.Attribute
 * @see entities.Protein
 */
public class ParameterQuery implements Query{

  private SoapClient client;
  private User user;
  private ParserAnswer parserAnswer;
  private List<Protein> proteins;
  private List<Attribute> attributes;

  /**
   * The constructor given a Brenda USer
   *
   * @param user Brenda User
   */
  public ParameterQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    parserAnswer = new ParserAnswer();
    proteins = new ArrayList<Protein>();
    attributes = new ArrayList<Attribute>();
  }

  /**
   * Set a list or entities
   *
   * @param entity
   */
  public void setEntities(Entity... entity) {
    for (Entity e:entity){
      proteins.add((Protein) e);
    }
  }

  public void addAttributes(Attribute... attribute) {
    for (Attribute a:attribute){
      attributes.add(a);
    }
  }

  public int numberOfAttributes() {
    return attributes.size();
  }

  public List<?> getResult() throws Exception {
    List<Protein> out = new ArrayList<Protein>();
    String result;
    List<HashMap<String, String>> results;
    HashMap<Enzyme, List<Protein>> enzyme = group(proteins);
    client.makeCall();
    for(Enzyme anEnzyme:enzyme.keySet()) {
      out.addAll(enzyme.get(anEnzyme));
      for (Attribute attribute : attributes) {
        result = client.getResult(anEnzyme.getParameter(), attribute.getMethod());
        if(!result.equals("")) {
          results = parserAnswer.getResult(result);
          for (HashMap<String, String> observation:results){
            for (Protein protein : enzyme.get(anEnzyme)) {
              int i = out.indexOf(protein);
              Attribute new_attribute = (Attribute) attribute.clone();
              if(observation.get("organism").equals(protein.getOrganism().getName())) {
                new_attribute.setAttribute(observation);
                out.get(i).addAttributes(new_attribute);
              }
            }
          }
        }
      }
    }
    this.proteins = out;
    return proteins;
  }

  private static HashMap<Enzyme, List<Protein>> group(List<Protein> proteins){
    HashMap<Enzyme, List<Protein>> out = new HashMap<Enzyme, List<Protein>>();
    for(Protein protein:proteins){
      Enzyme itsEnzyme = protein.getEnzyme();
      if(out.containsKey(itsEnzyme)){
        out.get(itsEnzyme).add(protein);
      }
      else{
        List<Protein> proteinList = new ArrayList<Protein>();
        proteinList.add(protein);
        out.put(itsEnzyme, proteinList);
      }
    }
    return out;
  }
}
