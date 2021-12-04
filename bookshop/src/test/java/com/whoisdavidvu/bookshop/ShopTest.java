package com.whoisdavidvu.bookshop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopTest {
    
    // tests if money is added to cash when book is sold
    @Test
    public void transaction_successful_afterMoneyReceived() {
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
    public void transaction_returnsFalse_ifSoldBookRemoved() {
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

    // tests if the filter-by-genre function throws error if invalid genre is given
    @Test
    public void filter_throwsError_ifNoBookFound() {
        assertThrows(Error.class, ()->{
            Shop bookshop = new Shop("Thalia", "1337");
            
            Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
            Book gatesBio = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
            Book jobsBio = new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography);
            Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure);
            Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure);
            
            bookshop.addBook(narnia);
            bookshop.addBook(gatesBio);
            bookshop.addBook(jobsBio);
            bookshop.addBook(mobydick);
            bookshop.addBook(odyssey);

            bookshop.filterBookByGenre("Sports");
        });
    }

    // tests if the filter-by-genre function finds the correct books
    @Test
    public void filter_returnsTrue_ifCorrectBooksFound() {
        Shop bookshop = new Shop("Thalia", "1337");
            
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        Book gatesBio = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
        Book jobsBio = new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography);
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure);
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure);
            
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        List<Book> filteredList = bookshop.filterBookByGenre("adventure");
        
        // function should only find Moby Dick and Odyssey
        Assertions.assertTrue(filteredList.contains(mobydick));
        Assertions.assertTrue(filteredList.contains(odyssey));
        Assertions.assertFalse(filteredList.contains(jobsBio));
        Assertions.assertFalse(filteredList.contains(gatesBio));
        Assertions.assertFalse(filteredList.contains(narnia));
    }

    // tests if shop still has duplicates after running function
    @Test
    public void duplicateDelete_returnsTrue_ifShopHasUniqueBooks() {
        Shop bookshop = new Shop("Thalia", "1337");

        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy);
        Book gatesBio = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "49.99", 512, Genres.Biography);
        Book jobsBio = new Book("Steve Jobs' Biography", "49.99", 1024, Genres.Biography);
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure);
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure);
        
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(gatesBioDuplicate);
        bookshop.addBook(jobsBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        // set contains unique elements
        Set<Book> set = new HashSet<Book>(bookshop.getInventory());
        Assertions.assertTrue(set.size() < bookshop.getInventory().size());
        bookshop.findDeleteDuplicates();
        Assertions.assertFalse(set.size() < bookshop.getInventory().size());
        Assertions.assertTrue(set.size() == bookshop.getInventory().size());
    }

    
}
