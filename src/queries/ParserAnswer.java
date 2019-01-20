package queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ParserAnswer class, parse a SOAP query result
 * and generate a list of HashMap with the result
 * and the parameters as keys
 *
 * @author Juan Saez Hidalgo
 */
public class ParserAnswer {

  private List<HashMap<String, String>> result;

  /**
   * Constructor
   */
  public ParserAnswer(){
    result = new ArrayList<HashMap<String, String>>();
  }

  /**
   * Get the List of HashMap, the result of the SOAP query
   * is seted on getResult(String query) method
   *
   * @return A list with the result of query as a HashMap
   */
  public List<HashMap<String, String>> getResult() {
    return result;
  }

  /**
   * Get the List of HashMap, given the result
   * of the SOAP query
   *
   * @param query A string with the format of a SOAP query
   * @return A list with the result of query as a HashMap
   */
  public List<HashMap<String, String>> getResult(String query) {
    result.clear();
    String[] firstParser, secondParser, thirdParser;
    HashMap<String, String> observation;
    firstParser = query.split("!");
    for (String answer:firstParser){
      secondParser = answer.split("#");
      observation = new HashMap<String, String>();
      for(String obs:secondParser){
        thirdParser = obs.split("\\*");
        try {
          observation.put(thirdParser[0], thirdParser[1]);
        } catch (Exception e){
          observation.put(thirdParser[0], "");
        }
      }
      result.add(observation);
    }
    return result;
  }
}
