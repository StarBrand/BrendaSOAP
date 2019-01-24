package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
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
    client.makeCall();
    for(Protein protein:proteins){
      Protein new_protein = new Protein(
          protein.getEnzyme(),
          protein.getOrganism(),
          protein.getUniprot()
      );
      for(Attribute attribute:attributes){
        Attribute new_attribute = (Attribute) attribute.clone();
        result = client.getResult(new_protein.getParameter(), new_attribute.getMethod());
        if (!result.equals("")) {
          results = parserAnswer.getResult(result);
          for (HashMap<String, String> observation : results) {
            new_attribute.setAttribute(observation);
          }
          new_protein.addAttributes(new_attribute);
        }
        else{
          new_protein.addAttributes(new_attribute);
        }
      }
      out.add(new_protein);
    }
    this.proteins = out;
    return proteins;
  }
}
