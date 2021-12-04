package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer {

    private String name;                // name of customer
    private ArrayList<Book> ownedBooks; // ArrayList of owned books
    private BigDecimal currentBalance;         // amount of customer's balance

    // constructor of class Customer
    public Customer(String newName, String balanceAsString) {
        this.name = newName;
        this.ownedBooks = new ArrayList<Book>();
        this.currentBalance = new BigDecimal(balanceAsString);
    }

    // returns customer's name 
    public String getName() {
        return name;
    }

    // returns Arraylist of customer's owned books
    public ArrayList<Book> getBooks() {
        return ownedBooks;
    }

    // returns customer's balance
    public BigDecimal getBalance() {
        return currentBalance;
    }

    @Override
    public String toString() {
        return ("customer name: " + this.getName() + "\n" + 
                "customer's books: " + this.getBooks() + "\n" + 
                "customer's balance: " + this.getBalance());
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Customer bill = new Customer("Bill Gates", "1000.00");
        System.out.println(bill.toString());
    }
    
}
