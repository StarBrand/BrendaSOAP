package attributes.funcional_parameters;

import attributes.Attribute;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AASequence implements Attribute {

  private Enzyme enzyme;
  private Protein protein;
  private ArrayList<Literature> references;


  public AASequence(Enzyme aEnzyme, Protein aProtein){
    enzyme = aEnzyme;
    protein = aProtein;
  }

  public Enzyme getEnzyme() {
    return enzyme;
  }

  public Protein getProtein() {
    return protein;
  }

  @Override
  public ArrayList<Literature> getReferences() {
    return references;
  }

  @Override
  public String getMethod() {
    return null;
  }

  /**
   * Write the sequence in FASTA format
   * @throws IOException
   */
  public void getFASTA() throws IOException {
    FileWriter write = new FileWriter("fasta_output.txt", false);
    PrintWriter print_line = new PrintWriter(write);
    print_line.printf("%s" + "%n", "Provisorio");
    print_line.close();
  }

  /**
   * Append the sequence in FASTA format
   * @throws IOException
   */
  public void addFASTA() throws IOException {
    FileWriter write = new FileWriter("fasta_output.txt", true);
    PrintWriter print_line = new PrintWriter(write);
    print_line.printf("%s" + "%n", "Provisorio 2");
    print_line.close();
  }
}
