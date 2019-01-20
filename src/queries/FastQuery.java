package queries;

import attributes.Attribute;
import client.SoapClient;
import client.User;
import entities.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * FastQuey abstract class, queries that just require
 * a list of attributes
 *
 * @author Juan Saez Hidalgo
 */
public abstract class FastQuery implements Query {

  private User user;
  private SoapClient client;
  private List<Attribute> attributes;
  private ParserAnswer parserAnswer;

  /**
   * Constructor given a Brenda {@Link client.User}
   *
   * @param user Brenda User
   * @see client.User
   */
  public FastQuery(User user){
    this.user = user;
    client = new SoapClient(user);
    attributes = new ArrayList<Attribute>();
    parserAnswer = new ParserAnswer();
  }


  public void setEntities(Entity... entity) {

  }

  public void addAttributes(Attribute... attribute) {
    for (Attribute a:attribute){
      attributes.add(a);
    }
  }

  public int numberOfAttributes(){
    return attributes.size();
  }

  public abstract List<?> getResult() throws Exception;

  /**
   * Standart getAnswer method to be used
   * by subclasses
   *
   * @param method      a String that indicates the SOAP method
   * @return            the result of the fast query
   * @throws Exception  client Exception
   */
  public List<String> getResult(String method, String parameter) throws Exception{
    client.makeCall();
    ArrayList<String> answer = new ArrayList<String>();
    for (Attribute a:attributes){
      String result = client.getResult(a.getParameter(), method);
      List<HashMap<String, String>> parser = parserAnswer.getResult(result);
      answer.add(parser.get(0).get(parameter));
    }
    return answer;
  }
}