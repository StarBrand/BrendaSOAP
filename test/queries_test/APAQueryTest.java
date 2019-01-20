package queries_test;

import static junit.framework.TestCase.assertEquals;

import attributes.APACitation;
import client.DefaultUser;
import entities.Literature;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.APAQuery;
import queries.Query;

public class APAQueryTest {

  private Query apaQuery1;
  private Query apaQuery2;
  private APACitation apaCitation;
  private APACitation nullApaCitation;
  private Literature literature;
  private Literature nullLiterature;
  private List<?> results1;
  private List<?> results2;

  @Before
  public void SetUp() throws Exception {
    apaQuery1 = new APAQuery(
        new DefaultUser(), "1.1.1.1",
        "Mus musculus", 285564
    );
    // this must fail
    apaQuery2 = new APAQuery(
        new DefaultUser(), "1.1.1.1",
        "Mus musculus", 111111
    );
    apaCitation = new APACitation(
        "Alcohol dehydrogenase gene expression and cloning of the mouse chi-like ADH",
        "Adv. Exp. Med. Biol.",
        284, 253, 262, 1991,
        "Edenberg, H.J.", "Brown, C.J.", "Carr, L.G.",
        "Ho, W.H.","Hu, M.W."
    );
    nullApaCitation = new APACitation();
    literature = new Literature(
        2053480,
        285564,
        apaCitation
    );
    nullLiterature = new Literature(
        0,
        111111,
        nullApaCitation
    );
    results1 = apaQuery1.getResult();
    results2 = apaQuery2.getResult();
  }

  @Test
  public void queryTest(){
    assertEquals(2, results1.size());
    assertEquals(2, results2.size());
    assertEquals(2053480, results1.get(0));
    assertEquals(0, results2.get(0));
    assertEquals(apaCitation, results1.get(1));
    assertEquals(nullApaCitation, results2.get(1));
    assertEquals(literature, new Literature(
        (Integer) results1.get(0),
        285564,
        (APACitation) results1.get(1)
        )
    );
    assertEquals(nullLiterature, new Literature(
            (Integer) results2.get(0),
            111111,
            (APACitation) results2.get(1)
        )
    );
  }

}
