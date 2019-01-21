package attributes.enzyme_structure;

import attributes.Attribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ECNumber class defined as the EC number format
 * of types of enzymes with many formats and constructor
 * for easy implementation
 *
 * @author Juan Saez Hidalgo
 */
public class ECNumber implements Attribute {

  private int digit1;
  private int digit2;
  private int digit3;
  private int digit4;
  private String ec_code;

  /**
   * A constructor given a String with EC Number format: X.X.X.X
   *
   * @param ec the string with the EC number
   */
  public ECNumber(String ec){
    ec_code = ec;
    decodeEC();
  }

  /**
   * A constructor given four digits in the EC number order
   *
   * @param ec1 the first digit
   * @param ec2 the second digit
   * @param ec3 the third digit
   * @param ec4 the four digit
   */
  public ECNumber(int ec1, int ec2, int ec3, int ec4){
    digit1 = ec1;
    digit2 = ec2;
    digit3 = ec3;
    digit4 = ec4;
    ec_code = String.valueOf(ec1) + "." + String.valueOf(ec2) + "." + String.valueOf(ec3) + "." + String.valueOf(ec4);
  }

  private void decodeEC(){
    String[] ecs = ec_code.split("\\.");
    digit1 = Integer.valueOf(ecs[0]);
    digit2 = Integer.valueOf(ecs[1]);
    digit3 = Integer.valueOf(ecs[2]);
    digit4 = Integer.valueOf(ecs[3]);
  }

  @Override
  public String toString(){
    return ec_code;
  }

  @Override
  public boolean equals(Object anotherOne){
    if (anotherOne instanceof ECNumber){
      return ec_code.equals(anotherOne.toString());
    }
    else{
      return false;
    }
  }

  /**
   * EC NUmber doesn't have reference
   *
   * @return null
   */
  public List<Literature> getReferences() {
    return null;
  }

  /**
   * the EC Number doesn't have commentaries
   *
   * @param commentary the commentary of an Attribute
   */
  public void setCommentary(String commentary) {

  }

  public String getCommentary() {
    return null;
  }

  public void setReferences(List<Literature> references) {  }

  public void addReference(Literature... reference) { }

  public String getMethod() {
    return "getEcNumber";
  }

  public String getParameter() {
    return "ecNumber*" + ec_code;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"ecNumber", "commentary"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {

  }

  public HashMap<String, String> getColumnsForTable() {
    HashMap<String, String> out = new HashMap<String, String>();
    out.put("EC Number", ec_code);
    return out;
  }
}
