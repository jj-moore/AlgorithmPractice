package freq_query;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {

  public static void main(String[] args) {
    List<List<Integer>> queries = new ArrayList<>();

    queries.add(Arrays.asList(1, 5));
    queries.add(Arrays.asList(1, 6));
    queries.add(Arrays.asList(3, 2));
    queries.add(Arrays.asList(1, 10));
    queries.add(Arrays.asList(1, 10));
    queries.add(Arrays.asList(1, 6));
    queries.add(Arrays.asList(2, 5));
    queries.add(Arrays.asList(3, 2));

    List<Integer> solution = solution(queries);
    System.out.println(solution.toString());
  } // END MAIN

  public static List<Integer> solution(List<List<Integer>> queries) {
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> frequencies = new HashMap<>();
    List<Integer> solution = new ArrayList<>();
    int command, data, exists, count;

    for (List<Integer> query : queries) {
      command = query.get(0);
      data = query.get(1);
      switch (command) {
        case 1:
          removeOrDecrement(frequencies, map.get(data), 1);
          count = addOrIncrement(map, data, 1);
          addOrIncrement(frequencies, count, 1);
          break;
        case 2:
          removeOrDecrement(frequencies, map.get(data), 1);
          count = removeOrDecrement(map, data, 1);
          addOrIncrement(frequencies, count, 1);
          break;
        case 3:
          exists = frequencies.containsKey(data) ? 1 : 0;
          solution.add(exists);
          break;
      }
    }
    return solution;
  } // END SOLUTION


  static <K> int addOrIncrement(HashMap<K, Integer> map, K key, int value) {
    int newValue;
    if (map.containsKey(key)) {
      newValue = map.get(key) + value;
      map.replace(key, newValue);
    }
    else {
      newValue = value;
      map.put(key, newValue);
    }
    return newValue;
  }

  static <K> int removeOrDecrement(HashMap<K, Integer> map, K key, int value) {
    int newValue = 0;
    if (map.containsKey(key)) {
      newValue = map.get(key) - value;
      if (newValue == 0) {
        map.remove(key);
      }
      else {
        map.replace(key, newValue);
      }
    }
    return newValue;
  }

} // END CLASS
