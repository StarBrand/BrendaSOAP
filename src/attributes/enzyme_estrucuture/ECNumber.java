package attributes.enzyme_estrucuture;

import attributes.Attribute;
import entities.Literature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ECNumber implements Attribute {

  private int digit1;
  private int digit2;
  private int digit3;
  private int digit4;
  private String ec_code;

  public ECNumber(String ec){
    ec_code = ec;
    decodeEC();
  }

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

  public List<Literature> getReferences() {
    return null;
  }

  public void setReferences(List<Literature> references) {  }

  public void addReference(Literature reference) { }

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
}
