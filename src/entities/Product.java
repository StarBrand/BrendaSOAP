package entities;

public class Product extends Molecule {

  public Product(String molecule_name) {
    super(molecule_name);
  }

  @Override
  public boolean isProduct() {
    return true;
  }

  public String getParameter() {
    return getParameter("product");
  }
}
