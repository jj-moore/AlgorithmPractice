package sherlocks_string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    String solution = isValid("aaaa");
    System.out.println(solution);
  }

  static String isValid(String s) {

    int[] occurences = new int[26];
    for (char c : s.toCharArray()) {
      occurences[c - 97]++;
    }

    occurences = Arrays.stream(occurences)
      .filter(i -> i > 0)
      .sorted()
      .toArray();

    int minimum = occurences[0];
    int maximum = occurences[occurences.length - 1];
    if (minimum == maximum) {
      return "YES";
    }

    int priorToMax = occurences[occurences.length - 2];

    // All frequencies are equal except last is one more than others
    if (minimum == priorToMax && (priorToMax == maximum || priorToMax == maximum - 1)) {
      return "YES";
    }

    // All frequencies are equal except first which equals 1
    if (minimum == 1 && occurences[1] > 1 && occurences[1] == maximum) {
      return "YES";
    }

    return "NO";

    // int[] freqOfOccurs = Arrays.stream(occurences)
    //   .filter(i -> i > 0)
    //   .distinct()
    //   .sorted()
    //   .toArray();
    //
    // if (freqOfOccurs.length > 2) {
    //   return "NO";
    // }
    // if (freqOfOccurs.length == 1) {
    //   return "YES";
    // }
    //
    // if (freqOfOccurs[0] == 1) {
    //   long countOfSingles = Arrays.stream(occurences)
    //     .filter(i -> i == 1)
    //     .count();
    //
    //   if (countOfSingles == 1) {
    //     return "YES";
    //   }
    // }
    //
    // if (freqOfOccurs[1] != freqOfOccurs[0] + 1) {
    //   return "NO";
    // }
    //
    // long countOfOutlier = Arrays.stream(occurences)
    //   .filter(i -> i == freqOfOccurs[1])
    //   .count();

    // return countOfOutlier == 1 ? "YES" : "NO";
  }




  static void printArray(int[] arr) {
    for (int i : arr) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

}
