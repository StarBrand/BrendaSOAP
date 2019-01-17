package entities;

/**
 * Product class, a {@Link Molecule}
 * in the rol of substrate in a catalytic reaction
 *
 * @author Juan Saez
 */
public class Substrate extends Molecule {

  /**
   * The constructor given the name
   *
   * @param molecule_name The name of the Molecule
   */
  public Substrate(String molecule_name) {
    super(molecule_name);
  }

  /**
   * Override the isSubstrate() method of {@Link Molecule}
   *
   * @return true
   * @see Molecule
   */
  @Override
  public boolean isSubstrate(){
    return true;
  }

  public String getParameter() {
    return getParameter("substrate");
  }
}
