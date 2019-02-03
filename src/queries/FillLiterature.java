package queries;

import attributes.Attribute;
import client.User;
import entities.Enzyme;
import entities.Literature;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * FillLiterature class, automatically fill the PubMed
 * parameter of Literature {@Link entities.Literature}
 * class from his Brenda reference
 * code in a list of {@Link entities.Protein}
 *
 * @author Juan Saez Hidalgo
 * @see entities.Literature
 * @see entities.Protein
 */
public class FillLiterature {

  private User user;
  private EnzymeQuery enzymeQuery;
  private ParserAnswer parserAnswer;
  private List<Protein> proteins;

  /**
   * Constructor given a Brenda User
   *
   * @param user Brenda User
   */
  public FillLiterature(User user){
    this.user = user;
    this.parserAnswer = new ParserAnswer();
    proteins = new ArrayList<Protein>();
  }

  /**
   * Set the list of {@Link entities.Protein} which {@Link entities.Literature}
   * are going to be filled, this erases the previous protein
   *
   * @param protein None, one or more protein to set as the Proteins
   * @see entities.Protein
   * @see entities.Literature
   */
  public void setProteins(Protein... protein){
    this.proteins = new ArrayList<Protein>();
    for(Protein p:protein){
      this.proteins.add(p);
    }
  }

  /**
   * Add to the list of {@Link entities.Protein} which {@Link entities.Literature}
   * are going to be filled, this adds and not erases the previous protein
   *
   * @param protein None, one or more protein to set as the Proteins
   * @see entities.Protein
   * @see entities.Literature
   */
  public void addProteins(Protein... protein){
    for(Protein p:protein){
      this.proteins.add(p);
    }
  }

  /**
   * Fill the {@Link entities.Literature} of every {@Link attributes.Attribute}
   * of every {@Link entities.Protein} in the list, and return this list
   *
   * @return A list of Protein with Literature parameters completely filled
   */
  public List<Protein> fill() throws Exception {
    enzymeQuery = new EnzymeQuery(user, proteins);
    enzymeQuery.getEnzymes();
    HashMap<Enzyme, String> queries = enzymeQuery.getQueries("getReference");
    HashMap<Integer, Integer> dic = buildDictionary(queries);
    for(Protein protein:proteins){
      String query = queries.get(protein.getEnzyme());
      setPubMed(protein.getOrganism().getReferences(), dic);
      for(Attribute attribute:protein.getAttribute()){
        setPubMed(attribute.getReferences(), dic);
      }
    }
    return proteins;
  }

  private HashMap<Integer, Integer> buildDictionary(HashMap<Enzyme, String> queries){
    HashMap<Integer, Integer> out = new HashMap<Integer, Integer>();
    for(String query:queries.values()){
      if(!query.equals("")) {
        List<HashMap<String, String>> results = parserAnswer.getResult(query);
        for(HashMap<String, String> result:results){
          int brenda, pubmed;
          try{
            brenda = Integer.valueOf(result.get("reference"));
          }catch (Exception e){
             brenda = 0;
          }
          try{
            pubmed = Integer.valueOf(result.get("pubmedId"));
          }catch (Exception e){
            pubmed = 0;
          }
          out.put(brenda, pubmed);
        }
      }
    }
    return out;
  }

  private void setPubMed(List<Literature> references, HashMap<Integer, Integer> dic){
    for(Literature reference:references){
      try {
        reference.setPubmed(dic.get(reference.getBrenda()));
      } catch (NullPointerException e){
        reference.setPubmed(0);
      }
    }
  }

}