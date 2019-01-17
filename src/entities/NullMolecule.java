package entities;

/**
 * NullMolecule class using NullObject pattern
 *
 * @author Juan Saez
 */
public class NullMolecule extends Molecule {

  /**
   * The constructor of NullMolecule
   *
   */
  public NullMolecule() {
    super("");
  }

  /**
   * Return nothing
   *
   * @return null
   */
  public String getParameter() {
    return "";
  }
}
