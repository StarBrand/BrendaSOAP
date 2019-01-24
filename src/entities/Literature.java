package entities;

import attributes.APACitation;
import attributes.Attribute;
import client.DefaultUser;
import client.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import queries.APAQuery;
import queries.PubmedQuery;
import queries.Query;

/**
 * Literatur class with an {@Link attributes.APACitation} the
 * brenda reference code and the pubmed reference code
 *
 * @author Juan Saez Hidalgo
 * @see APACitation
 */
public class Literature implements Entity {

  private List<Attribute> apa;
  private int pubmed = Integer.MIN_VALUE;
  private int brenda;
  private Query query;

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
   * The constructor given the brenda reference and the
   * pubmed reference the apa citation
   * is missing
   *
   * @param brenda_reference
   * @param pubmed_reference
   */
  public Literature(int brenda_reference, int pubmed_reference){
    brenda = brenda_reference;
    apa = new ArrayList<Attribute>();
    pubmed = pubmed_reference;
  }

  /**
   * Fill the rest of the parameter doing a {@Link queries.APAQuery}
   *
   * @param enzyme      the enzyme of the reference in EC Number format
   * @param user        the Brenda user
   * @throws Exception  the SOAP query exception
   * @see queries.APAQuery
   */
  public void fill(String enzyme, User user) throws Exception {
    query = new APAQuery(user, enzyme, this.brenda);
    List<Object> result = (List<Object>) query.getResult();
    pubmed = (Integer) result.get(0);
    apa.add((APACitation) result.get(1));
  }

  /**
   * Fill the Pubmed ID code with a {@Link queries.PubmedQuery}
   *
   * @param enzyme    the name of the enzyme in EC Number
   * @param user      the Brenda user
   * @throws Exception the SOAP query exception
   */
  public void pubmedFiller(String enzyme, User user) throws Exception{
    query = new PubmedQuery(user, enzyme, this.brenda);
    List<Object> result = (List<Object>) query.getResult();
    pubmed = (Integer) result.get(0);
    apa.clear();
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
    if (apa.size() == 0){
      return "Pubmed ID: " + String.valueOf(pubmed);
    }
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
      try {
        ans &= apa.get(0).equals(((Literature) anotherOne).getAPA());
      } catch (IndexOutOfBoundsException e){
        ans &= apa.size() == ((Literature) anotherOne).getAttribute().size();
      }
      ans &= pubmed == ((Literature) anotherOne).getPubmedID();
      ans &= brenda == ((Literature) anotherOne).getBrenda();
    }
    else{
      ans = false;
    }
    return ans;
  }
}