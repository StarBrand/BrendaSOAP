package cl.pesb2.best;

import cl.pesb2.best.dbconnection.Connection;
import cl.pesb2.best.parsersoap.client.SoapClient;
import cl.pesb2.best.parsersoap.default_user.DefaultUser;
import cl.pesb2.best.parsersoap.tables.Enzyme;
import cl.pesb2.best.parsersoap.tables.Literature;

import java.util.ArrayList;
import java.util.List;

public class FillEnzyme {

    public static void fill (String ec, Connection connection) throws Exception {
        SoapClient client = new SoapClient(new DefaultUser());
        List<String> values = new ArrayList<>();
        Enzyme enzyme = new Enzyme(ec);
        client.makeCall();
        String result = client.getResult(enzyme.getParameter(), enzyme.getMethod(), true);
        for (String aResult:result.split("!")){
            Enzyme newEnzyme = new Enzyme(ec);
            try {
                newEnzyme.parseFromResult(aResult);
                values.add(newEnzyme.getValues());
            } catch (Exception e){
                try {
                    List<Enzyme> enzymes = dealWithMultipleReference(e, newEnzyme, aResult);
                    for (Enzyme dealtEnzyme : enzymes)
                        values.add(dealtEnzyme.getValues());
                } catch (Exception exception){
                    System.err.println(exception.getMessage());
                }
            }
        }
        List<Literature> literatures = extractLiteratures(values);
        saveLiteratures(literatures, connection);
        connection.saveValues(values, enzyme.getParameters(), enzyme.getTableName());
    }

    public static List<Enzyme> dealWithMultipleReference(Exception e, Enzyme enzyme, String result) throws Exception {
        String[] literatures = e.getMessage().split(", ");
        List<Enzyme> enzymes = new ArrayList<>();
        for (String literature:literatures){
            String modifyResult = result.replace(e.getMessage(), literature);
            Enzyme newEnzyme = (Enzyme) enzyme.clone();
            try {
                newEnzyme.parseFromResult(modifyResult);
                enzymes.add(newEnzyme);
            } catch (Exception exception){
                exception.printStackTrace();
                throw new Exception(modifyResult);
            }
        }
        return enzymes;
    }

    public static List<Literature> extractLiteratures(List<String> enzymeValues){
        List<Literature> literatures = new ArrayList<>();
        for (String enzymeValue:enzymeValues) {
            int i = enzymeValue.lastIndexOf(", ");
            String literature = enzymeValue.substring(i)
                    .replace(", ", "")
                    .replace(")", "");
            try {
                Literature literatureObject = new Literature(Integer.parseInt(literature));
                literatureObject.setLink();
                literatures.add(literatureObject);
            } catch (NumberFormatException ignored) {}
        }
        return literatures;
    }

    public static void saveLiteratures(List<Literature> literatures, Connection connection){
        List<Integer> repeated = new ArrayList<>();
        for (Literature literature:literatures){
            if (!repeated.contains(literature.getBrenda())){
                repeated.add(literature.getBrenda());
                if (!connection.saveValue(
                        literature.getValues(), literature.getParameters(), literature.getTableName()
                )) System.err.printf("Brenda %d already on database\n", literature.getBrenda());
            } else System.err.printf("Brenda %d already registered locally\n", literature.getBrenda());
        }
    }

}
