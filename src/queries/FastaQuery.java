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
  private List<Entity> proteins;
  private List<Attribute> attributes;

  public FastaQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    proteins = new ArrayList<Entity>();
    attributes = new ArrayList<Attribute>();
  }


  public void setEntities(Entity... entity) {
    for (Entity e:entity){
      proteins.add(e);
    }
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
