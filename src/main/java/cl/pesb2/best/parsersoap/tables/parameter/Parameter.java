package cl.pesb2.best.parsersoap.tables.parameter;

import cl.pesb2.best.dbconnection.Connection;
import cl.pesb2.best.parsersoap.tables.Table;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Common behaviour for Table with parameter
 *
 * @author Juan Saez Hidalgo
 */
public abstract class Parameter implements Table {

    protected int ref;
    protected double value;
    protected double max_value;
    protected String commentary;
    private String ec_number;
    private String organism;
    private int literature;

    protected Parameter(){
        ref = 1;
        value = -999;
        max_value = -999;
        commentary = "NULL";
    }

    @Override
    public String getParameters() {
        return "ref, value, max_value, commentary";
    }

    @Override
    public String getValues() {
        if (ref == -1) {
            System.err.println("Ref not defined");
            return "";
        }
        return String.format(
                "(%d, %f, %f, '%s')", ref, value, max_value, preventErrorQuotes(commentary)
        );
    }

    /**
     * Parse one parameter for result of SOAP query
     *
     * @param parameter Parameter got for SOAP
     * @param attribute Attribute name for parse SOAP
     */
    protected void parseParameter(String parameter, String attribute) {
        String[] value = parameter.split("\\*", 2);
        if (attribute.equals(value[0]))
            this.value = Double.parseDouble(value[1]);
        else if (String.format("%sMaximum", attribute).equals(value[0]))
            try{
                max_value = Double.parseDouble(value[1]);
            } catch (NumberFormatException ignored){}
        else if (value[0].equals("commentary"))
            commentary = value[1]
                    .replace("&Acirc;&deg;", "°")
                    .replace("&deg;", "°")
                    .replace("&uuml;", "ü");
        else if (value[0].equals("ecNumber"))
            ec_number = value[1];
        else if (value[0].equals("organism"))
            organism = value[1];
        else if (value[0].equals("literature"))
            literature = Integer.parseInt(value[1]);
    }

    @Override
    public String getParameter() {
        return "";
    }

    @Override
    public String getMethod() {
        return "get%s";
    }

    @Override
    public void getFromDB(ResultSet result) throws SQLException {
    }

    /**
     * Gets ref value of enzymes relation on database
     *
     * @return ref, primary key of enzymes table
     */
    public int getRef() throws SQLException, IOException {
        Connection conn = new Connection();
        conn.connect();
        ResultSet result = conn.executeQuery(
                String.format("SELECT ref FROM enzymes " +
                        "WHERE ec_number = '%s' AND organism_commentary = '%s' " +
                        "AND literature = %d", ec_number,
                        preventErrorQuotes(organism), literature)
        );
        try {
            if (result.next()){
                ref = result.getInt("ref");
                if (result.wasNull())
                    ref = -1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            ref = -1;
        } finally {
            conn.close();
        }
        return ref;
    }

    protected String preventErrorQuotes(String text){
        return text.replace("'", "''");
    }

    @Override
    public Cloneable clone() throws CloneNotSupportedException {
        return (Cloneable) super.clone();
    }

}
