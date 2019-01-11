package queries;

import attributes.Attribute;
import attributes.enzyme_estrucuture.ECNumber;
import client.SoapClient;
import client.User;
import java.util.ArrayList;
import java.util.List;

public abstract class FastQuery implements Query {

  private User user;
  private SoapClient client;
  private ArrayList<Attribute> theAttributes;

  public FastQuery(User aUser){
    user = aUser;
    client = new SoapClient(user);
    theAttributes = new ArrayList<Attribute>();
  }

  @Override
  public void setAttributes(Attribute... attributes) {
    for (Attribute ec:attributes){
      theAttributes.add(ec);
    }
  }

  @Override
  public abstract List<String> getAnswer() throws Exception;

  /**
   * Standart getAnswer method
   * @param method a String that indicates the SOAP method
   * @return the property of the fast query
   * @throws Exception client Exception
   */
  public List<String> getAnswer(String method) throws Exception{
    client.makeCall();
    int i = 1;
    ArrayList<String> answer = new ArrayList<String>();
    for (Attribute ec:theAttributes){
      String result = client.getResult(",ecNumber*"+((ECNumber) ec).toString(), method);
      String[] parset_result = result.split("#");
      String[] clean_result = parset_result[1].split("\\*");
      answer.add(clean_result[1]);
    }
    return answer;
  }

  @Override
  public int numberOfAttibutes(){
    return theAttributes.size();
  }
}
