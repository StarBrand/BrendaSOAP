package queries;

import attributes.APACitation;
import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APAQuery implements Query {

  private String enzyme;
  private String organism;
  private int brenda;
  private SoapClient client;
  private User user;
  private ParserAnswer parserAnswer;


  public APAQuery(User user, String enzyme, String organism, int brenda_reference) {
    this.enzyme = enzyme;
    this.organism = organism;
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
        "ecNumber*" + enzyme + "#organism*" + organism + "#reference*" + brenda, "getReference"
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
