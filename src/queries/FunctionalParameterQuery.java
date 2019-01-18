package queries;

import attributes.Attribute;
import attributes.NumericalAttribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionalParameterQuery implements Query{

  private SoapClient client;
  private User user;
  private ParserAnswer parserAnswer;
  private List<Entity> entities;
  private List<Attribute> attributes;

  public FunctionalParameterQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    parserAnswer = new ParserAnswer();
    entities = new ArrayList<Entity>();
    attributes = new ArrayList<Attribute>();
  }

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
        results = parserAnswer.getResult(result);
        attribute = (NumericalAttribute) attribute;
        // the attribute should do something with that
        entity.addAttributes(attribute);
      }
      out.add(entity);
    }
    this.entities = out;
    return entities;
  }
}
