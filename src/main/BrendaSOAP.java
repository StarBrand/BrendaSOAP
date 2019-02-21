package main;

import attributes.enzyme_structure.MolecularWeight;
import attributes.functional_parameters.IC50Value;
import attributes.functional_parameters.KIValue;
import attributes.functional_parameters.Kcat;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHOptimum;
import attributes.functional_parameters.PHRange;
import attributes.functional_parameters.PIValue;
import attributes.functional_parameters.SpecificActivity;
import attributes.functional_parameters.TemperatureOptimum;
import attributes.functional_parameters.TemperatureRange;
import attributes.functional_parameters.TurnoverNumber;
import client.User;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import filters.Filter;
import filters.SequenceFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import output.FastaQuery;
import output.OutputTable;
import output.PdbQuery;
import queries.FillLiterature;
import queries.ParameterQuery;
import queries.ProteinQuery;
import queries.Query;

/**
 * The main class to be used in the web application
 *
 * @author Juan Saez Hidalgo
 */
public class BrendaSOAP {

  private List<String> enzymeECnumber;
  private User user;
  private Query query;
  private OutputTable outputTable;
  private List<Entity> proteins;
  private FillLiterature fillLiterature;
  private boolean mw = false;
  private boolean ic50 = false;
  private boolean kc = false;
  private boolean ki = false;
  private boolean km = false;
  private boolean pho = false;
  private boolean phr = false;
  private boolean pi = false;
  private boolean sa = false;
  private boolean to = false;
  private boolean tr = false;
  private boolean ton = false;
  private boolean up = false;

  public BrendaSOAP(String enzyme, User user, int parameters, int filters){
    this.enzymeECnumber = new ArrayList<String>();
    this.enzymeECnumber.add(enzyme);
    this.user = user;
    decoder(parameters, true);
    decoder(filters, false);
  }

  private void decoder(int conditions, boolean parameter){
    if (parameter){
      int cond = conditions % 2;
      if(cond == 1){
        this.mw = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.ic50 = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.kc = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.ki = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.km = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.pho = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.phr = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.pi = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.sa = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.to = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.tr = true;
      }
      conditions = conditions / 2;
      cond = conditions % 2;
      if(cond == 1){
        this.ton = true;
      }
    }
    else {
      if (conditions == 1){
        this.up = true;
      }
    }
  }

  public void getProtein() throws Exception {
    try {
      File folder = new File(user.getMail() + "_results\\attributes");
      String files[] = folder.list();
      for(String file:files){
        File toDelete = new File(folder, file);
        toDelete.delete();
      }
    }catch(Exception e){}
    List<Enzyme> enzymes = new ArrayList<Enzyme>();
    for(String ec:enzymeECnumber) {
      enzymes.add(new Enzyme(ec, user));
    }
    query = new ProteinQuery(user);
    outputTable = new OutputTable(user);
    for(Enzyme enzyme:enzymes) {
      query.setEntities(enzyme);
    }
    proteins = (List<Entity>) query.getResult();
    for (Entity protein:proteins) {
      outputTable.addProtein((Protein) protein);
    }
    outputTable.generateRows();
    outputTable.deleteLiteratureColumn();
    outputTable.proteins_out();
  }

  public void getParameters(int[] selected, boolean all_of_them) throws Exception {
    query = new ParameterQuery(user);
    outputTable = new OutputTable(user);
    if(mw){
      query.addAttributes(new MolecularWeight());
      outputTable.defineColumns(new MolecularWeight());
    }
    if(ic50){
      query.addAttributes(new IC50Value());
      outputTable.defineColumns(new IC50Value());
    }
    if(kc){
      query.addAttributes(new Kcat());
      outputTable.defineColumns(new Kcat());
    }
    if(ki){
      query.addAttributes(new KIValue());
      outputTable.defineColumns(new KIValue());
    }
    if(km){
      query.addAttributes(new Km());
      outputTable.defineColumns(new Km());
    }
    if(pho){
      query.addAttributes(new PHOptimum());
      outputTable.defineColumns(new PHOptimum());
    }
    if(phr){
      query.addAttributes(new PHRange());
      outputTable.defineColumns(new PHRange());
    }
    if(pi){
      query.addAttributes(new PIValue());
      outputTable.defineColumns(new PIValue());
    }
    if(sa){
      query.addAttributes(new SpecificActivity());
      outputTable.defineColumns(new SpecificActivity());
    }
    if(to){
      query.addAttributes(new TemperatureOptimum());
      outputTable.defineColumns(new TemperatureOptimum());
    }
    if(tr){
      query.addAttributes(new TemperatureRange());
      outputTable.defineColumns(new TemperatureRange());
    }
    if(ton){
      query.addAttributes(new TurnoverNumber());
      outputTable.defineColumns(new TurnoverNumber());
    }
    if(!all_of_them){
      List<Entity> selected_proteins = new ArrayList<Entity>();
      for(int i:selected){
        selected_proteins.add(proteins.get(i));
      }
      proteins = selected_proteins;
    }
    if(up) {
      Filter filter = new SequenceFilter();
      for (Entity protein : proteins) {
        filter.addEntities(protein);
      }
      proteins = filter.getFiltered();
    }
    for(Entity protein:proteins) {
      query.setEntities(protein);
    }
    proteins = (List<Entity>) query.getResult();
    fillLiterature = new FillLiterature(user);
    for(Entity protein:proteins) {
      fillLiterature.addProteins((Protein) protein);
    }
    List<Protein> proteins = fillLiterature.fill();
    for (Protein protein:proteins) {
      outputTable.addProtein(protein);
    }
    outputTable.generateRows();
    outputTable.proteins_out();
    outputTable.attributes_out();
  }

  public void getFastaSequence() throws Exception {
    query = new FastaQuery(user);
    List<Entity> proteins_to_fasta;
    Filter filter = new SequenceFilter();
    for(Entity protein:proteins){
      filter.addEntities(protein);
    }
    proteins_to_fasta = filter.getFiltered();
    for(Entity protein:proteins_to_fasta){
      query.setEntities(protein);
    }
    query.getResult();
    ((FastaQuery) query).generateFile();
  }

  public void getPDB() throws Exception {
    query = new PdbQuery(user);
    for(Entity protein:proteins){
      query.setEntities(protein);
    }
    query.getResult();
    ((PdbQuery) query).generateFile();
  }

  public void addEnzyme(String enzyme){
    this.enzymeECnumber.add(enzyme);
  }
}
