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
      //printCrossword(myWords);

      FirstLetter[] firstLetters = getFirstLetters(crossword, myWords.length);
      //printFirstLetters(firstLetters);

      solve(crossword, firstLetters, myWords);
      return new String[] {};
    }

    static void solve(String[] crossword, FirstLetter[] firstLetters, String[] words) {
      if (words.length == 0) {
        printCrossword(crossword);
      }
      else {
        for (int i = 0; i < firstLetters.length; i++) {
          int failed = 0;
          for (int j = 0; j < words.length; j++) {
            if (!canMatch(crossword, firstLetters[i], words[j])) {
              failed++;
              if (failed == words.length) {
                return;
              } else {
                continue;
              }
            }

            String[] newCrossword = replaceWord(crossword, firstLetters[i], words[j]);
            String[] newWords = removeWordFromArray(words, words[j]);
            //printCrossword(newCrossword);
            //printCrossword(newWords);
            solve(newCrossword, firstLetters, newWords);
          }
        }
      }
    }

    static boolean canMatch(String[] crossword, FirstLetter firstLetter, String word) {
      if (word.length() != firstLetter.length) {
        return false;
      }

      if (firstLetter.isHorizontal) {
        for (int i = 0; i < word.length(); i++) {
          char current = crossword[firstLetter.row].charAt(firstLetter.col + i);
          if (current != '-' && current != word.charAt(i)) {
            return false;
          }
        }
        return true;
      }
      else {
        for (int i = 0; i < word.length(); i++) {
          char current = crossword[firstLetter.row + i].charAt(firstLetter.col);
          if (current != '-' && current != word.charAt(i)) {
            return false;
          }
        }
        return true;
      }
    }

    static FirstLetter[] getFirstLetters(String[] crossword, int wordCount) {
      FirstLetter[] firstLetters = new FirstLetter[wordCount];
      int index = 0;

      for (int row = 0; row < crossword.length; row++) {
        for (int col = 0; col < crossword[row].length(); col++) {

          int verticalWord = isFirstLetterVertical(crossword, row, col);
          int horizontalWord = isFirstLetterHorizontal(crossword, row, col);
          if (verticalWord > 0) {
            firstLetters[index] = new FirstLetter(row, col, false, verticalWord);
            index++;
          }
          if (horizontalWord > 0) {
            firstLetters[index] = new FirstLetter(row, col, true, horizontalWord);
            index++;
          }

        } // END COL
      } // END ROW
      return firstLetters;
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

    static void printCrossword(String[] crossword) {
      for (String row : crossword) {
        System.out.println(row);
      }
      System.out.println();
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

    static String[] replaceWord(String[] crossword, FirstLetter firstLetter, String word) {
      String[] newCrossword = new String[crossword.length];
      for (int i = 0; i < crossword.length; i++) {
        newCrossword[i] = crossword[i];
      }
      if (firstLetter.isHorizontal) {
        StringBuilder newRow = new StringBuilder(crossword[firstLetter.row]);
        newRow.replace(firstLetter.col, firstLetter.col + firstLetter.length, word);
        newCrossword[firstLetter.row] = newRow.toString();
      }
      else {
        for (int i = 0; i < firstLetter.length; i++) {
          newCrossword[firstLetter.row + i] = replaceChar(newCrossword[firstLetter.row + i], word.charAt(i), firstLetter.col);
        }
      }
      return newCrossword;
    }

    static String replaceChar(String str, char ch, int index) {
    	    StringBuilder myString = new StringBuilder(str);
    	    myString.setCharAt(index, ch);
    	    return myString.toString();
    	}

      static void printFirstLetters(FirstLetter[] firstLetters) {
        for (FirstLetter firstLetter : firstLetters) {
          System.out.println(
            "row: " + firstLetter.row +
            " col: " + firstLetter.col +
            " length: " + firstLetter.length +
            " isHorizontal: " + firstLetter.isHorizontal);
        }
      }
  }

  class FirstLetter {
    int row;
    int col;
    int length;
    boolean isHorizontal;
    boolean filled;

    FirstLetter(int row, int col, boolean isHorizontal, int length) {
      this.row = row;
      this.col = col;
      this.length = length;
      this.isHorizontal = isHorizontal;
      this.filled = false;
    }
  }
