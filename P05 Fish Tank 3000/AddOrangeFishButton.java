//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, AddOrangeFishButton Class
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
 * This program derives from the button class and creates a button that adds a new orange fish to
 * the tank when pressed.
 * 
 * @author nguyenc
 *
 */
public class AddOrangeFishButton extends Button {

  /**
   * Creates new "Add Orange" button at the given position on the display screen.
   * 
   * @param x x-position of this button
   * @param y y-position of this button
   */
  public AddOrangeFishButton(float x, float y) {
    super("Add Orange", x, y);
  }

  /**
   * Overrides the base implementation by adding a new Orange Fish to the display screen if the
   * button is pressed.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      tank.addObject(new Fish());
    }
  }

}
