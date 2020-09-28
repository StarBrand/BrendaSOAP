package cl.pesb2.best.parsersoap.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Enzyme implements Table{

    private String ec_number;
    private String organism;
    private String organism_commentary;
    private String commentary;
    private String uniprot;
    private int literature;

    public Enzyme(){
        ec_number = "NULL";
        organism = "NULL";
        organism_commentary = "NULL";
        commentary = "NULL";
        uniprot = "NULL";
        literature = -1;
    }

    public Enzyme(String ec_number){
        this.ec_number = ec_number;
        organism = "NULL";
        organism_commentary = "NULL";
        commentary = "NULL";
        uniprot = "NULL";
        literature = -1;
    }

    public Enzyme(String ec_number, String organism){
        this.ec_number = ec_number;
        organism_commentary = organism;
        this.organism = getOrganism(organism);
        commentary = "NULL";
        uniprot = "NULL";
        literature = -1;
    }

    private String getOrganism(String organism){
        String[] words = organism.split(" ");
        if (words.length == 1)
            return organism;
        else if (words.length >= 2)
            return String.format("%s %s", words[0], words[1]);
        else
            return "";
    }

    @Override
    public String getTableName() {
        return "enzymes";
    }

    @Override
    public String getParameters() {
        return "ec_number, organism, organism_commentary, commentary, uniprot, literature";
    }

    @Override
    public String getValues() {
        String literature = String.valueOf(this.literature);
        if (this.literature == -1)
            literature = "NULL";
        String commentary = String.format("'%s'", preventErrorQuotes(this.commentary));
        if (this.commentary.equals("NULL"))
            commentary = "NULL";
        String uniprot = String.format("'%s'", preventErrorQuotes(this.uniprot));
        if (this.uniprot.equals("NULL"))
            uniprot = "NULL";
        return String.format(
                "('%s', '%s', '%s', %s, %s, %s)",
                ec_number, preventErrorQuotes(organism),
                preventErrorQuotes(organism_commentary),
                commentary, uniprot, literature
        );
    }

    private String preventErrorQuotes(String text){
        return text.replace("'", "''");
    }

    @Override
    public void parseFromResult(String result) throws Exception {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            String[] value = parameter.split("\\*", 2);
            switch (value[0]) {
                case "organism":
                    organism_commentary = value[1];
                    organism = getOrganism(value[1]);
                    break;
                case "commentary":
                    if (!value[1].equals(""))
                        commentary = value[1];
                    break;
                case "sequenceCode":
                    if (!value[1].equals(""))
                        uniprot = value[1];
                    break;
                case "literature":
                    try{
                        literature = Integer.parseInt(value[1]);
                    } catch (NumberFormatException e){
                        throw new Exception(value[1]);
                    }
                    break;
            }
        }
    }

    @Override
    public String getParameter() {
        return String.format("ecNumber*%s", ec_number);
    }

    @Override
    public String getMethod() {
        return "getOrganism";
    }

    @Override
    public void getFromDB(ResultSet result) throws SQLException {
        if (result.next()){
            ec_number = result.getString("ec_number");
            if (result.wasNull())
                ec_number = "NULL";
            organism = result.getString("organism");
            if (result.wasNull())
                organism = "NULL";
            organism_commentary = result.getString("organism_commentary");
            if (result.wasNull())
                organism_commentary = "NULL";
            commentary = result.getString("commentary");
            if (result.wasNull())
                commentary = "NULL";
            uniprot = result.getString("uniprot");
            if (result.wasNull())
                uniprot = "NULL";
            literature = result.getInt("literature");
            if (result.wasNull())
                literature = -1;
        }
    }

    public Cloneable clone() throws CloneNotSupportedException {
        return (Cloneable) super.clone();
    }

}
