package queries;

import attributes.APACitation;
import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * APAQuery class, makes a query to fill the apa
 * ({@link attributes.Attribute, attributes.APACitation})
 * in a {@Link entities.Literature} object when the
 * brenda code references, the ec Number of the enzyme and
 * the organism in which the reference is, is given
 *
 * @author Juan Saez Hidalgo
 *
 * @see attributes.Attribute
 * @see attributes.APACitation
 * @see entities.Literature
 */
public class APAQuery implements Query {

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
  public APAQuery(User user, String enzyme, int brenda_reference) {
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
    List<Object> out = new ArrayList<Object>();
    String pages;
    int volume, fp, lp, year;
    client.makeCall();
    String result = client.getResult(
        "ecNumber*" + enzyme + "#reference*" + brenda, "getReference"
    );
    APACitation apa = new APACitation();
    if (result.equals("")) {
      out.add(0);
    } else {
      HashMap<String, String> results = parserAnswer.getResult(result).get(0);
      apa.setAttribute(results);
      out.add(Integer.valueOf(results.get("pubmedId")));
    }
    out.add(apa);
    return out;
  }
}