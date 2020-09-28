package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * PIValue class, pI value (isoelectric point) [  ]
 *
 * @author Juan Saez Hidalgi
 */
public class PIValue extends Parameter {

    public PIValue(){
        super();
    }

    @Override
    public String getTableName() {
        return "pi";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "PiValue");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "piValue");
        }
    }

}
