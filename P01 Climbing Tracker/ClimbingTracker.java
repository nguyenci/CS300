//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 Climbing Tracker
// Course: CS 300 Fall 2021
//
// Author: Cinthya Nguyen
// Email: cnguyen37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Arushi Pandey
// Partner Email: renschlerpan@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment. x
// ___ We have both read and understand the course Pair Programming Policy. x
// ___ We have registered our team prior to the team registration deadline. x
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This contains a program creates a progress tracker for climbing. It records successful and failed
 * climbs, ranging from V0 to V7 and adds them into an existing array. Additionally, it also takes
 * the most recent history of a set of climbs and averages the grades, outputting an average grade
 * for successful climbs and an average grade for failed climbs. It also includes a method to create
 * a histogram of successful and failed climbs ranging from V0 to the highest climb grade in either
 * the send or fail array.
 * 
 * @author Cinthya Nguyen
 * @author Arushi Pandey
 *
 */
public class ClimbingTracker {

  /**
   * This method calls the helper method getClimb() and takes the desired grade and inputs it into
   * an array of successful climbs, incrementing the size of the array and returns the new size. If
   * there is no room to add a grade or the format of the grade is invalid (i.e V and number is less
   * than 0 or greater than 7), returns the initial size of the array.
   * 
   * @param send    Array of grades of successful climbs.
   * @param numSend Size of the send array.
   * @param grade   String that contains the grade.
   * @return An int of the new size of the send array. If array is full or grade input is invalid,
   *         return initial size of the send array.
   */
  public static int sendClimb(String[] send, int numSend, String grade) {
    int numFinal = getClimb(send, numSend, grade);

    return numFinal;
  }

  /**
   * This method is similar to the sendClimb method. It calls getClimb() and takes the desired grade
   * and inputs it into an array of failed climbs, incrementing the size of the array and returns
   * the new size. If there is no room to add a grade or the format of the grade is invalid (i.e V
   * and number is less than 0 or greater than 7), returns the initial size of the array.
   * 
   * @param fail    Array of grades of failed climbs.
   * @param numFail Size of the fail array.
   * @param grade   String that contains the grade.
   * @return An int of the new size of the fail array. If array is full or grade input is invalid,
   *         return initial size of the fail array.
   */
  public static int failClimb(String[] fail, int numFail, String grade) {
    int numFinal = getClimb(fail, numFail, grade);

    return numFinal;
  }

