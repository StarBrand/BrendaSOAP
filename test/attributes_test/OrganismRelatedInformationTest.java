package attributes_test;

import static junit.framework.TestCase.assertEquals;

import attributes.organism_related_information.Organism;
import attributes.organism_related_information.Taxonomy;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;
import queries.ParserAnswer;

public class OrganismRelatedInformationTest {
  private Organism organism;
  private Organism nullOrganism;
  private Taxonomy taxonomy;
  private Taxonomy nullTaxonomy;
  private String result;
  private ParserAnswer parserAnswer;

  @Before
  public void SetUp(){
    organism = new Organism("Homo sapiens",
        "", new Literature(11111));
    nullOrganism = new Organism();
    taxonomy = new Taxonomy("Homo sapiens", "Homo",
            "Hominidae", "Primates",
            "Mammalia", "Chordata",
            "Animalia");
    nullTaxonomy = new Taxonomy();
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
    assertEquals("Organism", organism.getAttributeName());
    assertEquals("Homo sapiens",taxonomy.getSpecies());
    assertEquals("Homo", taxonomy.getGenus());
    assertEquals("Hominidae", taxonomy.getFamily());
    assertEquals("Primates", taxonomy.getOrder());
    assertEquals("Mammalia", taxonomy.getPhylogenetic_class());
    assertEquals("Chordata", taxonomy.getPhylum());
    assertEquals("Animalia", taxonomy.getSuperkingdom());
    assertEquals("", taxonomy.getMethod());
    assertEquals("", taxonomy.getParameter());
    assertEquals("Taxonomy", taxonomy.getAttributeName());
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
    assertEquals("Organism", nullOrganism.getAttributeName());
    assertEquals("", nullTaxonomy.getSpecies());
    assertEquals("", nullTaxonomy.getGenus());
    assertEquals("", nullTaxonomy.getFamily());
    assertEquals("", nullTaxonomy.getOrder());
    assertEquals("", nullTaxonomy.getPhylogenetic_class());
    assertEquals("", nullTaxonomy.getPhylum());
    assertEquals("", nullTaxonomy.getSuperkingdom());
    assertEquals("", nullTaxonomy.getMethod());
    assertEquals("", nullTaxonomy.getParameter());
    assertEquals("Taxonomy", nullTaxonomy.getAttributeName());
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

  @Test
  public void toRowTest(){
    assertEquals("NA\tNA\tNA\tNA\tNA\tNA\tNA", nullTaxonomy.toRow());
    assertEquals(
            "Homo sapiens\tHomo\tHominidae\tPrimates\tMammalia\tChordata\tAnimalia",
            taxonomy.toRow()
    );
  }

}
