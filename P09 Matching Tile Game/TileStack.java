import java.util.EmptyStackException;
import java.util.Iterator;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Game, TileStack Class
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
 * This class creates an iterable stack of Tiles and implements StackADT and Iterable interfaces.
 * 
 * @author Cinthya Nguyen
 */
public class TileStack implements StackADT<Tile>, Iterable<Tile> {

  private Node top;
  private int size;

  /**
   * Creates an empty stack.
   */
  public TileStack() {
  }

  /**
   * 
   */
  @Override
  public Iterator<Tile> iterator() {
    TileListIterator itr = new TileListIterator(top);
    return itr;
  }

  /**
   * Pushes the provided tile at top of this stack
   */
  @Override
  public void push(Tile element) {
    Node tile = new Node(element);

    if (isEmpty()) {
      top = tile;
      size++;
    } else {
      tile.setNext(top);
      top = tile;
      size++;
    }
  }

  /**
   * Removes and returns the tile at the top of this stack.
   */
  @Override
  public Tile pop() throws EmptyStackException {

    if (isEmpty()) {
      throw new EmptyStackException();
    }

    Tile temp = top.getTile();
    top = top.getNext();
    size--;
    return temp;
  }

  /**
   * Returns the tile at the top of this stack.
   */
  @Override
  public Tile peek() throws EmptyStackException {

    if (isEmpty()) {
      throw new EmptyStackException();
    }

    return top.getTile();
  }

  /**
   * Check whether this stack is empty.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this stack.
   */
  @Override
  public int size() {
    return size;
  }

}
