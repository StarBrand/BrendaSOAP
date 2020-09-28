package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * TemperatureOptimum class, temperature optimum value [ °C ]
 *
 * @author Juan Saez Hidalgo
 */
public class TemperatureOptimum extends Parameter {

    public TemperatureOptimum(){
        super();
    }

    @Override
    public String getTableName() {
        return "temperature_optima";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "TemperatureOptimum");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "temperatureOptimum");
        }
    }


}
