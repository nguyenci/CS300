import java.util.NoSuchElementException;
import java.util.List;
import java.util.Arrays;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P11 Assignment Planner, AssignmentQueueTester class
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
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 * 
 * @author Cinthya Nguyen
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {

    try {

      // 0 capacity
      try {
        AssignmentQueue test = new AssignmentQueue(0);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      // negative capacity
      try {
        AssignmentQueue test = new AssignmentQueue(-1);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      // valid capacity
      AssignmentQueue test = new AssignmentQueue(5);

      if (!test.isEmpty() || test.size() != 0) {
        System.out.println("Incorrectly created empty assignment.");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException
   * 
   * - Calling enqueue on a queue which is empty should add the Assignment, and increment the size
   * of the queue
   * 
   * - Calling enqueue on a non-empty queue should add the Assignment at the proper position, and
   * increment the size of the queue. Try add at least 5 assignments
   * 
   * - Calling peek on a non-empty queue should always return the Assignment with the earliest due
   * date
   * 
   * - Calling enqueue on a full queue should throw an IllegalStateException
   * 
   * - Calling enqueue with a null Assignment should throw a NullPointerException
   *
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {

    try {

      AssignmentQueue test = new AssignmentQueue(5);
      Assignment one = new Assignment("A", 6, 1, 23);
      Assignment two = new Assignment("B", 2, 1, 23);
      Assignment three = new Assignment("C", 7, 1, 23);
      Assignment five = new Assignment("D", 5, 1, 23);
      Assignment four = new Assignment("E", 1, 1, 23);

      // empty queue
      try {
        test.peek();
      } catch (NoSuchElementException e) {
        System.out.println("Expected NoSuchElementException.");

      }

      // non-empty queue
      test.enqueue(one);

      if (test.size() != 1 || test.peek() != one
          || !test.toString().equals("A (Due 06-01 11PM)\n")) {
        System.out.println("Incorrectly added assignment.");
        return false;
      }

      test.enqueue(two);

      if (test.size() != 2 || test.peek() != two
          || !test.toString().equals("B (Due 02-01 11PM)\nA (Due 06-01 11PM)\n")) {
        System.out.println("Incorrectly added assignment.");
        return false;
      }

      test.enqueue(three);
      test.enqueue(four);
      test.enqueue(five);

      // peek on non-empty queue
      if (test.size() != 5 || test.peek() != four) {
        System.out.println("Incorrectly added assignments.");
        return false;
      }

      // full queue
      try {
        test.enqueue(new Assignment("F", 7, 7, 7));
      } catch (IllegalStateException e) {
        System.out.println("Expected IllegalStateException.");

      }

      // null assignment
      try {
        test.enqueue(null);
      } catch (NullPointerException e) {
        System.out.println("Expected NullPointerException.");
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {

    try {

      AssignmentQueue test = new AssignmentQueue(3);
      Assignment one = new Assignment("A", 1, 1, 11);
      Assignment two = new Assignment("B", 2, 1, 11);
      Assignment three = new Assignment("C", 3, 1, 11);
      Assignment four = new Assignment("D", 4, 1, 11);
      Assignment five = new Assignment("E", 5, 1, 1);
      Assignment six = new Assignment("F", 6, 1, 1);
      Assignment seven = new Assignment("F", 7, 1, 1);

      test.enqueue(one);
      test.enqueue(two);
      test.enqueue(three);

      // peek
      if (test.peek() != one || test.size() != 3 || test.isEmpty()) {
        System.out.println("Did not properly peek queue.");
        return false;
      }

      // dequeue
      if (test.dequeue() != one || test.size() != 2 || test.peek() != two) {
        System.out.println("Did not properly dequeue assignment.");
        return false;
      }

      if (test.dequeue() != two || test.size() != 1 || test.peek() != three) {
        System.out.println("Did not properly dequeue assignment.");
        return false;
      }

      if (test.dequeue() != three || test.size() != 0 || !test.isEmpty()) {
        System.out.println("Did not properly dequeue assignment.");
        return false;
      }

      // empty queue
      try {
        test.dequeue();
      } catch (NoSuchElementException e) {
        System.out.println("Expected NoSuchElementException.");
      }

      try {
        test.peek();
      } catch (NoSuchElementException e) {
        System.out.println("Expected NoSuchElementException.");
      }

      // one element queue
      test.enqueue(one);

      if (test.dequeue() != one || test.size() != 0) {
        System.out.println("Did not properly dequeue a queue of size 1.");
        return false;
      }

      test = new AssignmentQueue(6); // new queue

      test.enqueue(one);
      test.enqueue(two);
      test.enqueue(six);
      test.enqueue(three);
      test.enqueue(four);
      test.enqueue(five);

      // percolate down left
      if (test.dequeue() != one || test.size() != 5 || test.isEmpty()) {
        System.out.println("Did not properly dequeue assignment.");
        return false;
      }

      test = new AssignmentQueue(7); // new queue

      test.enqueue(one);
      test.enqueue(three);
      test.enqueue(two);
      test.enqueue(six);
      test.enqueue(four);
      test.enqueue(five);
      test.enqueue(seven);

      // percolate down right
      if (test.dequeue() != one || test.size() != 6 || test.isEmpty()) {
        System.out.println("Did not properly dequeue assignment.");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors
   * 
   * - clear can be called on a non-empty queue with no errors
   * 
   * - After calling clear, the queue should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {

    try {

      // empty queue
      AssignmentQueue test = new AssignmentQueue(5);
      test.clear();

      if (!test.isEmpty() || test.size() != 0) {
        return false;
      }

      // non-empty queue & check for no assignments
      test.enqueue(new Assignment("A", 1, 1, 1));
      test.enqueue(new Assignment("B", 2, 2, 2));

      test.clear();

      if (!test.isEmpty() || test.size() != 0 || !test.toString().equals("")) {
        System.out.println("Did not properly clear queue.");
        return false;
      }

      try {
        test.peek();
      } catch (NoSuchElementException e) {
        // expected behavior
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception thrown.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests all the methods of the AssignmentQueue class.
   */
  public static boolean runAllTests() {
    if (!testConstructor() || !testEnqueue() || !testDequeuePeek() || !testClear()) {
      return false;
    }

    return true;
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    runAllTests();

  }
}
