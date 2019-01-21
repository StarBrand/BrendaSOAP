import attributes.Attribute;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHOptimum;
import attributes.functional_parameters.PHRange;
import attributes.functional_parameters.TemperatureRange;
import client.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import filters.Filter;
import filters.SequenceFilter;
import java.lang.ref.Reference;
import java.util.List;
import queries.FastaQuery;
import queries.FillLiterature;
import queries.ParameterQuery;
import queries.ProteinQuery;
import queries.Query;

public class Main {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    /// Whatever

    Enzyme enzyme = new Enzyme("3.2.1.8",new DefaultUser());
    Query query;
    query = new ProteinQuery(new DefaultUser());
    query.setEntities(enzyme);
    System.out.print("Buscando proteínas...");
    List<Protein> proteins = (List<Protein>) query.getResult();
    System.out.print(" Proteinas encontradas: ");
    System.out.println(proteins.size());

    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    long flagTime = System.currentTimeMillis();
    System.out.print("Tomó: ");
    System.out.println(showTime(totalTime));

    Filter filter;
    filter = new SequenceFilter();
    for(Protein protein:proteins){
      filter.addEntities(protein);
    }
    System.out.print("Filtrando proteínas...");
    List<Entity> proteins_filtered = filter.getFiltered();
    System.out.print(" Proteinas filtradas: ");
    System.out.println(proteins_filtered.size());

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("Tomó: ");
    System.out.println(showTime(totalTime));

    FastaQuery fastaQuery = new FastaQuery(new DefaultUser());
    for(Entity protein:proteins_filtered) {
      fastaQuery.setEntities(protein);
    }
    System.out.print("Buscando secuencias...");
    fastaQuery.getResult();
    System.out.print("Generando archivo...");
    fastaQuery.generateFile();
    System.out.print(" Archivo generado!! ");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("Tomó: ");
    System.out.println(showTime(totalTime));

    query = new ParameterQuery(new DefaultUser());
    for(Entity protein:proteins_filtered) {
      query.setEntities(protein);
    }
    query.addAttributes(new PHRange(), new PHOptimum(), new Km(), new TemperatureRange());
    System.out.print("Buscando atributos...");
    proteins = (List<Protein>) query.getResult();
    System.out.println(" Atributos encontrados!!");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("Tomó: ");
    System.out.println(showTime(totalTime));

    FillLiterature fillLiterature = new FillLiterature(new DefaultUser());
    for(Protein protein:proteins) {
      fillLiterature.addProteins(protein);
    }
    System.out.print("Rellenando bibliografía...");
    proteins = fillLiterature.fill();
    System.out.println("... Bibliografía completa");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("Tomó: ");
    System.out.println(showTime(totalTime));

    System.out.println("Mostrar resultados:");

    for(Protein protein:proteins){
      System.out.println(protein);
      for(Attribute attribute:protein.getAttribute()){
        System.out.print(attribute.getParameter());
        for(Literature reference:attribute.getReferences()){
          System.out.println(reference);
        }
      }
    }

    /// Until here

    endTime   = System.currentTimeMillis();
    totalTime = endTime - startTime;
    System.out.print("Finalmente, tomó: ");
    System.out.print(showTime(totalTime));
  }

  private static String showTime(long time){
    String toPrint;
    long toNext;
    toPrint = String.valueOf(time%1000) + " ms";
    toNext = time/1000;
    toPrint = String.valueOf(toNext%60) + " s, " + toPrint;
    toNext = toNext/60;
    toPrint = String.valueOf(toNext%60) + " m, " + toPrint;
    toNext = toNext/60;
    toPrint = String.valueOf(toNext) + " hr, " + toPrint;
    return toPrint;
  }

}
