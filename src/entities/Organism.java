package entities;

public class Organism implements Entity{

  private String name;

  public Organism(String scientific_name){
    name = scientific_name;
  }

  public String getName(){
    return name;
  }

}
