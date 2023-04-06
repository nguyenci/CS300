//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 1000
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
 * This program creates a graphical interface of a fish tank that allows the user to add up to 8
 * fishes using the f/F key and remove fish with the r/R key with the mouse over a fish.
 * 
 * @author Cinthya Nguyen
 */
public class FishTank {

  private static PApplet processing; // PApplet object that represents the graphic interface of the
                                     // JunglePark application

  private static PImage backgroundImage; // PImage object that represents the background image

  private static Fish[] fishes; // perfect size array storing the different fish present in the fish
                                // tank. These fish can be of different species.

  private static Random randGen; // Generator of random numbers

  /**
   * @param args unused
   */
  public static void main(String[] args) {
    Utility.startApplication();

  }

  /**
   * Defines the initial environment properties of this application
   * 
   * @param processingObj a reference to the graphic display window of this application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj;

    // load the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    fishes = new Fish[8];

    randGen = new Random();

  }

  /**
   * Draws and updates the application display window. This callback method called in an infinite
   * loop.
   */
  public static void draw() {
    // draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width [resp.
    // height] of the display window.

    // draws fish
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].draw();
      }
    }
  }

  /**
   * Checks if the mouse is over a specific Fish whose reference is provided as input parameter
   *
   * @param Fish reference to a specific fish
   * @return true if the mouse is over the specific Fish object (i.e. over the image of the Fish),
   *         false otherwise *
   */
  public static boolean isMouseOver(Fish fish) {
    PImage image = fish.getImage();

    int xCord = processing.mouseX;
    int yCord = processing.mouseY;

    // lower and upper bound of width of fish image
    float imageXLowerBound = fish.getPositionX() - image.width / 2;
    float imageXUpperBound = fish.getPositionX() + image.width / 2;

    // lower and upper bound of height of fish image
    float imageYLowerBound = fish.getPositionY() - image.height / 2;
    float imageYUpperBound = fish.getPositionY() + image.height / 2;


    // checks if mouse x and y coord are within the image width and height
    if ((xCord >= imageXLowerBound && xCord <= imageXUpperBound)
        && (yCord >= imageYLowerBound && yCord <= imageYUpperBound)) {
      return true;
    }

    return false;
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    // checks which index in fishes array the mouse is over
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] == null) {
        continue;
      }

      // if mouse is over fish, allows dragging of it
      if (isMouseOver(fishes[i])) {
        fishes[i].setDragging(true);
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    // stops fish from being dragged when mouse is released
    for (int i = 0; i < fishes.length; i++) {
      if (fishes[i] != null) {
        fishes[i].setDragging(false);
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    char key = processing.key;

    // if F or f is pressed a fish will be created at a random position on the display screen
    if (key == 'F' || key == 'f') {
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] == null) {
          fishes[i] = new Fish(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break;

        }
      }
    }

    // if R or r is pressed a fish will be removed if a mouse is hovering over it
    if (key == 'R' || key == 'r') {
      for (int i = 0; i < fishes.length; i++) {
        if (fishes[i] == null) {
          continue;
        }
        if (isMouseOver(fishes[i]) && fishes[i] != null) {
          fishes[i] = null;
          break;
        }
      }
    }
  }

}
