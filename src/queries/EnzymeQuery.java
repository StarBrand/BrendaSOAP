package queries;

import client.SoapClient;
import client.User;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * EnzymeQuery class, does a single query
 * for every {@Link entities.Enzyme} in a
 * list of {@Link entities.Enzyme}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Enzyme
 * @see entities.Protein
 */
public class EnzymeQuery {

  private User user;
  private SoapClient client;
  private List<Protein> proteins;
  private List<Enzyme> enzymes;
  private HashMap<Enzyme, String> queries;

  /**
   * Constructor
   *
   * @param user      A valid Brenda account
   * @param proteins  The list og proteins
   */
  public EnzymeQuery(User user, List<Protein> proteins){
    this.user = user;
    this.client = new SoapClient(user);
    this.proteins = proteins;
    this.enzymes = new ArrayList<Enzyme>();
    this.queries = new HashMap<Enzyme, String>();
  }

  /**
   * Gets the list of enzyme on the
   * list of Protein
   *
   * @return The list of Protein
   */
  public List<Enzyme> getEnzymes() {
    for(Protein protein:proteins){
      if(!enzymes.contains(protein.getEnzyme())){
        enzymes.add(protein.getEnzyme());
      }
    }
    return enzymes;
  }

  /**
   * Realize one single query for enzyme on enzymes
   * and then, assigns the result to the especific
   * enzyme as a key.
   *
   * @param method The method of the query
   * @return A HashMap with enzyme and results
   * @throws Exception The client exception
   */
  public HashMap<Enzyme, String> getQueries(String method) throws Exception {
    for (Enzyme enzyme:enzymes){
      client.makeCall();
      queries.put(
          enzyme,
          client.getResult(enzyme.getParameter(), method)
      );
    }
    return queries;
  }

  /**
   * Return the query according to the enzyme and extra parameters
   *
   * @param enzyme      The enzyme
   * @param parameters  Any extra parameter in format:
   *                    <name>*<attribute>
   * @return  The specific query for enzyme
   */
  public String getQuery(Enzyme enzyme, String... parameters) {
    String out = "";
    String query = this.queries.get(enzyme);
    String[] queries = query.split("!");
    for(String observation:queries){
      boolean cond = true;
      for(String parameter:parameters) {
        cond &= observation.contains(parameter);
      }
      if(cond){
        out += observation + "!";
      }
    }
    return out;
  }

}