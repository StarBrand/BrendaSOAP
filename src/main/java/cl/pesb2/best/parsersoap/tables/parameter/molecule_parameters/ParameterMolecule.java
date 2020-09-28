package cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters;

import cl.pesb2.best.parsersoap.tables.parameter.Parameter;

public abstract class ParameterMolecule extends Parameter {

    private String molecule;
    protected String molecule_name;

    protected ParameterMolecule(){
        super();
        molecule = "NULL";
        molecule_name = "molecule";
    }

    @Override
    public String getParameters(){
        return String.format(
                "ref, value, max_value, %s, commentary", molecule_name
        );
    }

    @Override
    public String getValues() {
        if (ref == -1) {
            System.err.println("Ref not defined");
            return "";
        }
        return String.format(
                "(%d, %f, %f, '%s', '%s')", ref, value, max_value,
                preventErrorQuotes(molecule), preventErrorQuotes(commentary)
        );
    }

    @Override
    protected void parseParameter(String parameter, String attributes){
        super.parseParameter(parameter, attributes);
        String[] value = parameter.split("\\*", 2);
        if (value[0].equals(molecule_name))
            molecule = value[1];
    }

}
