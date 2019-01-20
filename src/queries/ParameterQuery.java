package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
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
  private List<Entity> entities;
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
    entities = new ArrayList<Entity>();
    attributes = new ArrayList<Attribute>();
  }

  /**
   * Set a list or entities
   *
   * @param entity
   */
  public void setEntities(Entity... entity) {
    for (Entity e:entity){
      entities.add(e);
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
    List<Entity> out = new ArrayList<Entity>();
    String result;
    List<HashMap<String, String>> results;
    client.makeCall();
    for(Entity entity:entities){
      for(Attribute attribute:attributes){
        result = client.getResult(entity.getParameter(), attribute.getMethod());
        if (!result.equals("")) {
          results = parserAnswer.getResult(result);
          for (HashMap<String, String> observation : results) {
            attribute.setAttribute(observation);
          }
          entity.addAttributes(attribute);
        }
        else{
          entity.addAttributes(attribute);
        }
      }
      out.add(entity);
    }
    this.entities = out;
    return entities;
  }
}
