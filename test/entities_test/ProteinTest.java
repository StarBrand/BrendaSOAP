package entities_test;

import static junit.framework.TestCase.assertEquals;

import attributes.Attribute;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHRange;
import attributes.organism_related_information.Organism;
import client.DefaultUser;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import org.junit.Before;
import org.junit.Test;

public class ProteinTest {

  private Protein protein1;
  private Protein protein2;
  private Attribute example1;
  private Attribute example2;

  @Before
  public void SetUp() throws Exception {
    protein1 = new Protein(new Enzyme(1,1,1,1, new DefaultUser()), new Organism("Mus musculus",
        "low-activity isozyme ADH2", new Literature(654730)), "Q9QYY9"
    );
    protein2 = new Protein(new Enzyme("1.1.1.1", new DefaultUser()), new Organism("Homo sapiens",
        "", new Literature(726769)), "D4GSN2"
    );
    example1 = new Km(10.0, "substrate", "", new Literature(11111));
    example2 = new PHRange(7.0, 7.2, "", new Literature(11111));
  }

  @Test
  public void gettersTest() throws Exception {
    assertEquals(new Enzyme(1,1,1,1, new DefaultUser()), protein1.getEnzyme());
    assertEquals(new Enzyme(1,1,1,1, new DefaultUser()), protein2.getEnzyme());
    assertEquals("Mus musculus", protein1.getOrganism().getName());
    assertEquals("", protein2.getOrganism().getCommentary());
    assertEquals("Q9QYY9", protein1.getUniprot());
    assertEquals("ecNumber*1.1.1.1#organism*Homo sapiens", protein2.getParameter());
    String protein2string = "ECNumber: 1.1.1.1/Organism: Homo sapiens/Commentary: /Sequence: D4GSN2/References: 726769.";
    assertEquals(protein2string, protein2.toString());
  }

  @Test
  public void attributesTest(){
    protein1.addAttributes(example1);
    assertEquals(example1, protein1.getAttribute().get(0));

    protein2.addAttributes(example1, example2);
    assertEquals(example1, protein2.getAttribute().get(0));
    assertEquals(example2, protein2.getAttribute().get(1));
  }


}
