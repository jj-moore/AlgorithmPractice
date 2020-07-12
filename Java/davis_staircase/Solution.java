/*
Count possible number of permutations when climbing steps of length n with
possible steps of 1, 2, or 3
recursion/dynamic programming
*/

package davis_staircase;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
      int solution = stepPermsBottomUp(5);
      System.out.println(solution);
    }

    // Complete the stepPerms function below.
    static int stepPerms(int n) {
      if (n == 0 || n == 1) {
        return 1;
      }
      if (n == 2) {
        return 2;
      }

      int k = stepPerms(n - 1);
      int l = stepPerms(n - 2);
      int m = stepPerms(n - 3);
      return (k + l + m);
    }

    // Complete the stepPerms function below.
    static int stepPermsBottomUp(int n) {
      int[] cache = {1, 1, 2};
      for(int i = 3; i <= n; i++) {
        cache[i % 3] = cache[0] + cache[1] + cache[2];
      }
      return cache[n % 3];
    }
  }
