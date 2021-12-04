package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;

public class Book {

    // enum of possible genres
    private enum Genre { Comedy, Horror, Action, Romance, Documentary, Thriller, Drama}
    
    private String title;       // title of book
    private BigDecimal bookValue;   // price of book
    private int pageCount;      // number of pages in the book
    private Genre genre;        // genre of book

    // constructor of class Book
    public Book(String newTitle, String bookPrice, int numberOfPages, Genre bookGenre) {
        this.title = newTitle;
        this.bookValue = new BigDecimal(bookPrice);
        this.pageCount = numberOfPages;
        this.genre = bookGenre;
    }

    // returns the title of the book
    public String getTitle() {
        return title;
    }

    // returns the value of the book
    public BigDecimal getValue() {
        return bookValue;
    }

    // returns the page count of the book
    public int getPageCount() {
        return pageCount;
    }

    // returns the genre of the book
    public Genre getGenre() {
        return genre;
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Book narnia = new Book("Narnia", "19.99", 365, Genre.Thriller);
        System.out.println("The title of the book is: " + narnia.getTitle());
        System.out.println("The price of the book is: " + narnia.getValue());
        System.out.println("The page count of the book is: " + narnia.getPageCount());
        System.out.println("The genre of the book is: " + narnia.getGenre());
    }
}
