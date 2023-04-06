//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P04 Fish Tank 2000, Decoration
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
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
public class Decoration {

  private static PApplet processing;
  private PImage image;
  private float x;
  private float y;
  private boolean isDragging;
  private static int oldMouseX;
  private static int oldMouseY;

  public Decoration(PApplet processing, float x, float y, String imageFileName) {
    Decoration.processing = processing; // PApplet reference to the display window of the Fish Tank
    this.image = processing.loadImage(imageFileName);// filename of the image to be loaded
    this.x = x; // x-position of this decoration object
    this.y = y; // y-position of this decoration object
  }

  // Returns the image of this decoration object
  public PImage getImage() {
    return image;
  }

  // Returns the x-position of this decoration object
  public float getPositionX() {
    return x;
  }

  // Returns the y-position of this decoration object
  public float getPositionY() {
    return y;
  }

  // Checks whether this decoration object is being dragged
  // returns true if the object is being dragged, false otherwise
  public boolean isDragging() {
    if (isDragging == true) {
      return true;
    } else {
      return false;
    }
  }
  // Starts dragging this decoration object

  // Sets the oldMouseX and oldMouseY to the current position of the mouse
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;

    isDragging = true;
  }

  // Stops dragging this decoration object
  public void stopDragging() {
    isDragging = false;
  }

  // Checks whether the mouse is over this decoration object
  public boolean isMouseOver() {
    int imageWidth = this.getImage().width;
    int imageHeight = this.getImage().height;

    return processing.mouseX >= this.getPositionX() - imageWidth / 2
        && processing.mouseX <= this.getPositionX() + imageWidth / 2
        && processing.mouseY >= this.getPositionY() - imageHeight / 2
        && processing.mouseY <= this.getPositionY() + imageHeight / 2;
  }

  // Moves this decoration object with dx and dy
  public void move(int dx, int dy) {
    x += dx; // dx move to the x-position of this decoration object
    y += dy; // dy move to the y-position of this decoration object
  }

  // Draws this decoration object to the display window.
  // This method sets also the position of this object to follow the moves of the mouse if it is
  // being dragged
  public void draw() {
    if (isDragging == true) {
      move(processing.mouseX - oldMouseX, processing.mouseY - oldMouseY);
      oldMouseX = processing.mouseX;
      oldMouseY = processing.mouseY;
    }

    processing.image(image, x, y);
  }

}
