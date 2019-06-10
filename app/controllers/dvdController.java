package controllers;

import model.Dvd;
import model.LibraryItem;
import model.WestminsterLibraryManager;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import util.DateTime;
import views.html.Add.addDvd;
import views.html.Borrow.borrowDvd;
import views.html.Borrow.dvdIsbn;
import views.html.Delete.deleteDvd;
import views.html.Display.*;
import views.html.ItemNotFound;
import views.html.Reservation.reservation;
import views.html.Return.returnDvd;
import views.html.Return.returnDvdIsbn;
import views.html.libraryMain;
import views.html.notBorrowed;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class dvdController extends Controller {

    @Inject
    FormFactory dvdformFactory;

    static int DVDCount = 0;
    static Integer spaceAvailable= 150;
    static String borrowingISBN;
    static LibraryItem revervedItem,returnedItem;

    static List<LibraryItem> List = new ArrayList<>();

    WestminsterLibraryManager manage = new WestminsterLibraryManager();

    public Result addDvdView() {
        Form<Dvd> form = dvdformFactory.form(Dvd.class);
        return ok(addDvd.render(form, DVDCount, spaceAvailable));
    }

    public Result addDvdSave() {
        Form<Dvd> form = dvdformFactory.form(Dvd.class).bindFromRequest();
        Dvd dvd = form.get();
        dvd.setLendingDays(7);
        List.add(dvd);
        Dvd.addDVD(dvd);
        DVDCount++;
        spaceAvailable--;
        return redirect(routes.dvdController.showDvdView());
    }

    public Result deleteDvdView() {
        Form<Dvd> deleteForm = dvdformFactory.form(Dvd.class);
        return ok(deleteDvd.render(deleteForm, DVDCount, spaceAvailable));
    }

    public Result deleteDvdComplete() {
        Form<Dvd> deleteForm = dvdformFactory.form(Dvd.class).bindFromRequest();
        Dvd dvd = deleteForm.get();
        Dvd deletingDvd = Dvd.findByIsbn(dvd.getIsbn());
        if (deletingDvd != null) {
            Dvd.removeDvd(deletingDvd);
            List.remove(deletingDvd);
            spaceAvailable++;
            DVDCount--;
            return redirect(routes.dvdController.showDvdView());
        } else {
            return notFound();
        }
    }

    public Result showDvdView(){
        List<Dvd> dvd = Dvd.allDvd();
        return ok(viewAllDvd.render(dvd));
    }

    public Result borrowDvdIsbn(){
        Form<Dvd> isbnForm = dvdformFactory.form(Dvd.class);
        return ok(dvdIsbn.render(isbnForm));
    }

    public Result addDvdIsbn(){
        Form<Dvd>isbnForm = dvdformFactory.form(Dvd.class).bindFromRequest();
        Dvd d = isbnForm.get();
        borrowingISBN = d.getIsbn();
        for(LibraryItem x :List){
            if(x.getIsbn().equals(d.getIsbn())){
                if(x.getAvailability()==1) {
                    x.setAvailability(0);
                    return borrowDvdView();
                }else {
                    revervedItem = x;
                    return reserveItem();
                }
            }
        }
        return ok(ItemNotFound.render());
    }

    public Result reserveItem(){
        manage.makeReservation(revervedItem);
        return ok(reservation.render(revervedItem));
    }

    public Result borrowDvdView(){
        Form<DateTime> dataTimeForm = dvdformFactory.form(DateTime.class);
        return ok(borrowDvd.render(dataTimeForm));
    }

    public Result borrowDvd(){
        Form<DateTime> DateTimeForm = dvdformFactory.form(DateTime.class).bindFromRequest();
        DateTime borrowingday= DateTimeForm.get();
        manage.borrowAnItem(borrowingday, borrowingISBN, List);
        return displayDvd();
    }

    public Result displayDvd(){
        for(LibraryItem x: List){
            if(x.getIsbn().equals(borrowingISBN)){
                return ok(viewBorrowDvd.render(x));
            }
        }
        return ok(libraryMain.render());
    }

    public Result returnDvdIsbn(){
        Form<Dvd> returnForm = dvdformFactory.form(Dvd.class);
        return ok(returnDvdIsbn.render(returnForm));
    }

    public Result addReturningDvdIsbn(){
        Form<Dvd> returnForm = dvdformFactory.form(Dvd.class).bindFromRequest();
        Dvd d = returnForm.get();
        for(LibraryItem x :List){
            if(x.getIsbn().equals(d.getIsbn())){
                if(x.getAvailability()==0) {
                    x.setAvailability(1);
                    returnedItem = x;
                    return returnDvdView();
                }else{
                    return notBorrowed();
                }
            }
        }
        return ok(ItemNotFound.render());
    }

    public Result returnDvdView(){
        Form<DateTime> returnForm = dvdformFactory.form(DateTime.class);
        return ok(returnDvd.render(returnForm));
    }

    public Result returnDvd(){
        Form<DateTime>returnForm = dvdformFactory.form(DateTime.class).bindFromRequest();
        DateTime d = returnForm.get();
        manage.fineCalculation(d, returnedItem,List);
        return ok(viewReturnDvd.render(returnedItem));
    }

    public Result notBorrowed(){
        return ok(notBorrowed.render());
    }

}
