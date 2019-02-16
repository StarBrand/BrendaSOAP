package queries_test;

import static junit.framework.TestCase.assertEquals;

import client.User;
import default_user.DefaultUser;
import org.junit.Before;
import org.junit.Test;

public class VerifyAccountTest {

  private User no_user;
  private User wrong_password;

  @Before
  public void SetUp(){
    no_user = new User("jdoe@example.edu", "password");
    wrong_password = new User("juan.saez.hidalgo@gmail.com", "password");
  }

  @Test
  public void verifyTest() throws Exception {
    String result = no_user.verifyAccount();
    assertEquals("Not a Brenda User",result);

    result = wrong_password.verifyAccount();
    assertEquals("Incorrect password", result);

    result = new DefaultUser().verifyAccount();
    assertEquals("You can search your enzyme", result);
  }

}
