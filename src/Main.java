import attributes.enzyme_estrucuture.ECNumber;
import client.DefaultUser;
import client.SoapClient;
import client.User;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.List;
import queries.ProteinQuery;
import queries.Query;

public class Main {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    Enzyme enzyme_type = new Enzyme("3.2.1.8");
    Query query = new ProteinQuery(new DefaultUser());
    List<Protein> proteins = ((ProteinQuery) query).getProteins(enzyme_type);
    for (Protein protein:proteins){
      System.out.println(protein);
    }
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.print("It takes ");
    System.out.print(totalTime);
    System.out.println(" ms");

  }


}
