package controllers;

import model.Book;
import model.LibraryItem;
import model.WestminsterLibraryManager;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import util.DateTime;
import views.html.Add.addBook;
import views.html.Borrow.*;
import views.html.Display.viewReturnBook;
import views.html.Report.report;
import views.html.Return.returnBook;
import views.html.Delete.deleteBook;
import views.html.Display.viewAllBook;
import views.html.Display.viewBorrowBook;
import views.html.ItemNotFound;
import views.html.Reservation.reservation;
import views.html.Return.returnBookIsbn;
import views.html.libraryMain;
import views.html.notBorrowed;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class booksController extends Controller {

    @Inject
    FormFactory bookformFactory;

    static Integer bookCount = 0;
    static Integer spaceAvailable = 150;
    static String borrowingISBN;
    static LibraryItem revervedItem,returnedItem;

    static List<LibraryItem> List = new ArrayList<>();

    WestminsterLibraryManager manage = new WestminsterLibraryManager();

    public Result addBookView() {
        Form<Book> form = bookformFactory.form(Book.class);
        return ok(addBook.render(form, bookCount, spaceAvailable));
    }

    public Result addBookSave() {
        Form<Book> form = bookformFactory.form(Book.class).bindFromRequest();
        Book book = form.get();
        book.setLendingDays(7);
        List.add(book);
        Book.addBook(book);
        bookCount++;
        spaceAvailable--;
        return redirect(routes.booksController.showBookView());
    }

    public Result deleteBookView() {
        Form<Book> deleteForm = bookformFactory.form(Book.class);
        return ok(deleteBook.render(deleteForm, bookCount, spaceAvailable));
    }

    public Result deleteBookComplete() {
        Form<Book> deleteForm = bookformFactory.form(Book.class).bindFromRequest();
        Book book = deleteForm.get();
        Book deletingBook = Book.findByIsbn(book.getIsbn());
        if (deletingBook != null) {
            Book.removeBook(deletingBook);
            List.remove(deletingBook);
            spaceAvailable++;
            bookCount--;
            return redirect(routes.booksController.showBookView());

        } else {
            return notFound();
        }
    }

        public Result showBookView(){
            List<Book> books = Book.allBooks();
            return ok(viewAllBook.render(books));
        }

    public Result borrowBookIsbn(){
        Form<Book> isbnForm = bookformFactory.form(Book.class);
        return ok(borrowBookIsbn.render(isbnForm));
    }

    public Result addBookIsbn(){
        Form<Book>isbnForm = bookformFactory.form(Book.class).bindFromRequest();
        Book b = isbnForm.get();
        borrowingISBN = b.getIsbn();
        for(LibraryItem x :List){
            if(x.getIsbn().equals(b.getIsbn())){
                if(x.getAvailability()==1) {
                    x.setAvailability(0);
                    return borrowBookView();
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

    public Result borrowBookView(){
        Form<DateTime> dataTimeForm = bookformFactory.form(DateTime.class);
        return ok(borrowBook.render(dataTimeForm));
    }

    public Result borrowBook(){
        Form<DateTime> DateTimeForm = bookformFactory.form(DateTime.class).bindFromRequest();
        DateTime borrowingday= DateTimeForm.get();
        manage.borrowAnItem(borrowingday, borrowingISBN, List);
        return displayBook();
    }

    public Result displayBook(){
        for(LibraryItem x: List){
            if(x.getIsbn().equals(borrowingISBN)){
                return ok(viewBorrowBook.render(x));
            }
        }
        return ok(libraryMain.render());
    }

    public Result returnBookIsbn(){
        Form<Book> returnForm = bookformFactory.form(Book.class);
        return ok(returnBookIsbn.render(returnForm));
    }

    public Result addReturningBookIsbn(){
        Form<Book> returnForm = bookformFactory.form(Book.class).bindFromRequest();
        Book b = returnForm.get();
        for(LibraryItem x :List){
            if(x.getIsbn().equals(b.getIsbn())){
                if(x.getAvailability()==0) {
                    x.setAvailability(1);
                    returnedItem = x;
                    return returnBookView();
                }else{
                    return notBorrowed();
                }
            }
        }
        return ok(ItemNotFound.render());
    }

    public Result returnBookView(){
        Form<DateTime> returnForm = bookformFactory.form(DateTime.class);
        return ok(returnBook.render(returnForm));
    }

    public Result returnBook(){
        Form<DateTime>returnForm = bookformFactory.form(DateTime.class).bindFromRequest();
        DateTime b = returnForm.get();
        manage.fineCalculation(b, returnedItem,List);
        return ok(viewReturnBook.render(returnedItem));
    }

    public Result notBorrowed(){
        return ok(notBorrowed.render());
    }

    public Result report(){
        manage.generateReport(List);
        return ok(report.render(List));
    }
}
