package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * TemperatureRange class, temperature range of activity [ °C ] - [ °C ]
 *
 * @author Juan Saez Hidalgo
 */
public class TemperatureRange extends Parameter {

    public TemperatureRange(){
        super();
    }

    @Override
    public String getTableName() {
        return "temperature_range";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "TemperatureRange");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "temperatureRange");
        }
    }

}
