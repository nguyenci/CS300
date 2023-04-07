import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Folder Content Explorer, FolderExplorer Class
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
// Online Sources: shorturl.at/duFU5 (search for a File, second answer)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This program searches through directories.
 * 
 * @author Cinthya Nguyen
 */
public class FolderExplorer {

  /**
   * Returns a list of the names of all files and directories in the given directory.
   * 
   * @param currentDirectory directory of files and/or folders
   * @return a list of the names of all files and directories
   * @throws NotDirectoryException if currentDirectory does not exist or if it is not a directory
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {

    ArrayList<String> list = new ArrayList<String>();

    if (!currentDirectory.isDirectory()) {
      throw new NotDirectoryException("Directory does not exist");
    }

    String[] array = currentDirectory.list();

    for (int i = 0; i < array.length; i++) { // add files/directories to list
      list.add(array[i]);
    }

    return list;
  }

  /**
   * Lists the names of all the files of a given directory and its sub-directories.
   * 
   * @param currentDirectory directory of files and/or folders
   * @return ArrayList of files from the directory and its sub-directories
   * @throws NotDirectoryException if currentDirectory does not exist or if it is not a directory
   */
  public static ArrayList<String> getDeepContents(File currentDirectory)
      throws NotDirectoryException {

    ArrayList<String> list = new ArrayList<String>();
    File[] array = currentDirectory.listFiles();

    if (!currentDirectory.isDirectory()) {
      throw new NotDirectoryException("Directory does not exist");
    }

    for (int i = 0; i < array.length; i++) {
      if (array[i].isFile()) { // base case
        list.add(array[i].getName());
      }

      if (array[i].isDirectory()) { // recursive call
        list.addAll(getDeepContents(array[i]));
      }
    }

    return list;
  }

  /**
   * Searches the given directory and all of its sub-directories for an match to the provided
   * fileName.
   * 
   * @param currentDirectory directory of files and/or folders
   * @param fileName         file to be searched for
   * @return path to the file if found
   * @throws NoSuchElementException if fileName is null, if input directory is not a directory or
   *                                does not exist, if the file was not found
   */
  public static String lookupByName(File currentDirectory, String fileName)
      throws NoSuchElementException {

    if (fileName == null) {
      throw new NoSuchElementException("File name is null");
    }

    if (!currentDirectory.isDirectory()) {
      throw new NoSuchElementException("Directory does not exist");
    }

    String path;

    path = lookupByNameHelper(currentDirectory, fileName); // call helper method

    if (path.equals("")) {
      throw new NoSuchElementException("No file found");
    } else {
      path = path.substring(path.indexOf(currentDirectory.getName()));
    }

    return path;
  }

  /**
   * Helper method for lookupByName().
   * 
   * @param currentDirectory directory of files and/or folders
   * @param fileName         file to be searched for
   * @return path to the file if found
   */
  private static String lookupByNameHelper(File currentDirectory, String fileName) {

    String path = "";
    File[] array = currentDirectory.listFiles();
    for (int i = 0; i < array.length; i++) {
      if (array[i].isFile() && array[i].getName().equals(fileName)) { // base case
        return array[i].getPath();
      } else if (array[i].isDirectory()) { // recursive call
        path = lookupByNameHelper(array[i], fileName);

        if (!path.equals("")) { // file found
          break;
        }
      }
    }

    return path;
  }

  /**
   * searches the given directory and its sub-directories for ALL files that contain the given key
   * in part of their name.
   * 
   * @param currentDirectory directory of files and/or folders
   * @param key              segment used to search file names for to see if they contain it
   * @return ArrayList of all files that have the key
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {

    ArrayList<String> list = new ArrayList<String>();

    if (!currentDirectory.isDirectory() || key == null) {
      return list;
    }

    File[] array = currentDirectory.listFiles();

    for (int i = 0; i < array.length; i++) {
      if (array[i].isFile() && array[i].getName().contains(key)) { // base case
        list.add(array[i].getName());
      }

      if (array[i].isDirectory()) { // recursive call
        list.addAll(lookupByKey(array[i], key));
      }
    }

    return list;
  }

  /**
   * Searches through a directory for a file/files that is in between the specified minimum and
   * maximum size (in bytes).
   * 
   * @param currentDirectory directory of files and/or folders
   * @param sizeMin          minimum size the file can be
   * @param sizeMax          maximum size the file can be
   * @return ArrayList of all files that fit between the minimum and maximum size
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {

    ArrayList<String> list = new ArrayList<String>();

    if (!currentDirectory.isDirectory()) {
      return list;
    }

    File[] array = currentDirectory.listFiles();

    for (int i = 0; i < array.length; i++) {
      if (array[i].isFile() && array[i].length() >= sizeMin && array[i].length() <= sizeMax) {
        list.add(array[i].getName()); // base case
      }

      if (array[i].isDirectory()) { // recursive call
        list.addAll(lookupBySize(array[i], sizeMin, sizeMax));
      }
    }

    return list;
  }

}
