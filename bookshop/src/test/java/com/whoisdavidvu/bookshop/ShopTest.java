package com.whoisdavidvu.bookshop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopTest {
    /* Shop bookshop = new Shop("Thalia", "1337");
    Shop secondshop = new Shop("Mayersche", "1024"); */

    String validISBN = "978-3608963762";
    String invalidISBN = "978-3442267747";

    Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, validISBN);
    Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, validISBN);
    Book gatesBioDuplicate = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, validISBN);
    Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, validISBN);
    Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, validISBN);
    Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, validISBN);
    Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, validISBN);

    Book narniaInv = new Book("Narnia", "19.99", 365, Genres.Fantasy, invalidISBN);
    Book gatesBioInv = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, invalidISBN);
    Book gatesBioDuplicateInv = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, invalidISBN);
    Book jobsBioInv = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, invalidISBN);
    Book mobydickInv = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, invalidISBN);
    Book odysseyInv = new Book("Odyssey", "19.99", 333, Genres.Adventure, invalidISBN);
    Book lotrInv = new Book("LOTR", "99.99", 1337, Genres.Fantasy, invalidISBN);

    Customer bill = new Customer("Bill Gates", "2000");

    // tests if money is added to cash when book is sold
    @Test
    public void transaction_successful_afterMoneyReceived() {
        Shop bookshop = new Shop("Thalia", "1337");
        BigDecimal oldBalance = bookshop.getBalance();
        bookshop.addBook(narnia);
        bill.purchasesBook(narnia, bookshop);
        Assertions.assertEquals(oldBalance.add(narnia.getValue()), bookshop.getBalance());
    }

    // tests if the purchased book is not in inventory anymore
    // correct this test if multiple numbers of one book is available
    @Test
    public void transaction_returnsFalse_ifSoldBookRemoved() {
        Shop bookshop = new Shop("Thalia", "1337");
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
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        List<Book> filteredList = bookshop.filterBookByGenre("adventure");
        
        // function should only find Moby Dick and Odyssey
        Assertions.assertTrue(filteredList.contains(mobydick));
        Assertions.assertTrue(filteredList.contains(odyssey));
    }

    // tests if the filter-by-genre function makes sure it doesnt find the incorrect books
    @Test
    public void filter_returnsFalse_ifIncorrectBooksFound() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        List<Book> filteredList = bookshop.filterBookByGenre("adventure");
        
        // function should not find biographies and fantasy books
        Assertions.assertFalse(filteredList.contains(jobsBio));
        Assertions.assertFalse(filteredList.contains(gatesBio));
        Assertions.assertFalse(filteredList.contains(narnia));
    }

    // tests if shop still has duplicates after running function
    @Test
    public void duplicateDelete_returnsTrue_ifShopHasUniqueBooks() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(gatesBioDuplicate);
        bookshop.addBook(jobsBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        // set contains unique elements, set should have smaller size if duplicates exist
        Set<Book> set = new HashSet<Book>(bookshop.getInventory());
        Assertions.assertTrue(set.size() < bookshop.getInventory().size());
        bookshop.findDeleteDuplicates();
        Assertions.assertFalse(set.size() < bookshop.getInventory().size());
        Assertions.assertEquals(set.size(), bookshop.getInventory().size());
    }

    // tests if two shops have matching books
    @Test
    public void matchingBooks_returnsTrue_ifFoundMatchingBooks() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        Shop secondshop = new Shop("Mayersche", "1024");
        secondshop.addBook(odyssey);
        secondshop.addBook(gatesBio);
        secondshop.addBook(jobsBio);
        secondshop.addBook(lotr);
        
        Assertions.assertEquals(bookshop.getThisList(secondshop).size(), bookshop.getOtherList(secondshop).size());

        // checks bookshop's list for 3 books that should match
        Assertions.assertTrue(
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Odyssey")) && 
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Bill Gates' Biography")) &&
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Steve Jobs' Biography"))  
        );

        // checks secondshop's list for the same 3 books that should match
        Assertions.assertTrue(
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Odyssey")) && 
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Bill Gates' Biography")) &&
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Steve Jobs' Biography"))  
        );
    }

    // tests if it finds books that should not overlap
    @Test
    public void matchingBooks_returnsFalse_ifNonMatchingBooksInput() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        Shop secondshop = new Shop("Mayersche", "1024");
        secondshop.addBook(odyssey);
        secondshop.addBook(gatesBio);
        secondshop.addBook(jobsBio);
        secondshop.addBook(lotr);
        
        Assertions.assertEquals(bookshop.getThisList(secondshop).size(), bookshop.getOtherList(secondshop).size());

        // checks bookshop's list for 3 books that should not overlap
        Assertions.assertFalse(
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Narnia")) ||
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("LOTR")) ||
            bookshop.getThisList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Moby-Dick"))
        );

        // checks secondshop's list for 3 books that should not overlap
        Assertions.assertFalse(
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Narnia")) ||
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("LOTR")) ||
            bookshop.getOtherList(secondshop).stream().anyMatch(o -> o.getTitle().equals("Moby-Dick"))
        );
    }

    // tests if it finds books with invalid ISBN-13
    @Test
    public void inventoryCheck_returnsFalse_ifItDoesntFindInvalidBookISBN() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBioInv);
        bookshop.addBook(jobsBioInv);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        Assertions.assertFalse(
            bookshop.getInventory().contains(gatesBioInv) ||
            bookshop.getInventory().contains(jobsBioInv));
    }

    // tests if it finds books with valid ISBN-13
    @Test
    public void inventoryCheck_returnsTrue_ifItFindsValidBookISBN() {
        Shop bookshop = new Shop("Thalia", "1337");
        bookshop.addBook(narnia);
        bookshop.addBook(gatesBioInv);
        bookshop.addBook(jobsBioInv);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);

        Assertions.assertTrue(
            bookshop.getInventory().contains(narnia) &&
            bookshop.getInventory().contains(mobydick) &&
            bookshop.getInventory().contains(odyssey));
    }
}
