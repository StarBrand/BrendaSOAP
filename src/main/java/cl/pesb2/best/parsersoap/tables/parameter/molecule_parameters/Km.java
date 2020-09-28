package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

/**
 * Km class, K_{M} value [ mM ]
 *
 * @author Juan Saez Hidalgo
 */
public class Km extends ParameterMolecule{

  public Km(){
    super();
    molecule_name = "substrate";
  }

  @Override
  public String getTableName() {
    return "km";
  }

  @Override
  public String getMethod() {
    return String.format(super.getMethod(), "KmValue");
  }

  @Override
  public void parseFromResult(String result) {
    String[] parameters = result.split("#");
    for (String parameter:parameters){
      super.parseParameter(parameter, "kmValue");
    }
  }

}
