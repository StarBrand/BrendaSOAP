package cl.pesb2.best.parsersoap.tables.parameter.functional_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

/**
 * MolecularWeight class, this attribute defines a number (with
 * few exceptions, a range) of molecular weight of a protein
 *
 * @author Juan Saez Hidalgo
 */
public class MolecularWeight extends Parameter {

    public MolecularWeight(){
        super();
    }

    @Override
    public String getTableName() {
        return "molecular_weight";
    }

    @Override
    public String getMethod() {
        return String.format(super.getMethod(), "MolecularWeight");
    }

    @Override
    public void parseFromResult(String result) {
        String[] parameters = result.split("#");
        for (String parameter:parameters){
            super.parseParameter(parameter, "molecularWeight");
        }
    }

}