  /**
   * This method takes the most recent history of a send and fail array of climbs (separately) and
   * averages it, returning a String that shows the send array's average and fail array's average.
   * 
   * @param send          Array of grades of successful climbs.
   * @param numSend       Size of send array.
   * @param fail          Array of grades of failed climbs.
   * @param numFail       Size of fail array.
   * @param historyLength How far back to go into the array, and what to average the sum of the
   *                      send/fail grades by.
   * @return String that shows the send and failed array's average (separately).
   */
  public static String getStats(String[] send, int numSend, String[] fail, int numFail,
      int historyLength) {
    String stats = "";

    int sumSend = 0; // Sum of send grades
    double statsFailAvg = 0.0;
    int historySend = 0;

    int sumFail = 0; // Sum of fail grades
    double statsSumAvg = 0.0;
    int historyFail = 0;

    if (historyLength == 0 || historyLength < 0) {
      return "send: --" + "\nfail: --";
    }
    // Calculates climb average for successful climbs and concatenates it into stats
    if (historyLength <= numSend) {
      for (int i = numSend - 1; i < send.length; i--) {
        sumSend += Integer.parseInt(send[i].substring(1));
        historySend++; // How far back into array's history the loop has iterated to

        if (historySend == historyLength) {
          break;
        }
      }
      statsSumAvg = (double) sumSend / historyLength;
      stats = "send: " + statsSumAvg;
    } else if (numSend == 0) {
      stats = "send: --";
    } else { // Runs if historyLength is greater than numSend
      for (int i = send.length - 1; i < send.length; i--) {
        if (send[i] == null) {
          continue;
        }

        sumSend += Integer.parseInt(send[i].substring(1));
        historySend++;

        // If historySend is greater equal to size of send array, stops adding to sumSend
        if (historySend == numSend) {
          break;
        } else if (historySend == send.length) {
          break;
        }
      }
      statsSumAvg = (double) sumSend / numSend;
      stats = "send: " + statsSumAvg;
    }

    // Calculates climb average for failed climbs and concatenates it into existing String
    if (historyLength <= numFail) {
      for (int i = numFail - 1; i < fail.length; i--) {
        sumFail += Integer.parseInt(fail[i].substring(1));
        historyFail++; // How far back into array's history the loop has iterated to

        if (historyFail == historyLength) {
          break;
        }
      }
      statsFailAvg = (double) sumFail / historyLength;

      stats += "\nfail: " + statsFailAvg;
    } else if (numFail == 0) {
      stats += "\nfail: --";
    } else { // Runs if historyLength is greater than numFail
      for (int i = numFail - 1; i < fail.length; i--) {
        if (fail[i] == null) {
          continue;
        }

        sumFail += Integer.parseInt(fail[i].substring(1));
        historyFail++;

        // If historyFail is greater equal to size of fail array, stops adding to sumFail
        if (historyFail == numFail) {
          break;
        } else if (historyFail == fail.length) {
          break;
        }
      }
      statsFailAvg = (double) sumFail / numFail;

      stats += "\nfail: " + statsFailAvg;
    }

    return stats;
  }

  /**
   * This method takes the highest grade between the successful and failed climbs arrays and records
   * the occurrences of each grade up to the highest grade, returning a histogram. Successful climbs
   * are represented by '+' and failed climbs are represented by '-'.
   * 
   * @param send    Array of grades of successful climbs.
   * @param numSend Size of send array.
   * @param fail    Array of grades of failed climbs.
   * @param numFail Size of fail array.
   * @return String that represents a histogram of both successful and failed climb grades.
   */
  public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
    String histogram = "";

    if (numSend == 0 && numFail == 0) {
      return "Error: no data to display";
    } else if (numSend == 0 || numFail == 0) {
      if (numSend == 0) {
        String sign = "- ";

        int maxGrade = getMaxGrade(fail, numFail); // Max grade in fail array
        int[] gradeCount = getGradeCount(fail, numFail, maxGrade); // Counts grade frequencies

        histogram = getHisto(gradeCount, maxGrade, sign);
      } else {
        String sign = "+ ";

        int maxGrade = getMaxGrade(send, numSend); // Max grade in send array
        int[] gradeCount = getGradeCount(send, numSend, maxGrade); // Counts grade frequencies

        histogram = getHisto(gradeCount, maxGrade, sign);
      }

    } else {
      int maxSendGrade = getMaxGrade(send, numSend); // Max grade in send array
      int maxFailGrade = getMaxGrade(fail, numFail); // Max grade in fail array
      int maxGrade = Math.max(maxSendGrade, maxFailGrade);

      // Counts grade frequencies
      int[] gradeSendCount = getGradeCount(send, numSend, maxGrade);
      int[] gradeFailCount = getGradeCount(fail, numFail, maxGrade);

      // Concatenates the String into a histogram
      for (int i = 0; i <= maxGrade; i++) {
        histogram += "V" + i + ": ";
        // If grade is less or equal to the max grade and occurs at least once, add a '-'
        if (i <= maxFailGrade && gradeFailCount[i] > 0) {
          for (int j = 0; j < gradeFailCount[i]; j++) {
            histogram += "- ";
          }

        }
        // If grade is less or equal to the max grade and occurs at least once, add a '+'
        if (i <= maxSendGrade && gradeSendCount[i] > 0) {
          for (int j = 0; j < gradeSendCount[i]; j++) {
            histogram += "+ ";
            // Checks if the amount of +'s reached the grade frequency, add newline if so
            if (j + 1 == (gradeSendCount[i])) {
              histogram += "\n";
            }
          }
        } else {
          histogram += "\n";

        }
      }
    }

