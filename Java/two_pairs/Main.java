package two_pairs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Main {

public static void main(String[] args) {
  if (args.length < 2) {
    System.out.println("Please supply two words.");
  }
  else {
    boolean twoPairs = twoPairs(args[0], args[1]);
    System.out.println(twoPairs);
  }
}

public static string twoPairs(String s1, String s2) {
  boolean[] alphabet = new boolean[26];
  char[] s1Array = s1.toLowerCase().toCharArray();
  char[] s2Array = s2.toLowerCase().toCharArray();

  for (char c : s1Array) {
    int index = (int)c - 97;
    alphabet[index] = true;
  }

  for (char c :s2Array) {
    int index = (int)c - 97;
    if (alphabet[index]) {
      return "YES";
    }
  }

  return "NO";

}
