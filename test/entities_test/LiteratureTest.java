package entities_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertNull;


import attributes.APACitation;
import client.DefaultUser;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;

public class LiteratureTest {
  private Literature literature1;
  private Literature literature2;
  private Literature literature3;
  private Literature literature4;
  private Literature literature5;
  private APACitation apa;
  private APACitation nullApa;

  @Before
  public void SetUp() throws Exception{
    apa = new APACitation("author", "title", "journal", 1, 10, 1994);
    nullApa = new APACitation();
    literature1 = new Literature(11111, 22222, apa);
    literature2 = new Literature(11111, 22222, apa);
    literature3 = new Literature(285564);
    literature4 = new Literature(285564);
    literature5 = new Literature(285564, 2053480);
    literature3.fill("1.1.1.1", "Mus musculus", new DefaultUser());
    literature4.pubmedFiller("1.1.1.1", "Mus musculus", new DefaultUser());
  }

  @Test
  public void literatureConstructorTest() {
    assertNotNull(literature3.getAPA());
    assertNotNull(literature3.getPubmedID());
    assertEquals(0, literature4.getAttribute().size());
  }

  @Test
  public void literatureEqualsTest(){
    assertEquals(literature1, literature2);
    assertNotSame(literature1, new Object());
    assertEquals(literature5, literature4);
  }

  @Test
  public void gettersTest(){
    assertEquals(22222, literature1.getBrenda());
    assertEquals(11111, literature1.getPubmedID());
    assertEquals(apa, literature1.getAttribute().get(0));
    assertEquals(apa, literature1.getAPA());
    assertEquals(literature1.getAPA(), literature1.getAttribute().get(0));
    literature1.addAttributes(nullApa);
    assertNotSame(nullApa, literature1.getAttribute().get(0));
    assertEquals(apa, literature1.getAttribute().get(0));
    assertEquals("literature*22222", literature1.getParameter());
  }

  @Test
  public void toStringTest(){
    String reference = "<22222> author: title. journal. (1994) 1, 10. {Pubmed:11111}";
    assertEquals(reference, literature1.toString());
    reference = "Pubmed ID: 2053480";
    assertEquals(reference, literature4.toString());
  }

}