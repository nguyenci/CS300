import java.util.ArrayList;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf, BinaryBookshelf class
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
 * Binary search tree, where all Books are sorted based on Attributes. Books must be compared first
 * based on their authors, and then on the other Attributes in the order specified.
 * 
 * @author Cinthya Nguyen
 */
public class BinaryBookshelf {

  private TreeNode<Book> root;
  private int size;
  private Attribute[] sortList;

  /**
   * One-arg constructor, initializes an empty BinaryBookshelf.
   * 
   * @param sortList array of Attributes, begins with AUTHOR and is unique
   * @throws IllegalArgumentException if sortList is not a four-element array, unique, or begins
   *                                  with Attribute.AUTHOR
   */
  public BinaryBookshelf(Attribute[] sortList) throws IllegalArgumentException {

    // throws exception if length is not four
    if (sortList.length != 4) {
      throw new IllegalArgumentException("sortList length is not 4.");
    }

    // throws exception if sortList does not start with Author
    if (sortList[0] != Attribute.AUTHOR) {
      throw new IllegalArgumentException("sortList's 0th element is not AUTHOR.");
    }

    // checks if there are any duplicate attributes
    boolean dupe = false;
    for (int i = 0; i < sortList.length; i++) {
      for (int j = i + 1; j < sortList.length; j++) {
        if (sortList[j] == sortList[i]) {
          dupe = true;
        }
      }
    }

    // throws exception if there are duplicate values
    if (dupe) {
      throw new IllegalArgumentException("sortList contains duplicate attributes.");
    }

    this.sortList = sortList;
  }

  /**
   * Get the number of nodes currently in the BST.
   * 
   * Complexity analysis: O(1), because returning a variable (in this case, the size field) is a
   * constant time operation.
   * 
   * @return number of nodes currently in the BST
   */
  public int size() {
    return size;
  }

  /**
   * Determine whether the BST is empty.
   * 
   * Complexity analysis: O(1), because equality operator is a constant time operation.
   * 
   * @return true if the BST is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns a shallow copy of the root node.
   * 
   * @return reference to the current root node
   */
  protected TreeNode<Book> getRoot() {
    return root;
  }

  /**
   * Resets the BST to be empty.
   */
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * Provides a String-formatted list of the attributes in the order in which they are used.
   * 
   * @return String-formatted list of the sorting attributes
   */
  public String getAttributeOrder() {

    String list = "";

    for (int i = 0; i < sortList.length; i++) {
      list += (i + 1) + ":" + sortList[i] + " ";
    }

    return list.trim();
  }

  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes.
   * 
   * @param book Book object to be added to the BST
   */
  public void insertBook​(Book book) throws IllegalArgumentException {

    if (root == null) { // base
      root = new TreeNode<Book>(book);
      size++;
    } else {
      insertBookHelper​(book, root);
    }

  }

  /**
   * Helper method for insertBook(). Adds the given Book to the subtree rooted at current.
   * 
   * @param book    Book object to be added to the BST
   * @param current the root of the current subtree
   */
  protected void insertBookHelper​(Book book, TreeNode<Book> current)
      throws IllegalArgumentException {

    if (compareToHelper​(book, current.getData()) < 0) {

      if (current.getLeft() == null) { // base
        current.setLeft​(new TreeNode<Book>(book));
        size++;
      } else { // recursive
        insertBookHelper​(book, current.getLeft());
      }

    } else if (compareToHelper​(book, current.getData()) > 0) {

      if (current.getRight() == null) { // base
        current.setRight​(new TreeNode<Book>(book));
        size++;
      } else { // recursive
        insertBookHelper​(book, current.getRight());
      }

    } else {

      return;
    }
  }

  /**
   * Searches for the input book in the bookshelf.
   * 
   * Complexity Analysis, where N is the number of nodes:
   * 
   * Average case: O(log N), because for a balanced tree, it gets split in half each time the search
   * traverses down a level.
   * 
   * Worst case: O(N), because if a tree is a general BST (i.e a Linked List), the search must
   * traverse down every node in order to find the specified book.
   * 
   * @param book the book to search for
   * @return true if the book is present in the shelf, false otherwise
   */
  public boolean contains​(Book book) {
    return containsHelper​(book, root);
  }

