//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf, TreeNode class
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
 * Creates a generic binary tree node.
 * 
 * @author Cinthya Nguyen
 */
public class TreeNode<T> {
  
  private T data;
  private TreeNode<T> left;
  private TreeNode<T> right;

/**
 * Constructs a node with the given data and no children.
 * 
 * @param data the data to be contained in this node
 */
  public TreeNode(T data) {
    this.data = data;
    left = null;
    right = null;
  }

  /**
   * Constructs a node with the given data and children.
   * 
   * @param data the data to be contained in this node
   * @param left the left child of this node, may be null
   * @param right the right child of this node, may be null
   */
  public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Accessor for the left child.
   * 
   * @return reference to the left child of this node
   */
  public TreeNode<T> getLeft() {
    return left;
  }

  /**
   * Accessor for the right child.
   * 
   * @return reference to the right child of this node
   */
  public TreeNode<T> getRight() {
    return right;
  }

  /**
   * Accessor for the data type contained in the node.
   * 
   * @return data contained in the node
   */
  public T getData() {
    return data;
  }

  /**
   * Change the left child reference of this node; may be null. 
   * 
   * @param left child node to set as parent's left field
   */
  public void setLeft​(TreeNode<T> left) {
    this.left = left;
  }

  /**
   * Change the right child reference of this node; may be null. 
   * 
   * @param right child node to set as parent's right field
   */
  public void setRight​(TreeNode<T> right) {
    this.right = right;
  }

  /**
   * Returns a string representation of this node's data
   * 
   * @return String representation of this node's data
   */
  @Override
  public String toString() {
    return data.toString();
  }

}
