//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, TankObject Class
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

import processing.core.PImage;

/**
 * This program creates tank objects and allows the dragging of TankObject objects.
 * 
 * @author Cinthya Nguyen
 */
public class TankObject implements TankListener {

  protected static FishTank tank; // PApplet object which represents the display window
  protected PImage image; // image of this tank object
  private float x; // x-position of this tank in the display window
  private float y; // y-position of this tank in the display window
  private boolean isDragging; // indicates whether this tank object is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  /**
   * Creates a new TankObject according to its image file name at a given position.
   * 
   * @param x             x-position to set for this tank object
   * @param y             y-position to set for this tank object
   * @param imageFileName file name of image to use for this tank object
   */
  public TankObject(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    image = tank.loadImage(imageFileName);
    isDragging = false;
  }

  /**
   * Sets the PApplet graphic display window for all TankObjects.
   * 
   * @param tank PApplet graphic display window
   */
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
  }

  /**
   * Moves this tank object with dx and dy.
   * 
   * @param dx amount to move x-position
   * @param dy amount to move y-position
   */
  public void move(int dx, int dy) {
    x += dx; // dx move to the x-position of this tank object
    y += dy; // dy move to the y-position of this tank object
  }

  /**
   * Returns the x-position of this tank object.
   * 
   * @return x-position of this tank object.
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this tank object.
   * 
   * @return y-position of this tank object
   */
  public float getY() {
    return y;
  }

  /**
   * Sets the x-position of this object.
   * 
   * @param x x-position to be set for this tank object
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this object
   * 
   * @param y y-position to be set for tank object
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Returns the image of this tank object.
   * 
   * @return image of tank object
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Getter of the isDragging field.
   * 
   * @return boolean that represents if fish is being dragged
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging this tank object.
   */
  public void startDragging() {
    oldMouseX = tank.mouseX;
    oldMouseY = tank.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this tank object.
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draws tank object to follow the mouse. Overrides interface implementation.
   */
  @Override
  public void draw() {
    if (isDragging == true) {
      move(tank.mouseX - oldMouseX, tank.mouseY - oldMouseY);
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }

    tank.image(image, x, y);
  }

  /**
   * Starts dragging tank object. Overrides interface implementation.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      startDragging();
    }
  }

  /**
   * Stop dragging tank object. Overrides interface implementation.
   */
  @Override
  public void mouseReleased() {
    stopDragging();
  }

  /**
   * Checks if mouse is over the tank object. Overrides interface implementation.
   */
  @Override
  public boolean isMouseOver() {
    int imageWidth = this.getImage().width;
    int imageHeight = this.getImage().height;

    return tank.mouseX >= this.getX() - imageWidth / 2
        && tank.mouseX <= this.getX() + imageWidth / 2
        && tank.mouseY >= this.getY() - imageHeight / 2
        && tank.mouseY <= this.getY() + imageHeight / 2;
  }

}
