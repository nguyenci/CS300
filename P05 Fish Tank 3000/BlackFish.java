//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, BlackFish Class
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
// N/A Write-up states that pair programming is allowed for this assignment.
// N/A We have both read and understand the course Pair Programming Policy.
// N/A We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: N/A
// Online Sources: N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * This program creates a black fish that swims between two tank objects.
 * 
 * @author Cinthya Nguyen
 */
public class BlackFish extends Fish {
  
  private TankObject source;
  private TankObject destination;

  /**
   * Creates a Black Fish that swims between two destinations at 2 speed.
   * 
   * @param source where this fish to after it reaches its initial destination
   * @param destination where this fish swims to
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images" + File.separator + "black.png");
    this.source = source;
    this.destination = destination;
  }

  /**
   * Makes one speed move towards destination.
   */
  public void moveTowardsDestination() { 
    float dx = destination.getX() - this.getX();
    float dy = destination.getY() - this.getY();
    int d = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    float xMove = this.getX() + (this.speed() * dx)/d; 
    float yMove = this.getY() + (this.speed() * dy)/d;
    
    this.setX(xMove);
    this.setY(yMove);
  }

  /**
   * Returns true if this black fish is over another tank object, and false otherwise.
   * 
   * @param other different tank object to check if tank object is over it.
   * @return true if this fish is over the tank object, if else false 
   */
  public boolean isOver(TankObject other) {
    boolean isOver = true;
    
    float x1 = getX() - image.width/2; // x lower bound of fish image
    float x2 = getX() + image.width/2; // x upper bound of fish image 
    float x3 = other.getX() - other.image.width/2; // x lower bound of decoration image 
    float x4 = other.getX() + other.image.width/2; // x upper bound of decoration image
    
    float y1 = getY() - image.height/2;
    float y2 = getY() + image.height/2;
    float y3 = other.getY() - other.image.height/2;
    float y4 = other.getY() + other.image.height/2;
    
    if (x3 > x2 || x1 > x4 || y3 > y2 || y1 > y4) {
      isOver = false;
    }
    
    return isOver;
  }
  
 /** 
  * Moves the fish towards its destination. Overrides base implementation.
  */
  @Override
  public void swim() {
    moveTowardsDestination();
    
    if (isOver(destination)) { // if BlackFish reaches destination, switch source & destination
      TankObject temp = destination;
      destination = source;
      source = temp;
    }
  }

}
