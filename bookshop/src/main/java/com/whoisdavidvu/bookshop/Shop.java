package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        this.currentInventory.add(b);
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




    // main method for testing basic functionality
    public static void main (String[] args) {
        Shop bookshop = new Shop("Thalia", "1337");

        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        Book gatesBio = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
        Book jobsBio = new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography);
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure);
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure);

        Customer steve = new Customer("Steve Jobs", "1984");

        
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(gatesBioDuplicate);
        bookshop.addBook(jobsBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        /* bookshop.addBook(new Book("Narnia", "19.99", 365, Genres.Fantasy));
        bookshop.addBook(new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography));
        bookshop.addBook(new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography));
        bookshop.addBook(new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography));
        bookshop.addBook(new Book("Moby-Dick", "29.99", 599, Genres.Adventure));
        bookshop.addBook(new Book("Odyssey", "19.99", 333, Genres.Adventure));
        bookshop.addBook(new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography)); */

        /* // 1. customer purchases book
        // before purchase 
        System.out.println("Sales figure is: " + bookshop.getBalance());
        System.out.println("Inventory is: " + bookshop.getInventory());
        System.out.println();

        // after purchase
        steve.purchasesBook(gatesBio, bookshop);
        System.out.println("Sales figure after sale: " + bookshop.getBalance());
        System.out.println("Inventory after sale: " + bookshop.getInventory());

        // updated customer data
        System.out.println();
        System.out.println(steve.toString()); */

        /* // 2. finds adventure books
        System.out.println(bookshop.filterBookByGenre("bio")); */

        // 3. finds duplicate books and deletes them
        System.out.println(bookshop.getInventory());
        bookshop.findDeleteDuplicates();
        System.out.println();
        System.out.println(bookshop.getInventory());

        
    }
    
}
