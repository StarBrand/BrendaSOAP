package queries;

import attributes.Attribute;
import entities.Entity;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * TaxonomyQuery class who complete the data of
 * {@Link attributes.organism_related_information.Taxonomy}
 * Class from an Organism Name
 *
 * @author Juan Saez Hidalgo
 * @see attributes.organism_related_information.Taxonomy
 */
public class TaxonomyQuery implements Query {

    private Connection connection;
    private String errorMessage;

    private void connect(String path) throws SQLException {
        String currentDir = System.getProperty("user.dir");
        String url = "jdbc:sqlite:" + currentDir + "/" + path;
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            this.connection = null;
            throw e;
        }
    }

    public void close() throws SQLException {
        if (isConnected()) this.connection.close();
    }

    public TaxonomyQuery(String path){
        try {
            connect(path);
            errorMessage = "";
        } catch (SQLException e) {
            errorMessage = e.getMessage();
        }
    }

    public boolean isConnected(){
        return this.connection != null;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setEntities(Entity... entity) {}

    public void addAttributes(Attribute... attribute) {}

    public int numberOfAttributes() {
        return 0;
    }

    public List<?> getResult() throws Exception {
        return null;
    }

    public HashMap<Integer, String> getSpecies(HashMap<Integer, String> rows){
        HashMap<Integer, String> output = new HashMap<Integer, String>();
        for (int row:rows.keySet()){
            String[] organism = rows.get(row).split(" ");
            if (organism.length >= 2) {
                output.put(row, organism[0] + " " + organism[1]);
            } else if (organism.length == 1){
                output.put(row, organism[0]);
            } else{
                output.put(row, null);
            }
        }
        return output;
    }

    public HashMap<String, String> getGenus(List<String> spp){
        HashMap<String, String> output = new HashMap<String, String>();
        for (String species:spp){
            if (species != null){
                output.put(species, species.split(" ")[0]);
            }
        }
        return output;
    }

    private HashMap<String, String> getNextRank(List<String> ranks, String nextRankName) throws SQLException {
        String sql = "SELECT n1.name_txt, n3.rank FROM names n2 JOIN nodes n4 ON n2.tax_id = n4.tax_id "
                + "JOIN nodes n3 ON n4.parent_tax_id = n3.tax_id JOIN names n1 ON n3.tax_id = n1.tax_id "
                + "WHERE n2.name_txt = ? AND n1.name_class = 'scientific name' AND n2.name_class = 'scientific name'";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        HashMap<String, String> output = new HashMap<String, String>();
        for (String name:ranks){
            String nextRank;
            String rank = "";
            String previous_rank = "";
            String nextName = name;
            while(!rank.equals(nextRankName)) {
                statement.setString(1, nextName);
                ResultSet result = statement.executeQuery();
                nextName = result.getString("name_txt");
                rank = result.getString("rank");
                while (nextName.equals(name) & previous_rank.equals(rank)){
                    result.next();
                    nextName = result.getString("name_txt");
                    rank = result.getString("rank");
                }
                previous_rank = rank;
                if (nextName.equals("root")){
                    nextName = null;
                    break;
                }
            }
            nextRank = nextName;
            output.put(name, nextRank);
        }
        return output;
    }

    public HashMap<String, String> getFamily(List<String> genuses) throws SQLException {
        return getNextRank(genuses, "family");
    }

    public HashMap<String, String> getOrder(List<String> families) throws SQLException {
        return getNextRank(families, "order");
    }

    public HashMap<String, String> getClass(List<String> orders) throws SQLException {
        return getNextRank(orders, "class");
    }

    public HashMap<String, String> getPhylum(List<String> classes) throws SQLException {
        return getNextRank(classes, "phylum");
    }

    public HashMap<String, String> getSuperkingdom(List<String> phylums) throws SQLException {
        return getNextRank(phylums, "superkingdom");
    }
}
