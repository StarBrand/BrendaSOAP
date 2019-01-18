package attributes;

import entities.Molecule;

/**
 * MoleculeDependentAttribute type
 * Implemented by the attributes that depends on the molecule
 * that interact with the enzyme, such as a substrate, an inhibitor,
 * a product, and to be expanded to another ones (e.g. co-factors)
 *
 * @author Juan Saez Hidalgo
 */
public interface MoleculeDependentAttribute extends Attribute {

  /**
   * Sets the molecule that the parameter depends
   *
   * @param molecule  A {@Link entities.Molecule} that the attribute
   *                  depends
   * @see entities.Molecule
   */
  void setMolecule(Molecule molecule);

  /**
   * Sets the molecule that the parameter depends, just
   * with the name given, the role (class) of the molecule
   * is responsibility of the the {@Link Attribute}
   *
   * @param molecule The name of the molecule
   * @see Attribute
   */
  void setMolecule(String molecule);

  /**
   * Gets the Molecule
   *
   * @return {@Link entities.Molecule} that define the parameter
   * @see entities.Molecule
   */
  Molecule getMolecule();

}
