package count_inversions;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Solution {
  static long inversions = 0L;

  public static void main(String[] args) {
    //int[] arr = {1, 4, 7, 2, 8, 5};
    int[] arr = {7, 5, 3, 1};
    long inversionCount = countInversions(arr);
    System.out.println("Inversions: " + inversionCount);
  }

  // Complete the countInversions function below.
  static long countInversions(int[] arr) {
    inversions = 0L;
    mergeSort(arr);
    return inversions;
  }

  static int[] mergeSort(int[] arr) {
    if (arr.length <= 1) {
        return arr;
    }

    int mid = arr.length / 2;
    int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
    int[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
    int[] merged = new int[left.length + right.length];

    int leftPtr = 0;
    int rightPtr = 0;
    while (leftPtr < left.length && rightPtr < right.length) {
      if (left[leftPtr] <= right[rightPtr]) {
        merged[leftPtr + rightPtr] = left[leftPtr];
        leftPtr++;
      }
      else {
        merged[leftPtr + rightPtr] = right[rightPtr];
        rightPtr++;
        inversions += left.length - leftPtr;
      }
    }

    while (leftPtr < left.length) {
      merged[leftPtr + rightPtr] = left[leftPtr];
      leftPtr++;
    }

    while (rightPtr < right.length) {
      merged[leftPtr + rightPtr] = right[rightPtr];
      rightPtr++;
    }

    return merged;
  }

}
