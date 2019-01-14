package attributes;

import static sun.swing.MenuItemLayoutHelper.max;

import entities.Literature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APAcitation implements Attribute{

  private List<String> authors;
  private String title;
  private String journal;
  private int vol;
  private int fp;
  private int lp;
  private int yyyy;

  public APAcitation(String theTitle, String theJournal, int volume, int first_page, int last_page, int year, String... theAuthors){
    authors = new ArrayList<String>();
    title = theTitle;
    journal = theJournal;
    vol = volume;
    fp = first_page;
    lp = last_page;
    yyyy = year;
    for (String author:theAuthors){
      authors.add(author);
    }
  }

  public APAcitation(String theAuthor, String theTitle, String theJournal, int volume, int first_page, int last_page, int year){
    authors = new ArrayList<String>();
    title = theTitle;
    journal = theJournal;
    vol = volume;
    fp = first_page;
    lp = last_page;
    yyyy = year;
    authors.add(theAuthor);
  }

  public APAcitation(String theTitle, String theJournal, int volume, int page, int year, String... theAuthors){
    authors = new ArrayList<String>();
    title = theTitle;
    journal = theJournal;
    vol = volume;
    fp = page;
    lp = page;
    yyyy = year;
    for (String author:theAuthors){
      authors.add(author);
    }
  }

  public APAcitation(String theAuthor, String theTitle, String theJournal, int volume, int page, int year){
    authors = new ArrayList<String>();
    title = theTitle;
    journal = theJournal;
    vol = volume;
    fp = page;
    lp = page;
    yyyy = year;
    authors.add(theAuthor);
  }

  public APAcitation(String theTitle, String theJournal, int volume, int year, String... theAuthors){
    authors = new ArrayList<String>();
    title = theTitle;
    journal = theJournal;
    vol = volume;
    fp = Integer.MAX_VALUE;
    lp = Integer.MAX_VALUE;
    yyyy = year;
    for (String author:theAuthors){
      authors.add(author);
    }
  }

  @Override
  public boolean equals(Object anotherOne){
    boolean ans;
    if (anotherOne instanceof APAcitation){
      ans = true;
      ans &= authors.equals(((APAcitation) anotherOne).getAuthors());
      ans &= title.equals(((APAcitation) anotherOne).getTitle());
      ans &= journal.equals(((APAcitation) anotherOne).getJournal());
      ans &= vol == ((APAcitation) anotherOne).getVolume();
      ans &= fp == ((APAcitation) anotherOne).getFirstPage();
      ans &= lp == ((APAcitation) anotherOne).getLastPage();
      ans &= yyyy == ((APAcitation) anotherOne).getYear();
    }
    else{
      ans = false;
    }
    return ans;
  }

  public void setAuthors(String... theAuthors) {
    authors = new ArrayList<String>();
    for (String author:theAuthors){
      authors.add(author);
    }
  }

  public void addAuthor(String anAuthor){
    try{
      authors.add(anAuthor);
    } catch (Exception e){
      authors = new ArrayList<String>();
      authors.add(anAuthor);
    }
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setVolume(int volume) {
    vol = volume;
  }

  public void setJournal(String journal) {
    this.journal = journal;
  }

  public void setFirstPage(int firstPage){
    fp = firstPage;
  }

  public void setLastPage(int lastPage) {
    lp = lastPage;
  }

  public void setYear(int year) {
    yyyy = year;
  }

  public List<Literature> getReferences() {
    return null;
  }

  public void setReferences(List<Literature> references) { }

  public void addReference(Literature reference) { }

  public String getMethod() {
    return "getReference";
  }

  public String getParameter() {
    return "title*" + title;
  }

  public List<String> getColumns() {
    String[] columns = new String[]{
        "ecNumber","reference","authors","title","journal","volume",
        "pages","year","organism","commentary","pubmedId","textmining"
    };
    return Arrays.asList(columns);
  }

  public List<String> getAuthors(){
    return authors;
  }

  public String getTitle(){
    return title;
  }

  public String getJournal(){
    return journal;
  }

  public int getVolume(){
    return vol;
  }

  public int getFirstPage(){
    return fp;
  }

  public int getLastPage(){
    return lp;
  }

  public int getYear() {
    return yyyy;
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
    return author + title + ". " + journal + ". (" + String.valueOf(yyyy) + ") " + String.valueOf(vol) + ", " + pages + ".";
  }
}
