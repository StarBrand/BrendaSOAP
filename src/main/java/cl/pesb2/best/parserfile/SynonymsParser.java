package cl.pesb2.best.parserfile;

import cl.pesb2.best.dbconnection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse synonyms text
 *
 * @author Juan Saez Hidalgo
 */
public class SynonymsParser implements Parser{

    private String id;
    private List<Synonym> synonymsList;

    protected void setEcNumber(String ecNumber){
        id = ecNumber;
        synonymsList = new ArrayList<>();
    }

    @Override
    public void parse(String text) throws Exception {
        StringBuilder oneSynonym = new StringBuilder();
        for(String line:text.split("\n")) {
            if(line.split("\t")[0].equals("SY")) {
                if (oneSynonym.toString().equals(""))
                        oneSynonym.append(line);
                else {
                    Synonym synonym = new Synonym(this.id);
                    synonym.parseFromResult(oneSynonym.toString());
                    synonymsList.add(synonym);
                    oneSynonym = new StringBuilder(line);
                }
            } else oneSynonym.append(" ").append(line.split("\t", 2)[1]);
        }
    }

    public void save(Connection conn) throws SQLException {
        List<String> values = new ArrayList<>();
        Synonym aSynonym = new Synonym(this.id);
        ResultSet check = conn.executeQuery(
                String.format("SELECT synonyms FROM synonyms WHERE ec_number = '%s';", id)
        );
        for(Synonym synonym:synonymsList) {
            synonym.getFromDB(check);
            if (synonym.isNew())
                values.add(synonym.getValues());
        } conn.saveValues(values, aSynonym.getParameters(), aSynonym.getTableName());
    }

}
