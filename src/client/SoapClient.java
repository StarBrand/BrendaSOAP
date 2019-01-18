package client;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import java.security.MessageDigest;

/**
 * SoapClient class, this generates the client
 * user, and service of the SOAP to make the queries
 * on Brenda
 *
 * @author Juan Saez Hidalgo
 */
public class SoapClient {
  private User user;
  private Call call;
  private StringBuffer hexString;

  /**
   * The constructor given the user
   *
   * @param user the user in User Class format
   */
  public SoapClient(User user){
    this.user = user;
  }

  /**
   * Do the previous protocol for a query
   *
   * @throws Exception Call SOAP Exception
   */
  public void makeCall() throws Exception {
    Service service = new Service();
    call = (Call) service.createCall();
    String endpoint = "https://www.brenda-enzymes.org/soap/brenda_server.php";
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(user.getPassword().getBytes());
    byte byteData[] = md.digest();
    hexString = new StringBuffer();

    for (int i = 0; i < byteData.length; i++){
      String hex = Integer.toHexString(0xff & byteData[i]);
      if(hex.length()==1) hexString.append('0');
      hexString.append(hex);
    }

    call.setTargetEndpointAddress( new java.net.URL(endpoint) );
  }

  /**
   * Gets the result of the query
   *
   * @param parameter The parameter of the query
   * @param method    The method of the query
   * @return The result in the Brenda format
   * @throws Exception Call Exception
   */
  public String getResult(String parameter, String method) throws Exception{
    String parameters = user.getMail()+","+hexString+","+parameter;
    call.setOperationName(new QName("http://soapinterop.org/", method));
    return (String) call.invoke( new Object[] {parameters} );
  }
}