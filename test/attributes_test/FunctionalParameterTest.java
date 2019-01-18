package attributes_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertTrue;

import attributes.functional_parameters.*;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;
import queries.ParserAnswer;

public class FunctionalParameterTest {
  private IC50Value ic50Value;
  private IC50Value nullIc50Value;
  private Kcat kcat;
  private KIValue kiValue;
  private Km km;
  private PHOptimum phOptimum;
  private PHRange phRange;
  private PIValue piValue;
  private SpecificActivity specificActivity;
  private TemperatureOptimum temperatureOptimum;
  private TemperatureRange temperatureRange;
  private TurnoverNumber turnoverNumber;
  private String expected;
  private ParserAnswer parserAnswer;

  @Before
  public void SetUp(){
    parserAnswer = new ParserAnswer();
    //IC50Value
    ic50Value = new IC50Value(
        1.7, "guanidine hydrochloride",
        "wild type enzyme", new Literature(678121)
    );
    nullIc50Value = new IC50Value();

    //Kcat
    kcat = new Kcat(
        0, 28, "Butanal",
        "pH 7.0, 30°C", new Literature(739927)
    );

    //KIValue
    kiValue = new KIValue(
        0.0047, "4-androsten-3,17-dione",
        "pH 7.3, 37°C, versus cyclohexanone", new Literature(655206)
    );

    //Km
    km = new Km(
        0.25, "(+)-2-methylcyclohexanone",
        "", new Literature(285621)
    );

    //PHOptimum
    phOptimum = new PHOptimum(
        3, "reduction of aldehydes",
        new Literature(735486)
    );

    //PHRange
    phRange = new PHRange(
        8.5,
        "more than 50% of maximum activity",
        new Literature(739588)
    );

    //PIValue
    piValue = new PIValue(
        4.8, "isoelectric focusing",
        new Literature(737865)
    );

    //SpecificActivity
    specificActivity = new SpecificActivity(
        0.01,
        "wild type recombinant enzyme, from crude extract, at 65°C",
        new Literature(682630)
    );

    //TemperaturOptimum
    temperatureOptimum = new TemperatureOptimum(
        25, 30,
        "assay at",
        new Literature(655649)
    );

    //TemperaturRange
    temperatureRange = new TemperatureRange(
        0, 60,
        "0°C: about 70% of maximal activity, about 45% of maximal activity",
        new Literature(668532)
    );


    //TurnoverNumber
    turnoverNumber = new TurnoverNumber(
        101, "(2E)-but-2-en-1-ol",
        "pH 8.0, 55°C",
        new Literature(735498)
    );

  }

  @Test
  public void gettersTest(){
    assertEquals(1.7,ic50Value.getValue());
    assertEquals(Double.NaN, ic50Value.getMax_value());
    assertEquals("guanidine hydrochloride", ic50Value.getMolecule().getName());
    assertTrue(ic50Value.getMolecule().isInhibitor());
    assertFalse(ic50Value.getMolecule().isSubstrate());
    assertEquals("wild type enzyme", ic50Value.getCommentary());
    expected = "ic50Value*1.7#ic50ValueMaximum*#commentary*wild type enzyme#inhibitor*guanidine hydrochloride";
    assertEquals(expected, ic50Value.getParameter());
    assertEquals("getIc50Value", ic50Value.getMethod());
    assertEquals(new Literature(678121), ic50Value.getReferences().get(0));
    assertEquals(Double.valueOf(0), kcat.getValue());
    assertNotSame(Double.NaN, kcat.getMax_value());
    assertEquals(Double.valueOf(28), kcat.getMax_value());
    expected = "kcatKmValue*0.0#kcatKmValueMaximum*28.0#commentary*pH 7.0, 30°C#substrate*Butanal";
    assertEquals(expected, kcat.getParameter());
    assertTrue(kcat.getMolecule().isSubstrate());
    assertFalse(kcat.getMolecule().isInhibitor());
    expected = "kiValue*0.0047#kiValueMaximum*#commentary*pH 7.3, 37°C, versus cyclohexanone#inhibitor*4-androsten-3,17-dione";
    assertEquals(expected, kiValue.getParameter());
    expected = "kmValue*0.25#kmValueMaximum*#commentary*#substrate*(+)-2-methylcyclohexanone";
    assertEquals(expected, km.getParameter());
    expected = "phOptimum*3.0#phOptimumMaximum*#commentary*reduction of aldehydes";
    assertEquals(expected, phOptimum.getParameter());
    expected = "phRange*8.5#phRangeMaximum*#commentary*more than 50% of maximum activity";
    assertEquals(expected, phRange.getParameter());

  }

  @Test
  public void defaultTest(){
    assertEquals(Double.NaN, nullIc50Value.getValue());
    assertEquals(Double.NaN, nullIc50Value.getMax_value());
    assertEquals("", nullIc50Value.getMolecule().getName());
    assertFalse(nullIc50Value.getMolecule().isSubstrate());
    assertFalse(nullIc50Value.getMolecule().isInhibitor());
    assertFalse(nullIc50Value.getMolecule().isProduct());
    assertEquals("", nullIc50Value.getCommentary());
    assertEquals(0, nullIc50Value.getReferences().size());
    expected = "ic50Value*#ic50ValueMaximum*#commentary*#inhibitor*";
    assertEquals(expected, nullIc50Value.getParameter());
    expected =  "piValue*4.8#piValueMaximum*#commentary*isoelectric focusing";
    assertEquals(expected, piValue.getParameter());
    expected =  "specificActivity*0.01#specificActivityMaximum*#commentary*wild type recombinant enzyme, from crude extract, at 65°C";
    assertEquals(expected, specificActivity.getParameter());
    expected = "temperatureOptimum*25.0#temperatureOptimumMaximum*30.0#commentary*assay at";
    assertEquals(expected, temperatureOptimum.getParameter());
    expected = "temperatureRange*0.0#temperatureRangeMaximum*60.0#commentary*0°C: about 70% of maximal activity, about 45% of maximal activity";
    assertEquals(expected, temperatureRange.getParameter());
    expected = "turnoverNumber*101.0#turnoverNumberMaximum*#commentary*pH 8.0, 55°C#substrate*(2E)-but-2-en-1-ol";
    assertEquals(expected, turnoverNumber.getParameter());
  }

  @Test
  public void setAttributesTest(){
    String resultOfQuery = "ecNumber*1.1.1.1#ic50Value*2.2#ic50ValueMaximum*#inhibitor*guanidine hydrochloride#commentary*mutant enzyme N249Y#organism*Sulfolobus solfataricus#ligandStructureId*804#literature*678121#";
    nullIc50Value.setAttribute(
        parserAnswer.getResult(resultOfQuery).get(0)
    );
    assertEquals(2.2,nullIc50Value.getValue());
    assertEquals(Double.NaN, nullIc50Value.getMax_value());
    assertEquals("guanidine hydrochloride", nullIc50Value.getMolecule().getName());
    assertTrue(nullIc50Value.getMolecule().isInhibitor());
    assertFalse(nullIc50Value.getMolecule().isSubstrate());
    assertEquals("mutant enzyme N249Y", nullIc50Value.getCommentary());
    expected = "ic50Value*2.2#ic50ValueMaximum*#commentary*mutant enzyme N249Y#inhibitor*guanidine hydrochloride";
    assertEquals(expected, nullIc50Value.getParameter());
    assertEquals("getIc50Value", nullIc50Value.getMethod());
    assertEquals(new Literature(678121), nullIc50Value.getReferences().get(0));
  }

}
