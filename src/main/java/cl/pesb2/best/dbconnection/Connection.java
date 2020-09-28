package cl.pesb2.best.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Connection {

    private static String DB_HOST;
    private static int DB_PORT;
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASS;
    private static String DB_SCHEMA;

    private java.sql.Connection sqlConnection;

    public Connection() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        DB_HOST = properties.getProperty("db_url");
        DB_PORT = Integer.parseInt(properties.getProperty("db_port"));
        DB_NAME = properties.getProperty("db_database");
        DB_USER = properties.getProperty("db_user");
        DB_PASS = properties.getProperty("db_password");
        DB_SCHEMA = properties.getProperty("db_schema");
    }

    public void connect() throws SQLException {
        String url = String.format("jdbc:postgresql://%s:%d/%s", DB_HOST, DB_PORT, DB_NAME);
        Properties properties = new Properties();
        properties.setProperty("user", DB_USER);
        properties.setProperty("password", DB_PASS);
        properties.setProperty("currentSchema", DB_SCHEMA);
        this.sqlConnection = DriverManager.getConnection(url, properties);
    }

    public void close() throws SQLException {
        if (isConnected()) this.sqlConnection.close();
    }

    private boolean isConnected() {
        return this.sqlConnection != null;
    }

    private boolean execute(String sql){
        try {
            Statement statement = this.sqlConnection.createStatement();
            System.err.printf("SQL to execute\n%s\n", sql);
            statement.execute(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean saveValues(List<String> values, String parameters, String table){
        String sql_statement = String.format(
                "INSERT INTO %s(%s) VALUES %s", table, parameters, String.join(", ", values)
        );
        return execute(sql_statement);
    }

    public boolean saveValue(String values, String parameters, String table){
        String sql_statement = String.format(
                "INSERT INTO %s(%s) VALUES %s", table, parameters, values
        );
        return execute(sql_statement);
    }

    public ResultSet executeQuery(String sql){
        try {
            Statement statement = this.sqlConnection.createStatement();
            System.err.printf("SQL query to execute\n%s\n", sql);
            statement.execute(sql);
            return statement.getResultSet();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new NullResultSet();
        }
    }

}
