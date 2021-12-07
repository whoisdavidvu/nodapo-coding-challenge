# nodapo-coding-challenge
10-day coding challenge given by nodapo.

## Tasks
Create a bookshop with following functions:
- A customer should be able to buy and pay for a specific book which then gets removed from the shop's book list / library and added to the customer's list of owned books.
- A customer should be able to filter out books so that the program only shows books with a certain genre. 
- The shop should be able to delete duplicate books from the library.
- A customer should be able to find out if two book shops have the same books offered and the program should show a view so that the customer can compare the output.
- The shop should only be able to add books if the ISBN-13 of the book is valid. If the ISBN is not valid, the shop should not add the book into the library / list.

## Usage
To run the program for testing its functionality:
- Make sure Java and Maven are installed on your System.
- Clone this repository using ```git clone```.
- Navigate into the directory ```bookshop``` and build the files by running ```mvn compile```
- When compile finished, run ```java -cp target/classes com.whoisdavidvu.bookshop.Main``` to run the main class.

Comment out the certain lines of code in the ```Main.java``` file to try out the functions according to the required tasks above.

## Tests
Creation of java tests using Maven for Java on VSCode.
To compile and run tests using Maven, simply run ```mvn test``` in the ```bookshop``` directory.
