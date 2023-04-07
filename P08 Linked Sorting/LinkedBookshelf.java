//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 LinkedBookshelf
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
 * This class manages a linked list
 *  
 * @author Cinthya Nguyen
 */
public class LinkedBookshelf {

  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;

  /**
   * Constructor.
   */
  public LinkedBookshelf() {
    sortedBy = Attribute.ID;
  }

  /**
   * Gets size of the list.
   * 
   * @return list size
   */
  public int size() {
    return size;
  }

  /**
   * Checks if list is empty.
   * 
   * @return true if list is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Converts list into a string.
   */
  @Override
  public String toString() {

    LinkedNode<Book> book = front;
    String shelf = sortedBy + "\n";

    for (int i = 0; i < size; i++) {
      shelf = shelf + book + "\n";
      if (book.getNext() != null) {
        book = book.getNext();
      }
    }

    return shelf;
  }

  /**
   * Gets node.
   * 
   * @param index where book is located
   * @return node at specified index
   * @throws IndexOutOfBoundsException if index is negative or greater than size of list
   */
  public LinkedNode<Book> getNode(int index) throws IndexOutOfBoundsException {

    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException();
    }

    LinkedNode<Book> book = front;

    for (int i = 0; i < size; i++) {
      if (i == index) {
        return book;
      }
      book = book.getNext();
    }

    return book;
  }

  /**
   * Gets book at a specific node. 
   * 
   * @param index where book is located
   * @return book at specified index
   * @throws IndexOutOfBoundsException if index is negative or greater than size of list
   */
  public Book get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException();
    }

    LinkedNode<Book> book = front;

    for (int i = 0; i < size; i++) {
      if (i == index) {
        return book.getData();
      }
      book = book.getNext();
    }

    return null;
  }

  /**
   * Gets the first book in the list.
   * 
   * @return first book
   */
  public Book getFirst() {
    return front.getData();
  }

  /**
   * Gets the last book in the list.
   * 
   * @return last book
   */
  public Book getLast() {
    return back.getData();
  }

  /**
   * Clears the list.
   */
  public void clear() {
    front = null;
    back = null;
    size = 0;
  }

  /**
   * Adds book to end of list.
   * 
   * @param toAdd
   */
  public void appendBook(Book toAdd) {
    LinkedNode<Book> book = new LinkedNode<>(toAdd);
    if (isEmpty()) {
      front = book;
      back = book;
      size++;
    } else {
      back.setNext(book);
      back = book;
      size++;
    }
  }

  /**
   * Inserts book.
   * 
   * @param toAdd book to add
   */
  public void insertBook(Book toAdd) {
    LinkedNode<Book> book = new LinkedNode<>(toAdd);

    if (isEmpty()) {
      front = book;
      back = book;
      size++;
    } else if (toAdd.compareTo(getFirst(), sortedBy) < 0) {
      book.setNext(front);
      front = book;
      size++;
    } else if (toAdd.compareTo(getLast(), sortedBy) > 0) {
      back.setNext(book);
      back = book;
      size++;
    } else {
      for (int i = 0; i < size; i++) {
        if (toAdd.compareTo(get(i), sortedBy) > 0 && toAdd.compareTo(get(i + 1), sortedBy) < 0) {

          LinkedNode<Book> temp = getNode(i + 1);
          getNode(i).setNext(book);
          book.setNext(temp);
          size++;
        }
      }

    }
  }

  /**
   * Sorts linked list.
   * 
   * @param b linked list to be sorted
   * @param sortedBy what to sort the list by
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {

    LinkedBookshelf temp = new LinkedBookshelf();
    b.sortedBy = sortedBy;

    for (int i = 1; i < b.size; i++) {
      temp.appendBook(b.get(i));
    }

    b.size -= temp.size;
    b.getNode(0).setNext(null);
    b.back = b.front;

    for (int i = 0; i < temp.size; i++) {
      b.insertBook(temp.get(i));
    }
  }

}
