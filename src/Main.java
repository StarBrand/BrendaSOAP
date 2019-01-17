import client.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import filters.Filter;
import filters.SequenceFilter;
import java.util.List;
import queries.ProteinQuery;
import queries.Query;

public class Main {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    /// Whatever
    Enzyme enzyme = new Enzyme(1,1,1,1);
    Query query = new ProteinQuery(new DefaultUser());
    query.setEntities(enzyme);
    List<Entity> proteins = (List<Entity>) query.getResult();
    System.out.print("Number of proteins: ");
    System.out.println(proteins.size());

    Filter filter = new SequenceFilter();
    filter.addEntities(proteins.toArray(new Protein[proteins.size()]));
    List<Entity> proteins_filtered = filter.getFiltered();
    System.out.print("Number of proteins with sequence: ");
    System.out.println(proteins_filtered.size());
    for(Entity entity:proteins_filtered){
      System.out.println(entity);
    }
    /// Until here

    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.print("It takes ");
    System.out.print(totalTime);
    System.out.println(" ms");

  }


}
