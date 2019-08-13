package queries_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queries.TaxonomyQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaxonomyQueryTest {

    private TaxonomyQuery query;
    private HashMap<Integer, String> rows;
    private List<String> inputRows;
    private String first;
    private String second;
    private String third;

    @Before
    public void setUp(){
        /*
        NCBI.db not available on Github due to
        storage restriction
         */
        query = new TaxonomyQuery("NCBI.db");
        rows = new HashMap<Integer, String>();
        inputRows = new ArrayList<String>();
    }

    @After
    public void close() throws SQLException {
        query.close();
    }

    @Test
    public void getSpeciesTest(){
        rows.put(0, "Homo sapiens sapiens");
        rows.put(1, "Cellulomonas gilvus");
        rows.put(2, "Xanthobacter viscosus Doronina and Trotsenko 2003");
        HashMap<Integer, String> actual = query.getSpecies(rows);
        assertEquals("Homo sapiens", actual.get(0));
        assertEquals("Cellulomonas gilvus", actual.get(1));
        assertEquals("Xanthobacter viscosus", actual.get(2));
    }

    @Test
    public void getGenusTest(){
        defineInput("Homo sapiens", "Cellulomonas gilvus", "Xanthobacter viscosus");
        HashMap<String, String> actual = query.getGenus(inputRows);
        assertEquals("Homo", actual.get(first));
        assertEquals("Cellulomonas", actual.get(second));
        assertEquals("Xanthobacter", actual.get(third));
    }

    @Test
    public void getFamilyTest() throws SQLException {
        defineInput("Homo", "Cellulomonas", "Xanthobacter");
        HashMap<String, String> actual = query.getFamily(inputRows);
        assertEquals("Hominidae", actual.get(first));
        assertEquals("Cellulomonadaceae", actual.get(second));
        assertEquals("Xanthobacteraceae", actual.get(third));
    }

    @Test
    public void getOrderTest() throws SQLException {
        defineInput("Hominidae", "Cellulomonadaceae", "Xanthobacteraceae");
        HashMap<String, String> actual = query.getOrder(inputRows);
        assertEquals("Primates", actual.get(first));
        assertEquals("Micrococcales", actual.get(second));
        assertEquals("Rhizobiales", actual.get(third));
    }

    @Test
    public void getClassTest() throws SQLException {
        defineInput("Primates", "Micrococcales", "Rhizobiales");
        HashMap<String, String> actual = query.getClass(inputRows);
        assertEquals("Mammalia", actual.get(first));
        assertEquals("Actinobacteria", actual.get(second));
        assertEquals("Alphaproteobacteria", actual.get(third));
    }

    @Test
    public void getPhylumTest() throws SQLException {
        defineInput("Mammalia", "Actinobacteria", "Alphaproteobacteria");
        HashMap<String, String> actual = query.getPhylum(inputRows);
        assertEquals("Chordata", actual.get(first));
        assertEquals("Actinobacteria", actual.get(second));
        assertEquals("Proteobacteria", actual.get(third));
    }

    @Test
    public void getSuperkingdomTest() throws SQLException {
        defineInput("Chordata", "Actinobacteria", "Proteobacteria");
        HashMap<String, String> actual = query.getSuperkingdom(inputRows);
        assertEquals("Eukaryota", actual.get(first));
        assertEquals("Bacteria", actual.get(second));
        assertEquals("Bacteria", actual.get(third));
    }

    private void defineInput(String first, String second, String third){
        this.first = first;
        this.second = second;
        this.third = third;
        inputRows.add(first);
        inputRows.add(second);
        inputRows.add(third);
    }

}
