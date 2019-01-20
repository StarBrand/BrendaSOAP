package entities_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

import attributes.Attribute;
import attributes.enzyme_structure.ECNumber;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHRange;
import client.DefaultUser;
import entities.Enzyme;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;

public class EnzymeTest {
  private Enzyme enzyme1;
  private Enzyme enzyme2;
  private Enzyme enzyme3;
  private Attribute example1;
  private Attribute example2;

  @Before
  public void SetUp() throws Exception{
    enzyme1 = new Enzyme(1,1,1,1, new DefaultUser());
    enzyme2 = new Enzyme("1.1.1.1", new DefaultUser());
    enzyme3 = new Enzyme(new ECNumber("1.1.1.1"), new DefaultUser());
    example1 = new Km(10.0, "substrate", "", new Literature(11111));
    example2 = new PHRange(7.0, 7.2, "", new Literature(11111));
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
    assertNotSame(enzyme1, new Object());
  }

  @Test
  public void gettersTest(){
    assertEquals("alcohol:NAD+ oxidoreductase", enzyme1.getSystematic_name());
    assertEquals("alcohol dehydrogenase", enzyme1.getRecommended_name());
    assertEquals("1.1.1.1", enzyme1.getEC().toString());
    assertEquals("ecNumber*1.1.1.1", enzyme1.getParameter());
    assertEquals("EC Number: 1.1.1.1/Recommended name: alcohol dehydrogenase/Systematic Name: alcohol:NAD+ oxidoreductase", enzyme1.toString());
  }

  @Test
  public void attributesTest(){
    enzyme1.addAttributes(example1);
    assertEquals(example1, enzyme1.getAttribute().get(0));

    enzyme2.addAttributes(example1, example2);
    assertEquals(example1, enzyme2.getAttribute().get(0));
    assertEquals(example2, enzyme2.getAttribute().get(1));
  }



}
