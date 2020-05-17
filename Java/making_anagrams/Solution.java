package making_anagrams;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    String a = "cde";
    String b = "abc";
    int solution = makeAnagram(a, b);
    System.out.println(solution);
  }

  static int makeAnagram(String a, String b) {
    int[] alphabet = new int[26];
    a.chars().forEach(i -> alphabet[i - 'a']++);
    b.chars().forEach(i -> alphabet[i - 'a']--);
    int removedChars = Arrays.stream(alphabet)
      .map(i -> Math.abs(i))
      .sum();
    return removedChars;
  }
}
