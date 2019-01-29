package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.List;

/**
 * UniprotQuery class, does a query of the proteins
 * through its Uniprot code
 *
 * @author Juan Saez Hidalgo
 */
public class UniprotQuery implements Query{

  private User user;
  private String file_name;
  private SoapClient client;
  private ParserAnswer parser;

  /**
   * Contructor given the Brenda User and the name
   * of the file with the Uniprot codes
   *
   * @param user      Brenda User
   * @param file_name The name of file with the codes
   */
  public UniprotQuery(User user, String file_name){
    this.user = user;
    this.file_name = file_name;
    client = new SoapClient(user);
    parser = new ParserAnswer();
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
