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
         
        if (newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is invalid!");
        }
        if (bookValue.compareTo(BigDecimal.ZERO) < 0)  {
            throw new IllegalArgumentException("Price can not be negative!");
        }
        if (bookValue.scale() > 2)  {
            throw new IllegalArgumentException("Price is invalid!");
        }
        if (pageCount <= 0 || numberOfPages <= 0) {
            throw new IllegalArgumentException("Book has to have pages!");
        }
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bookValue == null) ? 0 : bookValue.hashCode());
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + pageCount;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookValue == null) {
            if (other.bookValue != null)
                return false;
        } else if (!bookValue.equals(other.bookValue))
            return false;
        if (genre != other.genre)
            return false;
        if (pageCount != other.pageCount)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return ("\n" + 
                "book name: " + this.getTitle() + ", " + 
                "book price: " + this.getValue() + ", " + 
                "book page count: " + this.getPageCount() + ", " + 
                "book genre: " + this.getGenre());
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        System.out.println(narnia.toString());
    }
}
