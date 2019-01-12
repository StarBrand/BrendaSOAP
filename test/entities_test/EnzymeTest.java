package entities_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

import attributes.enzyme_estrucuture.ECNumber;
import entities.Enzyme;
import org.junit.Before;
import org.junit.Test;

public class EnzymeTest {
  private Enzyme enzyme1;
  private Enzyme enzyme2;
  private Enzyme enzyme3;

  @Before
  public void SetUp() throws Exception{
    enzyme1 = new Enzyme(1,1,1,1);
    enzyme2 = new Enzyme("1.1.1.1");
    enzyme3 = new Enzyme(new ECNumber("1.1.1.1"));
  }

  @Test
  public void enzymeConstructorTest() {
    assertNotNull(enzyme1.getEC());
    assertNotNull(enzyme1.getSystematic_name());
    assertNotNull(enzyme1.getRecommended_name());
    assertNotNull(enzyme2.getEC());
    assertNotNull(enzyme2.getSystematic_name());
    assertNotNull(enzyme2.getRecommended_name());
    assertNotNull(enzyme3.getEC());
    assertNotNull(enzyme3.getSystematic_name());
    assertNotNull(enzyme3.getRecommended_name());
  }

  @Test
  public void enzymeEqualsTest(){
    assertEquals(enzyme1, enzyme2);
    assertEquals(enzyme2, enzyme3);
    assertEquals(enzyme3, enzyme1);
    assertNotSame(new Object(), enzyme1);
  }

}
