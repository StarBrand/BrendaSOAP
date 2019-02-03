package main;

import attributes.enzyme_structure.MolecularWeight;
import attributes.functional_parameters.IC50Value;
import attributes.functional_parameters.Kcat;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHOptimum;
import attributes.functional_parameters.PHRange;
import attributes.functional_parameters.PIValue;
import attributes.functional_parameters.SpecificActivity;
import attributes.functional_parameters.TemperatureOptimum;
import attributes.functional_parameters.TemperatureRange;
import attributes.functional_parameters.TurnoverNumber;
import default_user.DefaultUser;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import filters.Filter;
import filters.SequenceFilter;
import java.util.List;
import output.FastaQuery;
import output.OutputTable;
import output.PdbQuery;
import queries.FillLiterature;
import queries.ParameterQuery;
import queries.ProteinQuery;
import queries.Query;

public class XylaseExample {

  public static void main (String... args) throws Exception{

    long startTime = System.currentTimeMillis();

    /// Whatever

    Enzyme enzyme = new Enzyme("3.2.1.8",new DefaultUser());
    Query query;
    query = new ProteinQuery(new DefaultUser());
    query.setEntities(enzyme);
    System.out.print("Searching for proteins...");
    List<Protein> proteins = (List<Protein>) query.getResult();

    System.out.print(" It has been found ");
    System.out.print(proteins.size());
    System.out.println(" proteins");

    long endTime = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    long flagTime = System.currentTimeMillis();
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    PdbQuery pdbQuery = new PdbQuery(new DefaultUser());
    for(Entity protein:proteins){
      pdbQuery.setEntities(protein);
    }

    System.out.print("Searching for PDBs...");
    pdbQuery.getResult();
    System.out.print(" Generating file...");
    pdbQuery.generateFile();
    System.out.println(" File has been generated!! ");

    endTime = System.currentTimeMillis();
    totalTime = endTime - startTime;
    flagTime = System.currentTimeMillis();
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    Filter filter;
    filter = new SequenceFilter();
    for(Protein protein:proteins){
      filter.addEntities(protein);
    }
    System.out.print("Filtering proteins...");
    List<Entity> proteins_filtered = filter.getFiltered();
    System.out.print(" Filtered Proteins: ");
    System.out.println(proteins_filtered.size());

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    FastaQuery fastaQuery = new FastaQuery(new DefaultUser());
    for(Entity protein:proteins_filtered) {
      fastaQuery.setEntities(protein);
    }
    System.out.print("Searching for sequences...");
    fastaQuery.getResult();
    System.out.print(" Generating file...");
    fastaQuery.generateFile();
    System.out.println(" File has been generated!! ");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    query = new ParameterQuery(new DefaultUser());
    for(Entity entity:proteins_filtered){
      query.setEntities(entity);
    }
    query.addAttributes(
        new MolecularWeight(),
        new IC50Value(),
        new Kcat(),
        new Km(),
        new PHOptimum(),
        new PHRange(),
        new PIValue(),
        new SpecificActivity(),
        new TemperatureOptimum(),
        new TemperatureRange(),
        new TurnoverNumber()
    );
    System.out.print("Searching for attributes...");
    proteins = (List<Protein>) query.getResult();
    System.out.println(" Attributes have been found!!");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    flagTime = System.currentTimeMillis();
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    FillLiterature fillLiterature = new FillLiterature(new DefaultUser(),false);
    for(Protein protein:proteins) {
      fillLiterature.addProteins(protein);
    }
    System.out.print("Filling reference...");
    proteins = fillLiterature.fill();
    System.out.println("... Reference has completed");

    endTime = System.currentTimeMillis();
    totalTime = endTime - flagTime;
    System.out.print("It took: ");
    System.out.println(showTime(totalTime));

    System.out.println("Showing results...");

    OutputTable table = new OutputTable(new DefaultUser());
    table.setProteins(proteins);
    table.defineColumns(
        new MolecularWeight(),
        new IC50Value(),
        new Kcat(),
        new Km(),
        new PHOptimum(),
        new PHRange(),
        new PIValue(),
        new SpecificActivity(),
        new TemperatureOptimum(),
        new TemperatureRange(),
        new TurnoverNumber()
    );
    table.generateRows();
    table.proteins_out();
    table.attributes_out();

    /// Until here

    endTime   = System.currentTimeMillis();
    totalTime = endTime - startTime;
    System.out.print("Finally, it took: ");
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