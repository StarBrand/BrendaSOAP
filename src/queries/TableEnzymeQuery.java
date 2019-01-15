package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Enzyme;
import java.util.ArrayList;
import java.util.List;

public class TableEnzymeQuery implements Query {

  private SoapClient client;
  private User user;
  private Enzyme enzyme;
  private ArrayList<Attribute> attributes;

  public TableEnzymeQuery(Enzyme aEnzyme, User aUser){
    enzyme = aEnzyme;
    user = aUser;
    client = new SoapClient(user);
    attributes = new ArrayList<Attribute>();
  }

  public void setEntities(Entity... entity) {

  }

  public void addAttributes(Attribute... attribute) {

  }

  public int numberOfAttributes() {
    return 0;
  }

  public List<?> getResult() throws Exception {
    return null;
  }
}