package cl.pesb2.best.parserfile;

import cl.pesb2.best.dbconnection.Connection;
import cl.pesb2.best.parsersoap.tables.Table;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Parse a part of the file that corresponds to
 * one EC Number
 *
 * @author Juan Saez Hidalgo
 */
public class ECNumberParser implements Parser, Table {

    private String id;
    private int topLevel;
    private int subClass;
    private int subSubClass;
    private int serialDigit;
    private String recommendedName;
    private String systematicName;
    private final SynonymsParser synonymsParser;

    public ECNumberParser(){
        id = "";
        synonymsParser = new SynonymsParser();
    }

    @Override
    public void parse(String text) throws Exception {
        boolean save = false;
        String textSynonyms = "";
        for(String line:text.split("\n")){
            String[] terms = line.split("\t");
            if(terms[0].equals("ID"))
                parseECNumber(terms[1]);
            else if(terms[0].equals("RN"))
                this.parseRecommendedName(terms[1]);
            else if(terms[0].equals("SN"))
                this.parseSystematicName(line);
            else if(terms[0].equals("SYNONYMS"))
                save = true;
            else if (save){
                if (line.equals("")){
                    save = false;
                    synonymsParser.parse(textSynonyms);
                } else textSynonyms += line + "\n";
            }
        }
    }

    private void parseECNumber(String text) throws Exception {
        String ec = text.split(" ")[0];
        if (checkEC(ec)) id = ec;
        else throw new Exception(String.format("Wrong EC number detected: %s", ec));
        synonymsParser.setEcNumber(ec);
    }

    private boolean checkEC(String ecCandid){
        String[] digits = ecCandid.split("\\.");
        if (digits.length != 4) return false;
        else{
            try {
                topLevel = Integer.parseInt(digits[0]);
                subClass = Integer.parseInt(digits[1]);
                subSubClass = Integer.parseInt(digits[2]);
                serialDigit = Integer.parseInt(digits[3]);
                return true;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException exception){
                return false;
            }
        }
    }

    private void parseRecommendedName(String text){
        recommendedName = text;
    }

    private void parseSystematicName(String text){
        String[] terms = text.split("\t", 2);
        if (terms[1].equals("")) systematicName = "NULL";
        else systematicName = terms[1];
    }

    @Override
    public String getTableName() {
        return "ec_number";
    }

    @Override
    public String getParameters() {
        return "ec_number, top_level, subclass, subsubclass, serial_digit, systematic_name, recommended_name";
    }

    @Override
    public String getValues() {
        String systematicName = String.format("'%s'", this.systematicName);
        if (this.systematicName.equals("NULSL")) systematicName = this.systematicName;
        return String.format("('%s', %d, %d, %d, %d, %s, '%s')",
                id, topLevel, subClass, subSubClass, serialDigit, systematicName, recommendedName);
    }

    @Override
    public void parseFromResult(String result) throws Exception {}

    @Override
    public String getParameter() {
        return null;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public void getFromDB(ResultSet result) {

    }

    public void save() throws IOException, SQLException {
        Connection conn = new Connection();
        conn.connect();
        conn.saveValue(String.format("(%d, %d, %d)", topLevel, subClass, subSubClass),
                "top_level, subclass, subsubclass", "ec_subsubclass");
        conn.saveValue(getValues(), getParameters(), getTableName());
        synonymsParser.save(conn);
        conn.close();
    }

    public String getECNumber() {
        return id;
    }
}
