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
            throw new IllegalArgumentException("Name is invalid!");
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



    // main method for testing basic functionality
    public static void main (String[] args) {
        Shop bookshop = new Shop("Thalia", "1337");
        System.out.println("First shop's name is: " + bookshop.getShopName());
        System.out.println("First shop's sales figure is: " + bookshop.getBalance());
        System.out.println("First shop's inventory is: " + bookshop.getInventory());
    }
    
}
