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
  private Query systematic_name;
  private Query recommended_name;
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
    assertEquals(0, systematic_name.numberOfAttibutes());
    assertEquals(0, recommended_name.numberOfAttibutes());
    systematic_name.setAttributes(ec1);
    recommended_name.setAttributes(ec1);
    assertEquals(1, systematic_name.numberOfAttibutes());
    assertEquals(1, recommended_name.numberOfAttibutes());
    recommended_name.setAttributes(ec2);
    systematic_name.setAttributes(ec2);
    recommended_name.setAttributes(ec3);
    systematic_name.setAttributes(ec3);
    assertEquals(3, systematic_name.numberOfAttibutes());
    assertEquals(3, recommended_name.numberOfAttibutes());
  }

  @Test
  public void getAnswerTest() throws Exception{
    //needed before every query
    systematic_name = new SystematicNameQuery(user);
    systematic_name.setAttributes(ec1);

    assertNotNull(systematic_name.getAnswer().get(0));
    assertEquals(systematic_name.getAnswer(), ( (FastQuery) systematic_name).getAnswer("getSystematicName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.setAttributes(ec1);

    assertNotNull(recommended_name.getAnswer());

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.setAttributes(ec1);

    assertEquals(recommended_name.getAnswer(), ( (FastQuery) recommended_name).getAnswer("getRecommendedName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.setAttributes(ec1);

    assertNotSame(recommended_name.getAnswer(), ( (FastQuery) recommended_name).getAnswer("getSystematicName"));

    //needed before every query
    recommended_name = new RecommendedNameQuery(user);
    recommended_name.setAttributes(ec1);
    recommended_name.setAttributes(ec2);
    recommended_name.setAttributes(ec3);

    assertEquals(3, recommended_name.getAnswer().size());
  }

}