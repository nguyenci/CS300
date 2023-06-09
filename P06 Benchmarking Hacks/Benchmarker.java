//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Benchmarking Hacks, Benchmarker Class
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
 * This program times how long hack() and bruteForce() take.
 * 
 * @author Cinthya Nguyen
 *
 */
public class Benchmarker {

  /**
   * Calculates the time it takes for bruteForce() of a PasswordHacker object to complete.
   * 
   * @param ph PasswordHacker object
   * @return time in milliseconds
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long a = System.currentTimeMillis();
    ph.bruteForce();
    long c = System.currentTimeMillis();
    
    return c - a;
  }

  /**
   * Calculates the time it takes for hack() of a PasswordHacker object to complete.
   * 
   * @param ph PassWordHacker object
   * @return time in milliseconds
   */
  public static long timeHack(PasswordHacker ph) {
    long a = System.currentTimeMillis();
    ph.hack();
    long c = System.currentTimeMillis();
    
    return c - a;
  }

  /**
   * Races hack() and bruteForce() and averages the times by numRuns.
   * 
   * @param passwordLength length of password to be generated by LockBox and PasswordHacker
   * @param numRuns        number of runs
   * @return String that records the time it took hack() and bruteForce() to run
   */
  public static String race(int passwordLength, int numRuns) {

    PasswordHacker[] array = new PasswordHacker[numRuns];

    for (int i = 0; i < numRuns; i++) {
      array[i] = new PasswordHacker(passwordLength);
    }

    long timeBruteForce = 0L;
    for (int i = 0; i < numRuns; i++) {
      timeBruteForce += timeBruteForce(array[i]); // sum of the times bruteForce() takes to run
    }

    double timeElapseBrute = (double)timeBruteForce/numRuns; 

    long timeHack = 0L;
    for (int i = 0; i < numRuns; i++) {
      timeHack += timeHack(array[i]); // sum of the times hack() takes to run
    }

    double timeElapsedHack = (double)timeHack/numRuns; 

    return "Brute force " + passwordLength + ": " + timeElapseBrute + "\nHack " + passwordLength
        + ": " + timeElapsedHack;
  }


  /**
   * This is the main method of the program.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println(race(7, 3));

  }

}
