package entities;

import attributes.Attribute;
import attributes.enzyme_estrucuture.ECNumber;
import client.DefaultUser;
import client.User;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import queries.FastQuery;
import queries.Query;
import queries.RecommendedNameQuery;
import queries.SystematicNameQuery;

public class Enzyme implements Entity{

  private ECNumber ec;
  private String recommended_name;
  private String systematic_name;
  private FastQuery query;
  private List<Attribute> attributes;
  private User user = new DefaultUser();

  public Enzyme(ECNumber ec_code) throws Exception{
    ec = ec_code;
    attributes = new ArrayList<Attribute>();
    fill();
  }

  public Enzyme(int ec1, int ec2, int ec3, int ec4) throws Exception{
    ec = new ECNumber(ec1, ec2, ec3, ec4);
    attributes = new ArrayList<Attribute>();
    fill();
  }

  public Enzyme(String input) throws Exception{
    ec = new ECNumber(input);
    attributes = new ArrayList<Attribute>();
    fill();
  }

  private void fill() throws Exception{
    query = new SystematicNameQuery(user);
    query.setAttributes(ec);
    systematic_name = query.getAnswer().get(0);
    query = new RecommendedNameQuery(user);
    query.setAttributes(ec);
    recommended_name = query.getAnswer().get(0);
  }

  public String getRecommended_name(){
    return recommended_name;
  }

  public String getSystematic_name(){
    return systematic_name;
  }

  public ECNumber getEC(){
    return ec;
  }

  @Override
  public boolean equals(Object anotherOne){
    boolean ans;
    if (anotherOne instanceof Enzyme) {
      ans = true;
      ans &= ec.equals( ((Enzyme)anotherOne).getEC() );
      ans &= systematic_name.equals( ((Enzyme)anotherOne).getSystematic_name() );
      ans &= recommended_name.equals( ((Enzyme)anotherOne).getRecommended_name() );
    }
    else {
      ans = false;
    }
    return ans;
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }
}
