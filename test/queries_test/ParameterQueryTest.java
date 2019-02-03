package queries_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import attributes.Attribute;
import attributes.MoleculeDependentAttribute;
import attributes.functional_parameters.KIValue;
import attributes.functional_parameters.Kcat;
import attributes.functional_parameters.RangeValue;
import attributes.functional_parameters.TemperatureRange;
import attributes.organism_related_information.Organism;
import default_user.DefaultUser;
import entities.Enzyme;
import entities.Literature;
import entities.NullMolecule;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.ParameterQuery;
import queries.Query;

public class ParameterQueryTest {

  private Query parameterQuery;
  private Protein protein1;
  private Protein protein2;
  private MoleculeDependentAttribute kcat;
  private MoleculeDependentAttribute kiValue;
  private RangeValue temperatureRange;

  @Before
  public void SetUp() throws Exception {
    parameterQuery = new ParameterQuery(new DefaultUser());
    protein1 = new Protein(
        new Enzyme("1.1.1.1", new DefaultUser()),
        new Organism(
            "Devosia riboflavina",
            "KNK10702",
            new Literature(688442)
        ),
        ""
    );
    protein2 = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Bacillus circulans",
            "P09850",
            new Literature(700545),
            new Literature(707522),
            new Literature(709203)
        ),
        "Q7Z1V6"
    );
    kcat = new Kcat();
    kiValue = new KIValue();
    temperatureRange = new TemperatureRange();
  }

  @Test
  public void protein1Test() throws Exception {
    parameterQuery.setEntities(protein1);
    parameterQuery.addAttributes(kcat, kiValue, temperatureRange);
    assertEquals(3, parameterQuery.numberOfAttributes());

    protein1 = (Protein) parameterQuery.getResult().get(0);
    assertEquals(1, protein1.getAttribute().size());

    List<Attribute> attributes = getAttributes(protein1);

    temperatureRange = (RangeValue) attributes.get(0);

    //Temperature Range found
    assertFalse( temperatureRange.getCommentary().equals("") );
    assertFalse( Double.isNaN( temperatureRange.getMin_value() ) );
    assertFalse( Double.isNaN( temperatureRange.getMax_value() ) );
    assertTrue(temperatureRange.getReferences().size() > 0);
  }

  @Test
  public void Protein2Test() throws Exception {
    parameterQuery.setEntities(protein1, protein2);
    parameterQuery.addAttributes(kcat, kiValue, temperatureRange);
    assertEquals(3, parameterQuery.numberOfAttributes());

    protein2 = (Protein) parameterQuery.getResult().get(1);
    assertEquals(2, protein2.getAttribute().size());
    List<Attribute> attributes = getAttributes(protein2);

    kcat = (MoleculeDependentAttribute) attributes.get(0);
    temperatureRange = (RangeValue) attributes.get(1);

    // kcat found
    assertFalse(kcat.getCommentary().equals(""));
    assertTrue(kcat.getMolecule().isSubstrate());
    assertFalse( Double.isNaN( ((Kcat) kcat).getValue() ) );
    assertTrue(kcat.getReferences().size() > 0);

    //Temperature Range found, but no comment
    assertEquals("", temperatureRange.getCommentary());
    assertFalse( Double.isNaN( temperatureRange.getMin_value() ) );
    assertFalse( Double.isNaN( temperatureRange.getMax_value() ) );
    assertTrue(temperatureRange.getReferences().size() > 0);
  }

  public List<Attribute> getAttributes(Protein protein){
    List<Attribute> out = new ArrayList<Attribute>();
    for(Attribute attribute:protein.getAttribute()){
      if (attribute instanceof Kcat){
        out.add(attribute);
      }
      else if (attribute instanceof KIValue){
        out.add(attribute);;
      }
      else if (attribute instanceof TemperatureRange){
        out.add(attribute);;
      }
    }
    return out;
  }

  @Test
  public void notEqualTest() throws Exception {
    parameterQuery.setEntities(protein1, protein2);
    parameterQuery.addAttributes(kcat, kiValue, temperatureRange);

    List<Protein> proteins = (List<Protein>) parameterQuery.getResult();
    assertFalse(
        proteins.get(0).getParameter().equals(
            proteins.get(1).getParameter()
        )
    );
    assertFalse(
        proteins.get(0).getAttribute().get(0).getParameter().equals
            (
                proteins.get(1).getAttribute().get(0).getParameter()
            )
    );
  }

}
