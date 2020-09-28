package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

/**
 * IC50 Value class, IC50 Value [ mM]
 *
 * @author Juan Saez Hidalgo
 */
public class IC50Value extends ParameterMolecule{

    public IC50Value(){
        super();
        molecule_name = "inhibitor";
    }

    @Override
    public String getTableName() {
        return "ic50";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "Ic50Value");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "ic50Value");
        }
    }
}