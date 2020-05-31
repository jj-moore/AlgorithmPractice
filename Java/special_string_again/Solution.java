/*
Count the number of substrings where:
1. All of the characters are the same, e.g. aaa
2. All characters except the middle one are the same, e.g. aadaa.
*/

package special_string_again;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
      long count = substrCount(12, "aaaabaaaabab"); // 30
      System.out.println(count);
    }

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
      char[] arr = s.toCharArray();
      char previous = 0;
      long specialStringCount = 0L;
      int seriesLength = 0;
      int offset = 1;

      for (int i = 0; i < n; i++) {

        // SUBSTRINGS OF A SINGLE CHARACTER
        seriesLength = (arr[i] == previous) ? seriesLength + 1 : 1;
        specialStringCount += seriesLength;

        // SUBSTRINGS WITH SAME CHARACTER EXCEPT MIDDLE CHARACTER
        // WALKS OUTWORD FROM THE MIDDLE CHARACTER IN BOTH DIRECTIONS
        while (i - offset >= 0 &&
               i + offset < n &&
               arr[i - offset] == previous &&
               arr[i - offset] == arr[i + offset] &&
               arr[i] != arr[i - offset]) {
        	specialStringCount++;
        	offset++;
        }

        // RESET TEMPORARY VARIABLES
        offset = 1;
        previous = arr[i];
      }

      return specialStringCount;
    }

  }
