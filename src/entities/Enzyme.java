package entities;

import attributes.Attribute;
import attributes.enzyme_structure.ECNumber;
import client.DefaultUser;
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
  private User user = new DefaultUser();

  /**
   * An Enzyme constructor given the {@Link attributes.ECNumber}
   *
   * @param ec_code
   * @throws Exception the SOAPquery exceptsion
   * @see ECNumber
   */
  public Enzyme(ECNumber ec_code) throws Exception{
    ec = ec_code;
    attributes = new ArrayList<Attribute>();
    fill();
  }

  /**
   * An Enzyme constructor given the separate digits of the {@Link attributes.enzyme_structure.EC Number}
   *
   * @param ec1 first digit of the EC Number
   * @param ec2 second digit of the EC Number
   * @param ec3 third digit of the EC Number
   * @param ec4 fourth digit of the EC Number
   * @throws Exception the SOAPquery exception
   * @see ECNumber
   */
  public Enzyme(int ec1, int ec2, int ec3, int ec4) throws Exception{
    ec = new ECNumber(ec1, ec2, ec3, ec4);
    attributes = new ArrayList<Attribute>();
    fill();
  }

  /**
   * An Enzyme contsructor given a String in {@Link attributes.enzyme_structure.EC Number} format
   *
   * @param input the EC Number format string
   * @throws Exception the SOAPQuery exception
   * @see ECNumber
   */
  public Enzyme(String input) throws Exception{
    ec = new ECNumber(input);
    attributes = new ArrayList<Attribute>();
    fill();
  }

  private void fill() throws Exception{
    query = new SystematicNameQuery(user);
    query.addAttributes(ec);
    systematic_name = (String) query.getResult().get(0);
    query = new RecommendedNameQuery(user);
    query.addAttributes(ec);
    recommended_name = (String) query.getResult().get(0);
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

  public List<Attribute> getAttribute() {
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
}
