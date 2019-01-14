package queries;

import attributes.Attribute;
import attributes.enzyme_estrucuture.ECNumber;
import client.SoapClient;
import client.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class FastQuery implements Query {

  private User user;
  protected SoapClient client;
  protected List<Attribute> theAttributes;
  private ParserAnswer parserAnswer;

  public FastQuery(User aUser){
    user = aUser;
    client = new SoapClient(user);
    theAttributes = new ArrayList<Attribute>();
    parserAnswer = new ParserAnswer();
  }

    public void setAttributes(Attribute... attributes) {
    for (Attribute ec:attributes){
      theAttributes.add(ec);
    }
  }

  public abstract List<String> getAnswer() throws Exception;

  /**
   * Standart getAnswer method
   * @param method a String that indicates the SOAP method
   * @return the property of the fast query
   * @throws Exception client Exception
   */
  public List<String> getAnswer(String method, String parameter) throws Exception{
    client.makeCall();
    int i = 1;
    ArrayList<String> answer = new ArrayList<String>();
    for (Attribute ec:theAttributes){
      String result = client.getResult(",ecNumber*"+((ECNumber) ec).toString(), method);
      List<HashMap<String, String>> parser = parserAnswer.getResult(result);
      answer.add(parser.get(0).get(parameter));
    }
    return answer;
  }

  public int numberOfAttibutes(){
    return theAttributes.size();
  }
}
