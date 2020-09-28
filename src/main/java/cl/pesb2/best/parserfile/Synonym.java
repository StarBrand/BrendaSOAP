package cl.pesb2.best.parserfile;

import cl.pesb2.best.parsersoap.tables.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Synonym object of brenda and parse
 * one synonym of EC Number
 *
 * @author Juan Saez Hidalgo
 */
public class Synonym implements Table {

    private static class ParsedText {

        private String text;

        private ParsedText(String text) {
            this.text = text;
        }

        private ParsedText extractSy() throws Exception {
            String[] terms = text.split("\t", 2);
            if(!terms[0].equals("SY")){
                throw new Exception(String.format("Wrong text as synonyms:\n\"%s\"", text));
            } text = terms[1];
            return this;
        }

        private ParsedText extractProteinReference(){
            if(text.charAt(text.length() - 1) == '>'){
                int first = text.lastIndexOf('<');
                int last = text.lastIndexOf('>');
                String test = text.substring(first + 1, last - 1);
                try{
                    for(String numberCandid:test.split(","))
                        Integer.parseInt(numberCandid);
                    text = text.substring(0, first - 1);
                    return this;
                } catch (Exception exception){
                    return this;
                }
            } else return this;
        }

        private ParsedText extractLiteratureReference(){
            String[] terms = text.split(" ", 2);
            text = terms[1];
            return this;
        }

        public ParsedText extractCommentary() {
            if(text.charAt(text.length() - 1) == ')'){
                int first = text.lastIndexOf('(');
                int last = text.lastIndexOf(')');
                String test = text.substring(first + 1, last);
                if(test.charAt(0) == '#' && test.charAt(test.length() - 1) == '>')
                    text = text.substring(0, first - 1);
            } return this;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private final String ec;
    String synonym;
    private boolean already;

    public Synonym(String ecNumber){
        ec = ecNumber;
        already = false;
    }

    @Override
    public String getTableName() {
        return "synonyms";
    }

    @Override
    public String getParameters() {
        return "ec_number, synonyms";
    }

    @Override
    public String getValues() {
        return String.format("('%s', '%s')", ec, preventErrorQuotes(synonym));
    }

    @Override
    public void parseFromResult(String result) throws Exception {
        synonym = new ParsedText(result)
                .extractSy()
                .extractProteinReference()
                .extractLiteratureReference()
                .extractCommentary()
                .toString();
    }

    @Override
    public String getParameter() {
        return null;
    }

    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public void getFromDB(ResultSet result) throws SQLException {
        while(result.next()){
            if (result.getString("synonyms").equals(synonym)){
                already = true;
                return;
            }
        }
    }

    public boolean isNew() {
        return !already;
    }

    private String preventErrorQuotes(String text){
        return text.replace("'", "''");
    }
}
