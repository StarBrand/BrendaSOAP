package queries;

import client.User;
import entities.Entity;
import java.util.List;

/**
 * SystematicNameQuery class, do the query anytime
 * an {@Link entities.Enzyme} is created
 *
 * @author Juan Saez Hidalgo
 * @see entities.Enzyme
 */
public class SystematicNameQuery extends FastQuery {

  /**
   * Constructor given a Brenda {@Link client.User}
   *
   * @param user Brenda User
   * @see client.User
   */
  public SystematicNameQuery(User user){
    super(user);
  }

  public List<?> getResult() throws Exception{
    return getResult("getSystematicName", "systematicName");
  }
}