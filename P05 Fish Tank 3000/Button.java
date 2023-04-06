//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, Button Class
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
 * This program creates a button in the top left corner of the fish tank display.
 * 
 * @author Cinthya Nguyen
 */
public class Button implements TankListener {
  private static final int WIDTH = 85; // Width of this Button
  private static final int HEIGHT = 32; // Height of this Button
  protected static FishTank tank; // PApplet object where this button will be displayed
  private float x; // x-position of this button in the display window
  private float y; // y-position of this button in the display window protected
  String label; // text/label which represents this button

  /**
   * Creates a new Button at a given position within the display window and sets its label.
   * 
   * @param label text that the button will display.
   * @param x     x-position of this button
   * @param y     y-position of this button
   */
  public Button(String label, float x, float y) {
    this.label = label;
    this.x = x;
    this.y = y;
  }

  /**
   * Sets the PApplet graphic display window for all Buttons.
   * 
   * @param tank PApplet graphic display window
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * Draws button to display window.
   * 
   * @see TankListener#draw()
   */
  @Override
  public void draw() {
    tank.stroke(0);// set line value to black

    if (isMouseOver()) {
      tank.fill(100); // if the mouse is over this button, sets the fill color to dark gray
    } else {
      tank.fill(200); // sets the fill color to light gray if otherwise
    }

    // draw the button (rectangle with a centered text)
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0); // set the fill color to black
    tank.text(label, x, y); // display the text of the current button
  }

  /**
   * Adds default behavior of this button when the mouse is pressed. Overrides interface
   * implementation.
   * 
   * @see TankListener#mousePressed()
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) {
      System.out.println("A button was pressed.");
    }

  }

  /**
   * Has no function for Button class. Overrides interface implementation.
   * 
   * @see TankListener#mouseReleased()
   */
  @Override
  public void mouseReleased() {
    // empty method
  }

  /**
   * Checks whether the mouse is over this button: returns true if the mouse is over this button,
   * false otherwise. Overrides interface implementation.
   * 
   * @see TankListener#isMouseOver()
   */
  @Override
  public boolean isMouseOver() {
    return tank.mouseX >= this.x - WIDTH / 2 && tank.mouseX <= this.x + WIDTH / 2
        && tank.mouseY >= this.y - HEIGHT / 2 && tank.mouseY <= this.y + HEIGHT / 2;
  }

}
