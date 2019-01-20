import attributes.organism_related_information.Organism;
import client.DefaultUser;
import client.SoapClient;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
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
    Protein protein1 = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Ampullaria crossean",
            "",
            new Literature(695295)
        ),
        "Q7Z1V6"
    );
    Protein protein2 = new Protein(
        new Enzyme("1.1.1.1", new DefaultUser()),
        new Organism(
            "Homo sapiens",
            ""
        ),
        "P40394"
    );
    Protein noSequenceProtein = new Protein(
        new Enzyme("3.2.1.8", new DefaultUser()),
        new Organism(
            "Acacia verek",
            "",
            new Literature(171611)
        ),
        ""
    );

    SoapClient client = new SoapClient(new DefaultUser());
    client.makeCall();
    String result = client.getResult(
        protein1.getParameter(),
        "getSequence"
        );
    System.out.print("Result of");
    System.out.println(protein1);
    for (String ans:result.split("!")){
      System.out.println(ans);
    }

    client = new SoapClient(new DefaultUser());
    client.makeCall();
    result = client.getResult(
        protein2.getParameter(),
        "getSequence"
    );
    System.out.print("Result of");
    System.out.println(protein2);
    for (String ans:result.split("!")){
      System.out.println(ans);
    }

    client = new SoapClient(new DefaultUser());
    client.makeCall();
    result = client.getResult(
        noSequenceProtein.getParameter(),
        "getSequence"
    );
    System.out.print("Result of");
    System.out.println(noSequenceProtein);
    for (String ans:result.split("!")){
      System.out.println(ans);
    }

    /// Until here

    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.print("It takes ");
    System.out.print(totalTime);
    System.out.println(" ms");

  }


}
