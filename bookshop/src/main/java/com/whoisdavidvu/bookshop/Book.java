package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.Arrays;

public class Book {
    
    private String title;           // title of book
    private BigDecimal bookValue;   // price of book
    private int pageCount;          // number of pages in the book
    private Genres genre;           // genre of book
    private String isbn13;          // ISBN13 code of the book

    // constructor of class Book
    public Book(String newTitle, String priceAsString, int numberOfPages, Genres bookGenre, String isbn) {
        
        this.title = newTitle;
        this.bookValue = new BigDecimal(priceAsString);
        this.pageCount = numberOfPages;
        this.genre = bookGenre;
        this.isbn13 = isbn;
         
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
        // in case creation of books with invalid ISBN-13 can throw an IAEX if needed
        if (checkISBN13() == false) {
            System.out.println("Warning: Book " + "\"" + this.title + "\"" + " has invalid ISBN-13 and has not been added!");
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

    //returns the ISBN
    public String getISBN() {
        return isbn13;
    }

    // checks if the book has a valid ISBN
    public boolean checkISBN13() {

        // gets and checks the correct prefix of the ISBN-13
        int prefixInt = Integer.parseInt(this.isbn13.substring(0, 3));
        if (prefixInt != 978) {
            return false;
        }

        // checking for correct suffix length
        String suffixString = this.isbn13.substring(4);
        if (suffixString.length() != 10) {
            return false;
        }

        // checking the validity of the last 9 digits (suffix)
        int[] suffixInts = new int[suffixString.length()];
        int prefixSum = 38;
        int suffixSum = 0;

        for (int i = 0; i < suffixString.length(); i++) {
            suffixInts[i] = suffixString.charAt(i) - '0';
        }

        for (int i = 0; i < (suffixInts.length/2); i++) {
            suffixSum += suffixInts[2*i]*3;
            suffixSum += suffixInts[2*i+1];
        }

        if ((suffixSum + prefixSum) % 10 == 0) {
            return true;
        }
        return false;
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
                "name: " + this.getTitle() + ", " + 
                "price: " + this.getValue() + ", " + 
                "page count: " + this.getPageCount() + ", " + 
                "genre: " + this.getGenre() + ", " + 
                "isbn-13: " + this.getISBN());
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, "978-3608963762");
        Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, "978-3442267747");
        Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, "978-758245159");
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, "978-3841335180");
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, "978-3442267819");
        Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, "978-0007136582");
        
        // 3*3 + 6*1 + 0*3 + 8*1 + 9*3 + 6*1 + 3*3 + 7*1 + 6*3 + 2*1
        // = 92 -> valid
        System.out.println(narnia.checkISBN13());
        
        // 3*3 + 4*1 + 4*3 + 2*1 + 2*3 + 6*1 + 7*3 + 7*1 + 4*3 + 7*1
        // = 86 -> invalid
        System.out.println(gatesBio.checkISBN13());

        // invalid because length not 13
        System.out.println(jobsBio.checkISBN13());
        
        System.out.println(mobydick.checkISBN13());
        System.out.println(odyssey.checkISBN13());
        System.out.println(lotr.checkISBN13());
    }
}
