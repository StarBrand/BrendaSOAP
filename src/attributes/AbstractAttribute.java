package attributes;

import entities.Literature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The AbstractAttribute abstract class
 * in which is defined the standard of an Attribute
 *
 * @author Juan Saez
 */
public abstract class AbstractAttribute implements Attribute{

  private List<Literature> references;
  private String commentary = "";

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public AbstractAttribute(){
    this.references = new ArrayList<Literature>();
  }

  /**
   * The constructor given the commentary an (a list of) reference
   * ({@Link entities.Literature}) to be used by the subclasses
   *
   * @param commentary A commentary of the Attribute
   * @param literature (A list of) reference of the Attribute
   * @see entities.Literature
   */
  public AbstractAttribute(String commentary, Literature... literature){
    this.references = new ArrayList<Literature>();
    this.commentary = commentary;
    for(Literature l:literature){
      this.references.add(l);
    }
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public String getCommentary() {
    return commentary;
  }

  public List<Literature> getReferences(){
    return references;
  }

  public void setReferences(List<Literature> references){
    this.references = references;
  }

  public void addReference(Literature... reference) {
    for(Literature l:reference) {
      try {
        references.add(l);
      } catch (Exception e) {
        references = new ArrayList<Literature>();
        references.add(l);
      }
    }
  }

  /**
   * Used by the setAttributes method, set the reference
   * given a string which contains Brenda reference codes separated
   * by ,
   *
   * @param string_reference the result of a query with the Brenda reference code(s)
   */
  protected void setReference(String string_reference){
    List<Literature> reference = new ArrayList<Literature>();
    for(String r:string_reference.split(", ")){
      reference.add(new Literature(Integer.valueOf(r)));
    }
    setReferences(reference);
  }

  /**
   * The setAttribute method to be used by the subclasses
   *
   * @param resultOfQuery A string with the result of a SOAP Query NOT EMPTY
   */
  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setCommentary(resultOfQuery.get( this.getColumns().get(this.getColumns().size() - 2)) );
    setReference(resultOfQuery.get( this.getColumns().get(this.getColumns().size() - 1)) );
  }
}
