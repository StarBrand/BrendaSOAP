package client;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import javax.xml.namespace.QName;
import java.security.MessageDigest;

public class SoapClient {
  private User user;
  private Call call;
  private StringBuffer hexString;

  public SoapClient(User aUser){
    user = aUser;
  }

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

  public String getResult(String parameter, String method) throws Exception{
    String parameters = user.getMail()+","+hexString+parameter;
    call.setOperationName(new QName("http://soapinterop.org/", method));
    return (String) call.invoke( new Object[] {parameters} );
  }
}