//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Benchmarking Hacks, PasswordHacker Class
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
 * This program hacks into a LockBox.
 * 
 * @author Cinthya Nguyen
 *
 */
public class PasswordHacker {

  private LockBox toPick;
  private int passwordLength;

  /**
   * Creates a new PasswordHacker and initializes toPick to a new LockBox.
   * 
   * @param passwordLength the length of the generated password
   */
  public PasswordHacker(int passwordLength) {
    this.passwordLength = passwordLength;
    toPick = new LockBox(passwordLength);
  }

  /**
   * Retrieves the LockBox's password and authenticates it.
   * 
   * Complexity: O(1) 
   * Constant time complexity because how fast the method runs does not depend on
   * number of characters in the password.
   */
  public void hack() {
    toPick.reset();

    String password = toPick.hackMe();
    toPick.authenticate(password);
  }

  /**
   * Calls generateGuess() and attempts to authenticate each String until LockBox is open.
   * 
   * Complexity: O(10^N) 
   * Where N is the number of characters in the password. The worst case for any password length
   * will run the while loop 10^passwordLength times.
   */
  public void bruteForce() {
    toPick.reset();

    int i = 0;
    while (toPick.isOpen() == false) { // unlocks LockBox
      toPick.authenticate(generateGuess(i));
      i++;
    }

  }

  /**
   * Generates a password that is passwordLength digits long.
   * 
   * @param count number of times the password was been guessed, new password is generated based on
   *              the iteration
   * @return password that is passwordLength digits long, with preceding zeroes if necessary
   */
  public String generateGuess(int count) {
    String guess = "";

    for (int i = 0; i < passwordLength; i++) { // pads count with 0's if less than passwordLength
      guess = (count % 10) + guess;
      count = count / 10;
    }

    return guess;
  }
}
