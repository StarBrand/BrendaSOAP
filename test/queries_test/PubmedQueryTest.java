package queries_test;

import static junit.framework.TestCase.assertEquals;

import client.DefaultUser;
import entities.Literature;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.PubmedQuery;
import queries.Query;

public class PubmedQueryTest {

  private Query pubmedQuery1;
  private Query pubmedQuery2;
  private Literature literature;
  private Literature nullLiterature;
  private List<?> results1;
  private List<?> results2;

  @Before
  public void SetUp() throws Exception {
    pubmedQuery1 = new PubmedQuery(
        new DefaultUser(), "1.1.1.1",
        "Mus musculus", 285564
    );
    // this must fail
    pubmedQuery2 = new PubmedQuery(
        new DefaultUser(), "1.1.1.1",
        "Mus musculus", 111111
    );
    literature = new Literature(
        285564,
        2053480
    );
    nullLiterature = new Literature(
        111111,
        0
    );
    results1 = pubmedQuery1.getResult();
    results2 = pubmedQuery2.getResult();
  }

  @Test
  public void queryTest(){
    assertEquals(1, results1.size());
    assertEquals(1, results2.size());
    assertEquals(2053480, results1.get(0));
    assertEquals(0, results2.get(0));
    assertEquals(literature, new Literature(
        285564,
            (Integer) results1.get(0)
        )
    );
    assertEquals(nullLiterature, new Literature(
            111111,
            (Integer) results2.get(0)
        )
    );
  }

}
