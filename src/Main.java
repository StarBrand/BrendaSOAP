import attributes.enzyme_estrucuture.ECNumber;
import client.DefaultUser;
import client.SoapClient;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;
import queries.ProteinQuery;
import queries.Query;

public class Main {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    /// Whatever

    SoapClient client = new SoapClient(new DefaultUser());
    client.makeCall();
    String result = client.getResult(",ecNumber*1.1.1.1#organism*Homo sapiens","getSequence");
    System.out.println(result);

    /// Until here

    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.print("It takes ");
    System.out.print(totalTime);
    System.out.println(" ms");

  }


}
