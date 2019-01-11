package attributes_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

import attributes.enzyme_estrucuture.ECNumber;
import org.junit.Before;
import org.junit.Test;

public class EnzymeTest {

  private ECNumber ec1;
  private ECNumber ec2;

  @Before
  public void SetUp() throws Exception{
    ec1 = new ECNumber(1,1,1,1);
    ec2 = new ECNumber("1.1.1.1");
  }

  @Test
  public void toStringTest(){
    assertEquals("1.1.1.1", ec1.toString());
    assertEquals("1.1.1.1", ec2.toString());
    assertEquals(ec1.toString(), ec2.toString());
  }

  @Test
  public void equalTest(){
    assertEquals(ec1, ec2);
    assertNotSame(new Object(), ec1);
    assertNotSame(new Object(), ec2);
  }

}
