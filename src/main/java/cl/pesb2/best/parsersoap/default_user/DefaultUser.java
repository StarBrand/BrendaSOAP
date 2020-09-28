package cl.pesb2.best.parsersoap.default_user;

import cl.pesb2.best.parsersoap.client.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * main.default_user.DefaultUser class for internal main.queries
 *
 * @author Juan Saez Hidalgo
 */
public class DefaultUser extends User {

  /**
   * Constructor has the default user
   */
  public DefaultUser() throws IOException {
    super("", "");
    Properties properties = getProperties();
    super.setMail(properties.getProperty("brenda_user"));
    super.setPassword(properties.getProperty("brenda_password"));
  }

  private Properties getProperties() throws IOException {
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
    Properties properties = new Properties();
    properties.load(inputStream);
    return properties;
  }

}
