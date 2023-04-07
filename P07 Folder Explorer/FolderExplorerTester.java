import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Arrays;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Folder Content Explorer, Tester Class
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
 * This contains testing methods for the FolderExplorer class.
 * 
 * @author Cinthya Nguyen
 */
public class FolderExplorerTester {

  /**
   * Tests getContents(), making sure that output is correct and the correct exceptions are thrown.
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testGetContents(File folder) {
    try {

      // Scenario 1 - list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);

      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println(expectedList.get(i) + "is missing from the output of the list contents"
              + "of cs300 folder.");
          return false;
        }
      }

      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Scenario 2: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println("p02 folder must contain only one file named FishTank.java.");
        return false;
      }

      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Needs to throw NotDirectoryException if input is not a directory.");
        return false;
      } catch (NotDirectoryException e) {
        // expected behavior
      }

      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println(
            "Needs to throw NotDirectoryException if the provided File does not" + "exist.");
        return false;
      } catch (NotDirectoryException e) {
        // expected behavior
      }
    } catch (Exception e) {
      System.out.println("getContents() has thrown a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests base case of getDeepContents(), with additional tests to make sure correct exceptions are
   * thrown.
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testDeepGetContentsBaseCase(File folder) {

    try {

      // Scenario 1 - list files of p01
      File f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p01");
      ArrayList<String> actualList = FolderExplorer.getDeepContents(f);
      String[] contents = {"ClimbingTracker.java", "ClimbingTrackerTester.java"};
      List<String> expectedList = Arrays.asList(contents);

      // check size and contents of output
      if (actualList.size() != 2) {
        System.out.println("p01 folder must contain 2 elements.");
      }

      for (int i = 0; i < expectedList.size(); i++) {
        if (!actualList.contains(expectedList.get(i))) {
          System.out
              .println(expectedList.get(i) + "is missing from the output contents of p01 folder.");
          return false;
        }
      }

      // Scenario 2 - empty folder
      f = new File(folder.getPath() + File.separator + "grades");
      actualList = FolderExplorer.getDeepContents(f);

      if (actualList.size() != 0) {
        System.out.println("grades folder must contain 0 elements.");
        return false;
      }

      // Scenario 3 - list contents of a file
      try {
        f = new File(folder.getPath() + File.separator + "syllabus.txt");
        actualList = FolderExplorer.getDeepContents(f);
        System.out.println("Unexpected exception was thrown in getDeepContents().");
        return false;
      } catch (NotDirectoryException e) {
        // expected behavior
      }

      // Scenario 4 - input is not a directory
      f = new File("cs400");
      try {
        actualList = FolderExplorer.getDeepContents(f);
        System.out.println(
            "getDeepContents() must throw a NotDirectoryException if provided input is not a"
                + "directory.");
        return false;
      } catch (NotDirectoryException e) {
        // expected behavior
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception was thrown in getDeepContents().");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests recursive case of getDeepContents().
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testDeepListRecursiveCase(File folder) {

    try {

      // Scenario 1 - directory with sub-directories containing files (programs folder)
      File f = new File(folder.getPath() + File.separator + "programs");
      ArrayList<String> actualList = FolderExplorer.getDeepContents(f);
      String[] contents = {"Program01.pdf", "Program02.pdf", "Program03.pdf",
          "ClimbingTrackerTester.java", "ClimbingTracker.java", "ExceptionalClimbingTester.java",
          "ExceptionalClimbing.java", "FishTank.java"};

      List<String> expectedList = Arrays.asList(contents);

      // check the size and the contents of the output
      if (actualList.size() != 8) {
        System.out.println("programs folder must contain 8 elements.");
        return false;
      }

      for (int i = 0; i < expectedList.size(); i++) {
        if (!actualList.contains(expectedList.get(i))) {
          System.out.println(expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println("Unexpected exception was thrown in getDeepContents().");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests lookupByFileName().
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testLookupByFileName(File folder) {

    try {

      // Scenario 1 - returns path of a file that exists, confirms correct path is returned
      String fileName = "Program01.pdf";
      File f = new File(folder.getPath() + File.separator + "programs");
      String expected = "programs" + File.separator + "writeUps" + File.separator + "Program01.pdf";
      String actual = FolderExplorer.lookupByName(f, fileName);

      if (!expected.equals(actual)) {
        System.out.println("Output does not equal path.");
        return false;
      }

      // Scenario 2 - file not found
      try {
        fileName = "doesntExist.txt";
        actual = FolderExplorer.lookupByName(folder, fileName);
        System.out.println("NoSuchElementException was supposed to be thrown.");
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 3 - input is not a directory
      try {
        fileName = "outline.txt";
        f = new File("cs400");
        actual = FolderExplorer.lookupByName(f, fileName);
        System.out.println("NoSuchElementException was supposed to be thrown.");
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 4 - file name is null
      try {
        fileName = null;
        actual = FolderExplorer.lookupByName(folder, fileName);
        System.out.println("NoSuchElementException was supposed to be thrown.");
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 5 - FishTank.java path
      fileName = "FishTank.java";
      actual = FolderExplorer.lookupByName(folder, fileName);
      expected = folder.getPath() + File.separator + "programs" + File.separator + "p02"
          + File.separator + "FishTank.java";

      if (!expected.equals(actual)) {
        System.out.println("Output does not equal path.");
        return false;
      }
      
      actual = FolderExplorer.lookupByName(folder, "todo.txt");
      expected = folder.getPath() + File.separator + "todo.txt";
      
      if (!expected.equals(actual)) {
        System.out.println("Output does not equal path.");
        return false;
      }
      

    } catch (Exception e) {
      System.out.print(e.getMessage());
      e.printStackTrace();
      return false;
    }
    
    return true;
  }

  /**
   * Tests base case of lookupByKey(), with additional tests.
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testLookupByKeyBaseCase(File folder) {

    try {

      // Scenario 1 - tests base case works
      File f =
          new File(folder.getPath() + File.separator + "lecture notes" + File.separator + "unit3");
      String key = "Recursion";
      ArrayList<String> actualList = FolderExplorer.lookupByKey(f, key);

      if (actualList.size() != 1 || !actualList.contains("Recursion.txt")) {
        System.out.println("List does not contain 1 element and one file named \"Recursion.txt\".");
        return false;
      }

      // Scenario 2 - key does not exist
      actualList = FolderExplorer.lookupByKey(folder, ".jpeg");

      if (actualList.size() != 0) {
        System.out.println("Output should contain 0 elements.");
        return false;
      }

      // Scenario 3 - file name is null
      key = null;
      actualList = FolderExplorer.lookupByKey(folder, key);

      if (actualList.size() != 0) {
        System.out.println("Output should contain 0 elements.");
        return false;
      }

      // Scenario 4 - directory does not exist
      f = new File(folder.getPath() + File.separator + "todo.txt");
      key = ".pdf";
      actualList = FolderExplorer.lookupByKey(f, key);

      if (actualList.size() != 0) {
        System.out.println("Output should contain 0 elements.");
        return false;
      }


    } catch (Exception e) {
      System.out.println("An unexpected exception was thrownin LookupByKey()");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Tests recursive case of lookupByKey().
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testLookupByKeyRecursiveCase(File folder) {

    try {

      // Scenario 1 - look for files containing .pdf
      ArrayList<String> actualList = FolderExplorer.lookupByKey(folder, ".pdf");
      String[] contents = {"Program01.pdf", "Program02.pdf", "Program03.pdf"};
      List<String> expected = Arrays.asList(contents);

      if (actualList.size() != 3) {
        System.out.println("List does not contain 3 elements.");
        return false;
      }

      for (int i = 0; i < expected.size(); i++) {
        if (!actualList.contains(expected.get(i))) {
          System.out.println(expected.get(i) + "is not in the output list.");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println("An unexpected exception was thrown");
      e.printStackTrace();
      return false;

    }

    return true;
  }

  /**
   * Tests lookupBySize().
   * 
   * @param folder directory containing all files and folders to be referenced
   * @return true if all test cases pass, false if one test case fails
   */
  public static boolean testLookupBySize(File folder) {

    try {

      // Scenario 1 - min = 9000, max = 9000000
      ArrayList<String> actualList = FolderExplorer.lookupBySize(folder, 9000, 9000000);
      String[] contents = {"Program01.pdf", "Program02.pdf", "Program03.pdf", "syllabus.txt"};
      List<String> expectedList = Arrays.asList(contents);

      for (int i = 0; i < expectedList.size(); i++) {
        if (!actualList.contains(expectedList.get(i))) {
          return false;
        }
      }

      // Scenario 2 - min = 100, max = 500
      actualList = FolderExplorer.lookupBySize(folder, 100, 5000);
      contents = new String[] {"Inheritance.txt", "Recursion.txt", "todo.txt"};
      expectedList = Arrays.asList(contents);

      for (int i = 0; i < expectedList.size(); i++) {
        if (!actualList.contains(expectedList.get(i))) {
          return false;
        }
      }

      // Scenario 3 - directory does not exist
      File f = new File(folder.getPath() + File.separator + "todo.txt");
      actualList = FolderExplorer.lookupBySize(f, 9000, 900000);

      if (actualList.size() != 0) {
        System.out.println("List does not contain 0 elements.");
        return false;
      }

      // Scenario 4 - no matches
      actualList = FolderExplorer.lookupBySize(folder, -5, -1);

      if (actualList.size() != 0) {
        System.out.println("List does not contain 0 elements.");
        return false;
      }

    } catch (Exception e) {
      System.out.println("An unexpected was thrown");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Calls all tester methods.
   */
  public static void runAllTests() {
    System.out.println("getContents(): " + testGetContents(new File("cs300")));
    System.out.println("getDeepContents() Base: " + testDeepGetContentsBaseCase(new File("cs300")));
    System.out
        .println("getDeepContents() Recursive: " + testDeepListRecursiveCase(new File("cs300")));
    System.out.println("lookupByName(): " + testLookupByFileName(new File("cs300")));
    System.out.println("lookupByKey() Base: " + testLookupByKeyBaseCase(new File("cs300")));
    System.out
        .println("lookupByKey() Recursive: " + testLookupByKeyRecursiveCase(new File("cs300")));
    System.out.println("lookupBySize(): " + testLookupBySize(new File("cs300")));

  }

  /**
   * Main method of tester class.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    runAllTests();
  }

}
