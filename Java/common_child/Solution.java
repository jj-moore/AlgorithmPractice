// HELP FROM https://www.youtube.com/watch?v=ASoaQq66foQ

package common_child;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // FOR TOP-DOWN (MEMOIZATION) APPROACH
    public static int[][] staticCache;

    public static void main(String args[]) {
      String s1 = "HANKEOAM"; // akaokamamkak
      String s2 = "AKALIEML"; // aokaokmakkakakok
      // akao

      // FOR TOP-DOWN (MEMOIZATION) APPROACH
      // int stringSize = s1.length();
      // cache = new int[stringSize][stringSize];
      // for (int i = 0; i < stringSize; i++) {
      //   Arrays.fill(cache[i], -1);
      // }
      // int solution = commonChildTopDown(s1, s2, s1.length(), s2.length());

      // FOR BOTTOM-UP APPROACH
      int solution = commonChildBottomUp(s1, s2);

      System.out.println(solution);
    }

    // Complete the commonChild function below.
    static int commonChildTopDown(String s1, String s2, int m, int n) {
      if (m == 0 || n == 0) {
        return 0;
      }
      if (staticCache[m - 1][n - 1] != -1) {
        return staticCache[m - 1][n - 1];
      }

      int result;

      if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
        result = 1 + commonChildTopDown(s1, s2, m - 1, n - 1);
      }
      else {
        int temp1 = commonChildTopDown(s1, s2, m - 1, n);
        int temp2 = commonChildTopDown(s1, s2, m, n - 1);
        result = Math.max(temp1, temp2);
      }
      staticCache[m - 1][n - 1] = result;
      return result;
    }

    // Complete the commonChild function below.
    static int commonChildBottomUp(String s1, String s2) {
      int m = s1.length() + 1;
      int n = s2.length() + 1;
      int[][] cache = new int[m][n];

      for (int row = 1; row < m; row++) {
        for (int col = 1; col < n; col++) {
          if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
              cache[row][col] = 1 + cache[row - 1][col - 1];
          }
          else {
              cache[row][col] = Math.max(
                cache[row - 1][col],
                cache[row][col - 1]);
          }
        }
      }
      return cache[m - 1][n - 1];
    }
}
