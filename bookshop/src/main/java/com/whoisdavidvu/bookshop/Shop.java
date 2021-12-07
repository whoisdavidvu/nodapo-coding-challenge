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
}
