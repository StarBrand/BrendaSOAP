package entities;

import attributes.Attribute;
import java.util.List;

/**
 * LiteratureLink class, it is special kind of Literature
 * which wrapped a Literature Object and complement it with
 * two new ...
 *
 * @author Juan Saez Hidalgo
 */
public class LiteratureLink implements Entity{

  private Literature literature;
  private String url;
  private final String commonUrl = "https://www.ncbi.nlm.nih.gov/pubmed/?term=";
  private final String brendaUrl = "https://www.brenda-enzymes.org/literature.php?r=";

  public LiteratureLink(Literature literature){
    this.literature = literature;
    generateUrl();
  }

  private void generateUrl() {
    if (this.literature.getPubmedID() == 0) {
      this.url = brendaUrl + this.literature.getBrenda();
    } else {
      this.url = commonUrl + this.literature.getPubmedID();
    }
  }

  /**
   * Gets the literature
   *
   * @return literature reference
   */
  public Literature getLiterature() {
    return literature;
  }

  /**
   * Gets the url
   *
   * @return a String containing the url
   */
  public String getUrl() {
    return url;
  }

  public List<Attribute> getAttribute() {
    return literature.getAttribute();
  }

  public void addAttributes(Attribute... attribute) {
    literature.addAttributes(attribute);
  }

  public String getParameter() {
    return literature.getParameter();
  }
}
