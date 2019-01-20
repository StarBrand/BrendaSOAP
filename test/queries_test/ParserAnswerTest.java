package queries_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import queries.ParserAnswer;

public class ParserAnswerTest {

  private ParserAnswer parserAnswer;
  private String queryResult;
  private String oneListQueryResult;
  private String nullQueryResult;
  private List<HashMap<String, String>> parsedResult1;
  private List<HashMap<String, String>> parsedResult2;

  @Before
  public void SetUp(){
    oneListQueryResult = "ecNumber*1.1.1.1#ic50Value*1.7#ic50ValueMaximum*#inhibitor*guanidine hydrochloride#commentary*wild type enzyme#organism*Sulfolobus solfataricus#ligandStructureId*804#literature*678121#";
    queryResult = "ecNumber*1.1.1.1#ic50Value*1.7#ic50ValueMaximum*#inhibitor*guanidine hydrochloride#commentary*wild type enzyme#organism*Sulfolobus solfataricus#ligandStructureId*804#literature*678121#!ecNumber*1.1.1.1#ic50Value*2.2#ic50ValueMaximum*#inhibitor*guanidine hydrochloride#commentary*mutant enzyme N249Y#organism*Sulfolobus solfataricus#ligandStructureId*804#literature*678121#";
    nullQueryResult = "";
    parserAnswer = new ParserAnswer();
  }

  @Test
  public void parseTest(){
    parsedResult1 = parserAnswer.getResult(oneListQueryResult);
    assertEquals(1,parsedResult1.size());

    parsedResult2 = parserAnswer.getResult();
    assertEquals(parsedResult1, parsedResult2);

    parsedResult1 = parserAnswer.getResult(queryResult);
    assertEquals(2, parsedResult1.size());

    hashMapTest(parsedResult1.get(0));
    hashMapTest(parsedResult2.get(0));

    assertEquals("1.1.1.1", parsedResult1.get(1).get("ecNumber"));
    assertEquals("2.2", parsedResult1.get(1).get("ic50Value"));
    assertEquals("mutant enzyme N249Y", parsedResult1.get(1).get("commentary"));
  }

  public void hashMapTest(HashMap<String, String> parsedResult){
    assertEquals("1.1.1.1", parsedResult.get("ecNumber"));
    assertEquals("1.7", parsedResult.get("ic50Value"));
    assertEquals("wild type enzyme", parsedResult.get("commentary"));
  }

  @Test
  public void nullTest(){
    parsedResult1 = parserAnswer.getResult(nullQueryResult);
    assertEquals(1, parsedResult1.size());
    assertEquals(1, parsedResult1.get(0).size());
    assertEquals("",parsedResult1.get(0).get(""));
  }

}
