package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * PHRange class, pH range [  ]-[  ]
 *
 * @author Juan Saez Hidalgo
 */
public class PHRange extends Parameter {

  public PHRange(){
    super();
  }

  @Override
  public String getTableName() {
    return "ph_range";
  }

  @Override
  public String getMethod() {
    return String.format(super.getMethod(), "PhRange");
  }

  @Override
  public void parseFromResult(String result) {
    String[] parameters = result.split("#");
    for (String parameter:parameters){
      super.parseParameter(parameter, "phRange");
    }
  }

}