package model;
import util.DateTime;
import io.ebean.Finder;
import util.LibraryItemComparator;

import java.util.*;

public class WestminsterLibraryManager implements LibraryManager {

    // for availability, 1 is used for available and 0 is used for unavailable
    private List<LibraryItem> items = new ArrayList<>();
    Book bookList[] = new Book[100];
    Dvd dvdList[] = new Dvd[50];
    private int itemDVD = 0;
    private int itemBooks = 0;
    private int totalItems = 0;
    private Scanner sc = new Scanner(System.in);
    private int choice;
    private int totalAvailableSpace = 150;
    private List<Reader> readerList = new ArrayList<>();

    @Override
    public void addNewItem() {
        // free space available, max items,
        if (totalItems == 150) {
            System.out.println("no space available to enter an item to the system.");
        } else {
            System.out.println("please enter what do you want to add ");
            System.out.println("1. book \n 2. DVD\n ");
            choice = sc.nextInt();
            if ((choice == 1) && (itemBooks < 100)) {
                Book book = new Book();
                System.out.println("please enter the isbn Number ");
                book.setIsbn(sc.next());
                System.out.println("please enter the title ");
                book.setTitle(sc.next());
                System.out.println("please enter the sector ");
                book.setSector(sc.next());
                System.out.println("please enter the publication Date ");
                book.setPublicationYear(sc.nextInt());
                System.out.println("please enter the author  ");
                book.setAuthor1(sc.next());
                System.out.println("please enter the publisher ");
                book.setPublisher(sc.next());
                System.out.println("please enter the total number of pages ");
                book.setTotalPages(sc.nextInt());

                itemBooks++;
                totalItems++;
                book.setAvailability(1);
                items.add(book);
                totalAvailableSpace--;

            } else if ((choice == 2) && (itemDVD < 50)) {
                Dvd dvd = new Dvd();

                System.out.println("please enter the isbn Number ");
                dvd.setIsbn(sc.next());
                System.out.println("please enter the title ");
                dvd.setTitle(sc.next());
                System.out.println("please enter the sector ");
                dvd.setSector(sc.next());
                System.out.println("please enter the publication Date ");
                dvd.setPublicationYear(sc.nextInt());
                System.out.println("please enter the producer name ");
                dvd.setProducer(sc.next());
                System.out.println("please enter the actors name ");
                dvd.setActor1(sc.next());
                System.out.println("please enter the available sub  ");
                dvd.setSubtitle1(sc.next());
                System.out.println("please enter the total languages ");
                dvd.setLanguage1(sc.next());

                itemDVD++;
                totalItems++;
                dvd.setAvailability(1);
                items.add(dvd);
                totalAvailableSpace--;
            }
        }
    }

    @Override
    public void deleteAnItem() {

        // when isbn number is given should delete the item.
        //free space left
        //type of item deleted.

        System.out.println("please enter the isbn number");
        String isbnNo = sc.next();
        for (LibraryItem x : items) {
            if (x.getIsbn().equals(isbnNo)) {
                    itemBooks--;

                } else {
                    itemDVD--;
                }
                totalAvailableSpace++;
                totalItems--;
                System.out.println("isbn number " + x.getIsbn() + "\nType " + x.getType() + "\navailable space " + totalAvailableSpace);
                items.remove(x);
            }
        }

    @Override
    public void displayAnItem() {
        /* isbn, type, title*/

        System.out.println("isbn no        type          title");
        for (LibraryItem x : items) {

            System.out.println(x.getIsbn() + "           " + x.getType() + "          " + x.getTitle());
        }
    }

    public void borrowAnItem(DateTime x, String isbn,List<LibraryItem> i) {
        for (LibraryItem y : i) {
            if (y.getIsbn().equals(isbn)) {
                y.setBurroweddateTime(x);
                DateTime nextDay = new DateTime();
                nextDay.setDay(y.getBurroweddateTime().getDay()+ y.getLendingDays());
                if (nextDay.getDay()>30){
 //                   if(((x.getMonth()%2)==1)|| (x.getMonth()==8)){
                    if((x.getMonth()<8 && ((x.getMonth()%2)==1)||(x.getMonth()==8)|| ((x.getMonth()==10))||((x.getMonth()==12)))){
                        nextDay.setDay(nextDay.getDay()-31);
                        nextDay.setMonth(x.getMonth()+1);
                        if (nextDay.getMonth()>12){
                            nextDay.setMonth(1);
                            nextDay.setYear((x.getYear())+1);
                        }

                    }else {
                        nextDay.setDay(nextDay.getDay() - 30);
                        nextDay.setMonth(x.getMonth() + 1);
                        if (nextDay.getMonth() > 12) {
                            nextDay.setMonth(1);
                            nextDay.setYear(x.getYear() + 1);
                        }
                    }
                } else {
                    nextDay.setMonth(x.getMonth());
                    nextDay.setYear(x.getYear());
                }

                y.setNextAvailable(nextDay);
            }

        }

    }


    public void generateReport(List <LibraryItem> a) {
        items.sort(new LibraryItemComparator());
    }


    public void makeReservation(LibraryItem x) {

        DateTime nextDay = new DateTime();
        nextDay.setDay(x.getNextAvailable().getDay()+ x.getLendingDays());
        if (nextDay.getDay()>30){
            if(((x.getNextAvailable().getMonth()%2)==1)|| (x.getBurroweddateTime().getMonth()==8)){
                nextDay.setDay(nextDay.getDay()-31);
                nextDay.setMonth(x.getNextAvailable().getMonth()+1);
                if (nextDay.getMonth()>12){
                    nextDay.setMonth(1);
                    nextDay.setYear(x.getNextAvailable().getYear()+1);
                }

            }else {
                nextDay.setDay(nextDay.getDay() - 30);
                nextDay.setMonth(x.getNextAvailable().getMonth() + 1);
                if (nextDay.getMonth() > 12) {
                    nextDay.setMonth(1);
                    nextDay.setYear(x.getNextAvailable().getYear() + 1);
                }
            }
        } else {
            nextDay.setMonth(x.getNextAvailable().getMonth());
            nextDay.setYear(x.getNextAvailable().getYear());
        }

        x.setReservedAvailableDate(nextDay);
    }

    public void fineCalculation(DateTime date, LibraryItem y, List<LibraryItem> list){
        int d1= y.getNextAvailable().getDay();
        int m1 = y.getNextAvailable().getMonth();
        int y1 = y.getNextAvailable().getYear();

        int d2= date.getDay();
        int m2 = date.getMonth();
        int y2 =date.getYear();
        int day, month,year;
        double fine =0.0;
        if (d2<d1){
            day =((d2+30)- d1);
            m2--;
            if(m2 >= m1){
                month = m2- m1;

            }else {
                month= (m2+12)- m1;
                y2--;

            }
        }else {
            day = d2- d1;
            if(m2 >= m1){
                month = m2- m1;


            }else {
                month= (m2+12)- m1;
                y2--;

            }
        }
        year= y2 - y1;
        if(day>3) {
            fine = ((day + (month * 30) + (year * 365)) * 24 * 0.5) - 21.6;
        }else {
            fine = ((day + (month * 30) + (year * 365)) * 24 * 0.2);
        }
        y.setFine(fine);
    }

    public static final Finder<Long, WestminsterLibraryManager> find = new Finder<>(WestminsterLibraryManager.class);
}