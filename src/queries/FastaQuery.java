package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Enzyme;
import java.util.ArrayList;
import java.util.List;

public class FastaQuery implements Query {

  private SoapClient client;
  private User user;
  private Enzyme enzyme;
  private List<Attribute> theAttibutes;

  public FastaQuery(Enzyme aEnzyme, User aUser){
    enzyme = aEnzyme;
    user = aUser;
    client = new SoapClient(user);
    theAttibutes = new ArrayList<Attribute>();
  }

  public void setAttributes(Attribute... attributes) {}

  public List<String> getAnswer() throws Exception {
    return null;
  }

  public int numberOfAttibutes() {
    return 0;
  }

  public void setEntity(Entity entity) {

  }

  public void setEntities(Entity... entity) {

  }

  public void addAttributes(Attribute... attribute) {

  }

  public int numberOfAttributes() {
    return 0;
  }

  public List<?> getResult() {
    return null;
  }
}
