package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;

public class Book {
    
    private String title;       // title of book
    private BigDecimal bookValue;   // price of book
    private int pageCount;      // number of pages in the book
    private Genres genre;        // genre of book

    // constructor of class Book
    public Book(String newTitle, String priceAsString, int numberOfPages, Genres bookGenre) {
        this.title = newTitle;
        this.bookValue = new BigDecimal(priceAsString);
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
    public Genres getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return ("book name: " + this.getTitle() + "\n" + 
                "book price: " + this.getValue() + "\n" + 
                "book page count: " + this.getPageCount() + "\n" + 
                "book genre: " + this.getGenre());
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Thriller);
        System.out.println(narnia.toString());
    }
}
