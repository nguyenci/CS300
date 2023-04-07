import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Game, TileListIterator Class
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
 * This class creates an iterator through a collection of tiles.
 * 
 * @author Cinthya Nguyen
 */
public class TileListIterator implements Iterator<Tile> {

  private Node current;

  /**
   * Creates a new iterator to iterate through a list of tiles starting from its head.
   * 
   * @param head top of list
   */
  public TileListIterator(Node head) {
    current = head;
  }

  /**
   * Checks if there are more tiles in the iteration.
   */
  @Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next tile in the iteration.
   */
  @Override
  public Tile next() throws NoSuchElementException {
    if (current == null) {
      throw new NoSuchElementException("Node does not exist.");
    }
    
    Tile temp = current.getTile();
    current = current.getNext();
    
    return temp;
  }

}