    return histogram;
  }

  /**
   * This helper method adds a valid grade to the first empty spot in the array, incrementing the
   * size the array. If there is no space or the grade is an invalid format, it returns the initial
   * starting size of the array. Returns either the new size of the array or the initial size of it
   * if a grade cannot be added.
   * 
   * @param array Array that the grade is to be added into.
   * @param num   Size of the array.
   * @param grade String of the grade.
   * @return New size of the array or initial size if grade cannot be added.
   */
  private static int getClimb(String[] array, int num, String grade) {
    boolean empty = false;

    // Checks if array is empty
    for (int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        empty = true;
      }
    }

    if (empty == false) {
      return num;
    }

    // Checks that grade is valid: contains 'V', is a number between 0-7, and is 2 characters long
    if (empty && grade.length() == 2 && grade.charAt(0) == 'V' && Character.isDigit(grade.charAt(1))
        && Integer.parseInt(grade.substring(1)) <= 7 && Integer.parseInt(grade.substring(1)) >= 0) {
      for (int i = 0; i < array.length; i++) {
        if (array[i] == null) {
          array[i] = grade;
          break;
        }
      }
      ++num;
    } else {
      return num;
    }

    return num;
  }


  /**
   * This helper method finds the highest climb grade in an array and returns it.
   * 
   * @param array Array that of grades that is to be searched.
   * @param num   Size of the array.
   * @return Highest grade in the array.
   */
  private static int getMaxGrade(String[] array, int num) {

    // Finds highest grade in array
    int maxGrade = Integer.parseInt(array[0].substring(1));
    for (int i = 1; i < num; i++) {
      if (Integer.parseInt(array[i].substring(1)) > maxGrade) {
        maxGrade = Integer.parseInt(array[i].substring(1));
      }
    }

    return maxGrade;
  }


  /**
   * This helper method records the frequencies of a grade occurring into an int[] array, with the
   * highest index of this array being the highest grade found between the send/fail array. Returns
   * an int[] array with the frequencies.
   * 
   * @param array    Array that the frequencies of each grade will be recorded from.
   * @param num      Size of array.
   * @param maxGrade Size of the int[] array.
   * @return Array containing frequencies of a grade occurring.
   */
  private static int[] getGradeCount(String[] array, int num, int maxGrade) {
    int[] gradeCount = new int[maxGrade + 1];
    for (int i = 0; i < maxGrade + 1; i++) {
      int count = 0;
      for (int j = 0; j < num; j++) {
        if (i == Integer.parseInt(array[j].substring(1))) {
          count++;
        }
      }
      gradeCount[i] = count;
    }

    return gradeCount;
  }

  /**
   * If numSend == 0 or numFail == 0; this method is called and concatenates a string of either '+'
   * or '-' to be added to the histogram.
   * 
   * @param array    Array of the frequencies of a grade occurring in either send or fail array.
   * @param maxGrade Highest grade in send/fail array, depending on which array was empty.
   * @param sign     Symbol to be added into the histogram, represents amount of successful or
   *                 failed climbs.
   * @return String that represents a histogram of either successful climbs or faled climbs.
   */
  private static String getHisto(int[] array, int maxGrade, String sign) {
    String histogram = "";
    for (int i = 0; i <= maxGrade; i++) {
      histogram += "V" + i + ": ";
      // If the grade, i, is lower than the max grade and occurs more than once, add +/- to the
      // String
      if (i <= maxGrade && array[i] > 0) {
        for (int j = 0; j < array[i]; j++) {
          histogram += sign;
          // If enough +/-'s have been added and equal the frequency of the grade occurring, add
          // newline
          if (j + 1 == (array[i])) {
            histogram += "\n";
          }
        }
      } else {
        histogram += "\n";
      }
    }

    return histogram;
  }


}
