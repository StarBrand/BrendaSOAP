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
  public String getMail(){
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

  /**
   * Indicate if the user exist
   *
   * @return A String indicating the situation of the user
   */
  public String verifyAccount() throws Exception {
    SoapClient client = new SoapClient(this);
    client.makeCall();
    String result = client.getResult("ecNumber*1.1.1.1", "getSystematicName");
    String out;
    if(result.equals("Password incorrect! Please try again.")){
      out = "Incorrect password";
    } else if(result.equals("Unknown user. Please register. www.brenda-enzymes.org/register.php")){
      out = "Not a Brenda User";
    } else {
      out = "You can search your enzyme";
    }
    return out;
  }

}
