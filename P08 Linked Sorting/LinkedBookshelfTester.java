//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 LinkedBookshelf Tester
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: N/A
// Partner Email: N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Tester class for LinkedNode and LinkedBookShelf.
 * 
 * @author Cinthya Nguyen
 */
public class LinkedBookshelfTester {

  /**
   * Tester method for LinkedNode.
   * 
   * @return true if class works properly, false otherwise
   */
  public static boolean testLinkedNode() {

    // Test 1: constructor
    LinkedNode<Integer> test = new LinkedNode<>(5);

    if (test.getData() != 5 || test.getNext() != null) {
      System.out.println("Constructor did not initialize fields correctly.");
      return false;
    }

    // Test 2: accessor
    if (test.getData() != 5 || test.getNext() != null) {
      System.out.println("Accessors returned incorrect values.");
      return false;
    }

    // Test 3: mutator
    LinkedNode<Integer> test2 = new LinkedNode<>(8);

    test.setNext(test2);

    if (test.getNext() != test2) {
      System.out.println("Did not property set next value.");
      return false;
    }

    return true;
  }

  /**
   * Tester method for clear().
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testClear() {

    Book one = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book two = new Book("FEED", 608, "Grant", "Mira");
    Book three = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book four = new Book("2001", 296, "Clarke", "Arthur");
    LinkedBookshelf shelf = new LinkedBookshelf();

    // Test one: regular clear
    try {
      shelf.appendBook(one);
      shelf.appendBook(two);
      shelf.appendBook(three);
      shelf.clear();

      if (shelf.size() != 0 || shelf.getFirst() != null || shelf.getLast() != null) {
        System.out.println("Did not properly clear the shelf.");
        return false;
      }

    } catch (NullPointerException e) {
      // expected behavior
    }
    
    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method for appendBook().
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testAddBooks() {

    Book one = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book two = new Book("FEED", 608, "Grant", "Mira");
    Book three = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book four = new Book("2001", 296, "Clarke", "Arthur");
    LinkedBookshelf shelf = new LinkedBookshelf();
    
    // Test 1: appending one book
    shelf.appendBook(one);
    
    if (shelf.size() != 1 || shelf.getFirst() != one || shelf.getLast() != one) {
      System.out.println("Did not properly append book.");
      return false;
    }

    // Test 2: appending multiple books
    shelf = new LinkedBookshelf();
    
    shelf.appendBook(one);
    shelf.appendBook(two);
    shelf.appendBook(three);

    if (shelf.size() != 3 || shelf.getFirst() != one || shelf.getLast() != three) {
      System.out.println("Did not properly append books.");
      return false;
    }
    
    // Test 3: inserting book at the front of list
    shelf = new LinkedBookshelf();
    
    shelf.appendBook(four);
    shelf.insertBook(one);
    
    if (shelf.size() != 2 || shelf.getFirst() != one || shelf.getLast() != four) {
      System.out.println("Did not properly insert book.");
      return false;
    }
    
    // Test 4: inserting book at the end of list
    shelf = new LinkedBookshelf(); 
    
    shelf.appendBook(two);
    shelf.insertBook(four);
    
    if (shelf.size() != 2 || shelf.getFirst() != two || shelf.getLast() != four) {
      System.out.println("Did not properly insert book.");
      return false;
    }

    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method for sort().
   * 
   * @return true if tests pass, false otherwise
   */
  public static boolean testSortBooks() {

    Book one = new Book("Good Omens", 288, "Gaiman", "Neil");
    Book two = new Book("FEED", 608, "Grant", "Mira");
    Book three = new Book("Snow Crash", 468, "Stephenson", "Neal");
    Book four = new Book("2001", 296, "Clarke", "Arthur");
    LinkedBookshelf shelf = new LinkedBookshelf();

    shelf.appendBook(one);
    shelf.appendBook(two);
    shelf.appendBook(three);
    shelf.appendBook(four);

    LinkedBookshelf.sort(shelf, Attribute.PAGECOUNT);

    // Test one: sort by page count
    if (shelf.getFirst() != one || shelf.get(1) != four || shelf.get(2) != three
        || shelf.getLast() != two) {
      System.out.println("Did not properly sort books.");
      return false;
    }
    
    Book.resetGenerator();
    
    // Test two: sort by author
    one = new Book("Good Omens", 288, "B", "B");
    two = new Book("FEED", 608, "A", "A");
    three = new Book("Snow Crash", 468, "D", "D");
    four = new Book("2001", 296, "C", "C");
    
    shelf = new LinkedBookshelf();
    
    shelf.appendBook(one);
    shelf.appendBook(two);
    shelf.appendBook(three);
    shelf.appendBook(four);
    
    LinkedBookshelf.sort(shelf, Attribute.AUTHOR);
    
    if (shelf.getFirst() != two || shelf.getLast() != three) {
      System.out.println("Did not properly sort books.");
      return false;
    }
     
    return true;
  }

  /**
   * Calls every test method.
   * 
   * @return true if all tests passed, false otherwise
   */
  public static boolean runAllTests() {
    if (!testLinkedNode() || !testClear() || !testAddBooks() || !testSortBooks()) {
      return false;
    }

    return true;
  }

  /**
   * Runs all tests.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    runAllTests();
  }

}
