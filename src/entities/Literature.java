package entities;

import attributes.APACitation;
import attributes.Attribute;
import client.DefaultUser;
import java.util.ArrayList;
import java.util.List;
import queries.APAQuery;
import queries.Query;

/**
 * Literatur class with an {@Link attributes.APACitation} the
 * brenda reference code and the pubmed reference code
 *
 * @author Juan Saez
 * @see APACitation
 */
public class Literature implements Entity {

  private List<Attribute> apa = null;
  private int pubmed = Integer.MIN_VALUE;
  private int brenda;
  private Query apaQuery;

  /**
   * The constructor given both reference codes and the {@Link attributes.APAcitaciton}
   *
   * @param pubmed_reference the pubmed reference code
   * @param brenda_reference the brenda reference code
   * @param reference the {@Link attributes.APAcitacion}
   * @see APACitation
   */
  public Literature(int pubmed_reference, int brenda_reference, APACitation reference){
    apa = new ArrayList<Attribute>();
    pubmed = pubmed_reference;
    brenda = brenda_reference;
    apa.add(reference);
  }

  /**
   * The constructor given the brenda reference code only
   * the rest of the parameters are null
   *
   * @param brenda_reference
   */
  public Literature(int brenda_reference){
    brenda = brenda_reference;
    apa = new ArrayList<Attribute>();
    pubmed = 0;
  }

  /**
   * Fill the rest of the parameter doing a {@Link queries.APAQuery}
   *
   * @param enzyme the enzyme of the reference in EC Number formar
   * @param organism the scientific name of the organism
   * @throws Exception the SOAP query exception
   * @see queries.APAQuery
   */
  public void fill(String enzyme, String organism) throws Exception {
    apaQuery = new APAQuery(new DefaultUser(), enzyme, organism, this.brenda);
    List<Object> result = (List<Object>) apaQuery.getResult();
    pubmed = (Integer) result.get(0);
    apa.add((APACitation) result.get(1));
  }

  /**
   * Gets the pumbed ID
   *
   * @return Pumbed ID reference code
   */
  public int getPubmedID(){
    return pubmed;
  }

  /**
   * Gets the brenda reference code
   *
   * @return Brenda reference code
   */
  public int getBrenda(){
    return brenda;
  }

  /**
   * Gets a list with the {@Link attributes.APACitation}
   *
   * @return List with a {@Link attributes.APACitation}
   * @see APACitation
   */
  public List<Attribute> getAttribute() {
    return apa;
  }

  /**
   * Gets the {@Link attributes.APACitation}
   *
   * @return the APA citacion reference
   * @see APACitation
   */
  public APACitation getAPA(){
    return (APACitation) apa.get(0);
  }

  /**
   * Do nothing
   *
   * @param apa any {@Link attributes.Attribute}
   * @see attributes.Attribute
   */
  public void addAttributes(Attribute... apa) {  }

  public String getParameter() {
    return "literature*" + String.valueOf(brenda);
  }

  @Override
  public String toString(){
    try {
      return "<" + String.valueOf(brenda) + "> " + apa.get(0).toString() + " {Pubmed:" + String
          .valueOf(pubmed) + "}";
    } catch (NullPointerException e){
      return "<" + String.valueOf(brenda) + "> " + "" + " {Pubmed:" + String.valueOf(pubmed) + "}";
    }
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