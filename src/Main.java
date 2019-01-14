import client.DefaultUser;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.List;
import queries.ProteinQuery;
import queries.Query;

public class Main {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    Enzyme enzyme_type = new Enzyme(3,2,1,8);
    Query query = new ProteinQuery(new DefaultUser());
    List<Protein> proteins = ((ProteinQuery) query).getProteins(enzyme_type);
    List<Protein> proteins_filter = new ArrayList<Protein>();
    System.out.print("Número de proteinas: ");
    System.out.println(proteins.size());
    for (Protein protein:proteins){
      if (!protein.getUniprot().equals("")){
        proteins_filter.add(protein);
      }
    }
    System.out.print("Número de proteinas filtradas: ");
    System.out.println(proteins_filter.size());
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.print("It takes ");
    System.out.print(totalTime);
    System.out.println(" ms");

  }


}
