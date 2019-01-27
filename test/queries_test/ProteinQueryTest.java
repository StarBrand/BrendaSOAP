package queries_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import attributes.enzyme_structure.ECNumber;
import DefaultUser;
import entities.Enzyme;
import entities.Protein;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.ProteinQuery;
import queries.Query;

public class ProteinQueryTest {

  private Query proteinQuery;
  private Enzyme enzyme1;
  private Enzyme enzyme2;

  @Before
  public void SetUp() throws Exception {
    proteinQuery = new ProteinQuery(new DefaultUser());
    enzyme1 = new Enzyme("1.1.1.1", new DefaultUser());
    enzyme2 = new Enzyme("1.1.1.2", new DefaultUser());
  }

  @Test
  public void queryTest() throws Exception {
    // Due to the complex of the result, it will be tested
    // just the result is obtain and not the result itself
    proteinQuery.setEntities(enzyme1);
    List<?> results = proteinQuery.getResult();

    assertTrue(results.size() > 1);
    assertTrue(results.get(0) instanceof Protein);
    assertEquals(
        new ECNumber(1,1,1,1),
        ((Protein) results.get(0)).getEnzyme().getEC()
    );
    int numberOfResult = results.size();

    proteinQuery = new ProteinQuery(new DefaultUser());
    proteinQuery.setEntities(enzyme1, enzyme2);
    results = proteinQuery.getResult();

    assertTrue(results.size() > (1 + numberOfResult) );
    assertTrue(results.get(numberOfResult+5) instanceof Protein);
    assertEquals(
        new ECNumber(1,1,1,2),
        ((Protein) results.get(numberOfResult+5)).getEnzyme().getEC()
    );
  }

}
