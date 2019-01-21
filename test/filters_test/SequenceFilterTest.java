package filters_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import attributes.organism_related_information.Organism;
import client.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import filters.Filter;
import filters.SequenceFilter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class SequenceFilterTest {

  private Enzyme enzyme;
  private Organism organism1;
  private Organism organism2;
  private Organism organism3;
  private Organism organism4;
  private Organism organism5;
  private Protein protein1;
  private Protein protein2;
  private Protein protein3;
  private Protein protein4;
  private Protein protein5;
  private Filter sequenceFilter;

  @Before
  public void SetUp() throws Exception {
    sequenceFilter = new SequenceFilter();
    enzyme = new Enzyme(1,1,1,1,new DefaultUser());
    organism1 = new Organism(
        "Mus musculus",
        "",
        new Literature(655233),
        new Literature(667832),
        new Literature(668205),
        new Literature(697214),
        new Literature(699982)
        );
    protein1 = new Protein(enzyme, organism1, "");
    organism2 = new Organism(
        "Mus musculus",
        "class III enzyme",
        new Literature(285564)
    );
    protein2 = new Protein(enzyme, organism2, "");
    organism3 = new Organism(
        "Mus musculus",
        "isoenzyme A2, B2 and C2",
        new Literature(285603)
    );
    protein3 = new Protein(enzyme, organism3, "");
    organism4 = new Organism(
        "Mus musculus",
        "isozymes ADH1 and ADH4",
        new Literature(655479)
    );
    protein4 = new Protein(enzyme, organism4, "");
    organism5 = new Organism(
        "Mus musculus",
        "low-activity isozyme ADH2",
        new Literature(654730)
    );
    protein5 = new Protein(enzyme, organism5, "Q9QYY9");
  }

  @Test
  public void filterTest(){
    sequenceFilter.setEntities(protein1, protein2, protein3, protein4, protein5);
    List<Entity> proteins_filtered  = sequenceFilter.getFiltered();
    assertEquals(1, proteins_filtered.size());
    assertEquals(enzyme.getEC().toString(), ((Protein) proteins_filtered.get(0)).getEnzyme().getEC().toString());
    assertEquals(organism5.getCommentary(), ((Protein) proteins_filtered.get(0)).getOrganism().getCommentary());
    assertEquals("Q9QYY9", ((Protein) proteins_filtered.get(0)).getUniprot());
  }

  @Test
  public void clearTest(){
    sequenceFilter.setEntities(protein5, protein4);
    sequenceFilter.clearEntities();
    List<Entity> proteins_filtered  = sequenceFilter.getFiltered();
    assertEquals(0, proteins_filtered.size());
  }

  @Test
  public void addTest(){
    sequenceFilter.setEntities(protein5, protein4);
    sequenceFilter.addEntities(protein5);
    List<Entity> proteins_filtered  = sequenceFilter.getFiltered();
    assertEquals(2, proteins_filtered.size());
  }

  @Test
  public void setTest(){
    sequenceFilter.setEntities(protein5, protein4);
    sequenceFilter.setEntities(protein1);
    List<Entity> proteins_filtered  = sequenceFilter.getFiltered();
    assertEquals(0, proteins_filtered.size());
  }
}
