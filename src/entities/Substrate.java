package entities;

public class Substrate extends Molecule {

  public Substrate(String molecule_name) {
    super(molecule_name);
  }

  @Override
  public boolean isSubstrate(){
    return true;
  }
}
