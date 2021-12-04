package com.whoisdavidvu.bookshop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    // tests if a purchase fails because of insufficient funds
    @Test
    public void purchase_throwsError_ifNotEnoughFunds() {
        assertThrows(Error.class, ()->{
            Customer bill = new Customer("Bill Gates", "5");
            Shop bookshop = new Shop("Thalia", "1337");
            Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
            bookshop.addBook(narnia);
            bill.purchasesBook(narnia, bookshop);
        });
    }

    // tests if a purchase is successful by checking updated balance 
    @Test
    public void purchase_successful_withUpdatedFunds() {
        Customer bill = new Customer("Bill Gates", "2000");
        BigDecimal oldBalance = bill.getBalance();
        Shop bookshop = new Shop("Thalia", "1337");
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        bookshop.addBook(narnia);
        bill.purchasesBook(narnia, bookshop);
        Assertions.assertEquals(oldBalance.subtract(narnia.getValue()), bill.getBalance());
    }

    // tests if a purchase is successful by checking existance of new book
    @Test
    public void purchase_returnsTrue_ifNewBookExists() {
        Customer bill = new Customer("Bill Gates", "2000");
        Shop bookshop = new Shop("Thalia", "1337");
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        bookshop.addBook(narnia);
        bill.purchasesBook(narnia, bookshop);
        Assertions.assertTrue(bill.getBooks().contains(narnia));
    }

    // tests if the customer name is empty/invalid
    @Test
    public void creatingCustomer_throwsIAEX_ifCustomerNameInvalid() {
        assertThrows(IllegalArgumentException.class, ()->{
            Customer bill = new Customer("", "2000");
        });
    }

    // tests if the customer's balance is negative
    @Test
    public void creatingCustomer_throwsIAEX_ifCustomerBalanceNegative() {
        assertThrows(IllegalArgumentException.class, ()->{
            Customer bill = new Customer("Bill Gates", "-2000");
        });
    }


}
