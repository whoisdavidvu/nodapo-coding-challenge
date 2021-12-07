package com.whoisdavidvu.bookshop;

public class Main {
    public static void main (String[] args) {
        Shop bookshop = new Shop("Thalia", "1337");

        // all ISBN valid for task 1-4
        String validISBN = "978-3608963762";
        Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, validISBN);
        Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, validISBN);
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, validISBN);
        Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, validISBN);
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, validISBN);
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, validISBN);
        Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, validISBN);

        // uncomment For 5. task with invalid ISBNs mixed in
        /* Book narnia = new Book("Narnia", "19.99", 365, Genres.Fantasy, "978-3608963762");
        Book gatesBio = new Book("Bill Gates' Biography", "69.99", 512, Genres.Biography, "978-3442267747");
        Book gatesBioDuplicate = new Book("Bill Gates' Biography", "79.99", 512, Genres.Biography, "978-3442267747");
        Book jobsBio = new Book("Steve Jobs' Biography", "79.99", 1024, Genres.Biography, "978-758245159");
        Book mobydick = new Book("Moby-Dick", "29.99", 599, Genres.Adventure, "978-3841335180");
        Book odyssey = new Book("Odyssey", "19.99", 333, Genres.Adventure, "978-3442267819");
        Book lotr = new Book("LOTR", "99.99", 1337, Genres.Fantasy, "978-0007136582"); */

        bookshop.addBook(narnia);
        bookshop.addBook(gatesBio);
        bookshop.addBook(gatesBioDuplicate);
        bookshop.addBook(jobsBio);
        bookshop.addBook(jobsBio);
        bookshop.addBook(mobydick);
        bookshop.addBook(odyssey);
        bookshop.addBook(lotr);

        Shop secondshop = new Shop("Mayersche", "1024");
        secondshop.addBook(odyssey);
        secondshop.addBook(new Book("Bill Gates' Biography", "39.99", 512, Genres.Biography, validISBN));
        secondshop.addBook(new Book("Steve Jobs' Biography", "29.99", 1024, Genres.Biography, validISBN));
        secondshop.addBook(lotr);

        // 1. customer purchases book
        // before purchase 
        /* System.out.println("Sales figure is: " + bookshop.getBalance());
        System.out.println("Inventory is: " + bookshop.getInventory());
        System.out.println();

        // after purchase
        Customer steve = new Customer("Steve Jobs", "1984");
        steve.purchasesBook(gatesBio, bookshop);
        System.out.println("Sales figure after sale: " + bookshop.getBalance());
        System.out.println("Inventory after sale: " + bookshop.getInventory());

        // updated customer data
        System.out.println();
        System.out.println(steve.toString()); */

        // 2. finds adventure books
        /* System.out.println(bookshop.filterBookByGenre("adventure")); */

        // 3. finds duplicate books and deletes them
        /* System.out.print("Before: ");
        System.out.println(bookshop.getInventory());
        bookshop.findDeleteDuplicates();
        System.out.print("After: ");
        System.out.println(bookshop.getInventory()); */

        // 4. output 2 lists to compare the identical books between two shops
        // bookshop.printMatchingList(secondshop);

        // 5. addBook should not add books with invalid ISBN
        // System.out.println(bookshop.getInventory());
    }
}
