//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, Fish Class
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

import java.io.File;

/**
 * This program creates fish in tank and allows them to swim.
 * 
 * @author Cinthya Nguyen
 */
public class Fish extends TankObject {

  private int speed;
  private boolean isSwimming;

  /**
   * Creates a new Fish that is drawn to random x and y-position and swims at the given speed.
   * 
   * @param speed             scalar int that represents how fast the Fish swims
   * @param fishImageFileName image to be used for this Fish object
   * @throws IllegalArgumentException if speed is negative
   */
  public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {

    super((float) tank.randGen.nextInt(tank.width), (float) tank.randGen.nextInt(tank.height),
        fishImageFileName);

    if (speed <= 0) {
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    }

    this.speed = speed;
  }

  /**
   * Creates a new orange Fish drawn to a random (x,y) that swims at speed 5.
   */
  public Fish() {
    this(5, "images" + File.separator + "orange.png");
  }

  /**
   * Sets the position of this fish to follow the mouse moves if it is dragging, calls its swim()
   * method if it is swimming, and draws it to the display window. Overrides base implementation but
   * also calls super.draw().
   * 
   * @see TankObject#draw()
   */
  @Override
  public void draw() {

    super.draw();

    if (isSwimming == true) {
      swim();
    }
  }

  /**
   * Checks whether this fish is swimming.
   * 
   * @return true if this fish is swimming, false otherwise
   */
  public boolean isSwimming() {
    return isSwimming;
  }

  /**
   * Stops dragging the fish and starts swimming this fish.
   */
  public void startSwimming() {
    stopDragging();
    isSwimming = true;
  }

  /**
   * Stops swimming this fish.
   */
  public void stopSwimming() {
    isSwimming = false;
  }

  /**
   * Gets the speed of this fish.
   * 
   * @return speed of this fish
   */
  public int speed() {
    return speed;
  }

  /**
   * Moves horizontally the fish one speed step from left to right.
   */
  public void swim() {
    this.setX(this.getX() + speed);
    this.setX(this.getX() % tank.width);
  }

}
