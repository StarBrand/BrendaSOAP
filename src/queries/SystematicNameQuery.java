package queries;

import client.User;
import entities.Entity;
import java.util.List;

public class SystematicNameQuery extends FastQuery {

  public SystematicNameQuery(User user){
    super(user);
  }

  @Override
  public List<?> getResult() throws Exception{
    return getResult("getSystematicName", "systematicName");
  }
}