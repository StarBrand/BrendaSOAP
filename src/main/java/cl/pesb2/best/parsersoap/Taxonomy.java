package cl.pesb2.best.parsersoap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Taxonomy Class, which stored taxonomy information of a
 * species
 *
 * @author Juan Saez Hidalgo
 */
public class Taxonomy{

    private String attributeName = "Taxonomy";
    private String species = "";
    private String genus = "";
    private String family = "";
    private String order = "";
    private String phylogenetic_class = "";
    private String phylum = "";
    private String superkingdom = "";

    /**
     * Constructor with no parameters
     */
    public Taxonomy() {

    }

    /**
     * Constructor given any parameter
     *
     * @param species Name of the species
     * @param genus Corresponding genus
     * @param family Corresponding family
     * @param order Corresponding order
     * @param phylogenetic_class Corresponding class
     * @param phylum Corresponding phylum
     * @param superkingdom Corresponding kingdom
     */
    public Taxonomy(String species, String genus, String family, String order,
                    String phylogenetic_class, String phylum, String superkingdom) {
        this.species = species;
        this.genus = genus;
        this.family = family;
        this.order = order;
        this.phylogenetic_class = phylogenetic_class;
        this.phylum = phylum;
        this.superkingdom = superkingdom;
    }

    /**
     * Gets species
     *
     * @return species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Gets genus
     *
     * @return genus
     */
    public String getGenus() {
        return genus;
    }

    /**
     * Gets family
     *
     * @return family
     */
    public String getFamily() {
        return family;
    }

    /**
     * Gets order
     *
     * @return order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Gets class
     *
     * @return class
     */
    public String getPhylogenetic_class() {
        return phylogenetic_class;
    }

    /**
     * Gets phylum
     *
     * @return phylum
     */
    public String getPhylum() {
        return phylum;
    }

    /**
     * Gets superkingdom
     *
     * @return superkingdom
     */
    public String getSuperkingdom() {
        return superkingdom;
    }

    /**
     * Sets species
     *
     * @param species species name
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Sets genus
     *
     * @param genus genus name
     */
    public void setGenus(String genus) {
        this.genus = genus;
    }

    /**
     * Sets family
     *
     * @param family family name
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * Sets order
     *
     * @param order order name
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Gets class
     *
     * @param phylogenetic_class class name
     */
    public void setPhylogenetic_class(String phylogenetic_class) {
        this.phylogenetic_class = phylogenetic_class;
    }

    /**
     * Sets phylum
     *
     * @param phylum phylum name
     */
    public void setPhylum(String phylum) {
        this.phylum = phylum;
    }

    /**
     * Sets superkingdom
     *
     * @param superkingdom superkingdom name
     */
    public void setSuperkingdom(String superkingdom) {
        this.superkingdom = superkingdom;
    }

    /**
     * Unused method due to taxonomy is not search
     * by Brenda SOAP
     *
     * @return An empty string
     */
    public String getMethod() {
        return "";
    }

    /**
     * Unused method due to taxonomy is not search
     * by Brenda SOAP
     *
     * @return An empty string
     */
    public String getParameter() {
        return "";
    }

    public List<String> getColumns() {
        String[] columns = new String[]{"species", "genus", "family",
                "order", "class", "phylum", "superkingdom"};
        return Arrays.asList(columns);
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public Object clone(){
        Taxonomy cloned;
        try{
            cloned = (Taxonomy) super.clone();
        } catch (Exception e) {
            cloned = null;
        }
        return cloned;
    }

    public HashMap<String, String> rowsToTable() {
        HashMap<String, String> out = new HashMap<String, String>();
        out.put("species", species);
        out.put("genus", genus);
        out.put("family", family);
        out.put("order", order);
        out.put("class", phylogenetic_class);
        out.put("phylum", phylum);
        out.put("superkingdom", superkingdom);
        return out;
    }

    /**
     * Return data as single row for write to
     * table on disk
     *
     * @return A row with all taxonomic information
     */
    public String toRow(){
        String line = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s",
                textOrNA(species),
                textOrNA(genus),
                textOrNA(family),
                textOrNA(order),
                textOrNA(phylogenetic_class),
                textOrNA(phylum),
                textOrNA(superkingdom));
        line = line.replace("null", "NA");
        return line;
    }

    private static String textOrNA(String text){
        if (text != null) return text;
        else return "NA";
    }

}
