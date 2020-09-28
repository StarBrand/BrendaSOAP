package cl.pesb2.best.parsersoap.client;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import java.rmi.RemoteException;
import java.security.MessageDigest;

/**
 * SoapClient class, this generates the main.client
 * user, and service of the SOAP to make the main.queries
 * on Brenda
 *
 * @author Juan Saez Hidalgo
 */
public class SoapClient {
  private final User user;
  private Call call;
  private StringBuffer hexString;

  private static final int LIMIT = 20;

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
    byte[] byteData = md.digest();
    hexString = new StringBuffer();

    for (byte byteDatum : byteData) {
      String hex = Integer.toHexString(0xff & byteDatum);
      if (hex.length() == 1) hexString.append('0');
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
   */
  public String getResult(String parameter, String method, boolean retrying){
    String parameters = user.getMail()+","+hexString+","+parameter;
    call.setOperationName(new QName("http://soapinterop.org/", method));
    boolean retrieved = false;
    String result = "";
    String add = "";
    if (retrying)
      add = ", retrying...";
    int retries = 0;
    while(!retrieved) {
      try {
        result = (String) call.invoke(new Object[]{parameters});
        retrieved = true;
      } catch (RemoteException e) {
        if (retries >= LIMIT) {
          e.printStackTrace();
          break;
        } else if (retrying) {
          if (retries == 0)
            System.err.printf("Problem with brenda.wsdl%s\n", add);
          retries++;
        } else {
          e.printStackTrace();
          break;
        }
      }
    }
    return result;
  }
}