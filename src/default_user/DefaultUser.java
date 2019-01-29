package default_user;

import client.User;

/**
 * default_user.DefaultUser class for internal queries
 *
 * @author Juan Saez Hidalgo
 */
public class DefaultUser extends User {

  /**
   * Constructor has the default user
   */
  public DefaultUser() {
    super("jdoe@example.edu", "password123");
  }

}
