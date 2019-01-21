package filters_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import attributes.enzyme_structure.MolecularWeight;
import attributes.functional_parameters.KIValue;
import attributes.functional_parameters.TemperatureRange;
import attributes.functional_parameters.TurnoverNumber;
import attributes.organism_related_information.Organism;
import client.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import filters.Filter;
import filters.MoleculeDependentFilter;
import filters.NumericalAttributeFilter;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class MoleculeDependentTest {

  private Enzyme enzyme;
  private Organism organism1;
  private Organism organism2;
  private Organism organism3;
  private Protein protein1;
  private Protein protein2;
  private Protein protein3;
  private Filter parameterFilter;
  private TurnoverNumber turnoverNumber;
  private KIValue kiValue;
  private TemperatureRange temperatureRange;
  private List<Entity> filtered;

  @Before
  public void SetUp() throws Exception {
    parameterFilter = new MoleculeDependentFilter();
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
        "Mus Musculus",
        "isozymes ADH1 and ADH4",
        new Literature(655479)
    );
    protein2 = new Protein(enzyme, organism2, "");
    protein2.addAttributes(
    new MolecularWeight(39000, "2 * 39000, isoenzyme B2, SDS-PAGE", new Literature(285603)),
        new TurnoverNumber(1.92,"ethanol","recombinant isozyme ADH1, pH 7.5, 25°C", new Literature(655479))
    );
    organism3 = new Organism(
        "Saccharomyces cerevisiae",
        "",
        new Literature(285642),
        new Literature(668532),
        new Literature(739927)
    );
    protein3 = new Protein(enzyme, organism3, "");
    protein3.addAttributes(
        new MolecularWeight(40000, "2 * 40000, SDS-PAGE", new Literature(285642)),
        new TemperatureRange(20, 85,
            "20°C: about 65% of maximal activity, 85°C: about 90% of maximal activity", new Literature(668532)),
        new TurnoverNumber(1.99, "Butanal", "pH 7.0, 30°C", new Literature(739927))
    );
    parameterFilter.setEntities(protein1, protein2, protein3);
  }

  @Test
  public void moleculeFilterTest(){
    turnoverNumber = new TurnoverNumber(3, 9, "Butanal", "");
    parameterFilter.addCriteria(turnoverNumber);
    filtered = parameterFilter.getFiltered();
    assertEquals(1, filtered.size());
    assertEquals(protein3, filtered.get(0));
  }

  @Test
  public void noMoleculeLeftFilterTest(){
    kiValue = new KIValue(Double.NaN, "Butanal", "");
    parameterFilter.setCriteria(kiValue);
    filtered = parameterFilter.getFiltered();
    assertEquals(0, filtered.size());

  }

  @Test
  public void noFilterTest(){
    temperatureRange = new TemperatureRange(0, 100, "");
    parameterFilter.setCriteria(temperatureRange);
    filtered = parameterFilter.getFiltered();
    assertEquals(3, filtered.size());
  }

  @Test
  public void noNameMoleculeFilterTest(){
    kiValue = new KIValue(Double.NaN, "", "");
    parameterFilter.setCriteria(kiValue);
    filtered = parameterFilter.getFiltered();
    assertEquals(3, filtered.size());
  }

  @Test
  public void noMoleculeFilterTest(){
    kiValue = new KIValue();
    parameterFilter.setCriteria(kiValue);
    filtered = parameterFilter.getFiltered();
    assertEquals(3, filtered.size());
  }
}