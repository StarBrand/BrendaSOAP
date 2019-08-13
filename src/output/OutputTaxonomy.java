package output;

import attributes.organism_related_information.Taxonomy;
import client.User;
import queries.TaxonomyQuery;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * OutputTaxonomy, the main class than look for taxonomy
 * and save the resulting table on disc
 *
 * @author Juan Saez Hidalgo
 */
public class OutputTaxonomy {

    private File inputPath;
    private File outputPath;
    private TaxonomyQuery query;
    private HashMap<Integer, Taxonomy> data;

    /**
     * Constructor
     *
     * @param user the user, needed to define folder of input and output
     * @param path the path of the local database
     */
    public OutputTaxonomy(User user, String path){
        File folder = new File("results", user.getMail());
        inputPath = new File(folder, "table.txt");
        outputPath = new File(folder, "taxonomy.txt");
        query = new TaxonomyQuery(path);
        data = new HashMap<Integer, Taxonomy>();
    }

    /**
     * Gets data
     *
     * @return data
     */
    public HashMap<Integer, Taxonomy> getData() {
        return data;
    }

    /**
     * Generate taxonomy table
     */
    public void out() throws IOException {
        FileWriter file = new FileWriter(outputPath);
        BufferedWriter writer = new BufferedWriter(file);
        String line = "Ref\tspecies\tgenus\tfamily\torder\tclass\tphylum\tsuperkingdom";
        writer.write(line);
        writer.newLine();
        for (int ref:data.keySet()){
            line = String.format("%d\t%s",
                    ref,
                    data.get(ref).toRow());
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        file.close();
    }

    /**
     * Fill the data with taxonomic information
     *
     * @param rows hash with ref on input table and organism
     * @throws SQLException
     */
    public void fillTaxonomy(HashMap<Integer, String> rows) throws SQLException {
        HashMap<Integer, String> species = query.getSpecies(rows);
        HashMap<String, String> genus = query.getGenus(
                new ArrayList<String>(species.values())
        );
        HashMap<String, String> families = query.getFamily(
                new ArrayList<String>(genus.values())
        );
        HashMap<String, String> orders = query.getOrder(
                new ArrayList<String>(families.values())
        );
        HashMap<String, String> classes = query.getClass(
                new ArrayList<String>(orders.values())
        );
        HashMap<String, String> phylums = query.getPhylum(
                new ArrayList<String>(classes.values())
        );
        HashMap<String, String> kingdoms = query.getSuperkingdom(
                new ArrayList<String>(phylums.values())
        );
        for (int ref:rows.keySet()){
            Taxonomy organism = new Taxonomy();
            organism.setSpecies(species.get(ref));
            organism.setGenus(genus.get(organism.getSpecies()));
            organism.setFamily(families.get(organism.getGenus()));
            organism.setOrder(orders.get(organism.getFamily()));
            organism.setPhylogenetic_class(classes.get(organism.getOrder()));
            organism.setPhylum(phylums.get(organism.getPhylogenetic_class()));
            organism.setSuperkingdom(kingdoms.get(organism.getPhylum()));
            data.put(ref, organism);
        }
    }

    /**
     * Extract organism from table
     *
     * @return Hash with ref, organism tuple
     */
    public HashMap<Integer, String> extractOrganisms() {
        HashMap<Integer, String> output = new HashMap<Integer, String>();
        try {
            FileReader file = new FileReader(inputPath);
            BufferedReader reader = new BufferedReader(file);
            List<String> header = Arrays.asList(reader.readLine().split("\t"));
            int ref = header.indexOf("Ref");
            int organism = header.indexOf("Organism");
            String line = reader.readLine();
            while (line != null){
                String[] row = line.split("\t");
                output.put(Integer.valueOf(row[ref]), row[organism]);
                line = reader.readLine();
            }
            reader.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void close() throws SQLException {
        query.close();
    }
}
