package attributes;

import static sun.swing.MenuItemLayoutHelper.max;

import entities.Literature;
import entities.Organism;
import java.util.ArrayList;
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

  @Override
  public ArrayList<Literature> getReferences() {
    return null;
  }

  @Override
  public String getMethod() {
    return "getReference";
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
