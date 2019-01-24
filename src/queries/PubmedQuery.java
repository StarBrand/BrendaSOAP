package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PubmedQuery class, makes a query to fill
 * the parameter pubmed in {@link entities.Literature}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Literature
 */
public class PubmedQuery implements Query {

  private String enzyme;
  private int brenda;
  private SoapClient client;
  private User user;
  private ParserAnswer parserAnswer;


  /**
   * The constructor given a Brenda user, the ecNumber of the enzyme
   * in which the reference to look for is, and the
   * Brenda reference code
   *
   * @param user              Brenda user
   * @param enzyme            the ecNumber of the enzyme
   * @param brenda_reference  the brenda reference code
   */
  public PubmedQuery(User user, String enzyme, int brenda_reference) {
    this.enzyme = enzyme;
    this.brenda = brenda_reference;
    this.user = user;
    client = new SoapClient(user);
    parserAnswer = new ParserAnswer();
  }


  public void setEntities(Entity... entity) {

  }

  public void addAttributes(Attribute... attribute) {

  }

  public int numberOfAttributes() {
    return 0;
  }

  public List<?> getResult() throws Exception {
    List<Integer> out = new ArrayList<Integer>();
    client.makeCall();
    String result = client.getResult(
        "ecNumber*" + enzyme + "#reference*" + brenda, "getReference"
    );
    if (result.equals("")){
      out.add(0);
    }
    else{
      HashMap<String, String> results = parserAnswer.getResult(result).get(0);
      try{
        out.add(Integer.valueOf(Integer.valueOf(results.get("pubmedId"))));
      } catch (Exception exception){
        out.add(0);
      }
    }
    return out;
  }
}
