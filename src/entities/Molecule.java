package entities;

import attributes.Attribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Molecule class defined as a molecule
 * which determines some attribute ({@Link attributes.MoleculeDependentAttribute})
 *
 * @author Juan Saez
 * @see attributes.MoleculeDependentAttribute
 */
public abstract class Molecule implements Entity{

  private boolean substrate;
  private boolean product;
  private boolean inhibitor;
  private String name;
  private List<Attribute> attributes;

  /**
   * Default contructor for every rol (SubClass) of Molecule
   *
   * @param molecule_name The name of the molecule
   *                      used in BRENDA
   */
  public Molecule(String molecule_name){
    name = molecule_name;
    attributes = new ArrayList<Attribute>();
  }

  /**
   * The Rol (subclass) of the molecule is {@Link Substrate}?
   *
   * @return Default return false
   * @see Substrate
   */
  public boolean isSubstrate(){
    return false;
  }

  /**
   * The Rol (subclass) of the molecule is {@Link Product}?
   *
   * @return Default return false
   * @see Product
   */
  public boolean isProduct() {
    return false;
  }

  /**
   * The Rol (subclass) of the molecule is {@Link Inhibitor}?
   *
   * @return Default return false
   * @see Inhibitor
   */
  public boolean isInhibitor() {
    return false;
  }

  /**
   * Gets the name of the molecule
   *
   * @return The name of the molecule
   */
  public String getName() {
    return name;
  }

  public List<Attribute> getAttribute() {
    return attributes;
  }

  public void addAttributes(Attribute... attribute) {
    for(Attribute a:attribute){
      attributes.add(a);
    }
  }

  /**
   * The internal getParameter of the superclass (abstract class)
   * Molecule, to be used only for the subclasses
   *
   * @param molecule the rol (subclass) of the Molecule
   * @return the parameter for the query
   */
  protected String getParameter(String molecule){
    return molecule + "*" + name;
  }
}
