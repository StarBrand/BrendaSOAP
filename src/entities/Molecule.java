package entities;

import attributes.Attribute;
import java.util.ArrayList;
import java.util.List;

public abstract class Molecule implements Entity{

  private boolean substrate;
  private boolean product;
  private boolean inhibitor;
  private String name;
  private List<Attribute> attributes;

  public Molecule(String molecule_name){
    name = molecule_name;
  }

  public boolean isSubstrate(){
    return false;
  }

  public boolean isProduct() {
    return false;
  }

  public boolean isInhibitor() {
    return false;
  }

  public String getName() {
    return name;
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public void addAttribute(Attribute attribute) {
    attributes.add(attribute);
  }
}
