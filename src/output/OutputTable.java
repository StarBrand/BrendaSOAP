package output;

import static java.lang.StrictMath.max;

import attributes.Attribute;
import client.User;
import entities.Protein;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.control.Tab;

/**
 * output.OutputTable class, generates the table in csv format
 * of a list of {@Link entities.Protein} and his
 * {@Link attributes.Attribute}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Protein
 * @see attributes.Attribute
 */
public class OutputTable {

  private List<Protein> proteins;
  private List<String> data;
  private List<String> protein_columns;
  private HashMap<String, List<String>> attributes_columns;
  private List<HashMap<String, String>> protein_rows;
  private HashMap<String, List <HashMap<String, String>>> attributes_rows;
  private User user;

  /**
   * Constructor
   */
  public OutputTable(User user){
    proteins = new ArrayList<Protein>();
    data = new ArrayList<String>();
    protein_columns = new ArrayList<String>();
    attributes_columns = new HashMap<String, List<String>>();
    protein_rows = new ArrayList<HashMap<String, String>>();
    attributes_rows = new HashMap<String, List<HashMap<String, String>>>();
    protein_columns.add("EC_Number");
    protein_columns.add("Systematic_name");
    protein_columns.add("Recommended_name");
    protein_columns.add("UniProt");
    protein_columns.add("Organism");
    protein_columns.add("Commentary");
    protein_columns.add("Literature(PubmedID)");
    protein_columns.add("Ref");
    this.user = user;
  }

  /**
   * Sets the proteins, this erase the previous ones
   *
   * @param protein None, one or more proteins
   */
  public void setProteins(Protein... protein){
    proteins = new ArrayList<Protein>();
    for(Protein p:protein){
      proteins.add(p);
    }
  }

  /**
   * Sets the proteins, this erase the previous ones
   *
   * @param proteins A list of protein
   */
  public void setProteins(List<Protein> proteins){
    this.proteins = proteins;
  }

  /**
   * Adds protein(s) to the list
   *
   * @param protein None, one or more proteins
   */
  public void addProtein(Protein... protein){
    for(Protein p:protein) {
      this.proteins.add(p);
    }
  }

  /**
   * Define the extra columns for the table
   *
   * @param attribute None, one or more attributes to show as columns
   */
  public void defineColumns(Attribute... attribute){
    for(Attribute a:attribute){
      this.data.add(a.getAttributeName());
    }
  }

  /**
   * Generates the rows to the output
   */
  public void generateRows() {
    for (Protein protein : proteins) {
      HashMap<String, String> row = new HashMap<String, String>();
      row.put(protein_columns.get(0), protein.getEnzyme().getEC().toString());
      row.put(protein_columns.get(1), protein.getEnzyme().getSystematic_name());
      row.put(protein_columns.get(2), protein.getEnzyme().getRecommended_name());
      row.put(protein_columns.get(3), protein.getUniprot());
      HashMap<String, String> organism = protein.getOrganism().rowsToTable();
      row.put(protein_columns.get(4), organism.get(protein_columns.get(4)));
      row.put(protein_columns.get(5), organism.get(protein_columns.get(5)));
      row.put(protein_columns.get(6), organism.get(protein_columns.get(6)));
      String ref = String.valueOf(proteins.indexOf(protein));
      row.put(protein_columns.get(7), ref);
      for (Attribute attribute : protein.getAttribute()) {
        String name = attribute.getAttributeName();
        if (data.contains(name)){
          try {
            HashMap<String, String> new_row = attribute.rowsToTable();
            new_row.put("Ref", ref);
            if (!attributes_columns.keySet().contains(name)) {
              List<String> columns = new ArrayList<String>();
              for (String column : new_row.keySet()) {
                columns.add(column);
              }
              attributes_columns.put(name, columns);
            }
            new_row.put("Attribute", name);
            if(attributes_rows.containsKey(name)){
              attributes_rows.get(name).add(new_row);
            }
            else{
              List<HashMap<String, String>> new_rows = new ArrayList<HashMap<String, String>>();
              new_rows.add(new_row);
              attributes_rows.put(name, new_rows);
            }
          } catch (NullPointerException e) {
          }
        }
      }
      protein_rows.add(row);
    }
  }

  public void proteins_out() throws IOException{
    (new File(user.getMail() + "_results")).mkdirs();
    out(user.getMail() + "_results\\table.txt", protein_columns, protein_rows);
  }

  public void attributes_out() throws IOException {
    (new File(user.getMail() + "_results\\attributes")).mkdirs();
    for(String name:attributes_columns.keySet()) {
      String file_name = user.getMail() + "_results\\attributes\\";
      out(file_name + name +".txt", attributes_columns.get(name), attributes_rows.get(name));
    }
  }

  private void out(String name, List<String> head_line, List<HashMap<String, String>> row_lines)  throws IOException {
    FileWriter file = new FileWriter(name);
    BufferedWriter bw = new BufferedWriter(file);
    List<String> header = new ArrayList<String>();
    List<String> line = new ArrayList<String>();
    for(String column:head_line){
      header.add(column);
    }
    TableWriter.write(header, "\t", bw);
    for(HashMap<String, String> row:row_lines){
      line.clear();
      for(String column:header){
        try {
          line.add(row.get(column));
        } catch (Exception e){
          line.add("NA");
        }
      }
      TableWriter.write(line, "\t", bw);
    }
    bw.close();
  }

  public void deleteLiteratureColumn(){
    for(HashMap<String, String> row:protein_rows){
      row.remove(protein_columns.get(6));
    }
    protein_columns.remove(6);
  }

  public List<String> getProtein_columns() {
    return protein_columns;
  }

  public List<HashMap<String, String>> getProtein_rows() {
    return protein_rows;
  }

  public HashMap<String, List<String>> getAttributes_columns() {
    return attributes_columns;
  }

  public HashMap<String, List<HashMap<String, String>>> getAttributes_rows() {
    return attributes_rows;
  }
}