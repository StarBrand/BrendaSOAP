package queries;

import client.User;
import java.util.List;

public class RecommendedNameQuery extends FastQuery {

  public RecommendedNameQuery(User aUser){
    super(aUser);
  }

  @Override
  public List<String> getAnswer() throws Exception{
    return getAnswer("getRecommendedName", "recommendedName");
  }
}