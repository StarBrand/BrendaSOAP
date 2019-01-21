package attributes;

import static sun.swing.MenuItemLayoutHelper.max;

import entities.Literature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * APACitation class defined as an APA reference
 *
 * @author Juan Saez Hidalgo
 */
public class APACitation implements Attribute{

  private List<String> authors = new ArrayList<String>();
  private String title = "";
  private String journal = "";
  private int vol = 0;
  private int fp = Integer.MAX_VALUE;
  private int lp = Integer.MAX_VALUE;
  private String special_page = "";
  private int year = 0;

  /**
   * An empty constructor
   * letting the parameters for default (empty lists, empty string,
   * Nan numbers) to be filled with a query
   */
  public APACitation(){
    authors.add("");
  }

  /**
   * The constructor given the parameters of a APA reference
   * for a Paper with more than one page and one, none or more authors
   *
   * @param title       The title of a paper
   * @param journal     The scientific journal in which a paper was published
   * @param volume      The volume of the journal
   * @param first_page  The first page
   * @param last_page   The last page
   * @param year        The year in which it was published
   * @param authors     One, none or more authors of the paper
   */
  public APACitation(String title, String journal, int volume, int first_page, int last_page, int year, String... authors){
    this.authors = new ArrayList<String>();
    this.title = title;
    this.journal = journal;
    vol = volume;
    fp = first_page;
    lp = last_page;
    this.year = year;
    for (String author:authors){
      this.authors.add(author);
    }
  }

  /**
   * The constructor given just one author and the rest of the parameter
   *
   * @param author      the only author of a paper
   * @param title       the title of the paper
   * @param journal     the journal in which the paper was published
   * @param volume      the volume of the journal
   * @param first_page  The first page
   * @param last_page   The last page
   * @param year        The year on which it was published
   */
  public APACitation(String author, String title, String journal, int volume, int first_page, int last_page, int year){
    this.authors = new ArrayList<String>();
    this.title = title;
    this.journal = journal;
    vol = volume;
    fp = first_page;
    lp = last_page;
    this.year = year;
    authors.add(author);
  }

  /**
   * The constructor of a paper with one page
   *
   * @param title    The title of the paper
   * @param journal  The journal of the paper
   * @param volume   The volume of the journal
   * @param page     The only page of the paper
   * @param year     The year of the paper
   * @param authors  One, none or many authors
   */
  public APACitation(String title, String journal, int volume, int page, int year, String... authors){
    this.authors = new ArrayList<String>();
    this.title = title;
    this.journal = journal;
    vol = volume;
    fp = page;
    lp = page;
    this.year = year;
    for (String author:authors){
      this.authors.add(author);
    }
  }

  /**
   * The constructor with one author, an one page
   *
   * @param author   The only author of the paper
   * @param title    The title of the paper
   * @param journal  The journal of the paper
   * @param volume   The volume of the journal
   * @param page     The only page of the paper
   * @param year     The year of the paper
   */
  public APACitation(String author, String title, String journal, int volume, int page, int year){
    this.authors = new ArrayList<String>();
    this.title = title;
    this.journal = journal;
    vol = volume;
    fp = page;
    lp = page;
    this.year = year;
    this.authors.add(author);
  }

  /**
   * The constructor when the page is special (not a number)
   *
   * @param title    The title of the paper
   * @param journal  The journal of the paper
   * @param volume   The volume of the journal
   * @param page     The special page
   * @param year     The year of the paper
   * @param authors  One, none or many authors
   */
  public APACitation(String title, String journal, int volume, String page, int year, String... authors){
    this.authors = new ArrayList<String>();
    this.title = title;
    this.journal = journal;
    vol = volume;
    this.special_page = page;
    this.year = year;
    for (String author:authors){
      this.authors.add(author);
    }
  }

  @Override
  public boolean equals(Object anotherOne){
    boolean ans;
    if (anotherOne instanceof APACitation){
      ans = true;
      ans &= authors.equals(((APACitation) anotherOne).getAuthors());
      ans &= title.equals(((APACitation) anotherOne).getTitle());
      ans &= journal.equals(((APACitation) anotherOne).getJournal());
      ans &= vol == ((APACitation) anotherOne).getVolume();
      ans &= fp == ((APACitation) anotherOne).getFirstPage();
      ans &= lp == ((APACitation) anotherOne).getLastPage();
      ans &= special_page.equals(((APACitation) anotherOne).getSpecialPage());
      ans &= year == ((APACitation) anotherOne).getYear();
    }
    else{
      ans = false;
    }
    return ans;
  }

  /**
   * Sets the authors erasing the previous ones
   *
   * @param authors One, many or none authors
   */
  public void setAuthors(String... authors) {
    this.authors = new ArrayList<String>();
    for (String author:authors){
      this.authors.add(author);
    }
  }

  /**
   * Add one author without erasing the previous ones
   *
   * @param anAuthor A new author
   */
  public void addAuthor(String anAuthor){
    try{
      authors.add(anAuthor);
    } catch (Exception e){
      authors = new ArrayList<String>();
      authors.add(anAuthor);
    }
  }

