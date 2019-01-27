package output_test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import attributes.Attribute;
import attributes.functional_parameters.Km;
import attributes.functional_parameters.PHRange;
import attributes.functional_parameters.SpecificActivity;
import attributes.organism_related_information.Organism;
import DefaultUser;
import entities.Enzyme;
import entities.Literature;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import output.OutputTable;
import entities.Protein;

public class OutputTableTest {

  private OutputTable outputTable;
  private Protein protein1;
  private Protein protein2;
  private Attribute attribute1;
  private Attribute attribute2;
  private Attribute attribute3;
  private Attribute attribute4;
  private Protein protein3;

  @Before
  public void SetUp() throws Exception {
    outputTable = new OutputTable();
    protein1 = new Protein(
        new Enzyme(1,1,1,1,new DefaultUser()),
        new Organism("Acinetobacter calcoaceticus", "", new Literature(659674)),
        ""
    );
    protein2 = new Protein(
        new Enzyme(3,2,1,8,new DefaultUser()),
        new Organism("Arthrobacter sp.", "isolated from the feces of Grus nigricollis", new Literature(737475)),
        "A0A0F6MV31"
    );
    protein3 = new Protein(
        new Enzyme(4,1,1,1, new DefaultUser()),
        new Organism("Fusarium graminearum", "gene FG09834.1", new Literature(727549)),
        "V6RPS5"
    );
    attribute1 = new PHRange(5, 8, "");
    attribute2 = new Km(10, "ethanol", "");
    attribute3 = new PHRange(6, 7, "");
    attribute4 = new SpecificActivity(0.01, "");
    protein1.addAttributes(attribute1, attribute2, attribute3, attribute4);
    protein2.addAttributes(attribute1, attribute2, attribute3, attribute4);
  }


  @Test
  public void oneProteinOneAttribute(){
    outputTable.defineColumns(attribute1);
    outputTable.addProtein(protein1);
    outputTable.generateRows();
    List<String> columns = outputTable.getColumns();
    List<HashMap<String, String>> rows = outputTable.getRows();

    assertEquals(6 + 1*3, columns.size());
    assertEquals(1, rows.size());

    assertEquals(2, rows.get(0).get("pH Range value").split(";").length);
  }

  @Test
  public void twoProteinsTwoAttributes(){
    outputTable.defineColumns(attribute1, attribute2);
    outputTable.addProtein(protein1, protein2);
    outputTable.generateRows();
    List<String> columns = outputTable.getColumns();
    List<HashMap<String, String>> rows = outputTable.getRows();

    assertEquals(6 + /* Km */ 4 + /* pH */ 3, columns.size());
    assertEquals(2, rows.size());

    assertEquals(2, rows.get(0).get("pH Range value").split(";").length);
    assertEquals(2, rows.get(1).get("pH Range value").split(";").length);
    assertEquals(1, rows.get(0).get("Km value").split(";").length);
    assertEquals(1, rows.get(1).get("Km value").split(";").length);
  }

  @Test
  public void twoProteinsThreeAttribute(){
    outputTable.defineColumns(attribute1, attribute2, attribute3);
    outputTable.addProtein(protein1, protein2);
    outputTable.generateRows();
    List<String> columns = outputTable.getColumns();
    List<HashMap<String, String>> rows = outputTable.getRows();

    assertEquals(6 + /* Km */ 4 + /* pH Range */ 3, columns.size());
    assertEquals(2, rows.size());

    assertEquals(1, rows.get(0).get("Km value").split(";").length);
    assertEquals(1, rows.get(1).get("Km value").split(";").length);
    assertEquals(2, rows.get(0).get("pH Range value").split(";").length);
    assertEquals(2, rows.get(1).get("pH Range value").split(";").length);

  }

  @Test
  public void twoProteinFourAttribute(){
    outputTable.defineColumns(attribute1, attribute2, attribute3, attribute4);
    outputTable.addProtein(protein1, protein2);
    outputTable.generateRows();
    List<String> columns = outputTable.getColumns();
    List<HashMap<String, String>> rows = outputTable.getRows();

    assertEquals(6 + /* Km */ 4 + /* pH Range */ 3 + /* Spec. Ac. */ 3, columns.size());
    assertEquals(2, rows.size());

    assertEquals(2, rows.get(0).get("pH Range value").split(";").length);
    assertEquals(2, rows.get(1).get("pH Range value").split(";").length);
    assertEquals(1, rows.get(0).get("Km value").split(";").length);
    assertEquals(1, rows.get(1).get("Km value").split(";").length);
    assertEquals(1, rows.get(0).get("Specific Activity value").split(";").length);
    assertEquals(1, rows.get(1).get("Specific Activity value").split(";").length);
  }

  @Test
  public void twoProteinsVariableAttribute(){
    protein3.addAttributes(attribute2, attribute3);
    outputTable.defineColumns(attribute1, attribute2, attribute3, attribute4);
    outputTable.addProtein(protein1, protein3);
    outputTable.generateRows();
    List<String> columns = outputTable.getColumns();
    List<HashMap<String, String>> rows = outputTable.getRows();

    assertEquals(6 + /* Km */ 4 + /* pH Range */ 3 + /*Spec. Act.*/ 3, columns.size());
    assertEquals(2, rows.size());

    assertEquals(2, rows.get(0).get("pH Range value").split(";").length);
    assertEquals(1, rows.get(1).get("pH Range value").split(";").length);
    assertEquals(1, rows.get(0).get("Km value").split(";").length);
    assertEquals(1, rows.get(1).get("Km value").split(";").length);
    assertEquals(1, rows.get(0).get("Specific Activity value").split(";").length);
    try {
      assertEquals(0, rows.get(1).get("Specific Activity value").split(";").length);
    } catch (NullPointerException e){
      assertTrue(true);
    }
  }

  @Test
  public void generateFileTest() throws IOException {
    protein3.addAttributes(attribute2, attribute3);
    outputTable.defineColumns(attribute1, attribute2, attribute3, attribute4);
    outputTable.addProtein(protein1, protein2, protein3);
    outputTable.generateRows();
    outputTable.out();
  }

}