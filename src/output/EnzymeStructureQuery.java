package output;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;
import queries.Query;

/**
 * EnzymeStructureQuery class, is used as a
 * Template for the queries that look for
 * enzyme structure parameters
 *
 * @author Juan Saez Hidalgo
 */
public abstract class EnzymeStructureQuery implements Query {

  private SoapClient client;
  protected User user;

  public EnzymeStructureQuery(User user){
    this.user = user;
    client = new SoapClient(user);
  }

  public void addAttributes(Attribute... attribute) {}

  public int numberOfAttributes() {
    return 0;
  }

  public List<String> getResult(boolean organism_as_parameter, boolean uniprot_as_parameter, Attribute attribute, List<Entity> proteins) throws Exception {
    List<String> result = new ArrayList<String>();
    client.makeCall();
    for (Entity protein : proteins) {
      if (((Protein) protein).getUniprot().equals("") && uniprot_as_parameter) {
        result.add("");
      } else {
        String parameter = "";
        parameter += ((Protein) protein).getEnzyme().getParameter();
        if (uniprot_as_parameter)
          parameter += "#firstAccessionCode*" + ((Protein) protein).getUniprot();
        if (organism_as_parameter)
          parameter += "#organism*" + ((Protein) protein).getOrganism().getName();
        result.add(client.getResult(parameter, attribute.getMethod()));
      }
    }
    return result;
  }
}
