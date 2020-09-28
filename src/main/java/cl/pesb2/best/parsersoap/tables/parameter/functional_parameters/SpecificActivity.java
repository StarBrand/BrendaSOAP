package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * SpecificActivity class, specific activity value [ µmol/min/mg ]
 *
 * @author Juan Saez Hidalgo
 */
public class SpecificActivity extends Parameter {

    public SpecificActivity(){
        super();
    }

    @Override
    public String getTableName() {
        return "specific_activity";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "SpecificActivity");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "specificActivity");
        }
    }


}
