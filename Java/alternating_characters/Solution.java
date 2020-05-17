package alternating_characters;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

  public static void main(String[] args) {
    String input = "AABAAB";
    int solution = alternatingCharacters(input);
    System.out.println(solution);
  }

  static int alternatingCharacters(String s) {
    char[] arr = s.toCharArray();
    int solution = IntStream.range(1, arr.length)
      .reduce(0, (subtotal, current) -> {
        int incrementIfMatch = arr[current] == arr[current - 1] ? 1 : 0;
        return subtotal + incrementIfMatch;
      });
    return solution;
  }
}
