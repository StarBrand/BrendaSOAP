package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Enzyme;
import java.util.ArrayList;
import java.util.List;

public class TableEnzymeQuery implements Query {

  private SoapClient client;
  private User user;
  private Enzyme enzyme;
  private ArrayList<Attribute> theAttributes;

  public TableEnzymeQuery(Enzyme aEnzyme, User aUser){
    enzyme = aEnzyme;
    user = aUser;
    client = new SoapClient(user);
    theAttributes = new ArrayList<Attribute>();
  }

  public void setAttributes(Attribute... attributes) {

  }

  public List<String> getAnswer() {
    return null;
  }

  public int numberOfAttibutes() {
    return theAttributes.size();
  }
}
