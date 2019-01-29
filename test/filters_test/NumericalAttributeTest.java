package filters_test;

import static junit.framework.TestCase.assertEquals;

import attributes.enzyme_structure.MolecularWeight;
import attributes.functional_parameters.TemperatureRange;
import attributes.functional_parameters.TurnoverNumber;
import attributes.organism_related_information.Organism;
import default_user.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import filters.Filter;
import filters.NumericalAttributeFilter;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class NumericalAttributeTest {

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
  private Filter parameterFilter;
  private MolecularWeight molecularWeight;
  private TurnoverNumber turnoverNumber;
  private TemperatureRange temperatureRange;
  private List<Entity> filtered;

  @Before
  public void SetUp() throws Exception {
    parameterFilter = new NumericalAttributeFilter();
    enzyme = new Enzyme(1,1,1,1,new DefaultUser());
    organism1 = new Organism(
        "Candida maris",
        "",
        new Literature(721868)
    );
    protein1 = new Protein(enzyme, organism1, "");
    protein1.addAttributes(
        new MolecularWeight(28900,"2 * 28900, SDS-PAGE", new Literature(721868)),
        new TemperatureRange(10, 75,
            "activity range, profile overview", new Literature(721868))
    );
    organism2 = new Organism(
        "Haloferax volcanii",
        "",
        new Literature(726769)
    );
    protein2 = new Protein(enzyme, organism2, "D4GSN2");
    protein2.addAttributes(
        new MolecularWeight(37600, "4 * 37600, SDS-PAGE", new Literature(726769)),
        new TemperatureRange(60, 90,
            "60°C: 75% of maximal activity, 90°C: 75% of maximal activity", new Literature(726769))
    );
    organism3 = new Organism(
        "Zygosaccharomyces rouxii",
        "",
        new Literature(658565)
    );
    protein3 = new Protein(enzyme, organism3, "");
    protein3.addAttributes(
        new TemperatureRange(33, 35,
        "", new Literature(658565))
    );
    organism4 = new Organism(
        "Mus Musculus",
        "isozymes ADH1 and ADH4",
        new Literature(655479)
    );
    protein4 = new Protein(enzyme, organism4, "");
    protein4.addAttributes(
        new MolecularWeight(39000, "2 * 39000, isoenzyme B2, SDS-PAGE", new Literature(285603)),
        new TurnoverNumber(1.92,"ethanol","recombinant isozyme ADH1, pH 7.5, 25°C", new Literature(655479))
    );
    organism5 = new Organism(
        "Saccharomyces cerevisiae",
        "",
        new Literature(285642),
        new Literature(668532),
        new Literature(739927)
    );
    protein5 = new Protein(enzyme, organism5, "");
    protein5.addAttributes(
        new MolecularWeight(40000, "2 * 40000, SDS-PAGE", new Literature(285642)),
        new TemperatureRange(20, 85,
            "20°C: about 65% of maximal activity, 85°C: about 90% of maximal activity", new Literature(668532)),
        new TurnoverNumber(1.99, "Butanal", "pH 7.0, 30°C", new Literature(739927))
    );
    parameterFilter.setEntities(protein1, protein2, protein3, protein4, protein5);
  }

  @Test
  public void moreOfFilterTest(){
    molecularWeight = new MolecularWeight(30000, "");
    temperatureRange = new TemperatureRange(50, "");
    parameterFilter.setCriteria(molecularWeight, temperatureRange);
    filtered = parameterFilter.getFiltered();
    assertEquals(1, filtered.size());
    assertEquals(protein2,filtered.get(0));
  }

  @Test
  public void lessOfFilterTest(){
    molecularWeight = new MolecularWeight(Double.NaN, 50000, "");
    temperatureRange = new TemperatureRange(Double.NaN,90, "");
    turnoverNumber = new TurnoverNumber(Double.NaN, 2.0, "", "");
    parameterFilter.setCriteria(molecularWeight, temperatureRange, turnoverNumber);
    filtered = parameterFilter.getFiltered();
    assertEquals(1, filtered.size());
    assertEquals(protein5,filtered.get(0));
  }

  @Test
  public void betweenFilterTest(){
    temperatureRange = new TemperatureRange(30, 90, "");
    parameterFilter.setCriteria(temperatureRange);
    filtered = parameterFilter.getFiltered();
    assertEquals(2, filtered.size());
    assertEquals(protein2,filtered.get(0));
    assertEquals(protein3,filtered.get(1));
  }
}
