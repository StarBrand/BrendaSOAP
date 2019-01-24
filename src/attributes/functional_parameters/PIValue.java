package attributes.functional_parameters;

import entities.Literature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * PIValue class, pI value (isoelectric point) [  ]
 *
 * @author Juan Saez Hidalgi
 */
public class PIValue extends SingleValue{

  private String attributeName = "pI";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public PIValue(){

  }

  /**
   * The constructor given all parameters
   *
   * @param piValue     The value of the attribute
   * @param commentary  The commentary of the observation
   * @param references  The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PIValue(double piValue, String commentary, Literature... references){
    super(piValue, commentary, references);
  }


  /**
   * The constructor given all parameters
   * And the maximum value when Brenda defines a range for a single value
   * (exceptionally)
   *
   * @param piValue      The minimum value of the attribute
   * @param piMaxValue   The maximum value of the attribute
   * @param commentary   The commentary of the observation
   * @param references   The literature {@Link entities.Literature}of the observation
   * @see entities.Literature
   */
  public PIValue(double piValue, double piMaxValue, String commentary, Literature... references){
    super(piValue, piMaxValue, commentary, references);
  }

  public String getMethod() {
    return "getPiValue";
  }

  public String getParameter() {
    return getParameter("piValue");
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"piValue","piValueMaximum","commentary","literature"};
    return Arrays.asList(columns);
  }

  @Override
  public Object clone(){
    PIValue cloned;
    try{
      cloned = (PIValue) super.clone();
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
