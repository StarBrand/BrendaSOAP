package queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserAnswer {

  private List<HashMap<String, String>> result;

  public ParserAnswer(){
    result = new ArrayList<HashMap<String, String>>();
  }

  public List<HashMap<String, String>> getResult() {
    return result;
  }

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
