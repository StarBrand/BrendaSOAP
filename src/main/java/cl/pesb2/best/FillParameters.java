package cl.pesb2.best;

import cl.pesb2.best.dbconnection.Connection;
import cl.pesb2.best.parsersoap.client.SoapClient;
import cl.pesb2.best.parsersoap.default_user.DefaultUser;
import cl.pesb2.best.parsersoap.tables.Enzyme;
import cl.pesb2.best.parsersoap.tables.parameter.Parameter;
import cl.pesb2.best.parsersoap.tables.parameter.functional_parameters.*;
import cl.pesb2.best.parsersoap.tables.parameter.molecule_parameters.*;

import java.util.ArrayList;
import java.util.List;

public class FillParameters {

    public static void fill(String ec, Connection connection) throws Exception {
        Enzyme enzyme = new Enzyme(ec);
        String results;
        List<String> values = new ArrayList<>();
        List<Parameter> parameters = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        List<String> resultsDetected;
        parameters.add(new MolecularWeight()); parameters.add(new Km());
        parameters.add(new Kcat()); parameters.add(new KIValue());
        parameters.add(new IC50Value()); parameters.add(new TurnoverNumber());
        parameters.add(new PHOptimum()); parameters.add(new PHRange());
        parameters.add(new TemperatureOptimum()); parameters.add(new TemperatureRange());
        parameters.add(new PIValue()); parameters.add(new SpecificActivity());
        SoapClient client = new SoapClient(new DefaultUser());
        for (Parameter parameter:parameters){
            values.clear();
            client.makeCall();
            System.err.printf("Searching for %s\n", parameter.getMethod());
            results = client.getResult(
                    enzyme.getParameter(), parameter.getMethod(), true
            );
            if (results.equals("")) continue;
            resultList.clear();
            for (String result:results.split("!")){
                if (result.equals("")) continue;
                if (resultList.contains(result)){
                    System.err.printf("Result:\n%s\nappeared before", result);
                    continue;
                }
                resultList.add(result);
                resultsDetected = detectMultipleReference(result);
                for(String aResult:resultsDetected) {
                    Parameter aParameter = (Parameter) parameter.clone();
                    aParameter.parseFromResult(aResult);
                    if (aParameter.getRef() != -1) {
                        values.add(aParameter.getValues());
                    } else System.err.printf("No ref found using %s\n", aResult);
                }
            }
            connection.saveValues(values, parameter.getParameters(), parameter.getTableName());
        }
    }

    private static List<String> detectMultipleReference(String result){
        List<String> resultList = new ArrayList<>();
        for(String parameter:result.split("#")){
            String[] keyCandid = parameter.split("\\*", 2);
            if(keyCandid[0].equals("literature")){
                String[] literatures = keyCandid[1].split(", ");
                if (literatures.length > 1){
                    for(String literature:literatures)
                        resultList.add(result.replace(keyCandid[1], literature));
                } else resultList.add(result);
            }
        } return resultList;
    }

}
