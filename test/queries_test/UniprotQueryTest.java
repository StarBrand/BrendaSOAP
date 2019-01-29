package queries_test;

import static junit.framework.TestCase.assertEquals;

import attributes.enzyme_structure.MolecularWeight;
import default_user.DefaultUser;
import org.junit.Before;
import org.junit.Test;
import queries.Query;
import queries.UniprotQuery;

public class UniprotQueryTest {

  private Query query;
  private String filename;

  @Before
  public void SetUp(){
    filename = "uniprotTest.txt";
    query = new UniprotQuery(new DefaultUser(), filename);
  }

  /*@Test
  public void test() throws Exception {
    query.addAttributes(new MolecularWeight());
    assertEquals(0, query.numberOfAttributes());
    assertEquals(5, query.getResult().size());
  }*/

}
