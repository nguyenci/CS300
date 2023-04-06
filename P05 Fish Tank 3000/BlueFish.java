//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name: N/A
//Partner Email: N/A
//Partner Lecturer's Name: N/A
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons: N/A
//Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This program creates a blue fish that swims right to left.
 * 
 * @author Cinthya Nguyen
 */
public class BlueFish extends Fish {

  /**
   * Creates a new BlueFish at a random location that swims at speed 2.
   */
  public BlueFish() {
    super(2, "images" + File.separator + "blue.png");
  }

  /**
   * Moves this blue fish from right to left of display screen. Overrides base implementation.
   */
  @Override
  public void swim() {
    this.setX(this.getX() - this.speed());
    this.setX((tank.width + this.getX()) % tank.width); // once this fish reaches left side of the
                                                        // screen, move it back to the right side

  }

}
