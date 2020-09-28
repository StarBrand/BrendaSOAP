package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

/**
 * TurnoverNumber class, turnover number, kcat, k2 [ s^(-1) ]
 *
 */
public class TurnoverNumber extends ParameterMolecule{

    public TurnoverNumber(){
        super();
        molecule_name = "substrate";
    }

    @Override
    public String getTableName() {
        return "turnover_number";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "TurnoverNumber");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "turnoverNumber");
        }
    }

}
