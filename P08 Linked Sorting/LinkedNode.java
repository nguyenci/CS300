//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 LinkedNode
// Course:   CS 300 Fall 2021
//
// Author:   Cinthya Nguyen
// Email:    cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This program creates a node.
 * 
 * @author Cinthya Nguyen
 */
public class LinkedNode<T> {
  
  private T data;
  private LinkedNode<T> next;
  
  /**
   * Constructor, initializes data type of node.
   * 
   * @param data data type
   */
  public LinkedNode(T data) {
    this.data = data;
    next = null;
    
  }
  
  /**
   * Constructor, initializes node's data type and next pointer.
   * @param data
   * @param next
   */
  public LinkedNode(T data,LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }
  
  /**
   * Get next node.
   * 
   * @return next node
   */
  public LinkedNode<T> getNext() { 
    return next;
    
  }
  
  /**
   * Return data type. 
   * 
   * @return data type of node
   */
  public T getData() {
    return data;
    
  }
  
  /**
   * Converts data type to string.
   * 
   * @return string format of the data type
   */
  @Override
  public String toString() {
    return data.toString();
  }
  
  /**
   * Set next node.
   * 
   * @param next node to be set
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

}
