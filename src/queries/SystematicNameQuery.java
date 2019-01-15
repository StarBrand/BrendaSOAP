package queries;

import client.User;
import entities.Entity;
import java.util.List;

public class SystematicNameQuery extends FastQuery {

  public SystematicNameQuery(User aUser){
    super(aUser);
  }

  @Override
  public List<?> getResult() throws Exception{
    return getResult("getSystematicName", "systematicName");
  }
}