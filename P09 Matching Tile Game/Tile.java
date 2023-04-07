//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Game, Tile Class
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
 * This class models a Tile of a specific color.
 *
 * @author mouna
 * @author Cinthya Nguyen
 */
public class Tile {
  private Color color; // color of this Tile

  /**
   * Creates a Tile with a specific color.
   * 
   * @param color color to be assigned to this tile
   */
  public Tile(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of this tile.
   * 
   * @return the color of this tile
   */
  public Color getColor() {
    return color;
  }


  /**
   * Returns a string representation of this tile.
   * 
   * @return the color of this tile as a string
   */
  @Override
  public String toString() {
    return color.name();
  }


  /**
   * Checks whether this tile equals to the other object provided as input.
   * 
   * @return true if other is a Tile and has the same color as this tile, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Tile && ((Tile) other).getColor() == this.getColor()) {
      return true;
    }

    return false;
  }
}
