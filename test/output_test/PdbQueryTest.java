package output_test;

import static junit.framework.TestCase.assertEquals;

import attributes.Attribute;
import attributes.enzyme_structure.PDB;
import attributes.organism_related_information.Organism;
import default_user.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.PdbQuery;
import queries.Query;

public class PdbQueryTest {

  private Query pdbQuery;
  private Protein protein1;
  private Protein protein2;
  private Protein protein3;
  private Protein protein4;
  private PDB pdb;

  @Before
  public void SetUp() throws Exception {
    pdbQuery = new PdbQuery(new DefaultUser());
    // One PDB ID
    protein1 = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Acidomyces acidophilus",
            "",
            new Literature(707765)
        ),
        ""
    );
    // Three PDB ID
    protein2 = new Protein(
        new Enzyme("1.1.1.1", new DefaultUser()),
        new Organism(
            "Mus musculus",
            "",
            new Literature(655233),
            new Literature(667832),
            new Literature(668205),
            new Literature(697214),
            new Literature(699982)
        ),
        ""
    );
    // The Same one
    protein3 = new Protein(
        new Enzyme("1.1.1.1", new DefaultUser()),
        new Organism(
            "Mus musculus",
            "low-activity isozyme ADH2",
            new Literature(654730)),
        "Q9QYY9"
    );
    // Protein with no PDB ID
    protein4 = new Protein(
        new Enzyme("6.6.1.2", new DefaultUser()),
        new Organism(
            "Pseudomonas denitrificans (nomen rejiciendum)",
            "",
            new Literature(653078)
        ),
        "P29933 AND P29934 AND Q9HZQ3 AND P29929"
    );
  }

  @Test
  public void oneProteinTest() throws Exception {
    pdbQuery.setEntities(protein1);

    List<Entity> result = (List<Entity>) pdbQuery.getResult();
    assertEquals(1, result.size());

    List<Attribute> pdbs = result.get(0).getAttribute();
    assertEquals(1, pdbs.size());
  }

  @Test
  public void twoProteinTest() throws Exception{
    pdbQuery.setEntities(protein1, protein2);

    List<Entity> result = (List<Entity>) pdbQuery.getResult();
    assertEquals(2, result.size());

    List<Attribute> pdbs = result.get(0).getAttribute();
    assertEquals(1, pdbs.size());

    pdbs = result.get(1).getAttribute();
    assertEquals(3, pdbs.size());
  }

  @Test
  public void repeatedProteinTest() throws Exception {
    pdbQuery.setEntities(protein1, protein2, protein3);

    List<Entity> result = (List<Entity>) pdbQuery.getResult();
    assertEquals(2, result.size());

    List<Attribute> pdbs = result.get(0).getAttribute();
    assertEquals(1, pdbs.size());

    pdbs = result.get(1).getAttribute();
    assertEquals(3, pdbs.size());
  }

  @Test
  public void noPDBProteinTest() throws Exception {
    pdbQuery.setEntities(protein4);

    List<Entity> result = (List<Entity>) pdbQuery.getResult();
    assertEquals(1, result.size());

    List<Attribute> pdbs = result.get(0).getAttribute();
    assertEquals(0, pdbs.size());
  }

  @Test
  public void finalTest() throws Exception {
    pdbQuery.setEntities(protein1, protein2, protein3, protein4);

    List<Entity> result = (List<Entity>) pdbQuery.getResult();
    assertEquals(3, result.size());

    List<Attribute> pdbs = result.get(0).getAttribute();
    assertEquals(1, pdbs.size());

    pdbs = result.get(1).getAttribute();
    assertEquals(3, pdbs.size());

    pdbs = result.get(2).getAttribute();
    assertEquals(0, pdbs.size());
  }

  @Test
  public void generateFileTest() throws Exception {
    pdbQuery.setEntities(protein1, protein2, protein3, protein4);
    pdbQuery.getResult();
    ((PdbQuery) pdbQuery).generateFile();
  }

}
