package attributes_test;

import static junit.framework.TestCase.assertEquals;

import attributes.organism_related_information.Organism;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;
import queries.ParserAnswer;

public class OrganismRelatedInformationTest {
  private Organism organism;
  private Organism nullOrganism;
  private String result;
  private ParserAnswer parserAnswer;

  @Before
  public void SetUp(){
    organism = new Organism("Homo sapiens",
        "", new Literature(11111));
    nullOrganism = new Organism();
    parserAnswer = new ParserAnswer();
  }

  @Test
  public void gettersTest(){
    assertEquals("Homo sapiens",organism.getName());
    assertEquals("Homo", organism.getGenre());
    assertEquals("sapiens", organism.getSpecies());
    assertEquals("", organism.getSpecies_commentary());
    assertEquals("", organism.getCommentary());
    assertEquals(new Literature(11111), organism.getReferences().get(0));
    assertEquals("organism*Homo sapiens", organism.getParameter());
    assertEquals("getOrganism", organism.getMethod());
  }

  @Test
  public void defaultTest(){
    assertEquals("",nullOrganism.getName());
    assertEquals("", nullOrganism.getGenre());
    assertEquals("", nullOrganism.getSpecies());
    assertEquals("", nullOrganism.getSpecies_commentary());
    assertEquals("", nullOrganism.getCommentary());
    assertEquals(0, nullOrganism.getReferences().size());
    assertEquals("organism*", nullOrganism.getParameter());
    assertEquals("getOrganism", nullOrganism.getMethod());
  }

  @Test
  public void setAttributesTest(){
    result = "organism*Mus musculus L45#commentary*something#literature*11111";
    nullOrganism.setAttribute(
        parserAnswer.getResult(result).get(0)
    );
    assertEquals("Mus musculus L45",nullOrganism.getName());
    assertEquals("Mus", nullOrganism.getGenre());
    assertEquals("musculus", nullOrganism.getSpecies());
    assertEquals("L45", nullOrganism.getSpecies_commentary());
    assertEquals("something", nullOrganism.getCommentary());
    assertEquals(new Literature(11111), nullOrganism.getReferences().get(0));
    assertEquals("organism*Mus musculus L45", nullOrganism.getParameter());
    assertEquals("getOrganism", nullOrganism.getMethod());
  }

}
