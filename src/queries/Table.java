package queries;

import joinery.DataFrame;

public class Table {

  private DataFrame<String> answer;

  public Table(){
    answer = new DataFrame<String>("column", "column2", "column3");
  }

  public void calculate(){
    //do shit
  }

  public DataFrame<String> result(){
    return answer;
  }

}
