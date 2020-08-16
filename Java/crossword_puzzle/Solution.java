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
      String words2 = "POLAND;LHASA;SPAIN;INDIA";

      crosswordPuzzle(crossword2, words2);
    }
    // Complete the crosswordPuzzle function below.
    static String[] crosswordPuzzle(String[] crossword, String words) {
      String[] myWords = words.split(";");
      CrosswordField[] fields = getCrosswordFields(crossword, myWords.length);
      String[] solution = solve(crossword, fields, myWords);
      printStringArray(solution);
      return solution;
    }

    static String[] solve(String[] crossword, CrosswordField[] fields, String[] words) {
      if (words.length == 0) {
        return crossword;
      }
      else if (crossword == null) {
        return crossword;
      }
      else {
        for (int i = 0; i < fields.length; i++) {
          int failed = 0;
          for (int j = 0; j < words.length; j++) {
            if (!canMatch(crossword, fields[i], words[j])) {
              failed++;
              if (failed == words.length) {
                return crossword;
              } else {
                continue;
              }
            }

            String[] newCrossword = replaceWord(crossword, fields[i], words[j]);
            String[] newWords = removeWordFromArray(words, words[j]);
            return solve(newCrossword, fields, newWords);
          }
        }
      }
      return crossword;
    }

    static boolean canMatch(String[] crossword, CrosswordField field, String word) {
      if (word.length() != field.length) {
        return false;
      }

      if (field.isHorizontal) {
        for (int i = 0; i < word.length(); i++) {
          char current = crossword[field.row].charAt(field.col + i);
          if (current != '-' && current != word.charAt(i)) {
            return false;
          }
        }
        return true;
      }
      else {
        for (int i = 0; i < word.length(); i++) {
          char current = crossword[field.row + i].charAt(field.col);
          if (current != '-' && current != word.charAt(i)) {
            return false;
          }
        }
        return true;
      }
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
      if (row == 0 || crossword[row - 1].charAt(col) == '+') {
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
      if (col == 0 || crossword[row].charAt(col - 1) == '+') {
        int length = 0;
        while (col < crossword[row].length() && crossword[row].charAt(col) == '-') {
          length++;
          col++;
        }
        return length;
      }
      return -1;
    }

    static int getPositionLength(String[] crossword, int row, int col, int direction) {
      int length = 0;
      switch (direction) {
        case -1:
          return 0;
        case 0:
          while (col < crossword[row].length() && crossword[row].charAt(col) != '+') {
            length++;
            col++;
          }
          return length;
        case 1:
          while (row < crossword.length && crossword[row].charAt(col) != '+') {
            length++;
            row++;
          }
          return length;
        default:
          return 0;
      }
    }

    static String[] removeWordFromArray(String[] arr, String word) {
      String[] newArr = new String[arr.length - 1];
      int newIndex = 0;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i].equals(word) || newIndex >= newArr.length) {
          continue;
        }
        newArr[newIndex] = arr[i];
        newIndex++;
      }
      return newArr;
    }

    static String[] replaceWord(String[] crossword, CrosswordField field, String word) {
      String[] newCrossword = new String[crossword.length];
      for (int i = 0; i < crossword.length; i++) {
        newCrossword[i] = crossword[i];
      }
      if (field.isHorizontal) {
        StringBuilder newRow = new StringBuilder(crossword[field.row]);
        newRow.replace(field.col, field.col + field.length, word);
        newCrossword[field.row] = newRow.toString();
      }
      else {
        for (int i = 0; i < field.length; i++) {
          newCrossword[field.row + i] = replaceChar(newCrossword[field.row + i], word.charAt(i), field.col);
        }
      }
      return newCrossword;
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
    boolean filled;

    CrosswordField(int row, int col, boolean isHorizontal, int length) {
      this.row = row;
      this.col = col;
      this.length = length;
      this.isHorizontal = isHorizontal;
      this.filled = false;
    }
  }
