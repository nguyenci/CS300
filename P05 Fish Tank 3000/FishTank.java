//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000, FishTank Class
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

import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Program creates decorations and fish for the fish tank and adds them to an ArrayList. Allows for
 * keyboard inputs that add, remove, start swimming, or stop swimming fish.
 * 
 * @author Cinthya Nguyen
 */
public class FishTank extends PApplet {

  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers
  private TankObject flower; // tank decoration
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  /**
   * Sets the size of this PApplet to 800 width x 600 height.
   */
  @Override
  public void settings() {
    size(800, 600);

  }

  /**
   * Defines screen size and loads background image and fonts as the program starts and initializes
   * all data fields. Adds tank objects to objects ArrayList. Overrides base implementation.
   * 
   * @see PApplet#setup()
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Fish Tank 3000"); // Set and display the title of the display window

    this.imageMode(PApplet.CENTER); // Set the location from which images are drawn to CENTER

    this.rectMode(PApplet.CORNERS); // Set the location from which rectangles are drawn

    this.focused = true; // Processing program is active and will accept mouse or keyboard input

    this.textAlign(PApplet.CENTER, PApplet.CENTER); // sets the text alignment to center

    // load the background image and store the loaded image to backgroundImage
    backgroundImage = this.loadImage("images" + File.separator + "background.png");

    objects = new ArrayList<TankListener>();

    randGen = new Random();

    TankObject.setProcessing(this);

    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");

    // add decoration objects to fish tank
    addObject(flower);
    addObject(log);
    addObject(shell);
    addObject(ship);

    // add Black Fish
    addObject(new BlackFish(log, flower));
    addObject(new BlackFish(shell, flower));

    Button.setProcessing(this);

    // add buttons to fish tank
    addObject(new AddBlueFishButton(43, 16));
    addObject(new AddOrangeFishButton(129, 16));
    addObject(new AddYellowFishButton(215, 16));
    addObject(new ClearTankButton(301, 16));
  }

  /**
   * Continuously draws and updates the application display window. Overrides base implementation.
   * 
   * @see PApplet#draw()
   */
  @Override
  public void draw() {
    this.image(backgroundImage, this.width / 2, this.height / 2); // draw background image

    // draw objects ArrayList to display window
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse. Overrides base implementation.
   */
  @Override
  public void mousePressed() {
    // traverse the objects and call mousePressed method of the first object clicked
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break; // only the fish at the lowest index will start dragging
      }
    }
  }

  /**
   * Callback method called each time the mouse is released. Overrides base implementation.
   */
  @Override
  public void mouseReleased() {
    // call each object's mouseReleased() method
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  /**
   * TankListener object to the objects ArrayList.
   * 
   * @param object object of type TankListener to be added to objects ArrayList
   */
  public void addObject(TankListener object) {
    objects.add(object);
  }

  /**
   * Callback method called each time the user presses a key. Overrides base implementation.
   */
  @Override
  public void keyPressed() {
    switch (Character.toUpperCase(this.key)) {
      case 'O':
        objects.add(new Fish());
        break;
      case 'Y':
        objects.add(new Fish(2, "images" + File.separator + "yellow.png"));
        break;
      case 'R':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish && objects.get(i).isMouseOver()) {
            objects.remove(i);
            break; // if multiple Fish overlap, removes one Fish of lowest index
          }
        }
        break;
      case 'S':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            ((Fish) objects.get(i)).startSwimming();
          }
        }
        break;
      case 'X':
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            ((Fish) objects.get(i)).stopSwimming();
          }
        }
        break;
      case 'C':
        clear();
        break;
      case 'B':
        addObject(new BlueFish());
    }
  }

  /**
   * Removes all instances of Fish in objects ArrayList. Overrides base implementation.
   */
  public void clear() { // GradeScope does not accept code w/ @Override tag above this method
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        objects.remove(i);
        i--;
      }
    }
  }

  /**
   * Calls PApplet.main() to take String, which represents name of PApplet class.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    PApplet.main("FishTank");
  }

}
