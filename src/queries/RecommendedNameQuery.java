package queries;

import client.User;
import java.util.List;

public class RecommendedNameQuery extends FastQuery {

  public RecommendedNameQuery(User user){
    super(user);
  }

  @Override
  public List<?> getResult() throws Exception{
    return getResult("getRecommendedName", "recommendedName");
  }
}