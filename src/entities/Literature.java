package entities;

import attributes.APAcitation;
import attributes.Attribute;
import client.DefaultUser;
import client.SoapClient;
import java.util.ArrayList;
import java.util.List;

public class Literature implements Entity {

  private List<Attribute> apa;
  private int pubmed;
  private int brenda;

  public Literature(int pubmed_reference, int brenda_reference, APAcitation reference){
    apa = new ArrayList<Attribute>();
    pubmed = pubmed_reference;
    brenda = brenda_reference;
    apa.add(reference);
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
        "ecNumber*" + enzyme +"#organism*" + organism + "#reference*" + brenda, "getReference"
    );
    String[] parser_result = result.split("#");
    authors = parser_result[2].split("\\*")[1].split("; ");
    title = parser_result[3].split("\\*")[1];
    journal = parser_result[4].split("\\*")[1];
    volume = Integer.valueOf(parser_result[5].split("\\*")[1]);
    pages = parser_result[6].split("\\*")[1];
    year = Integer.valueOf(parser_result[7].split("\\*")[1]);
    apa = new ArrayList<Attribute>();
    if (pages.contains("-")){
      fp = Integer.valueOf(pages.split("-")[0]);
      lp = Integer.valueOf(pages.split("-")[1]);
      apa.add(new APAcitation(title, journal, volume, fp, lp, year, authors));
    }
    else{
      try {
        apa.add( new APAcitation(title, journal, volume, Integer.valueOf(pages), year, authors) );
      } catch (NumberFormatException e){
        apa.add( new APAcitation(title, journal, volume, year, authors) );
      }
    }
    pubmed = Integer.valueOf(parser_result[10].split("\\*")[1]);

  }

  public int getPubmedID(){
    return pubmed;
  }

  public int getBrenda(){
    return brenda;
  }

  public List<Attribute> getAttribute() {
    return apa;
  }

  public Attribute getAPA(){
    return apa.get(0);
  }

  public void addAttributes(Attribute... apa) {  }

  public String getParameter() {
    return "literature*" + String.valueOf(brenda);
  }

  @Override
  public String toString(){
    return "<" + String.valueOf(brenda) + "> " + apa.get(0).toString() + " {Pubmed:" + String.valueOf(pubmed) + "}";
  }

  @Override
  public boolean equals(Object anotherOne){
    boolean ans;
    if (anotherOne instanceof Literature){
      ans = true;
      ans &= apa.get(0).equals(((Literature) anotherOne).getAPA());
      ans &= pubmed == ((Literature) anotherOne).getPubmedID();
      ans &= brenda == ((Literature) anotherOne).getBrenda();
    }
    else{
      ans = false;
    }
    return ans;
  }
}