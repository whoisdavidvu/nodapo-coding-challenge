package com.whoisdavidvu.bookshop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopTest {
    
    // tests if money is added to cash when book is sold
    @Test
    public void moneyReceived_afterTransaction() {
        Customer bill = new Customer("Bill Gates", "2000");
        Shop bookshop = new Shop("Thalia", "1337");
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        BigDecimal oldBalance = bookshop.getBalance();
        bookshop.addBook(narnia);
        bill.purchasesBook(narnia, bookshop);
        Assertions.assertEquals(oldBalance.add(narnia.getValue()), bookshop.getBalance());
    }

    // tests if the purchased book is not in inventory anymore
    // correct this test if multiple numbers of one book is available
    @Test
    public void bookRemoval_returnsFalse_afterSuccessfulTransaction() {
        Customer bill = new Customer("Bill Gates", "2000");
        Shop bookshop = new Shop("Thalia", "1337");
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        bookshop.addBook(narnia);
        bill.purchasesBook(narnia, bookshop);
        Assertions.assertFalse(bookshop.getInventory().contains(narnia));
    }

    // tests if the shop name is empty/invalid
    @Test
    public void creatingShop_throwsIAEX_ifShopNameInvalid() {
        assertThrows(IllegalArgumentException.class, ()->{
            Shop bookshop = new Shop("", "1337");
        });
    }

}
