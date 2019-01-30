package output_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

import attributes.enzyme_structure.AASequence;
import attributes.organism_related_information.Organism;
import default_user.DefaultUser;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.FastaQuery;
import queries.Query;

public class FastaQueryTest {

  private Query fastaQuery;
  private Protein protein1;
  private Protein protein2;
  private Protein protein3;
  private Protein noSequenceProtein;
  private AASequence aaSequence;

  @Before
  public void SetUp() throws Exception {
    fastaQuery = new FastaQuery(new DefaultUser());
    protein1 = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Ampullaria crossean",
            "",
            new Literature(695295)
        ),
        "Q7Z1V6"
    );
    protein2 = new Protein(
        new Enzyme("1.1.1.1", new DefaultUser()),
        new Organism(
            "Homo sapiens",
            ""
        ),
        "P40394"
    );
    protein3 = new Protein(
    new Enzyme("6.6.1.2", new DefaultUser()),
        new Organism(
            "Pseudomonas denitrificans (nomen rejiciendum)",
            "",
            new Literature(1429466)
        ),
        "P29933 AND P29934 AND Q9HZQ3 AND P29929"
    );
    noSequenceProtein = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Acacia verek",
            "",
            new Literature(171611)
        ),
        ""
    );
  }

  @Test
  public void oneProteinTest() throws Exception {
    fastaQuery.setEntities(protein1);
    List<AASequence> result = (List<AASequence>) fastaQuery.getResult();
    assertEquals(1, result.size());

    assertFalse(result.get(0).getSequence().equals(""));
    assertFalse(result.get(0).getNumberOfAminoacids() == 0);
  }

  @Test
  public void noSequenceProteinTest() throws Exception{
    fastaQuery.setEntities(noSequenceProtein);
    List<AASequence> result = (List<AASequence>) fastaQuery.getResult();
    assertEquals(0, result.size());
  }

  @Test
  public void threeProteinTest() throws Exception {
    fastaQuery.setEntities(protein1, protein2, noSequenceProtein);
    List<AASequence> result = (List<AASequence>) fastaQuery.getResult();
    assertEquals(2, result.size());

    assertFalse(result.get(1).getSequence().equals(""));
    assertFalse(result.get(1).getNumberOfAminoacids() == 0);
  }

  @Test
  public void multipleSequencePerProteinTest() throws Exception {
    fastaQuery.setEntities(protein3);
    List<AASequence> result = (List<AASequence>) fastaQuery.getResult();
    assertEquals(4, result.size());

    assertFalse(result.get(1).getSequence().equals(""));
    assertFalse(result.get(1).getNumberOfAminoacids() == 0);
  }

  @Test
  public void multipleSequencePerProtein2Test() throws Exception {
    fastaQuery.setEntities(protein1, protein3);
    List<AASequence> result = (List<AASequence>) fastaQuery.getResult();
    assertEquals(5, result.size());

    assertFalse(result.get(1).getSequence().equals(""));
    assertFalse(result.get(1).getNumberOfAminoacids() == 0);
  }

  @Test
  public void generateFileTest() throws Exception {
    fastaQuery.setEntities(protein1, protein2, protein3, noSequenceProtein);
    fastaQuery.getResult();
    ((FastaQuery) fastaQuery).generateFile();
  }

}