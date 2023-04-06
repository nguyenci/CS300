import java.util.Random;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Benchmarking Hacks, LockBox Class
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
 * This program creates a LockBox with a randomly generated password based on the given length.
 * 
 * @author Cinthya Nguyen
 */
public class LockBox {

  protected static Random randGen;
  private String password;
  private boolean isOpen;

  /**
   * Creates a new LockBox with a random password that is passwordLength digits long. 
   * 
   * @param passwordLength the length of the generated password
   * @throws IllegalArgumentException if passwordLength is 0 or negative
   */
  public LockBox(int passwordLength) throws IllegalArgumentException {
    randGen = new Random();

    if (passwordLength <= 0) {
      throw new IllegalArgumentException("Invalid password length");
      
    } else {
      password = "";    
      int randNum = randGen.nextInt((int)Math.pow(10, passwordLength));
      
      for (int i = 0; i < passwordLength; i++ ) {
        password = (randNum % 10) + password;
        randNum = randNum / 10;
      }
      
    }
  }

  /**
   * Sees if a guess equals the LockBox's password.
   * 
   * @param guess password compared with LockBox's password
   */
  public void authenticate(String guess) {
    if (guess.equals(password)) {
      isOpen = true;
    }
  }

  /**
   * Getter method that returns the password. 
   * 
   * @return the LockBox's password
   */
  public String hackMe() {
    return password;
  }

  /**
   * Getter method that returns true if the LockBox is open and false otherwise.
   * 
   * @return true if LockBox is opened, false otherwise
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Setter method that sets isOpen to false.
   */
  public void reset() {
    isOpen = false;
  }

}
