package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
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

  @Override
  public void setAttributes(Attribute... attributes) {}

  @Override
  public List<String> getAnswer() throws Exception {
    return null;
  }

  @Override
  public int numberOfAttibutes() {
    return 0;
  }
}
