package model;

import io.ebean.Finder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

 @Entity
 @Table(name= "book")
    public class Book extends LibraryItem {
        @Column
        private String publisher;
        @Column
        private int totalPages;
        private String authors[]= new String[3];
        @Column
        private String author1;
        @Column
        private String author2;
        @Column
        private String author3;

        public Book(){

        }
        public Book(String isbn,String title,String sector,int publicationYear,String author1,String author2,String author3,String publisher,int totalPages){
            super();
            this.setIsbn(isbn);
            this.setTitle(title);
            this.setSector(sector);
            this.setPublicationYear(publicationYear);
            this.setAuthor1(author1);
            this.setAuthor1(author2);
            this.setAuthor1(author3);
            this.setTotalPages(totalPages);
            this.setPublisher(publisher);
        }

        private static List<model.Book> books = new ArrayList<>();

        public static List<model.Book> allBooks() {
            return books;
        }

        public static void addBook(model.Book book){
            books.add(book);
        }

        public static Book findByIsbn(String isbn){
           for (Book book : books){
             if(book.getIsbn().equals(isbn)){
                 return book;
             }
           }
         return null;
         }

         public static void removeBook(model.Book e){
               books.remove(e);
         }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public String[] getAuthors() {
            return authors;
        }

        public void setAuthors( String authors1, String authors2, String authors3) {
            authors[0]= authors1;
            authors[1]= authors2;
            authors[2]= authors3;
        }
        public String getAuthor1() {
            return author1;
        }

        public void setAuthor1(String author1) {
            this.author1 = author1;
        }

        public String getAuthor2() {
            return author2;
        }

        public void setAuthor2(String author2) {
            this.author2 = author2;
        }

        public String getAuthor3() {
            return author3;
        }

        public void setAuthor3(String author3) {
            this.author3 = author3;
        }

        public static final Finder<Long, model.Book> find = new Finder<>(model.Book.class);
}