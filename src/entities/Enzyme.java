package entities;

import attributes.Attribute;
import attributes.enzyme_structure.ECNumber;
import client.User;
import java.util.ArrayList;
import java.util.List;
import queries.FastQuery;
import queries.RecommendedNameQuery;
import queries.SystematicNameQuery;

/**
 * The Enzyme class, define as the type of enzyme according to the {@Link attributes.enzyme_structure.ECNumber} systematic name
 *
 * @author Juan Saez Hidalgo
 * @see ECNumber
 */
public class Enzyme implements Entity{

  private ECNumber ec;
  private String recommended_name;
  private String systematic_name;
  private FastQuery query;
  private List<Attribute> attributes;
  private User user;

  /**
   * An Enzyme constructor given the {@Link attributes.ECNumber}
   *
   * @param ec_code ECNumber
   * @param user    Brenda user
   * @throws Exception the SOAPquery exceptsion
   * @see ECNumber
   */
  public Enzyme(ECNumber ec_code, User user) throws Exception{
    ec = ec_code;
    attributes = new ArrayList<Attribute>();
    this.user = user;
    fill();
  }

  /**
   * An Enzyme constructor given the separate digits of the {@Link attributes.enzyme_structure.EC Number}
   *
   * @param ec1   first digit of the EC Number
   * @param ec2   second digit of the EC Number
   * @param ec3   third digit of the EC Number
   * @param ec4   fourth digit of the EC Number
   * @param user  Brenda user
   * @throws Exception the SOAPquery exception
   * @see ECNumber
   */
  public Enzyme(int ec1, int ec2, int ec3, int ec4, User user) throws Exception{
    ec = new ECNumber(ec1, ec2, ec3, ec4);
    attributes = new ArrayList<Attribute>();
    this.user = user;
    fill();
  }

  /**
   * An Enzyme contsructor given a String in {@Link attributes.enzyme_structure.EC Number} format
   *
   * @param input the EC Number format string
   * @param user  Brenda user
   * @throws Exception the SOAPQuery exception
   * @see ECNumber
   */
  public Enzyme(String input, User user) throws Exception{
    ec = new ECNumber(input);
    attributes = new ArrayList<Attribute>();
    this.user = user;
    fill();
  }

  private void fill() throws Exception{
    query = new SystematicNameQuery(user);
    query.addAttributes(ec);
    String result = (String) query.getResult().get(0);
    try {
      result = result.replace("&gt;", ">");
    } catch(NullPointerException e) {
      result = "";
    }
    systematic_name = result;
    query = new RecommendedNameQuery(user);
    query.addAttributes(ec);
    result = (String) query.getResult().get(0);
    try {
      result = result.replace("&gt;", ">");
    } catch(NullPointerException e) {
      result = "";
    }
    recommended_name = result;
  }

  /**
   * Gets the recommended name of an enzyme
   *
   * @return the recommended name
   */
  public String getRecommended_name(){
    return recommended_name;
  }

  /**
   * Gets the systematic name of an enzyme
   *
   * @return the systematic name
   */
  public String getSystematic_name(){
    return systematic_name;
  }

  /**
   * Gets the {@Link attributes.enzyme_structure.ECNumber} of an enzyme type
   *
   * @return the EC Number
   * @see ECNumber
   */
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

  public List <Attribute>getAttribute() {
    return attributes;
  }

  public void addAttributes(Attribute... attribute) {
    for(Attribute a:attribute){
      attributes.add(a);
    }
  }

  public String getParameter() {
    return "ecNumber*"+ec.toString();
  }

  @Override
  public String toString(){
    return "EC Number: "+ec.toString()+"/Recommended name: "+recommended_name+"/Systematic Name: "+systematic_name;
  }

  @Override
  public Object clone(){
    try{
      Enzyme clone = (Enzyme) super.clone();
      return clone;
    } catch (Exception e){
      return null;
    }
  }
}
