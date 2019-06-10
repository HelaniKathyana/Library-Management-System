package model;

import util.DateTime;
import io.ebean.Model;
import javax.persistence.Column;
import java.util.List;

 public abstract class LibraryItem extends Model {
    @Column
    private String isbn;
    @Column
    private String title;
    @Column
    private String sector;
    @Column
    private int publicationYear;
    private Reader currentReader;
    private int[] datetime= new int[5];
    // private int day, month, year, hr, mint;
    private DateTime burroweddateTime = new DateTime();
    private double fine;
    private Reader borrower = new Reader();
    private int type;
    private int availability= 1;
    private DateTime nextAvailable= new DateTime();
    private int lendingDays;
    private DateTime reservedAvailableDate = new DateTime();
    private Reader nextreader = new Reader();
    private String nextReader;

    public static LibraryItem deleteanItem(LibraryItem e, List<LibraryItem> list){

        for (LibraryItem x : list){
            if(x.getIsbn().equals(e.getIsbn())){
                return e;
            }
        }
        return null;
    }

    public int getAvailabitliy() {
        return availabitliy;
    }

    public void setAvailabitliy(int availabitliy) {
        this.availabitliy = availabitliy;
    }

    private int availabitliy;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Reader getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(Reader currentReader) {
        this.currentReader = currentReader;
    }

    public int[] getDatetime() {
        return datetime;
    }

    public void setDatetime(int[] datetime) {
        this.datetime = datetime;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String toString(){
        return String.valueOf(fine);
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    public DateTime getBurroweddateTime() {
        return burroweddateTime;
    }

    public void setBurroweddateTime(DateTime burroweddateTime) {
        this.burroweddateTime = burroweddateTime;
    }
    public DateTime getNextAvailable() {
        return nextAvailable;
    }

    public void setNextAvailable(DateTime nextAvailable) {
        this.nextAvailable = nextAvailable;
    }

    public int getLendingDays() {
        return lendingDays;
    }

    public void setLendingDays(int days) {
        this.lendingDays = days;
    }

    public String getNextReader() {
        return nextReader;
    }

    public void setNextReader(String nextReader) {
        this.nextReader = nextReader;
    }

    public DateTime getReservedAvailableDate() {
        return reservedAvailableDate;
    }

    public void setReservedAvailableDate(DateTime reservedAvailableDate) {
        this.reservedAvailableDate = reservedAvailableDate;
    }
}
