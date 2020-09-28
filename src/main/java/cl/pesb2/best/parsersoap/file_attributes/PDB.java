package cl.pesb2.best.parsersoap.file_attributes;

import java.io.BufferedWriter;
import java.io.IOException;

public class PDB implements BrendaAttribute{

    private final String ecNumber;
    private String organism;
    private String pdb;
    private String link;

    public PDB(String ecNumber){
        this.ecNumber = ecNumber;
        organism = "";
        pdb = null;
        link = "";
    }

    @Override
    public void parseFromResult(String result) {
        for(String parameter:result.split("#")){
            String[] values = parameter.split("\\*");
            switch (values[0]){
                case "pdb":
                    setPDB(values[1]);
                case "organism":
                    organism = values[1];
            }
        }
    }

    private void setPDB(String value) {
        pdb = value;
        link = "https://www.rcsb.org/structure/" + value;
    }

    @Override
    public String getMethod() {
        return "getPdb";
    }

    @Override
    public void writeOutput(BufferedWriter file) throws IOException {
        if (pdb != null){
            StringBuilder line = new StringBuilder();
            line.append(ecNumber).append("\t")
                    .append(organism).append("\t")
                    .append(pdb).append("\t")
                    .append(link).append("\n");
            file.append(line);
        }
    }

    public String getCode() {
        return pdb;
    }
}
