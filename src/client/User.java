package client;

/**
 * User class
 * The object that saves the user
 * and password of the user of main.BrendaSOAP
 *
 * @author Juan Saez Hidalgo
 */
public class User {

  private String mail;
  private String password;

  /**
   * The constructor given mail and password
   * (password of a Brenda account)
   *
   * @param userMail      The mail of the user
   * @param userPassword  The password of the user
   */
  public User(String userMail, String userPassword){
    mail = userMail;
    password = userPassword;
  }

  /**
   * Gets the mail to sing in on Brenda
   *
   * @return mail
   */
  String getMail(){
    return mail;
  }

  /**
   * Gets the password to sign in on Brenda
   *
   * @return
   */
  String getPassword(){
    return password;
  }

}
