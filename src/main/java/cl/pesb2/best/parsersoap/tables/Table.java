package cl.pesb2.best.parsersoap.tables;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Table
 *
 * @author Juan Saez Hidalgo
 */
public interface Table extends Cloneable{

  /**
   * Gets table name of SQL equivalent table
   *
   * @return Table name
   */
  String getTableName();

  /**
   * Gets parameter of SQL equivalent table
   * on format: column1, column2, ..., columnN
   *
   * @return Parameters separated by commas
   */
  String getParameters();

  /**
   * Gets values of this table on format:
   * (value1, value2, value3, ..., valueN)
   *
   * @return Values on given format
   */
  String getValues();

  /**
   * Parses result of SOAP query and alter values of
   * Table object
   *
   * @param result Result of SOAP query
   */
  void parseFromResult(String result) throws Exception;

  /**
   * Gets parameter for SOAP query
   *
   * @return Parameter for SOAP query
   */
  String getParameter();

  /**
   * Gets method for SOAP query
   *
   * @return Method for SOAP query
   */
  String getMethod();

  /**
   * Get attributes from result query on database
   *
   * @param result A result set to get parameter
   */
  void getFromDB(ResultSet result) throws SQLException;

}
