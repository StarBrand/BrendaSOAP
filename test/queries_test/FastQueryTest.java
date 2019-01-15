package queries_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNotSame;

import attributes.enzyme_estrucuture.ECNumber;
import client.DefaultUser;
import client.User;
import org.junit.Before;
import org.junit.Test;
import queries.FastQuery;
import queries.Query;
import queries.RecommendedNameQuery;
import queries.SystematicNameQuery;

public class FastQueryTest {
  private FastQuery systematic_name;
  private FastQuery recommended_name;
  private User user;
  private ECNumber ec1;
  private ECNumber ec2;
  private ECNumber ec3;

  @Before
  public void SetUp() throws Exception{
    systematic_name = new SystematicNameQuery(user);
    recommended_name = new RecommendedNameQuery(user);
    user = new DefaultUser();
    ec1 = new ECNumber("1.1.1.1");
    ec2 = new ECNumber("1.1.1.2");
    ec3 = new ECNumber("1.1.1.3");
  }

  @Test
  public void setAttributesTest(){
    assertEquals(0, systematic_name.numberOfAttributes());
    assertEquals(0, recommended_name.numberOfAttributes());
    systematic_name.addAttributes(ec1);
    recommended_name.addAttributes(ec1);
    assertEquals(1, systematic_name.numberOfAttributes());
    assertEquals(1, recommended_name.numberOfAttributes());
    recommended_name.addAttributes(ec2);
    systematic_name.addAttributes(ec2);
    recommended_name.addAttributes(ec3);
    systematic_name.addAttributes(ec3);
    assertEquals(3, systematic_name.numberOfAttributes());
    assertEquals(3, recommended_name.numberOfAttributes());
  }

  @Test
  public void getAnswerTest() throws Exception{
    //needed before every query
    systematic_name = new SystematicNameQuery(user);
    systematic_name.addAttributes(ec1);

    assertNotNull(systematic_name.getResult().get(0));
    assertEquals(systematic_name.getResult(), ( (FastQuery) systematic_name).getResult("getSystematicName", "systematicName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.addAttributes(ec1);

    assertNotNull(recommended_name.getResult());

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.addAttributes(ec1);

    assertEquals(recommended_name.getResult(), ( (FastQuery) recommended_name).getResult("getRecommendedName", "recommendedName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.addAttributes(ec1);

    assertNotSame(recommended_name.getResult(), ( (FastQuery) recommended_name).getResult("getSystematicName", "systematicName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.addAttributes(ec1);
    recommended_name.addAttributes(ec2);
    recommended_name.addAttributes(ec3);

    assertEquals(3, recommended_name.getResult().size());
  }

}