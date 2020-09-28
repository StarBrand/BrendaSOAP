package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * PHOptimum class, pH optimum value [  ]
 *
 * @author Juan Saez Hidalgo
 */
public class PHOptimum extends Parameter {

  public PHOptimum(){
    super();
  }

  @Override
  public String getTableName() {
    return "ph_optima";
  }

  @Override
  public String getMethod() {
    return String.format(super.getMethod(), "PhOptimum");
  }

  @Override
  public void parseFromResult(String result) {
    String[] parameters = result.split("#");
    for (String parameter:parameters){
      super.parseParameter(parameter, "phOptimum");
    }
  }

}
