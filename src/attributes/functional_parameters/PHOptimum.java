package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * PHOptimum class, pH optimum value [  ]
 *
 * @author Juan Saez Hidalgo
 */
public class PHOptimum extends SingleValue{

  private String attributeName = "pH Optimum";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public PHOptimum(){ }

  /**
   * The constructor given all parameters
   *
   * @param PHOptimum   The value of the attribute
   * @param commentary  The commentary of the observation
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PHOptimum(double PHOptimum, String commentary, Literature... references){
    super(PHOptimum, commentary, references);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param PHOptimum_value The minimum value of the attribute
   * @param maxPHOptimum    The maximum value of the attribute
   * @param commentary      The commentary of the observation
   * @param references      The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PHOptimum(double PHOptimum_value, double maxPHOptimum, String commentary, Literature... references){
    super(PHOptimum_value, maxPHOptimum, commentary, references);
  }

  public String getMethod() {
    return "getPhOptimum";
  }

  public String getParameter() {
    return getParameter("phOptimum");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"phOptimum","phOptimumMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public Object clone(){
    PHOptimum cloned;
    try{
      cloned = (PHOptimum) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public HashMap<String, String> rowsToTable() {
    HashMap<String, String> out = super.rowsToTable(attributeName);
    return out;
  }

  public String getAttributeName() {
    return attributeName;
  }
}
