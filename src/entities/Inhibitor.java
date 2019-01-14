package entities;

public class Inhibitor extends Molecule{

  public Inhibitor(String molecule_name) {
    super(molecule_name);
  }

  @Override
  public boolean isInhibitor() {
    return true;
  }
}
