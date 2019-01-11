package client;

public class User {

  private String mail;
  private String password;

  public User(String userMail, String userPassword){
    mail = userMail;
    password = userPassword;
  }

  String getMail(){
    return mail;
  }

  String getPassword(){
    return password;
  }

}
