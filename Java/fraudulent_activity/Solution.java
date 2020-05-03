package fraudulent_activity;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

  public static void main(String[] args) {
    int[] expenditure = { 0, 82, 180, 55, 168, 41, 179, 59, 139, 71, 6, 12 };
    int d = 5;
    int solution = activityNotifications(expenditure, d);
    System.out.println("Notifications: " + solution);
  }

  // Complete the activityNotifications function below.
  static int activityNotifications(int[] expenditure, int d) {
    int medianIndex = d / 2;
    int notifications = 0;

    int currentExpenditure = 0;
    int expenditureLimit = 0;
    int runningCount = 0;

    int[] countArray = new int[201];
    for (int i = 0; i < d; i++) {
      countArray[expenditure[i]]++;
    }

    for (int start = 0; start < expenditure.length - d; start++) {
      expenditureLimit = 0;
      runningCount = 0;
      currentExpenditure = expenditure[start + d];

      for (int i = 0; i < countArray.length; i++) {
        runningCount += countArray[i];

        if (runningCount == medianIndex && (d % 2 == 0) && expenditureLimit == 0) {
          expenditureLimit = i;
        }

        if (runningCount > medianIndex) {
          if (expenditureLimit == 0) {
            expenditureLimit = i * 2;
          }
          else {
            expenditureLimit += i;
          }
          break;
        }
      }

      if (currentExpenditure >= expenditureLimit) {
        notifications++;
      }

      countArray[expenditure[start]]--;
      countArray[expenditure[start + d]]++;
    }
    return notifications;

  }
}
