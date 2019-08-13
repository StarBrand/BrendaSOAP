package output_test;

import attributes.organism_related_information.Taxonomy;
import client.User;
import default_user.DefaultUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import output.OutputTaxonomy;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class OutputTaxonomyTest {

    private OutputTaxonomy outputTaxonomy;
    private OutputTaxonomy nullOutput;

    @Before
    public void setUp(){
        outputTaxonomy = new OutputTaxonomy(new DefaultUser(), "NCBI.db");
        nullOutput = new OutputTaxonomy(new User("false@email.com", "pass"), "NCBI.db");
    }

    @After
    public void close() throws SQLException {
        outputTaxonomy.close();
        nullOutput.close();
    }

    @Test
    public void extractOrganismsTest(){
        HashMap<Integer, String> organism = outputTaxonomy.extractOrganisms();
        assertEquals(3, organism.size());
        assertEquals("Acinetobacter calcoaceticus", organism.get(0));
        assertEquals("Arthrobacter sp.", organism.get(1));
        assertEquals("Fusarium graminearum", organism.get(2));
        HashMap<Integer, String> noOrganism = nullOutput.extractOrganisms();
        assertEquals(0, noOrganism.size());
    }

    @Test
    public void fillTaxonomyTest() throws SQLException {
        HashMap<Integer, String> organism = new HashMap<Integer, String>();
        organism.put(0, "Acinetobacter calcoaceticus");
        organism.put(1, "Arthrobacter sp.");
        organism.put(2, "Fusarium graminearum");
        HashMap<Integer, Taxonomy> data = outputTaxonomy.getData();
        for (Taxonomy datum:data.values()){
            assertNull(datum.getSpecies());
            assertNull(datum.getGenus());
            assertNull(datum.getFamily());
            assertNull(datum.getOrder());
            assertNull(datum.getPhylogenetic_class());
            assertNull(datum.getPhylum());
            assertNull(datum.getSuperkingdom());
        }
        outputTaxonomy.fillTaxonomy(organism);
        data = outputTaxonomy.getData();
        for(Taxonomy datum:data.values()){
            assertNotNull(datum.getSpecies());
            assertNotNull(datum.getGenus());
            assertNotNull(datum.getFamily());
            assertNotNull(datum.getOrder());
            assertNotNull(datum.getPhylogenetic_class());
            assertNotNull(datum.getPhylum());
            assertNotNull(datum.getSuperkingdom());
        }
    }

    @Test
    public void generateTableTest() throws SQLException, IOException {
        outputTaxonomy.fillTaxonomy(outputTaxonomy.extractOrganisms());
        outputTaxonomy.out();
        File file = new File(new File("results", new DefaultUser().getMail()), "taxonomy.txt");
        assert(file.exists());
    }
}
