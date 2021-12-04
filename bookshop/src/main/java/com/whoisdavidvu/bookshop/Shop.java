package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Shop {

    private String shopName;                    // name of bookshop
    private BigDecimal totalCash;              // name of total sales figure
    private ArrayList<Book> currentInventory;   // list of current book inventory

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
    public ArrayList<Book> getInventory() {
        return currentInventory;
    }


    // simply adds a book to the inventory arraylist
    public void addBook(Book b) {
        currentInventory.add(b);
    }

    // adds value of sold book into cash and removes book from inventory
    public void soldBook(Book b) {
        this.totalCash = this.totalCash.add(b.getValue());
        this.currentInventory.remove(b);
    }    



    // main method for testing basic functionality
    public static void main (String[] args) {
        Shop bookshop = new Shop("Thalia", "1337");

        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        Book gatesBio = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);

        Customer steve = new Customer("Steve Jobs", "1984");

        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        System.out.println("Sales figure is: " + bookshop.getBalance());
        System.out.println("Inventory is: " + bookshop.getInventory());
        System.out.println();

        steve.purchasesBook(gatesBio, bookshop);
        System.out.println("Sales figure after sale: " + bookshop.getBalance());
        System.out.println("Inventory after sale: " + bookshop.getInventory());

        System.out.println();
        System.out.println(steve.toString());
    }
    
}
