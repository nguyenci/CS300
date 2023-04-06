//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Fish Tank 2000; Fish Class
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author nguyenc
 *
 */
public class Fish {

  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private int speed;
  private boolean isDragging;
  private boolean isSwimming;
  private static int oldMouseX;
  private static int oldMouseY;

  // Creates a new fish object located at a specific (x, y) position of the display window
  public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
    Fish.processing = processing; // processing PApplet object that represents the display window
    this.x = x; // x x-position of the image of this fish in the display window
    this.y = y; // y y-position of the image of this fish in the display window
    this.speed = speed; // speed the swimming speed of this fish
    this.image = processing.loadImage(fishImageFileName); // file name of the image of the fish
  }

  // creates new Fish object positioned at center of display window
  public Fish(PApplet processing) {
    Fish.processing = processing; // processing PApplet object that represents the display window
    // sets the image to a PImage whose file name is "orange.png"
    this.image = processing.loadImage("images" + File.separator + "orange.png");
    this.speed = 5; // Sets speed to 5
    // sets the x and y position of the fish to the center of the display window
    this.x = processing.width / 2;
    this.y = processing.height / 2;
    // created fish won’t be dragging nor swimming.
    this.isDragging = false;
    this.isSwimming = false;
  }

  // Returns the image of type PImage of this fish
  public PImage getImage() {
    return image;
  }

  // Returns the x-position of this fish in the display window
  public float getPositionX() {
    return x;
  }

  // Returns the y-position of this fish in the display window
  public float getPositionY() {
    return y;
  }

  // Moves this fish with dx and dy
  public void move(int dx, int dy) {
    x += dx; // adds dx move to the x-position of this fish
    y += dy; // adds dy move to the y-position of this fish
  }

  // Checks whether this fish is being dragged
  public boolean isDragging() {
    return isDragging;
  }

  // Starts dragging this fish
  public void startDragging() {
    oldMouseX = processing.mouseX; // sets oldMouseX to the current x-position of the mouse
    oldMouseY = processing.mouseY; // sets oldMouseY to the current y-position of the mouse
    isDragging = true;

  }

  // Stops dragging this fish
  public void stopDragging() {
    isDragging = false;
  }

  // Draws this fish to the display window.
  // This method sets also the position of this fish to follow the moves of the mouse if it is
  // being dragged
  public void draw() {
    // if this fish is dragging, move it with (dx, dy) to follow the moves of the mouse
    if (isDragging == true) {
      move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }

    if (isSwimming == true) {
      swim();
    }

    // draw this fish to the display window by calling processing.image() method
    processing.image(image, x, y);
  }

  // Checks if mouse is over fish image
  public boolean isMouseOver() {
    int fishWidth = this.getImage().width;
    int fishHeight = this.getImage().height;

    // checks if the mouse is over the provided fish
    return processing.mouseX >= this.getPositionX() - fishWidth / 2
        && processing.mouseX <= this.getPositionX() + fishWidth / 2
        && processing.mouseY >= this.getPositionY() - fishHeight / 2
        && processing.mouseY <= this.getPositionY() + fishHeight / 2;
  }

  // Starts swimming this fish
  public void startSwimming() {
    stopDragging(); // 1. stops dragging the fish
    isSwimming = true; // 2. sets the isSwimming instance field to true
  }

  // Stops swimming this fish
  public void stopSwimming() {
    isSwimming = false; // Sets the isSwimming instance field of this fish to false
  }

  // Moves horizontally the fish one speed step from left to right
  public void swim() {
    x += speed;
    x %= processing.width;
  }

}
