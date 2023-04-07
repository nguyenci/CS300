//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2021
//
// Author:   Cinthya Nguyen
// Email:    cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * @author Cinthya Nguyen
 *
*/
public class Node {

  private Tile tile; // tile carried by this linked node
  private Node next; // reference to the next node in the chain of linked nodes

  /**
   * Creates a new node with a given tile 
   * @param tile tile to be carried by this node
   */
  public Node(Tile tile) {
    this.tile = tile;
  }
  
  /**
   * Creates a new node with a given tile and a given reference to the next node in the list
   * @param tile tile to be carried by this node
   * @param next reference to the next node
   */
  public Node(Tile tile, Node next) {
    this.tile = tile;
    this.next = next;
  }

  /**
   * Gets the tile carried by this node
   * @return the tile of this node
   */
  public Tile getTile() {
    return tile;
  }

  /**
   * Gets the reference to the next node
   * @return the reference to the next node
   */
  public Node getNext() {
    return next;
  }

  /**
   * Sets the reference to the next node
   * @param next the reference to the next node to set
   */
  public void setNext(Node next) {
    this.next = next;
  }
}