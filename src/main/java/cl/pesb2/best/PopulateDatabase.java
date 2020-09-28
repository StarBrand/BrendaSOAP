package cl.pesb2.best;

import cl.pesb2.best.dbconnection.Connection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulateDatabase {

    public static void main(String... args) throws SQLException, IOException {
        System.out.println("Starts here:");
        Connection connection = new Connection();
        BufferedWriter writer = new BufferedWriter(new FileWriter("size.csv"));
        connection.connect();
        try {
            doIt(connection, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } connection.close();
        writer.close();
        System.err.println("Done");
    }

    public static void doIt(Connection connection, BufferedWriter writer) throws Exception {
        ResultSet ecNumbers = connection.executeQuery(
                "SELECT a.ec_number FROM ec_number a " +
                        "LEFT JOIN populated b ON a.ec_number = b.ec_number " +
                        "WHERE b.ec_number IS NULL;"
        );
        ResultSet size;
        int i = 1245;
        int bytes;
        while (ecNumbers.next()){
            i++;
            System.out.printf(
                    "============================> %s <============================\n",
                    ecNumbers.getString("ec_number")
            );
            FillEnzyme.fill(ecNumbers.getString("ec_number"), connection);
            FillParameters.fill(ecNumbers.getString("ec_number"), connection);
            size = connection.executeQuery("SELECT pg_database_size('brenda_local');");
            size.next();
            bytes = size.getInt("pg_database_size");
            writer.write(String.format("%d,%d\n", i, bytes));
        }
    }

}
