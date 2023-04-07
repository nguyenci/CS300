import java.util.Iterator;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P11 Assignment Planner, AssignmentQueue class
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
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 * 
 * @author Cinthya Nguyen
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) throws IllegalArgumentException {

    if (capacity <= 0) {
      throw new IllegalArgumentException("Queue capacity must be a positive integer.");
    }

    queue = new Assignment[capacity];
    size = 0;

  }

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return true if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this AssignmentQueue
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the capacity of this AssignmentQueue
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    return queue.length;
  }

  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    size = 0;
    queue = new Assignment[queue.length];
  }

  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() throws NoSuchElementException {

    if (size == 0) {
      throw new NoSuchElementException("Queue is empty.");
    }

    return queue[0];
  }

  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) {

    if (e == null) {
      throw new NullPointerException("Assignment is null.");
    }

    if (size == queue.length) {
      throw new IllegalStateException("Queue is full.");
    }

    if (size == 0) {
      queue[0] = e;
      size++;
    } else {

      for (int i = 0; i < queue.length; i++) {

        if (queue[i] == null) {
          queue[i] = e;
          percolateUp(i);
          size++;
          break;
        }

      }
    }

  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() {

    if (size == 0) {
      throw new NoSuchElementException("Queue is empty.");
    }

    Assignment lowest = queue[0];

    if (size > 1) {
      queue[0] = queue[size - 1];
      queue[size - 1] = null;
      size--;

    } else {
      queue[0] = null;
      size--;
    }

    percolateDown(0);

    return lowest;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * Time complexity: O(log N), where N is the size of this queue
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i) {

    if (i < 0 || i >= queue.length) {
      throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    int left = (i * 2) + 1;
    int right = (i * 2) + 2;

    if (left >= size) { // base (node has no children)
      return;
    } else if (queue[i].compareTo(queue[left]) > 0) { // recursive (parent > left child)

      if (right < size) { // checks if right child exists

        // swap with smaller child
        int lesser = (queue[left].compareTo(queue[right]) < 0) ? left : right;
        swap(i, lesser);
        percolateDown(lesser);

      } else { // right child does not exist, swap with left
        swap(i, left);
        percolateDown(left);

      }

    } else if (right < size && queue[i].compareTo(queue[right]) > 0) { // recursive (parent > right
                                                                       // child)
      swap(i, right);
      percolateDown(right);

    }
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * Time complexity: O(log N), where N is the size of this queue
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {

    if (i < 0 || i >= queue.length) {
      throw new IndexOutOfBoundsException("Index out of bounds.");
    }

    if (i == 0) { // base (i is at the root)
      return;

    } else if (queue[i].compareTo(queue[(i - 1) / 2]) > 0) { // base (child > parent, so no swap)
      return;

    } else if (queue[i].compareTo(queue[(i - 1) / 2]) < 0) { // recursive
      swap(i, (i - 1) / 2);
      percolateUp((i - 1) / 2);
    }

  }

  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * assignments. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {

    AssignmentQueue newQueue = new AssignmentQueue(queue.length);
    Assignment[] otherQueue = new Assignment[queue.length];

    for (int i = 0; i < size; i++) {
      otherQueue[i] = queue[i];
    }

    newQueue.queue = otherQueue;
    newQueue.size = this.size;

    return newQueue;
  }

  /**
   * Returns string representation of the queue array (FAQ version implemented and used).
   * 
   * Original: Returns a String representing this AssignmentQueue, where each element (assignment)
   * of the queue is listed on a separate line, in order from earliest to latest.
   * 
   * @see Assignment#toString()
   * @see AssignmentIterator
   * @return a String representing this AssignmentQueue
   */
  @Override
  public String toString() {

    StringBuilder val = new StringBuilder();

    for (int i = 0; i < size; i++) {
      val.append(queue[i]).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }

  private void swap(int index1, int index2) {
    Assignment temp = queue[index1];
    queue[index1] = queue[index2];
    queue[index2] = temp;

  }

}
