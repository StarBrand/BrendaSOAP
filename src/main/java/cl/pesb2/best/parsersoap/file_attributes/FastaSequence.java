package cl.pesb2.best.parsersoap.file_attributes;

import java.io.BufferedWriter;
import java.io.IOException;

public class FastaSequence implements BrendaAttribute{

    private final String ecNumber;
    private String uniprot;
    private String recommendedName;
    private String sequence;
    private String organism;
    private String source;

    public FastaSequence(String ecNumber){
        this.ecNumber = ecNumber;
        uniprot = "";
        recommendedName = "";
        sequence = null;
        organism = "";
        source = "";
    }

    public FastaSequence(String ecNumber, String recommendedName){
        this.ecNumber = ecNumber;
        uniprot = "";
        this.recommendedName = recommendedName;
        sequence = null;
        organism = "";
        source = "";
    }

    public void setRecommendedName(String recommendedName) {
        this.recommendedName = recommendedName;
    }

    @Override
    public void parseFromResult(String result) {
        for(String parameter:result.split("#")){
            String[] values = parameter.split("\\*");
            switch (values[0]){
                case "sequence":
                    setSequence(values[1]);
                case "firstAccessionCode":
                    uniprot = values[1];
                case "source":
                    source = values[1];
                case "organism":
                    organism = values[1];
            }
        }
    }

    private void setSequence(String value) {
        StringBuilder sequence = new StringBuilder();
        int sequenceLength = value.length();
        int i;
        int iter = sequenceLength / 80;
        int rest = sequenceLength % 80;
        for (i = 0; i < iter; i++){
            sequence.append(value, 80 * i, 80 * (i + 1)).append("\n");
        }
        sequence.append(value, 80 * i, 80 * i + rest).append("\n");
        this.sequence = sequence.toString();
    }

    @Override
    public String getMethod() {
        return "getSequence";
    }

    @Override
    public void writeOutput(BufferedWriter file) throws IOException {
        if (sequence != null) {
            StringBuilder line = new StringBuilder(">");
            line.append(uniprot).append("|").append(recommendedName)
                    .append("|").append(organism).append("|")
                    .append("Source: Brenda == ").append(source).append("\n");
            file.append(line);
            file.append(sequence);
        } file.append("\n");
    }

    /**
     * Appends report to file
     *
     * @param file FIle to append report values
     */
    public void writeReport(BufferedWriter file) throws IOException {
        StringBuilder line = new StringBuilder();
        line.append(uniprot).append("\t")
                .append(recommendedName).append("\t")
                .append(organism).append("\t")
                .append(source).append("\t")
                .append("TRUE").append("\n");
        file.append(line);
    }
}

