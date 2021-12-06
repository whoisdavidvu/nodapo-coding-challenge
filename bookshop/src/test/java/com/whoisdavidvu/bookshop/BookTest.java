package com.whoisdavidvu.bookshop;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    // tests if the book has empty title
    @Test
    public void creatingBook_throwsIAEX_ifTitleEmpty() {
        assertThrows(IllegalArgumentException.class, ()->{
            Book narnia = new Book("", "19.99", 365, Genres.Fantasy, "978-3608963762");
        });
    }

    // tests if the book price is negative
    @Test
    public void creatingBook_throwsIAEX_ifPriceNegative() {
        assertThrows(IllegalArgumentException.class, ()->{
            Book narnia = new Book("Narnia", "-19.99", 365, Genres.Fantasy, "978-3608963762");
        });
    }

    // tests if the book price has too many digits after decimal point
    @Test
    public void creatingBook_throwsIAEX_ifPriceHasTooManyDigits() {
        assertThrows(IllegalArgumentException.class, ()->{
            Book narnia = new Book("Narnia", "19.999", 365, Genres.Fantasy, "978-3608963762");
        });
    }

    // tests if the book has 0 pages or a negative page count
    @Test
    public void creatingBook_throwsIAEX_ifPageCountInvalid() {
        assertThrows(IllegalArgumentException.class, ()->{
            Book narnia = new Book("Narnia", "19.99", 0, Genres.Fantasy, "978-3608963762");
        });
        assertThrows(IllegalArgumentException.class, ()->{
            Book narnia = new Book("Narnia", "19.99", -1, Genres.Fantasy, "978-3608963762");
        });
    }

    // returns trus if new book has valid ISBN
    @Test
    public void creatingBook_returnsTrue_ifISBNValid() {
        String validISBN = "978-3608963762";
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, validISBN);
        Assertions.assertTrue(narnia.checkISBN13());    
    }

    // returns false if new book has invalid ISBN
    @Test
    public void creatingBook_returnsFalse_ifISBNInvalid() {
        String invalidISBN = "978-3442267747";
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, invalidISBN);
        Assertions.assertFalse(narnia.checkISBN13());
    }
    
}