package entities;

public class Literature implements Entity {

  private String apa;
  private int pubmed;

  public Literature(String citation, int code){
    apa = citation;
    pubmed = code;
  }

}