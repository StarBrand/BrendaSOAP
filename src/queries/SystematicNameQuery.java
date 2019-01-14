package queries;

import client.User;
import java.util.List;

public class SystematicNameQuery extends FastQuery {

  public SystematicNameQuery(User aUser){
    super(aUser);
  }

    @Override
  public List<String> getAnswer() throws Exception{
    return getAnswer("getSystematicName", "systematicName");
  }
}