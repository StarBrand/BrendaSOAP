package output;

import attributes.Attribute;
import client.User;
import entities.Entity;
import entities.Enzyme;
import entities.Protein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import queries.EnzymeQuery;
import queries.ParserAnswer;
import queries.Query;

/**
 * EnzymeStructureQuery class, is used as a
 * Template for the queries that look for
 * enzyme structure parameters
 *
 * @author Juan Saez Hidalgo
 */
public abstract class EnzymeStructureQuery implements Query {

  protected User user;
  private EnzymeQuery enzymeQuery;
  private ParserAnswer parserAnswer;

  public EnzymeStructureQuery(User user){
    this.user = user;
  }

  public void addAttributes(Attribute... attribute) {}

  public int numberOfAttributes() {
    return 0;
  }

  public List<String> getResult(boolean organism_as_parameter, boolean uniprot_as_parameter, Attribute attribute, List<Entity> proteins) throws Exception {
    List<String> result = new ArrayList<String>();
    List<Protein> proteinList = new ArrayList<Protein>();
    for(Entity protein:proteins){
      proteinList.add((Protein) protein);
    }
    enzymeQuery = new EnzymeQuery(this.user, proteinList);
    List<Enzyme> enzymes = enzymeQuery.getEnzymes();
    HashMap<Enzyme, String> queries = enzymeQuery.getQueries(attribute.getMethod());
    for (Entity protein : proteins) {
      if (((Protein) protein).getUniprot().equals("") && uniprot_as_parameter) {
        result.add("");
      } else {
        String parameter = "";
        if (uniprot_as_parameter)
          parameter += "firstAccessionCode*" + ((Protein) protein).getUniprot();
        if (organism_as_parameter)
          parameter += "organism*" + ((Protein) protein).getOrganism().getName();
        result.add(
            enzymeQuery.getQuery(((Protein) protein).getEnzyme(), parameter)
        );
      }
    }
    return result;
  }
}
