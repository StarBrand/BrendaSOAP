package cl.pesb2.best;

import cl.pesb2.best.parsersoap.client.SoapClient;
import cl.pesb2.best.parsersoap.default_user.DefaultUser;
import cl.pesb2.best.parsersoap.file_attributes.FastaSequence;
import cl.pesb2.best.parsersoap.file_attributes.PDB;
import cl.pesb2.best.parsersoap.tables.Enzyme;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BrendaSOAP {

    private final List<String> ecNumberList;
    private final File filePath;
    private final SoapClient client;

    public BrendaSOAP(String ecNumber, String folder) throws IOException {
        ecNumberList = new ArrayList<>();
        ecNumberList.add(ecNumber);
        filePath = new File(folder);
        System.err.println(filePath.mkdirs());
        client = new SoapClient(new DefaultUser());
    }

    public void addEnzyme(String ecNumber){
        ecNumberList.add(ecNumber);
    }

    public int getFastaSequence() {
        try {
            BufferedWriter fileOutput = new BufferedWriter(
                    new FileWriter(new File(filePath, "fasta_output.txt")));
            BufferedWriter fileReport = new BufferedWriter(
                    new FileWriter(new File(filePath,"report_fasta.txt")));
            fileReport.append("UniProt\tEnzyme\tOrganism\tSource\tFound_using_brenda\n");
            for (String ecNumber : ecNumberList) {
                getSequence(ecNumber, fileOutput, fileReport);
            }
            fileOutput.close();
            fileReport.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getPDB(){
        try {
            BufferedWriter fileOutput = new BufferedWriter(
                    new FileWriter(new File(filePath,"pdb_table.txt")));
            fileOutput.append("EC_Number\tOrganism\tPDB\tlink\n");
            for (String ecNumber : ecNumberList) {
                getPdb(ecNumber, fileOutput);
            }
            fileOutput.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void getSequence(String ecNumber, BufferedWriter fileOutput, BufferedWriter fileReport) throws Exception{
        Enzyme enzyme = new Enzyme(ecNumber);
        client.makeCall();
        String result = client.getResult(enzyme.getParameter(), "getRecommendedName", true);
        String[] values = result.split("!")[0].split("#");
        String recommendedName = "";
        for (String value:values)
            if (value.split("\\*")[0].equals("recommendedName"))
                recommendedName = value.split("\\*")[1];
        FastaSequence fastaSequence = new FastaSequence(ecNumber, recommendedName);
        result = client.getResult(enzyme.getParameter(), fastaSequence.getMethod(), true);
        for (String aResult:result.split("!")){
            FastaSequence fasta = new FastaSequence(ecNumber, recommendedName);
            fasta.parseFromResult(aResult);
            fasta.writeOutput(fileOutput);
            fasta.writeReport(fileReport);
        }
    }

    private void getPdb(String ecNumber, BufferedWriter fileOutput) throws Exception{
        Enzyme enzyme = new Enzyme(ecNumber);
        client.makeCall();
        PDB pdb = new PDB(ecNumber);
        List<String> obtainedCodes = new ArrayList<>();
        String result = client.getResult(enzyme.getParameter(), pdb.getMethod(), true);
        for (String aResult:result.split("!")) {
            pdb.parseFromResult(aResult);
            if (! obtainedCodes.contains(pdb.getCode())){
                obtainedCodes.add(pdb.getCode());
                pdb.writeOutput(fileOutput);
            }
        }
    }

}
