import client.DefaultUser;
import client.SoapClient;
import java.io.IOException;
import java.util.HashMap;

public class Main {

  public static void main(String... args) throws Exception {

    SoapClient client = new SoapClient(new DefaultUser());
    client.makeCall();

    String result = client.getResult(
        "ecNumber*3.2.1.8#firstAccessionCode*A0A0P0ISK8",
        "getSequence"
    );

    for(String observation:result.split("!")){
      System.out.println(observation);
    }

  }

}
