package entities_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;


import attributes.APAcitation;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;

public class LiteratureTest {
  private Literature literature1;
  private Literature literature2;
  private Literature literature3;
  private APAcitation apa;

  @Before
  public void SetUp() throws Exception{
    apa = new APAcitation("author", "title", "journal", 1, 10, 1994);
    literature1 = new Literature(apa, 11111, 22222);
    literature2 = new Literature(apa, 11111, 22222);
    literature3 = new Literature(285564, "1.1.1.1", "Mus musculus");
  }

  @Test
  public void literatureConstructorTest() {
    assertNotNull(literature3.getAPA());
    assertNotNull(literature3.getPubmedID());
  }

  @Test
  public void literatureEqualsTest(){
    assertEquals(literature1, literature2);
    assertNotSame(literature1, new Object());
  }

  @Test
  public void toStringTest(){
    String reference = "<22222> author: title. journal. (1994) 1, 10. {Pubmed:11111}";
    assertEquals(reference, literature1.toString());
  }

}
