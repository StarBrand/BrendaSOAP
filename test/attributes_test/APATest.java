package attributes_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

import attributes.APAcitation;
import entities.Organism;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class APATest {
  private APAcitation apa1;
  private APAcitation apa2;
  private APAcitation apa3;
  private APAcitation apa4;
  private APAcitation apa5;
  private APAcitation apa6;

  @Before
  public void SetUp(){
    apa1 = new APAcitation("author", "title", "journal", 1, 10, 12, 1994);
    apa2 = new APAcitation("title", "journal", 1, 10, 12, 1994, "author");
    apa3 = new APAcitation("title", "journal", 1, 10, 12, 1994, "author1", "author2");
    apa4 = new APAcitation("author", "title", "journal", 1, 10, 1994);
    apa5 = new APAcitation("title", "journal", 1, 10, 12, 1994);
    apa6 = new APAcitation("title", "journal", 1, 10, 1994, "author");
  }

  @Test
  public void apaConstructorTest(){
    assertNotNull(apa4.getLastPage());
    assertNotNull(apa5.getAuthors());
    assertEquals(0,apa5.getAuthors().size());
  }

  @Test
  public void apaEqualTest(){
    assertEquals(apa1, apa2);
    assertEquals(apa4, apa6);
    assertNotSame(apa1, apa3);
    assertNotSame(apa3, apa5);
  }

  @Test
  public void toStringTest(){
    String reference1 = "author: title. journal. (1994) 1, 10-12.";
    assertEquals(reference1,apa1.toString());
    String reference2 = "author1; author2: title. journal. (1994) 1, 10-12.";
    assertEquals(reference2,apa3.toString());
    String reference3 = ": title. journal. (1994) 1, 10-12.";
    assertEquals(reference3,apa5.toString());
    String reference4 = "author: title. journal. (1994) 1, 10.";
    assertEquals(reference4,apa4.toString());
  }

  @Test
  public void methodTest(){
    assertEquals("getReference", apa1.getMethod());
    assertEquals("getReference", apa2.getMethod());
    assertEquals("getReference", apa3.getMethod());
    assertEquals("getReference", apa4.getMethod());
    assertEquals("getReference", apa5.getMethod());
    assertEquals("getReference", apa6.getMethod());
  }

}
