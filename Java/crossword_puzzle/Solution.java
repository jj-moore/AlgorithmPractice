/* A Crossword grid is provided to you, along with a set of words
(or names of places) which need to be filled into the grid. Cells are marked
either + or -. Cells marked with a - are to be filled with the word list.
recursion/dynamic programming
*/
package crossword_puzzle;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
      String[] crossword = new String[] {
        "+-++++++++",
        "+-++++++++",
        "+-++++++++",
        "+-----++++",
        "+-+++-++++",
        "+-+++-++++",
        "+++++-++++",
        "++------++",
        "+++++-++++",
        "+++++-++++"
      };
      String words = "ANKARA;LONDON;DELHI;ICELAND";

      String[] crossword2 = new String[] {
        "++++++++++",
        "+------+++",
        "+++-++++++",
        "+++-++++++",
        "+++-----++",
        "+++-++-+++",
        "++++++-+++",
        "++++++-+++",
        "++++++-+++",
        "++++++++++"
      };
      String words2 = "POLAND;INDIA;LHASA;SPAIN";

      String[] crossword3 = new String[] {
        "XXXXXX-XXX",
        "XX------XX",
        "XXXXXX-XXX",
        "XXXXXX-XXX",
        "XXX------X",
        "XXXXXX-X-X",
        "XXXXXX-X-X",
        "XXXXXXXX-X",
        "XXXXXXXX-X",
        "XXXXXXXX-X"
      };
      String words3 = "ICELAND;MEXICO;PANAMA;ALMATY";
      crosswordPuzzle(crossword, words);
    }
    // Complete the crosswordPuzzle function below.
    static void crosswordPuzzle(String[] crossword, String words) {
      String[] myWords = words.split(";");
      CrosswordField[] fields = getCrosswordFields(crossword, myWords.length);

      boolean hasSolution = isSolution(crossword, fields, myWords);
      if (hasSolution) {
        printStringArray(crossword);
      }
      else {
        System.out.println("No solution");
      }
    }

    static boolean isSolution(String[] crossword, CrosswordField[] fields, String[] words) {
      // FIND FIRST EMPTY FIELD AND MARK IT
      CrosswordField nextField = null;
      for (CrosswordField field : fields) {
        if (!field.marked) {
          nextField = field;
          break;
        }
      }
      // IF NO EMPTY FIELDS, WE'RE DONE
      if (nextField == null) {
        return true;
      }
      // FIND WORD THAT CAN FIT
      for (String word : words) {
        boolean willFit = willFit(crossword, nextField, word);
        if (willFit) {
          nextField.marked = true;
          placeWord(crossword, nextField, word);
          printStringArray(crossword);
          // CHECK IF THIS PATH WILL LEAD TO A SOLUTION
          boolean isSolution = isSolution(crossword, fields, words);
          if (isSolution) {
            return true;
          }
          else {
            // BACKTRACK BY UNDOING THE WORD PLACEMENT AND TRYING THE NEXT WORD
            placeWord(crossword, nextField, word.replaceAll("[A-Z]","-"));
          }
        }
      }
      // IF NO WORDS LEAD TO A SOLUTION, NO SOLUTION
      return false;
    }

    static boolean willFit(String[] crossword, CrosswordField field, String word) {
      if (word.length() != field.length) {
        return false;
      }

      int row = field.row;;
      int col = field.col;
      for (char char1 : word.toCharArray()) {
        char char2 = crossword[row].charAt(col);
        if (char2 != '-' && char2 != char1) {
          return false;
        }

        if (field.isHorizontal) {
          col++;
        }
        else {
          row++;
        }

      }
      return true;
    }

    static CrosswordField[] getCrosswordFields(String[] crossword, int wordCount) {
      CrosswordField[] fields = new CrosswordField[wordCount];
      int index = 0;

      for (int row = 0; row < crossword.length; row++) {
        for (int col = 0; col < crossword[row].length(); col++) {

          int verticalWord = isFirstLetterVertical(crossword, row, col);
          int horizontalWord = isFirstLetterHorizontal(crossword, row, col);
          if (verticalWord > 0) {
            fields[index] = new CrosswordField(row, col, false, verticalWord);
            index++;
          }
          if (horizontalWord > 0) {
            fields[index] = new CrosswordField(row, col, true, horizontalWord);
            index++;
          }

        } // END COL
      } // END ROW
      return fields;
    }

    static int isFirstLetterVertical(String[] crossword, int row, int col) {
      if (row >= crossword.length - 1) {
        return -1;
      }
      if (crossword[row].charAt(col) != '-' || crossword[row + 1].charAt(col) != '-') {
        return -1;
      }
      if (row == 0 || crossword[row - 1].charAt(col) == '+' || crossword[row - 1].charAt(col) == 'X') {
        int length = 0;
        while (row < crossword.length && crossword[row].charAt(col) == '-') {
          length++;
          row++;
        }
        return length;
      }
      return -1;
    }

    static int isFirstLetterHorizontal(String[] crossword, int row, int col) {
      if (col >= crossword[row].length() - 1) {
        return -1;
      }
      if (crossword[row].charAt(col) != '-' || crossword[row].charAt(col + 1) != '-') {
        return -1;
      }
      if (col == 0 || crossword[row].charAt(col - 1) == '+' || crossword[row].charAt(col - 1) == 'X') {
        int length = 0;
        while (col < crossword[row].length() && crossword[row].charAt(col) == '-') {
          length++;
          col++;
        }
        return length;
      }
      return -1;
    }

    static String[] placeWord(String[] crossword, CrosswordField field, String word) {
      if (field.isHorizontal) {
        StringBuilder newRow = new StringBuilder(crossword[field.row]);
        newRow.replace(field.col, field.col + field.length, word);
        crossword[field.row] = newRow.toString();
      }
      else {
        for (int i = 0; i < field.length; i++) {
          crossword[field.row + i] = replaceChar(crossword[field.row + i], word.charAt(i), field.col);
        }
      }
      return crossword;
    }

    static String replaceChar(String str, char ch, int index) {
    	    StringBuilder myString = new StringBuilder(str);
    	    myString.setCharAt(index, ch);
    	    return myString.toString();
    	}

      static void printStringArray(String[] crossword) {
        for (String row : crossword) {
          System.out.println(row);
        }
        System.out.println();
      }
  }

  class CrosswordField {
    int row;
    int col;
    int length;
    boolean isHorizontal;
    boolean marked;

    CrosswordField(int row, int col, boolean isHorizontal, int length) {
      this.row = row;
      this.col = col;
      this.length = length;
      this.isHorizontal = isHorizontal;
      this.marked = false;
    }
  }
