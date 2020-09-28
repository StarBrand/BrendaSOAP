package cl.pesb2.best.parsersoap.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Literature class with the brenda reference code
 * and the pubmed reference code if available
 *
 * @author Juan Saez Hidalgo
 */
public class Literature implements Table {

    private int brenda;
    private int pubmed;
    private String link;

    public Literature(){
        brenda = -1;
        pubmed = -1;
        link = "NULL";
    }

    public Literature(int brenda){
        this.brenda = brenda;
        pubmed = -1;
        link = "NULL";
    }

    /**
     * Set pubmed literature reference
     *
     * @param pmed PubMed number for abstract
     */
    public void setPubmed(int pmed){
        pubmed = pmed;
    }

    /**
     * Set Link from brenda or pubmed (the one available)
     */
    public void setLink(){
        if (pubmed == -1)
            link = String.format("https://www.brenda-enzymes.org/literature.php?r=%d", brenda);
        else
            link = String.format("https://www.ncbi.nlm.nih.gov/pubmed/?term=%d", pubmed);
    }

    @Override
    public String getTableName() {
        return "literature";
    }

    @Override
    public String getParameters() {
        return "brenda, pmed, link";
    }

    @Override
    public String getValues() {
        String pubmed = "NULL";
        if (this.pubmed != -1)
            pubmed = String.valueOf(this.pubmed);
        return String.format(
                "(%d, %s, '%s')", brenda, pubmed, link
        );
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            String[] value = parameter.split("\\*", 2);
            if (value[0].equals("pubmedId"))
                pubmed = Integer.parseInt(value[1]);
            setLink();
        }
    }

    @Override
    public String getParameter() {
        return String.valueOf(brenda);
    }

    @Override
    public String getMethod() {
        return "getReferenceById";
    }

    @Override
    public void getFromDB(ResultSet result) throws SQLException {
        if (result.next()){
            brenda = result.getInt("brenda");
            if (result.wasNull())
                brenda = -1;
            pubmed = result.getInt("pmed");
            if (result.wasNull())
                pubmed = -1;
            link = result.getString("link");
            if (result.wasNull())
                link = "NULL";
        }
    }

    /**
     * Get brenda number
     *
     * @return Brenda number
     */
    public int getBrenda() {
        return brenda;
    }

}
