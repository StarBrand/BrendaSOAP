package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

import cl.pesb2.best.parsersoap.tables.Literature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * KIValue class, K_{I} value [ mM ]
 *
 * @author Juan Saez Hidalgo
 */
public class KIValue extends ParameterMolecule{

  public KIValue(){
    super();
    molecule_name = "inhibitor";
  }

  @Override
  public String getTableName() {
    return "ki";
  }

  @Override
  public String getMethod() {
    return String.format(super.getMethod(), "KiValue");
  }

  @Override
  public void parseFromResult(String result) {
    String[] parameters = result.split("#");
    for (String parameter:parameters){
      super.parseParameter(parameter, "kiValue");
    }
  }

}
