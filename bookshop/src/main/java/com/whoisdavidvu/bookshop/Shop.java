package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {

    private String shopName;                    // name of bookshop
    private BigDecimal totalCash;              // name of total sales figure
    private List<Book> currentInventory;   // list of current book inventory

    // constructor of class Shop
    public Shop(String newName, String cashAsString) throws IllegalArgumentException {
        this.shopName = newName;
        this.totalCash = new BigDecimal(cashAsString);
        this.currentInventory = new ArrayList<Book>();

        if (shopName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Shop must have a name!");
        }
        // unsure if cash can be negative
        if (totalCash.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Warning: Shop's cash is negative!");
            //throw new IllegalArgumentException("Account balance can not be negative!");
        } 
    }

    // returns shop name
    public String getShopName() {
        return shopName;
    }

    // returns shop 
    public BigDecimal getBalance() {
        return totalCash;
    }

    // returns ArrayList of book inventory
    public List<Book> getInventory() {
        return currentInventory;
    }


    // simply adds a book to the inventory arraylist
    public void addBook(Book b) {
        if (b.checkISBN13()) {
            this.currentInventory.add(b);
        }
    }

    // adds value of sold book into cash and removes book from inventory
    public void soldBook(Book b) {
        this.totalCash = this.totalCash.add(b.getValue());
        this.currentInventory.remove(b);
    }    

    // filters out and returns books with a desired genre
    public List<Book> filterBookByGenre(String genre) {
        //ArrayList<Book> filteredList = new ArrayList<Book>();
        List<Book> filteredList = currentInventory.stream()
                                .filter(book -> book.getGenre().toString().toUpperCase().contains(genre.toUpperCase()))
                                .collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new Error("Can not find Books with desired genre.");
        }
        return filteredList;
    }

    // finds duplicate books and deletes them
    public void findDeleteDuplicates() {
        this.currentInventory = new ArrayList<Book>(new HashSet<Book>(this.currentInventory));
    }

    // returns list of same books as other shop
    public List<Book> getThisList(Shop otherShop) {
        return this.currentInventory.stream()
                .filter(two -> otherShop.currentInventory.stream()
                .anyMatch(one -> one.getTitle().equals(two.getTitle())
                    && (one.getPageCount() == two.getPageCount())
                    && one.getGenre().equals(two.getGenre())
                    && one.getISBN().equals(two.getISBN())))
                .collect(Collectors.toList());
    }

    // returns list of same books as this shop
    public List<Book> getOtherList(Shop otherShop) {
        return otherShop.currentInventory.stream()
                .filter(one -> this.currentInventory.stream()
                .anyMatch(two -> one.getTitle().equals(two.getTitle())
                    && (one.getPageCount() == two.getPageCount())
                    && one.getGenre().equals(two.getGenre())
                    && one.getISBN().equals(two.getISBN())))
                .collect(Collectors.toList());
    }

    // displays same books in two different shops
    public void printMatchingList(Shop otherShop) {
        System.out.println("Matching books from " + this.shopName + ": " + getThisList(otherShop));
        System.out.println();
        System.out.println("Matching books from " + otherShop.shopName + ": " + getOtherList(otherShop));
    }


    // main method for testing basic functionality
    public static void main (String[] args) {
        Shop bookshop = new Shop("Thalia", "1337");

        String validISBN = "978-3608963762";

        // all ISBN valid
        /* Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, validISBN);
        Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, validISBN);
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, validISBN);
        Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, validISBN);
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, validISBN);
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, validISBN);
        Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, validISBN); */

        // For 5. with invalid ISBNs mixed in
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, "978-3608963762");
        Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, "978-3442267747");
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, "978-3442267747");
        Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, "978-758245159");
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, "978-3841335180");
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, "978-3442267819");
        Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, "978-0007136582");

        Customer steve = new Customer("Steve Jobs", "1984");

        
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(gatesBioDuplicate);
        bookshop.addBook(jobsBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);
        bookshop.addBook(lotr);

        // 1. customer purchases book
        // before purchase 
        /* System.out.println("Sales figure is: " + bookshop.getBalance());
        System.out.println("Inventory is: " + bookshop.getInventory());
        System.out.println();

        // after purchase
        steve.purchasesBook(gatesBio, bookshop);
        System.out.println("Sales figure after sale: " + bookshop.getBalance());
        System.out.println("Inventory after sale: " + bookshop.getInventory());

        // updated customer data
        System.out.println();
        System.out.println(steve.toString()); */

        // 2. finds adventure books
        /* System.out.println(bookshop.filterBookByGenre("adventure")); */

        // 3. finds duplicate books and deletes them
        /* System.out.print("Before: ");
        System.out.println(bookshop.getInventory());
        bookshop.findDeleteDuplicates();
        System.out.print("After: ");
        System.out.println(bookshop.getInventory()); */

        // 4. output 2 lists to compare the identical books between two shops
        /* Shop secondshop = new Shop("Mayersche", "1024");
        secondshop.addBook(odyssey);
        secondshop.addBook(new Book("Bill Gates' Biography", "39.99", 512, Genres.Biography, "978-3442267747"));
        secondshop.addBook(new Book("Steve Jobs' Biography", "29.99", 1024, Genres.Biography, "978-3841335180"));
        secondshop.addBook(lotr);
        bookshop.printMatchingList(secondshop); */

        // 5. addBook should not add books with invalid ISBN
        System.out.println(bookshop.getInventory());
    }
    
}
