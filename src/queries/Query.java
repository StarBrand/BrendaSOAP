package queries;

import attributes.Attribute;
import java.util.List;

/**
 * A query type for every model query
 */
public interface Query {

  /**
   * Add attributes to a query
   * @param attributes the atributes to add
   */
  void setAttributes(Attribute... attributes);

  /**
   * Made the SOAP query and get the result in a array of String
   * @return The answer to the query, every cell is a different one line answer
   * @throws Exception the client Exception
   */
  List<String> getAnswer() throws Exception;

  /**
   * Indicates the number of attributes the query is handling
   * @return number of attributes
   */
  int numberOfAttibutes();

}
