package entities;

/**
 * Product class, a {@Link Molecule}
 * in the rol of inhibitor to an enzyme in a catalytic reaction
 *
 * @author Juan Saez Hidalgo
 */
public class Inhibitor extends Molecule{

  /**
   * The constructor given the name
   *
   * @param molecule_name The name of the Molecule
   */
  public Inhibitor(String molecule_name) {
    super(molecule_name);
  }

  /**
   * Override the isInhibitor() method of {@Link Molecule}
   *
   * @return true
   * @see Molecule
   */@Override
  public boolean isInhibitor() {
    return true;
  }

  public String getParameter() {
    return getParameter("inhibitor");
  }
}
