package com.whoisdavidvu.bookshop;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer {

    private String name;                // name of customer
    private ArrayList<Book> ownedBooks; // ArrayList of owned books
    private BigDecimal currentBalance;  // amount of customer's balance

    // constructor of class Customer
    public Customer(String newName, String balanceAsString) {
        this.name = newName;
        this.ownedBooks = new ArrayList<Book>();
        this.currentBalance = new BigDecimal(balanceAsString);

        if (currentBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Account balance can not be negative!");
        }
        if (this.name.isEmpty() || newName.isEmpty()) {
            throw new IllegalArgumentException("Customer must have a name!");
        }
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

    public void purchasesBook(Book acquiredBook, Shop shop) {
        if (this.currentBalance.compareTo(acquiredBook.getValue()) >= 0) {
            this.currentBalance = this.currentBalance.subtract(acquiredBook.getValue());
        }
        else {
            throw new Error("Not enough funds to purchase item!");
        }
        shop.soldBook(acquiredBook);
        this.ownedBooks.add(acquiredBook);
    }

    @Override
    public String toString() {
        return ("customer name: " + this.getName() + "\n" + 
                "customer's books: " + this.getBooks() + "\n" + 
                "customer's balance: " + this.getBalance());
    }




    // main method to test basic functionality
    public static void main (String[] args) {
        Customer bill = new Customer("Bill Gates", "1337");
        System.out.println(bill.toString());
    }
    
}
