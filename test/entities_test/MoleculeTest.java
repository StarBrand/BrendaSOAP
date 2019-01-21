package entities_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;

import attributes.Attribute;
import entities.Inhibitor;
import entities.Literature;
import entities.NullMolecule;
import entities.Product;
import entities.Substrate;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MoleculeTest {
  private Substrate substrate;
  private Inhibitor inhibitor;
  private Product product;
  private NullMolecule nullMolecule;
  private Attribute example;

  @Before
  public void SetUp(){
    substrate = new Substrate("(+)-2-methylcyclohexanone");
    inhibitor = new Inhibitor("4-androsten-3,17-dione");
    product = new Product("(rac)-3-methylcyclohexanone + NADH + H+");
    nullMolecule = new NullMolecule();
    example = new Attribute() {
      public List<Literature> getReferences() {
        return null;
      }
      public void setCommentary(String commentary) {
      }
      public String getCommentary() {
        return null;
      }
      public void setReferences(List<Literature> references) {
      }
      public void addReference(Literature... reference) {
      }
      public String getMethod() {
        return null;
      }
      public String getParameter() {
        return null;
      }
      public List<String> getColumns() {
        return null;
      }
      public void setAttribute(HashMap<String, String> resultOfQuery) {
      }

      public HashMap<String, String> getColumnsForTable() {
        return null;
      }
    };
  }

  @Test
  public void isSubstrateTest(){
    assertTrue(substrate.isSubstrate());
    assertFalse(inhibitor.isSubstrate());
    assertFalse(product.isSubstrate());
    assertFalse(nullMolecule.isSubstrate());
  }

  @Test
  public void isInhibitorTest(){
    assertFalse(substrate.isInhibitor());
    assertTrue(inhibitor.isInhibitor());
    assertFalse(product.isInhibitor());
    assertFalse(nullMolecule.isInhibitor());
  }

  @Test
  public void isProductTest(){
    assertFalse(substrate.isProduct());
    assertFalse(inhibitor.isProduct());
    assertTrue(product.isProduct());
    assertFalse(nullMolecule.isProduct());
  }

  @Test
  public void gettersTest(){
    assertNotNull(nullMolecule);
    assertEquals("(+)-2-methylcyclohexanone", substrate.getName());
    assertEquals("4-androsten-3,17-dione", inhibitor.getName());
    assertEquals("(rac)-3-methylcyclohexanone + NADH + H+", product.getName());
    assertEquals("", nullMolecule.getName());
    assertNotNull(nullMolecule.getName());
    assertEquals("substrate*(+)-2-methylcyclohexanone", substrate.getParameter());
    assertNotSame("inhibitor*(+)-2-methylcyclohexanone", substrate.getParameter());
    assertEquals("", nullMolecule.getParameter());
    assertNotNull(nullMolecule.getParameter());
    assertEquals("inhibitor*4-androsten-3,17-dione", inhibitor.getParameter());
    assertEquals("product*(rac)-3-methylcyclohexanone + NADH + H+", product.getParameter());
  }

  @Test
  public void attributesTest(){
    substrate.addAttributes(example);
    assertEquals(example, substrate.getAttribute().get(0));

    product.addAttributes(example, example);
    assertEquals(example, substrate.getAttribute().get(0));
  }

}