  /**
   * Sets the title
   *
   * @param title The title of the paper
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Sets the volume of the journal in which the paper was published
   *
   * @param volume The volume of the paper
   */
  public void setVolume(int volume) {
    vol = volume;
  }

  /**
   * Set the journal in which the paper was published
   *
   * @param journal The name of scientific journal
   */
  public void setJournal(String journal) {
    this.journal = journal;
  }

  /**
   * Sets the first (numerical) page of the paper
   *
   * @param firstPage The first page
   */
  public void setFirstPage(int firstPage){
    fp = firstPage;
    special_page = "";
    if (lp == Integer.MAX_VALUE){
      lp = fp;
    }
  }

  /**
   * Sets the last (numerical) page of the paper
   *
   * @param lastPage The last page
   */
  public void setLastPage(int lastPage) {
    lp = lastPage;
    special_page = "";
  }

  /**
   * Sets the new special (not numerical) page
   *
   * @param special_page
   */
  public void setSpecial_page(String special_page) {
    this.special_page = special_page;
    fp = lp = Integer.MAX_VALUE;
  }

  /**
   * Sets the year of the paper
   *
   * @param year The year of publication
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Return null, because the APACitation doesn't have
   * a reference
   *
   * @return null
   */
  public List<Literature> getReferences() {
    return null;
  }

  /**
   * Do nothing
   *
   * @param commentary the commentary of an Attribute
   */
  public void setCommentary(String commentary) { }

  /**
   * Return nothing (null), reference doesn't
   * have commentaries
   *
   * @return null
   */
  public String getCommentary() {
    return null;
  }

  /**
   * Do nothing
   *
   * @param references the new list of reference
   */
  public void setReferences(List<Literature> references) { }

  /**
   * Do nothing
   *
   * @param reference A reference({@Link entities.Literature}), a list of reference, or an empty argument
   */
  public void addReference(Literature... reference) { }

  /**
   * Gets the methods to do the SOAP query
   *
   * @return The method of the SOAP query
   */
  public String getMethod() {
    return "getReference";
  }

  /**
   * Gets the parameter for a query
   * for simplificaton just the title
   *
   * @return the parameter for the query
   */
  public String getParameter() {
    return "title*" + title;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{"authors","title","journal","volume","pages","year"};
    return Arrays.asList(columns);
  }

  public void setAttribute(HashMap<String, String> resultOfQuery) {
    setAuthors(resultOfQuery.get("authors").split("; "));
    setTitle(resultOfQuery.get("title"));
    setJournal(resultOfQuery.get("journal"));
    try {
      setVolume(Integer.valueOf(resultOfQuery.get("volume")));
    } catch (Exception e) {
    }
    String pages = resultOfQuery.get("pages");
    try {
      setYear(Integer.valueOf(resultOfQuery.get("year")));
    } catch (Exception e) {
    }
    if (pages.contains("-")) {
      fp = Integer.valueOf(pages.split("-")[0]);
      lp = Integer.valueOf(pages.split("-")[1]);
      if (lp < fp) {
        lp = (fp / 10) * 10 + lp;
      }
      setFirstPage(fp);
      setLastPage(lp);
    } else {
      try {
        setFirstPage(Integer.valueOf(pages));
        setLastPage(Integer.valueOf(pages));
      } catch (NumberFormatException e) {
        setSpecial_page(pages);
      }
    }
  }

  public HashMap<String, HashMap<String, ?>> getColumnsForTable() {
    HashMap<String, HashMap<String, ?>> out = new HashMap<String, HashMap<String, ?>>();
    HashMap<String, String> apa_out = new HashMap<String, String>();
    apa_out.put("Literature", this.toString());
    out.put("Reference", apa_out);
    return out;
  }

  /**
   * Gets the list of authors
   *
   * @return List of the authors of the paper
   */
  public List<String> getAuthors(){
    return authors;
  }

  /**
   * Gets the title of the scientific paper
   *
   * @return The title of the paper
   */
  public String getTitle(){
    return title;
  }

  /**
   * Gets the journal of the paper
   *
   * @return The journal
   */
  public String getJournal(){
    return journal;
  }

  /**
   * Gets the volume
   *
   * @return Volume
   */
  public int getVolume(){
    return vol;
  }

  /**
   * Gets the first page
   *
   * @return The first page
   */
  public int getFirstPage(){
    return fp;
  }

  /**
   * Gets the last page
   *
   * @return The Last page
   */
  public int getLastPage(){
    return lp;
  }

  /**
   * Gets the special (not numerical) page
   *
   * @return The non numerical page
   */
  public String getSpecialPage(){
    return special_page;
  }

  /**
   * Gets the year of publication
   *
   * @return The year
   */
  public int getYear() {
    return year;
  }

  @Override
  public String toString(){
    String author_string = "";
    for (String author:authors){
      author_string += author + "; ";
    }
    String author = author_string.substring(0, max(author_string.length() - 2, 0));
    author += ": ";
    String pages = String.valueOf(fp);
    if (fp != lp){
      pages += "-" + String.valueOf(lp);
    }
    if (special_page != ""){
      pages = special_page;
    }
    return author + title + ". " + journal + ". (" + String.valueOf(year) + ") " + String.valueOf(vol) + ", " + pages + ".";
  }
}