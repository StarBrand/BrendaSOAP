package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

/**
 * Kcat class, kcat/KM value [ (1/mMs^(-1) ]
 *
 * @author Juan Saez Hidalgo
 */
public class Kcat extends ParameterMolecule{

  public Kcat(){
    super();
    molecule_name = "substrate";
  }

  @Override
  public String getTableName() {
    return "kcat_km";
  }

  @Override
  public String getMethod() {
    return String.format(super.getMethod(), "KcatKmValue");
  }

  @Override
  public void parseFromResult(String result) {
    String[] parameters = result.split("#");
    for (String parameter:parameters){
      super.parseParameter(parameter, "kcatKmValue");
    }
  }

}