package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * PHRange class, pH range [  ]-[  ]
 *
 * @author Juan Saez Hidalgo
 */
public class PHRange extends RangeValue{

  private String attributeName = "pH Range";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public PHRange(){ }

  /**
   * The constructor given just the minimum value
   * this is useful when Brenda database define a range too small
   * that is better defined as a unique value
   *
   * @param phRangeValue  The minimum value of the attribute
   * @param commentary    The commentary of the observation
   * @param references    The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PHRange(double phRangeValue, String commentary, Literature... references){
    super(phRangeValue, commentary, references);
  }

  /**
   * The constructor given all the parameters
   *
   * @param phMinValue  The minimum value of the range
   * @param phMaxValue  The maximum value of the range
   * @param commentary  The commentary of the data
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PHRange(double phMinValue, double phMaxValue, String commentary, Literature... references){
    super(phMinValue, phMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getPhRange";
  }

  public String getParameter() {
    return getParameter("phRange");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"phRange","phRangeMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public Object clone(){
    PHRange cloned;
    try{
      cloned = (PHRange) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public String getAttributeName() {
    return attributeName;
  }
}