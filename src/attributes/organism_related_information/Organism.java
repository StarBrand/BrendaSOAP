package attributes.organism_related_information;

import attributes.AbstractAttribute;
import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Organism class, in which the protein is
 *
 * @author Juan Saez Hidalgo
 */
public class Organism extends AbstractAttribute {

    private String attributeName = "Organism";
    private String name = "";
    private String genre = "";
    private String species = "";
    private String species_commentary = "";

    /**
     * An empty constructor
     * letting the parameters for default (empty lists, empty string,
     * Nan numbers) to be filled with a query
     */
    public Organism(){

    }

    /**
     * The constructor given the scientific name, a commentary and (a list
     * of) reference ({@Link entities.Literature})
     *
     * @param scientific_name The scientific name of the organism
     * @param commentary A commentary of the Attribute
     * @param reference (A list of) reference of the Attribute
     * @see entities.Literature
     */
    public Organism(String scientific_name, String commentary, Literature... reference){
        super(commentary, reference);
        name = scientific_name;
        String[] names = scientific_name.split(" ");
        genre = names[0];
        try{
            species = names[1];
        } catch (Exception e){

        }
        try{
            species_commentary = names[2];
        } catch (Exception e){

        }
    }

    /**
     * Sets the scientific name of the organism
     *
     * @param name The Scientific name
     */
    public void setName(String name) {
        this.name = name;
        String[] names = name.split(" ");
        this.genre = names[0];
        try {
            this.species = names[1];
        } catch (Exception e){
            this.species = "";
        }
        try{
            species_commentary = names[2];
        } catch (Exception e){
            species_commentary = "";
        }
    }

    /**
     * Gets the scientific name
     *
     * @return the scientific name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the genre of the name (calculated from
     * the scientific name)
     *
     * @return Genre of the species
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the specific name of species
     * sp. means every species of the genre
     *
     * @return species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Gets commentary about the species
     * code for the general
     *
     * @return the species commentary
     */
    public String getSpecies_commentary() {
        return species_commentary;
    }

    public String getMethod() {
        return "getOrganism";
    }

    public String getParameter() {
        return "organism*" + name;
    }

    public List<String> getColumns() {
        String[] columns = new String[]{"organism","sequenceCode","commentary","literature"};
        return Arrays.asList(columns);
    }

    public void setAttribute(HashMap<String, String> resultOfQuery) {
        setName(resultOfQuery.get("organism"));
        super.setAttribute(resultOfQuery);
    }

    @Override
    public Object clone(){
        Organism cloned;
        try{
            cloned = (Organism) super.clone();
        } catch (Exception e) {
            cloned = null;
        }
        return cloned;
    }

    public HashMap<String, String> rowsToTable() {
        HashMap<String, String> out = super.rowsToTable();
        out.put(attributeName, name);
        return out;
    }

    public String getAttributeName() {
        return attributeName;
    }
}