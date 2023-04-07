import java.util.Iterator;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Game, TileMatchingGame Class
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
 * This class manages a list of TileStacks.
 * 
 * @author Cinthya Nguyen
 */
public class TileMatchingGame {

  private TileStack[] columns;

  /**
   * Creates a new matching tile game with a given number of columns. A new matching tile game
   * stores an empty TileStack at each of its columns.
   * 
   * @param columnCount number of columns
   * @throws IllegalArgumentException if given columnCount is 0 or negative
   */
  public TileMatchingGame(int columnCount) throws IllegalArgumentException {
    if (columnCount <= 0) {
      throw new IllegalArgumentException();
    }

    columns = new TileStack[columnCount];
    for (int i = 0; i < columnCount; i++) {
      columns[i] = new TileStack();
    }
  }

  /**
   * Gets the number of columns in this tile matching game.
   * 
   * @return number of columns
   */
  public int getColumnsNumber() {
    return columns.length;
  }

  /**
   * Drops a tile at the top of the stack located at the given column index. If tile is dropped on
   * top of a tile of the same color, both tiles will be removed from the stack of tiles at column
   * index.
   * 
   * @param tile  to be dropped
   * @param index column position of the stack of tiles where tile will be dropped
   * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of the columns
   */
  public void dropTile​(Tile tile, int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException();
    }

    if (columns[index].isEmpty()) {
      columns[index].push(tile);
    } else {
      if (columns[index].peek().equals(tile)) {
        columns[index].pop();
      } else {
        columns[index].push(tile);
      }
    }
  }

  /**
   * Removes all the tiles from a column with a given index.
   * 
   * @param index of the column to clear
   * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of the columns
   */
  public void clearColumn​(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException();
    }

    while (!columns[index].isEmpty()) {
      columns[index].pop();
    }

  }

  /**
   * All tile stacks in each column will be cleared.
   */
  public void restartGame() {
    for (int i = 0; i < getColumnsNumber(); i++) {
      clearColumn​(i);
    }

  }

  /**
   * Returns a string representation of the stack of tiles at a given column index, and an empty
   * string "" if the stack is empty.
   * 
   * @param index of a column
   * @return string representation of the column at given index
   * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of the columns
   */
  public String column(int index) throws IndexOutOfBoundsException {

    if (index < 0 || index >= getColumnsNumber()) {
      throw new IndexOutOfBoundsException();
    }

    Iterator<Tile> iterator = columns[index].iterator();

    String column = "";

    while (iterator.hasNext()) {
      column = column + iterator.next() + " ";
    }

    return column.trim();
  }

  /**
   * Returns a string representation of this tile matching game.
   * 
   * @return string representation of this tile matching game
   */
  @Override
  public String toString() {
    String column = "GAME COLUMNS:\n";

    for (int i = 0; i < getColumnsNumber(); i++) {
      column = column + i + ": " + column(i) + "\n";
    }

    return column.trim();
  }

}
