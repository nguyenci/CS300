import java.util.ArrayList;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf, BinaryBookshelfTester Class
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
 * Tester class for BinaryBookshelf.
 * 
 * @author Cinthya Nguyen
 *
 */
public class BinaryBookshelfTester {

  /**
   * Tester method for the TreeNode class.
   * 
   * @return true
   */
  public static boolean testTreeNode() {
    try {

      // Scenario 1: single TreeNode with no children
      TreeNode<Integer> test = new TreeNode<>(5);

      if (test.getData() != 5 || test.getLeft() != null || test.getRight() != null) {
        System.out.println("Constructor incorrectly initialized fields.");
        return false;
      }

      // Test: toString
      if (!test.toString().equals("5")) {
        System.out.println("toString returned incorrect string.");
        return false;
      }

      // Scenario 2: a simple collection of TreeNodes
      TreeNode<Integer> test2 = new TreeNode<>(7);

      test.setLeft​(test2);

      if (test.getLeft() != test2 || test.getRight() != null) {
        System.out.println("Did not property set left or right tree nodes.");
        return false;
      }

      test.setLeft​(null);

      if (test.getLeft() != null) {
        System.out.println("Did not property set left as null.");
        return false;
      }

      // Scenario 3: multi-arg constructor
      test = new TreeNode<>(1);
      test2 = new TreeNode<>(2);
      TreeNode<Integer> test3 = new TreeNode<>(3, test, test2);

      if (test3.getLeft() != test || test3.getRight() != test2) {
        System.out.println("Multi-arg constructor incorrectly initialized fields.");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method that tests if an empty bookshelf was created correctly.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testEmptyTree() {

    try {

      Attribute[] list;
      BinaryBookshelf shelf;

      // Scenario 1: invalid constructor inputs
      try { // empty list
        list = new Attribute[0];
        shelf = new BinaryBookshelf(list);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        // expected
      }

      try { // length > 4
        list = new Attribute[] {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.TITLE,
            Attribute.ID, Attribute.AUTHOR};
        shelf = new BinaryBookshelf(list);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        // expected
      }

      try { // duplicate attribute
        list = new Attribute[] {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.AUTHOR};
        shelf = new BinaryBookshelf(list);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        // expected
      }

      try { // author not in 0th index
        list =
            new Attribute[] {Attribute.PAGECOUNT, Attribute.TITLE, Attribute.ID, Attribute.AUTHOR};
        shelf = new BinaryBookshelf(list);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        // expected behavior
      }

      // Scenario 2: valid input
      list = new Attribute[] {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
      shelf = new BinaryBookshelf(list);

      // Test: verify fields of an empty shelf
      if (shelf.size() != 0 || !shelf.isEmpty() || !shelf.toString().equals("")
          || shelf.getRoot() != null) {
        System.out.println("Constructor incorrectly initialized empty shelf.");
        return false;
      }

      // Test: verifies getAttributeOrder()
      if (!shelf.getAttributeOrder().equals("1:AUTHOR 2:PAGECOUNT 3:ID 4:TITLE")) {
        System.out.println("Attributes in incorrect order.");
        return false;
      }

      // Test: verifies contains()
      Book book = new Book("A", 1, "Last", "First");
      if (shelf.contains​(book)) {
        System.out.println("contains() should have returned false.");
        return false;
      }

      if (shelf.getBooksByAuthor​("Last, First").size() != 0) {
        System.out.println("Did not return empty ArrayList.");
        return false;
      }

    } catch (Exception e1) {
      System.out.println("Unexpected exception thrown.");
      e1.printStackTrace();
      return false;
    }

    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method for BinaryBookshelf.insertBook().
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testInsertBook() {

    try {

      // Scenario 1: inserting into an empty tree
      Attribute[] list = {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
      BinaryBookshelf shelf = new BinaryBookshelf(list);

      // Test: add single book
      Book one = new Book("A", 1, "B", "B");
      shelf.insertBook​(one);

      if (shelf.size() != 1 || shelf.getRoot().getData() != one) {
        System.out.println("Incorrect size or root.");
        return false;
      }

      // Test: book less than root node
      Book two = new Book("A", 1, "A", "A");
      shelf.insertBook​(two);

      if (shelf.size() != 2 || shelf.getRoot().getLeft().getData() != two) {
        System.out.println("Shelf size was not incremented and book was not added as left child.");
        return false;
      }

      // Scenario 3: inserting a value with a shared author attribute
      Book three = new Book("A", 2, "B", "B");
      shelf.insertBook​(three);

      if (shelf.size() != 3 || shelf.getRoot().getRight().getData() != three) {
        System.out.println("Shelf size was not incremented and book was not added as right child.");
        return false;
      }

      // Scenario 4: inserting a duplicate value
      try {
        shelf.insertBook​(three);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      // Scenario 5: toString()
      if (!shelf.toString().equals("1: \"A\", A, A (1)\n0: \"A\", B, B (1)\n2: \"A\", B, B (2)")) {
        System.out.println("toString() method incorrect.");
        return false;
      }

      // Scenario 6: clear shelf
      shelf.clear();

      try {

        if (shelf.size() != 0 || shelf.getRoot() != null || shelf.getRoot().getLeft() == null
            || shelf.getRoot().getRight() != null) {
          System.out.println("Shelf not properly cleared.");
          return false;
        }
      } catch (NullPointerException e) {
        // expected
      }


    } catch (Exception e1) {
      System.out.println("Unexpected exception thrown.");
      e1.printStackTrace();
      return false;
    }

    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method for BinaryBookshelf.contains().
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testContains() {

    try {

      Attribute[] list = {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
      BinaryBookshelf shelf = new BinaryBookshelf(list);

      Book one = new Book("A", 1, "D", "D");
      Book two = new Book("A", 1, "A", "A");
      Book three = new Book("A", 1, "B", "B");
      Book four = new Book("A", 1, "C", "C");
      Book five = new Book("A", 1, "E", "E");
      Book six = new Book("A", 1, "Doesn't", "Exist");

      // Scenario 1: non-recursive case
      shelf.insertBook​(one);

      if (!shelf.contains​(one) || shelf.contains​(two)) {
        System.out.println("contains() returned incorrect value.");
        return false;
      }

      // Scenario 2: recursive case
      shelf.getRoot().setLeft​(new TreeNode<>(three));
      shelf.getRoot().setRight​(new TreeNode<>(five));
      shelf.getRoot().getLeft().setLeft​(new TreeNode<>(two));
      shelf.getRoot().getLeft().setRight​(new TreeNode<>(four));

      // Test: root node
      if (!shelf.contains​(one)) {
        System.out.println("Shelf does not contain root.");
        return false;
      }

      // Test: leaf nodes
      if (!shelf.contains​(four) || !shelf.contains​(two) || !shelf.contains​(five)) {
        System.out.println("Shelf does not contain leaf nodes.");
        return false;
      }

      // Test: internal node
      if (!shelf.contains​(three)) {
        System.out.println("Shelf does not contain internal node.");
        return false;
      }

      // Test: node that should not exist
      if (shelf.contains​(six)) {
        System.out.println("Shelf should not contain this book.");
        return false;
      }
      
      shelf = new BinaryBookshelf(list);
      
      shelf.insertBook​(one);
      shelf.insertBook​(three);
      shelf.insertBook​(five);
      shelf.insertBook​(two);
      shelf.insertBook​(four);
      
      System.out.println(shelf.contains​(three));      

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    Book.resetGenerator();

    return true;
  }

  /**
   * Tester method for BinaryBookshelf.getBooksByAuthor().
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testGetBooksByAuthor() {

    try {

      // Scenario 1: non-recursive case
      Attribute[] list = {Attribute.AUTHOR, Attribute.PAGECOUNT, Attribute.ID, Attribute.TITLE};
      BinaryBookshelf shelf = new BinaryBookshelf(list);

      Book one = new Book("A", 1, "Last", "First");
      Book two = new Book("A", 1, "No", "Author");

      shelf.insertBook​(one);

      if (shelf.getBooksByAuthor​("Last, First").get(0) != one
          || shelf.getBooksByAuthor​("Last, First").size() != 1) {
        System.out.println("Did not get correct author or properly add book to ArrayList.");
        return false;
      }

      if (shelf.getBooksByAuthor​("No, Author").size() != 0) {
        System.out.println("No book should be in ArrayList.");
        return false;
      }

      Book.resetGenerator();

      // Scenario 2: recursive case

      BinaryBookshelf shelf2 = new BinaryBookshelf(list);

      one = new Book("A", 1, "D", "D");
      two = new Book("A", 1, "B", "B");
      Book three = new Book("A", 2, "B", "B");
      Book four = new Book("A", 1, "A", "A");
      Book five = new Book("A", 2, "E", "E");
      Book six = new Book("A", 1, "E", "E");

      shelf2.insertBook​(one);

      // construct tree
      shelf2.getRoot().setLeft​(new TreeNode<>(two));
      shelf2.getRoot().setRight​(new TreeNode<>(five));
      shelf2.getRoot().getLeft().setLeft​(new TreeNode<>(four));
      shelf2.getRoot().getLeft().setRight​(new TreeNode<>(three));
      shelf2.getRoot().getRight().setLeft​(new TreeNode<>(six));

      // Test: one book
      ArrayList<Book> bookList = shelf2.getBooksByAuthor​("A, A");

      if (bookList.size() != 1 || !bookList.get(0).getAuthor().equals(four.getAuthor())) {
        System.out.println("Incorrect list.");
        return false;
      }

      // Test: two books (root's left subtree)
      bookList = shelf2.getBooksByAuthor​("B, B");

      if (bookList.size() != 2 || !bookList.get(0).getAuthor().equals(two.getAuthor())
          || !bookList.get(1).getAuthor().equals(three.getAuthor())) {
        System.out.println("List does not contain correct books.");
        return false;
      }

      // Test: two books (root's right subtree)
      bookList = shelf2.getBooksByAuthor​("E, E");

      if (bookList.size() != 2 || !bookList.get(0).getAuthor().equals(five.getAuthor())
          || !bookList.get(1).getAuthor().equals(six.getAuthor())) {
        System.out.println("List does not contain correct books.");
        return false;
      }

      // Test: book not in BST
      bookList = shelf2.getBooksByAuthor​("F, F");

      if (bookList.size() != 0) {
        System.out.println("List should not contain this book.");
        return false;
      }
      
      shelf2 = new BinaryBookshelf(list);
      
      shelf2.insertBook​(one);
      shelf2.insertBook​(two);
      shelf2.insertBook​(five);
      shelf2.insertBook​(four);
      shelf2.insertBook​(three);
      shelf2.insertBook​(six);
      
      Book seven = new Book("A", 3, "E", "E");
      Book eight = new Book("A", 3, "B", "B");
      
      shelf2.insertBook​(eight);

      System.out.println(bookList = shelf2.getBooksByAuthor​("B, B"));
      
    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Calls tester methods.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    testTreeNode();
    testEmptyTree();
    testInsertBook();
    testContains();
    testGetBooksByAuthor();
  }

}
