package entities;

/**
 * Product class, a {@Link Molecule}
 * in the rol of product in a catalytic reaction
 *
 * @author Juan Saez
 */
public class Product extends Molecule {

  /**
   * The constructor given the name
   *
   * @param molecule_name The name of the Molecule
   */
  public Product(String molecule_name) {
    super(molecule_name);
  }

  /**
   * Override the isProduct() method of {@Link Molecule}
   *
   * @return true
   * @see Molecule
   */
  @Override
  public boolean isProduct() {
    return true;
  }

  public String getParameter() {
    return getParameter("product");
  }
}
