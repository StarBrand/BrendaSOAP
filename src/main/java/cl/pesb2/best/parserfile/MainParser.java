package cl.pesb2.best.parserfile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Class with the execution of the parsing
 *
 * @author Juan Saez Hidalgo
 */
public class MainParser implements Parser{

    public static void main(String... args) throws Exception {
        MainParser This = new MainParser();
        This.parse("");
    }

    @Override
    public void parse(String text) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass()
                        .getClassLoader()
                        .getResourceAsStream("brenda_download.txt")))
        );
        String line = reader.readLine();
        StringBuilder textBuilder = new StringBuilder(text).append(line).append("\n");
        line = reader.readLine();
        while(line != null){
            String[] terms = line.split("\t");
            if(terms[0].equals("ID")){
                ECNumberParser ecNumberParser = new ECNumberParser();
                    ecNumberParser.parse(textBuilder.toString());
                    System.err.printf("Parsed EC: %s\n", ecNumberParser.getECNumber());
                    System.err.println(ecNumberParser.getValues());
                    ecNumberParser.save();
                    textBuilder = new StringBuilder();
            } textBuilder.append(line).append("\n");
            line = reader.readLine();
        } reader.close();
    }

}
