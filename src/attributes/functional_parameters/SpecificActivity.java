package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * SpecificActivity class, specific activity valur [ µmol/min/mg ]
 *
 * @author Juan Saez Hidalgo
 */
public class SpecificActivity extends SingleValue {

  private String attributeName = "Specific Activity";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public SpecificActivity(){

  }

  /**
   * The constructor given all parameters
   *
   * @param specificActivityValue The value of the attribute
   * @param commentary            The commentary of the observation
   * @param references            The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public SpecificActivity(double specificActivityValue, String commentary, Literature... references){
    super(specificActivityValue, commentary, references);
  }

  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param specificActivityValue     The minimum value of the attribute
   * @param specificActivityMaxValue  The maximum value of the attribute
   * @param commentary                The commentary of the observation
   * @param references                The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public SpecificActivity(double specificActivityValue, double specificActivityMaxValue, String commentary, Literature... references){
    super(specificActivityValue, specificActivityMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getSpecificActivity";
  }

  public String getParameter() {
    return getParameter("specificActivity");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"specificActivity","specificActivityMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public Object clone(){
    SpecificActivity cloned;
    try{
      cloned = (SpecificActivity) super.clone();
    } catch (Exception e) {
      cloned = null;
    }
    return cloned;
  }

  public String getAttributeName() {
    return attributeName;
  }
}