  /**
   * Helper method for contains(). Searches for the input book in the subtree rooted at current.
   * 
   * @param book    the query book to search for
   * @param current the root of the current subtree
   * @return true if the book is contained in this subtree, false otherwise
   */
  protected boolean containsHelper​(Book book, TreeNode<Book> current) {

    if (current == null) { // base
      return false;
    } else if (current.getData() == book) { // base
      return true;
    } else if (compareToHelper​(book, current.getData()) > 0) { // recursive
      return containsHelper​(book, current.getRight());
    } else if (compareToHelper​(book, current.getData()) < 0) { // recursive
      return containsHelper​(book, current.getLeft());
    }

    return false;
  }

  /**
   * Helper method to compare two Book objects according to the sortList of attributes. Uses both
   * equals() and compareTo() from the Book class.
   * 
   * @param one first book
   * @param two second book
   * @return a negative value if one is less than two, a positive value if one is greater than 2
   *         two, and 0 if they are equal
   */
  protected int compareToHelper​(Book one, Book two) throws IllegalArgumentException {

    for (int i = 0; i < sortList.length; i++) {

      if (one.compareTo(two, sortList[i]) < 0) {
        return one.compareTo(two, sortList[i]);
      }

      if (one.compareTo(two, sortList[i]) > 0) {
        return one.compareTo(two, sortList[i]);
      }

      if (one.equals(two)) { // throw exception if two books equal
        throw new IllegalArgumentException("Book already exists.");
      }

    }

    return 0;
  }

  /**
   * Returns a list of books in the bookshelf written by the given author.
   * 
   * @param authorName the author name to filter on
   * @return list of books by the author
   */
  public ArrayList<Book> getBooksByAuthor​(String authorName) {

    ArrayList<Book> list = new ArrayList<>();

    if (root == null) { // base
      return list;
    } else {
      list = getBooksByAuthorHelper​(authorName, root);
    }

    return list;
  }

  /**
   * Helper method for getBooksByAuthor(). Returns a list of books in the subtree rooted at current
   * which were written by the given author.
   * 
   * @param authorName the author name to filter on
   * @param current    root of the current subtree
   * @return list of books by the author in the current subtree
   */
  protected ArrayList<Book> getBooksByAuthorHelper​(String authorName, TreeNode<Book> current) {

    ArrayList<Book> list = new ArrayList<Book>();

    if (current == null) { // base
      return list;
    }

    if (current.getData().getAuthor().equals(authorName)) { // base
      list.add(current.getData());
    }

    if (current.getLeft() != null) { // recursive
      list.addAll(getBooksByAuthorHelper​(authorName, current.getLeft()));
    }


    if (current.getRight() != null) { // recursive
      list.addAll(getBooksByAuthorHelper​(authorName, current.getRight()));
    }

    return list;
  }

  /**
   * Creates and returns an in-order traversal of the BST, with each Book on a separate line.
   * 
   * Complexity analysis, where N is the number of nodes: O(N), because it traverses every node in
   * order to add every node to the String.
   * 
   * @returns in-order traversal of the BST, with each Book on a separate line
   */
  @Override
  public String toString() {
    String result = "";

    if (root == null) { // base case
      return "";
    } else if (root.getLeft() == null && root.getRight() == null) { // base case
      result += root.getData();
      return result;
    } else {
      result = toStringHelper​(root);
    }

    return result;
  }

  /**
   * Helper method for toString(). Creates and returns the String representation of the subtree
   * rooted at the current node.
   * 
   * @param current root of the current subtree
   * @return String representation of this subtree
   */
  protected String toStringHelper​(TreeNode<Book> current) {

    String result = "";

    if (current.getLeft() != null) {
      result += toStringHelper​(current.getLeft()) + "\n";
    }

    result += current.getData() + "\n";

    if (current.getRight() != null) {
      result += toStringHelper​(current.getRight()) + "\n";
    }

    return result.trim();
  }

}
