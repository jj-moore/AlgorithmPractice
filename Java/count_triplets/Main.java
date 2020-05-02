package count_triplets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Main {

  public static void main(String[] args) {
    List<Long> myList = Arrays.asList(
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L,
      1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L
    );
    long r = 1L;
    long answer = countTriplets(myList, r);
    System.out.println(answer);
  }

  public static long countTriplets(List<Long> arr, long r) {
    HashMap<Long, Long> singlets = new HashMap<>();
    HashMap<Long, Long> doublets = new HashMap<>();
    long triplets = 0L;
    long first = 0L;

    for (long number : arr) {

        // IF FIRST OF POTENTIAL TRIPLET IS IN DOUBLETS THEN
        // INCREMENT COUNT OF FIRST TO TRIPLETS
        boolean isPotentialEnd = ((number % (r * r)) == 0L);
        if (isPotentialEnd) {
          first = number / (r * r);
          if (doublets.containsKey(first)) {
              triplets += doublets.get(first);
          }
        }

        // IF FIRST OF POTENTIAL DOUBLET IS IN SINGLETS THEN
        // INCMENT COUNT OF FIRST TO DOUBLETS
        boolean isPotentialMiddle = ((number % r) == 0L);
        if (isPotentialMiddle) {
          first = number / r;
          if (singlets.containsKey(first)) {
            addOrIncrement(doublets, first, singlets.get(first));
          }
        }

        // ALWAYS TRACK COUNT OF EACH NUMBER
        addOrIncrement(singlets, number, 1L);
    }
    return triplets;
  }

  public static <T> void addOrIncrement(HashMap<T, Long> map, T key, long value) {
    if (map.containsKey(key)) {
      long newValue = map.get(key) + value;
      map.replace(key, newValue);
    }
    else {
      map.put(key, value);
    }
  }

}
