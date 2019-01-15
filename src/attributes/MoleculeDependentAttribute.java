package attributes;

import entities.Molecule;

public interface MoleculeDependentAttribute extends Attribute {

  void setMolecule(Molecule molecule);

  void setMolecule(String molecule);

  Molecule getMolecule();

}
