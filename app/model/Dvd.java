package model;

import io.ebean.Finder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Dvd extends LibraryItem {

    private String language[] = new String[3];
    @Column
    private String language1;
    @Column
    private String language2;
    @Column
    private String language3;

    private String subtitle[] = new String[3];
    @Column
    private String subtitle1;
    @Column
    private String subtitle2;
    @Column
    private String subtitle3;
    @Column
    private String producer;
    private String actors[] = new String[3];
    @Column
    private String actor1;
    @Column
    private String actor2;
    @Column
    private String actor3;

    public Dvd() {}

    public Dvd(String isbn, String title, String sector, int publicationYear, String language1, String language2, String language3, String subtitle1, String subtitle2, String subtitle3, String producer, String actor1, String actor2, String actor3) {
        super();
        this.setIsbn(isbn);
        this.setSector(sector);
        this.setTitle(title);
        this.setPublicationYear(publicationYear);
        this.language1 = language1;
        this.language2 = language2;
        this.language3 = language3;
        this.subtitle1 = subtitle1;
        this.subtitle2 = subtitle2;
        this.subtitle3 = subtitle3;
        this.producer = producer;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }

    static List<Dvd> dvdList = new ArrayList<>();

    public static List<Dvd> allDvd() {
        return dvdList;
    }

    public static void addDVD(Dvd dvd) {
        dvdList.add(dvd);
    }

    public static Dvd findByIsbn(String isbn) {
        for (Dvd x : dvdList) {
            if (x.getIsbn().equals(isbn)) {
                return x;
            }
        }
        return null;
    }

    public static void removeDvd(Dvd e) {
        dvdList.remove(e);
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage() {
        language[0] = getLanguage1();
        language[1] = getLanguage2();
        language[2] = getLanguage3();
    }

    public String[] getSubtitle() {
        return subtitle;
    }

    public void setSubtitle() {
        subtitle[0] = getSubtitle1();
        subtitle[1] = getSubtitle2();
        subtitle[2] = getSubtitle3();
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors() {
        actors[0] = getActor1();
        actors[1] = getActor2();
        actors[2] = getActor3();
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }

    public String getLanguage3() {
        return language3;
    }

    public void setLanguage3(String language3) {
        this.language3 = language3;
    }

    public String getSubtitle1() {
        return subtitle1;
    }

    public void setSubtitle1(String subtitle1) {
        this.subtitle1 = subtitle1;
    }

    public String getSubtitle2() {
        return subtitle2;
    }

    public void setSubtitle2(String subtitle2) {
        this.subtitle2 = subtitle2;
    }

    public String getSubtitle3() {
        return subtitle3;
    }

    public void setSubtitle3(String subtitle3) {
        this.subtitle3 = subtitle3;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public static final Finder<Long, model.Dvd> find = new Finder<>(model.Dvd.class);
}

