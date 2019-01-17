package attributes_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

import attributes.APACitation;
import org.junit.Before;
import org.junit.Test;

public class APATest {
  private APACitation apa1;
  private APACitation apa2;
  private APACitation apa3;
  private APACitation apa4;
  private APACitation apa5;
  private APACitation apa6;
  private APACitation apa7;
  private APACitation nullApa;

  @Before
  public void SetUp(){
    apa1 = new APACitation("author", "title", "journal", 1, 10, 12, 1994);
    apa2 = new APACitation("title", "journal", 1, 10, 12, 1994, "author");
    apa3 = new APACitation("title", "journal", 1, 10, 12, 1994, "author1", "author2");
    apa4 = new APACitation("author", "title", "journal", 1, 10, 1994);
    apa5 = new APACitation("title", "journal", 1, 10, 12, 1994);
    apa6 = new APACitation("title", "journal", 1, 10, 1994, "author");
    apa7 = new APACitation("title", "journal", 1, "EF", 1994, "author");
    nullApa = new APACitation();
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
    String reference5 = "author: title. journal. (1994) 1, EF.";
    assertEquals(reference5,apa7.toString());
  }

  @Test
  public void gettersTest(){
    assertEquals("getReference", apa1.getMethod());
    assertEquals("getReference", apa2.getMethod());
    assertEquals("getReference", apa3.getMethod());
    assertEquals("getReference", apa4.getMethod());
    assertEquals("getReference", apa5.getMethod());
    assertEquals("getReference", apa6.getMethod());
    assertEquals("author", apa1.getAuthors().get(0));
    assertEquals("title", apa1.getTitle());
    assertEquals("journal", apa1.getJournal());
    assertEquals(1994, apa1.getYear());;
    assertEquals(10, apa1.getFirstPage());
    assertEquals(12, apa1.getLastPage());
    assertEquals(1, apa1.getVolume());
    assertEquals("EF", apa7.getSpecialPage());
  }

  @Test
  public void nullTest(){
    assertEquals("", nullApa.getAuthors().get(0));
    assertEquals("", nullApa.getTitle());
    assertEquals("", nullApa.getJournal());
    assertEquals(0, nullApa.getVolume());
    assertEquals(Integer.MAX_VALUE, nullApa.getFirstPage());
    assertEquals(Integer.MAX_VALUE, nullApa.getLastPage());
    assertEquals("", nullApa.getSpecialPage());
    assertEquals(0, nullApa.getYear());
  }

  @Test
  public void settersTest(){
    nullApa.setAuthors("author");
    nullApa.setTitle("title");
    nullApa.setJournal("journal");
    nullApa.setVolume(1);
    nullApa.setFirstPage(10);
    nullApa.setLastPage(12);
    nullApa.setYear(1994);
    assertEquals(apa1, nullApa);
    nullApa.setSpecial_page("EF");
    assertEquals(apa7, nullApa);
    nullApa.setFirstPage(10);
    assertFalse(Integer.MAX_VALUE == nullApa.getLastPage());
  }

}
