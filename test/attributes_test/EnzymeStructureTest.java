package attributes_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

import attributes.enzyme_structure.AASequence;
import attributes.enzyme_structure.ECNumber;
import attributes.enzyme_structure.MolecularWeight;
import entities.Literature;
import org.junit.Before;
import org.junit.Test;
import queries.ParserAnswer;

public class EnzymeStructureTest {

  private ECNumber ec1;
  private ECNumber ec2;
  private AASequence aaSequence;
  private AASequence nullSequence;
  private MolecularWeight molecularWeight1;
  private MolecularWeight molecularWeight2;
  private MolecularWeight nullMW;
  private ParserAnswer parserAnswer;
  private String results;

  @Before
  public void SetUp() throws Exception{
    ec1 = new ECNumber(1,1,1,1);
    ec2 = new ECNumber("1.1.1.1");
    //A simple one
    aaSequence = new AASequence(
        "ALWTA", "something",
        new Literature(11111)
    );
    nullSequence = new AASequence();
    molecularWeight1 = new MolecularWeight(
      1000, "unique value",
        new Literature(11111)
    );
    molecularWeight2 = new MolecularWeight(
        1000, 2000,
        "range value",
        new Literature(11111)
    );
    nullMW = new MolecularWeight();
    parserAnswer = new ParserAnswer();
  }

  @Test
  public void toStringTest(){
    assertEquals("1.1.1.1", ec1.toString());
    assertEquals("1.1.1.1", ec2.toString());
    assertEquals(ec1.toString(), ec2.toString());
  }

  @Test
  public void equalTest(){
    assertEquals(ec1, ec2);
    assertNotSame(new Object(), ec1);
    assertNotSame(new Object(), ec2);
  }

  @Test
  public void gettersTest(){
    // EC Number
    assertEquals(null, ec1.getCommentary());
    assertEquals("ecNumber*1.1.1.1", ec1.getParameter());
    assertEquals("getEcNumber", ec1.getMethod());

    // AASequence
    assertEquals("something", aaSequence.getCommentary());
    assertEquals(new Literature(11111), aaSequence.getReferences().get(0));
    assertEquals("ALWTA", aaSequence.getSequence());
    assertEquals(5, aaSequence.getNumberOfAminoacids());
    assertEquals("getSequence", aaSequence.getMethod());
    assertEquals("sequence*ALWTA#noOfAminoAcids*5", aaSequence.getParameter());

    // MolecularWeight
    assertEquals("range value", molecularWeight2.getCommentary());
    assertEquals(Double.valueOf(1000), molecularWeight1.getValue());
    assertEquals(Double.valueOf(2000), molecularWeight2.getMax_value());
    assertEquals(new Literature(11111), molecularWeight1.getReferences().get(0));
    assertEquals("getMolecularWeight", molecularWeight1.getMethod());
    assertEquals("molecularWeight*1000.0#molecularWeightMaximum*#commentary*unique value", molecularWeight1.getParameter());
    assertEquals("molecularWeight*1000.0#molecularWeightMaximum*2000.0#commentary*range value", molecularWeight2.getParameter());
  }

  @Test
  public void settersTest(){
    // EC Number
    ec1.setCommentary("something");
    assertNotSame("something", ec1.getCommentary());
  }

  @Test
  public void defaultParametersTest(){
    // AASequence
    assertEquals("", nullSequence.getCommentary());
    assertEquals(0, nullSequence.getReferences().size());
    assertEquals("", nullSequence.getSequence());
    assertEquals(0, nullSequence.getNumberOfAminoacids());
    assertEquals("getSequence", nullSequence.getMethod());
    assertEquals("sequence*#noOfAminoAcids*0", nullSequence.getParameter());

    // MolecularWeight
    assertEquals("", nullMW.getCommentary());
    assertEquals(Double.NaN, nullMW.getValue());
    assertEquals(Double.NaN, nullMW.getMax_value());
    assertEquals(Double.NaN, molecularWeight1.getMax_value());
    assertEquals("getMolecularWeight", nullMW.getMethod());
    assertEquals("molecularWeight*#molecularWeightMaximum*#commentary*", nullMW.getParameter());
  }

  @Test
  public void setAttributeTest(){
    // AASequence
    results = "sequence*LLLLLL#noAminoAcids*6";
    nullSequence.setAttribute(
        parserAnswer.getResult(results).get(0)
    );
    assertEquals("LLLLLL", nullSequence.getSequence());
    assertEquals(6, nullSequence.getNumberOfAminoacids());
    assertEquals("sequence*LLLLLL#noOfAminoAcids*6", nullSequence.getParameter());

    // MolecularWeight
    results = "molecularWeight*10000#molecularWeightMaximum*#commentary*unique value for test#literature*11111";
    nullMW.setAttribute(
        parserAnswer.getResult(results).get(0)
    );
    assertEquals("unique value for test", nullMW.getCommentary());
    assertEquals(Double.valueOf(10000), nullMW.getValue());
    assertEquals(Double.NaN, nullMW.getMax_value());
    assertEquals("getMolecularWeight", nullMW.getMethod());
    assertEquals("molecularWeight*10000.0#molecularWeightMaximum*#commentary*unique value for test", nullMW.getParameter());
  }

}
