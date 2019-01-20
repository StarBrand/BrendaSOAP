package queries;

import client.User;
import java.util.List;

/**
 * RecommendedNameQuery, do the query every time
 * an {@Link entities.Enzyme} is created
 *
 * @author Juan Saez Hidalgo
 * @see entities.Enzyme
 */
public class RecommendedNameQuery extends FastQuery {

  /**
   * Constructor given a Brenda {@Link client.User}
   *
   * @param user Brenda User
   * @see client.User
   */
  public RecommendedNameQuery(User user){
    super(user);
  }

  @Override
  public List<?> getResult() throws Exception{
    return getResult("getRecommendedName", "recommendedName");
  }
}