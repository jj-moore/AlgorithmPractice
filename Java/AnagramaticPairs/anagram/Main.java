package anagram;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Main {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please supply a word.");
    }
    else {
      int anagrams = countAnagrams(args[0]);
      System.out.println(anagrams);
    }
  } // END MAIN

  public static int countAnagrams(String word) {
    int anagrams = 0;
    int wordlength = word.length();

    for (int anagramLength = 1; anagramLength < wordlength; anagramLength++) {

      for (int start = 0; start < wordlength - anagramLength; start++) {
        String target = word.substring(start, start + anagramLength);

        for (int test = start + 1; test <= wordlength - anagramLength; test++) {
          String testString = word.substring(test, test + anagramLength);
          boolean isAnagram = isAnagram(target, testString);
          if (isAnagram) {
            anagrams++;
          }
        }
      }
    }
    return anagrams;
  }

  public static boolean isAnagram(String word1, String word2) {
    if (word1.length() != word2.length()) {
      return false;
    }

    Hashtable<Character, Integer> hashtable = new Hashtable<>();
    Integer count = 0;

    for (Character c : word1.toCharArray()) {
      if (hashtable.containsKey(c)) {
        count = hashtable.get(c);
        hashtable.replace(c, count + 1);
      }
      else {
        hashtable.put(c, 1);
      }
    }

    for (Character c : word2.toCharArray()) {
      if (hashtable.containsKey(c)) {
        count = hashtable.get(c);
        if (count == 0 || count == null) {
          return false;
        }
        else {
          hashtable.replace(c, count - 1);
        }
      }
      else {
        return false;
      }
    }

    return true;
  }

} // END CLASS
