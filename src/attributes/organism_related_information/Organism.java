package attributes.organism_related_information;

import attributes.Attribute;
import entities.Literature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organism implements Attribute {

  private String name;
  private String genre;
  private String species;
  private List<Literature> references;

  public Organism(){

  }

  public Organism(String scientific_name, Literature... someReferences){
     name = scientific_name;
      String[] names = scientific_name.split(" ");
      genre = names[0];
      try{
        species = names[1];
      } catch (Exception e){
        species = "";
      }
      references = new ArrayList<Literature>();
      for (Literature reference:someReferences){
        references.add(reference);
      }
  }

  public void setName(String name) {
    this.name = name;
    String[] names = name.split(" ");
    this.genre = names[0];
    try {
      this.species = names[1];
    } catch (Exception e){
      this.species = "";
    }

  }

  public String getName(){
      return name;
    }

  public String getGenre() {
      return genre;
    }

  public String getSpecies() {
      return species;
    }

  public List<Literature> getReferences() {
    return references;
  }

  public void setReferences(List<Literature> references) {
    this.references = references;
  }

  public void addReference(Literature reference) {
    try {
      references.add(reference);
    } catch (Exception e){
      references = new ArrayList<Literature>();
      references.add(reference);
    }
  }

  public String getMethod() {
    return "getOrganism";
  }

  public String getParameter() {
    return "organism*" + name;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"organism","sequenceCode","commentary","textmining","ecNumber"};
    return Arrays.asList(columns);
  }
}
