package entities_test;

import static junit.framework.TestCase.assertEquals;

import default_user.DefaultUser;
import entities.Literature;
import entities.LiteratureLink;
import org.junit.Before;
import org.junit.Test;

public class LiteratureLinkTest {
  private LiteratureLink literature1;
  private LiteratureLink literature2;
  private LiteratureLink literature3;

  @Before
  public void SetUp() throws Exception {
    Literature temp = new Literature(285564);
    temp.setPubmed(2053480);
    literature1 = new LiteratureLink(temp);
    literature2 = new LiteratureLink(new Literature(285564));
    Literature temp2 = new Literature(285564);
    temp2.fill("1.1.1.1", new DefaultUser());
    literature3 = new LiteratureLink(temp2);
  }

  @Test
  public void getUrlTest(){
    assertEquals("https://www.ncbi.nlm.nih.gov/pubmed/?term=2053480", literature1.getUrl());
    assertEquals("https://www.brenda-enzymes.org/literature.php?r=285564", literature2.getUrl());
    assertEquals("https://www.ncbi.nlm.nih.gov/pubmed/?term=2053480", literature3.getUrl());
  }

}
