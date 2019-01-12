package entities;

import attributes.APAcitation;
import client.DefaultUser;
import client.SoapClient;

public class Literature implements Entity {

  private APAcitation apa;
  private int pubmed;
  private int brenda;

  public Literature(APAcitation reference, int pubmed_reference, int brenda_reference){
    apa = reference;
    pubmed = pubmed_reference;
    brenda = brenda_reference;
  }

  public Literature(int brenda_reference, String enzyme, String organism) throws Exception{
    brenda = brenda_reference;
    fill(enzyme, organism);
  }

  private void fill(String enzyme, String organism) throws Exception {
    String title, journal, pages;
    String[] authors;
    int volume, fp, lp, year;
    SoapClient client = new SoapClient(new DefaultUser());
    client.makeCall();
    String result = client.getResult(
        ",ecNumber*" + enzyme +"#organism*" + organism + "#reference*" + brenda, "getReference"
    );
    String[] parser_result = result.split("#");
    authors = parser_result[2].split("\\*")[1].split("; ");
    title = parser_result[3].split("\\*")[1];
    journal = parser_result[4].split("\\*")[1];
    volume = Integer.valueOf(parser_result[5].split("\\*")[1]);
    pages = parser_result[6].split("\\*")[1];
    year = Integer.valueOf(parser_result[7].split("\\*")[1]);
    if (pages.contains("-")){
      fp = Integer.valueOf(pages.split("-")[0]);
      lp = Integer.valueOf(pages.split("-")[1]);
      apa = new APAcitation(title, journal, volume, fp, lp, year, authors);
    }
    else{
      apa = new APAcitation(title, journal, volume, Integer.valueOf(pages), year, authors);
    }
    pubmed = Integer.valueOf(parser_result[10].split("\\*")[1]);

  }

  @Override
  public String toString(){
    return "<" + String.valueOf(brenda) + "> " + apa.toString() + " {Pubmed:" + String.valueOf(pubmed) + "}";
  }

  @Override
  public boolean equals(Object anotherOne){
    boolean ans;
    if (anotherOne instanceof Literature){
      ans = true;
      ans &= apa.equals(((Literature) anotherOne).getAPA());
      ans &= pubmed == ((Literature) anotherOne).getPubmedID();
      ans &= brenda == ((Literature) anotherOne).getBrenda();
    }
    else{
      ans = false;
    }
    return ans;
  }

  public APAcitation getAPA(){
    return apa;
  }

  public int getPubmedID(){
    return pubmed;
  }

  public int getBrenda(){
    return brenda;
  }

}