package output;

import static java.lang.StrictMath.max;

import attributes.Attribute;
import entities.Protein;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
  private List<String> columns;
  private List<HashMap<String, String>> rows;

  /**
   * Constructor
   */
  public OutputTable(){
    proteins = new ArrayList<Protein>();
    data = new ArrayList<String>();
    columns = new ArrayList<String>();
    rows = new ArrayList<HashMap<String, String>>();
    columns.add("EC Number");
    columns.add("Systematic Name");
    columns.add("Recommended Name");
    columns.add("Organism");
    columns.add("Organism Commentary");
    columns.add("Organism Literature (Pubmed ID)");
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
      row.put(columns.get(0), protein.getEnzyme().getEC().toString());
      row.put(columns.get(1), protein.getEnzyme().getSystematic_name());
      row.put(columns.get(2), protein.getEnzyme().getRecommended_name());
      HashMap<String, String> organism = protein.getOrganism().rowsToTable();
      row.put(columns.get(3), organism.get(columns.get(3)));
      row.put(columns.get(4), organism.get(columns.get(4)));
      row.put(columns.get(5), organism.get(columns.get(5)));
      for (Attribute attribute : protein.getAttribute()) {
        try {
          HashMap<String, String> new_rows = attribute.rowsToTable();
          if (data.contains(attribute.getAttributeName())) {
            if (row.keySet().contains(attribute.getAttributeName() + " value")) {
              for (String column : new_rows.keySet()) {
                try {
                  String value = row.get(column);
                  value += "; " + new_rows.get(column);
                  row.put(column, value);
                } catch (Exception e) {
                  row.put(column, "");
                }
              }
            } else {
              for (String column : new_rows.keySet()) {
                row.put(column, new_rows.get(column));
                if(!columns.contains(column)){
                  columns.add(column);
                }
              }
            }
          }
        } catch (NullPointerException e){}
      }
      rows.add(row);
    }
  }

  public void out() throws IOException{
    String line, header = "";
    FileWriter file = new FileWriter("table.txt");
    BufferedWriter bf = new BufferedWriter(file);
    for(String column:columns){
      header += column + "\t";
    }
    header = header.substring(0, max(header.length() - 1, 0));
    bf.write(header);
    bf.newLine();
    for(HashMap<String, String> row:rows){
      line = "";
      for(String column:header.split("\t")){
        try {
          line += row.get(column) + "\t";
        } catch (Exception e){
          line += "NA" + "\t";
        }
      }
      line = line.substring(0, max(line.length() - 1, 0));
      bf.write(line);
      bf.newLine();
    }
    bf.close();
  }

  public List<HashMap<String, String>> getRows() {
    return rows;
  }

  public List<String> getColumns() {
    return columns;
  }
}