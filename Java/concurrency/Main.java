package concurrency;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Main {

  public static void main(String[] args) {
    example9();
  }

  static void example1() {
    Runnable task = () -> {
      String threadName = Thread.currentThread().getName();
      System.out.println("Hello " + threadName);
    };

    task.run();

    Thread thread = new Thread(task);
    thread.start();

    System.out.println("Done!");
  }

  static void example2() {
    Runnable runnable = () -> {
      try {
          String name = Thread.currentThread().getName();
          System.out.println("Foo " + name);
          TimeUnit.SECONDS.sleep(1);
          System.out.println("Bar " + name);
      }
      catch (InterruptedException e) {
          e.printStackTrace();
      }
    };

    Thread thread = new Thread(runnable);
    thread.start();
  }

  static void example3() {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> {
      try {
        String threadName = Thread.currentThread().getName();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Hello " + threadName);
      }
      catch (InterruptedException e) {
          e.printStackTrace();
      }
    });

    shutdownExecutor(executor);
  }

  static void example4() {
    ExecutorService executor = Executors.newFixedThreadPool(1);
    Callable<Integer> task = () -> {
      try {
          TimeUnit.SECONDS.sleep(2);
          return 123;
      }
      catch (InterruptedException e) {
          throw new IllegalStateException("task interrupted", e);
      }
    };

    Future<Integer> future = executor.submit(task);
    System.out.println("future done? " + future.isDone());

    try {
      Integer result = future.get(1, TimeUnit.SECONDS);
      System.out.println("future done? " + future.isDone());
      System.out.println("result: " + result);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    catch (ExecutionException e) {
      e.printStackTrace();
    }
    catch (TimeoutException e) {
      e.printStackTrace();
    }

    shutdownExecutor(executor);
  }

  static void example5() {
    ExecutorService executor = Executors.newWorkStealingPool();

    List<Callable<String>> callables = Arrays.asList(
        () -> "task1",
        () -> "task2",
        () -> "task3"
    );
    try {
      executor.invokeAll(callables)
        .stream()
        .map(future -> {
          try {
              return future.get();
          }
          catch (Exception e) {
              throw new IllegalStateException(e);
          }
        })
      .forEach(System.out::println);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    shutdownExecutor(executor);
  }

  static void example6() {
    ExecutorService executor = Executors.newWorkStealingPool();
    List<Callable<String>> callables = Arrays.asList(
      callable("task1", 2),
      callable("task2", 1),
      callable("task3", 3)
    );
    try {
      String result = executor.invokeAny(callables);
      System.out.println(result);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    catch (ExecutionException e) {
      e.printStackTrace();
    }
    shutdownExecutor(executor);
  }

  static void example7() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
    ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

    try {
      TimeUnit.MILLISECONDS.sleep(1337);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
    System.out.printf("Remaining Delay: %sms%n", remainingDelay);

    shutdownExecutor(executor);
  }

  static void example8() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

    int initialDelay = 0;
    int period = 1;
    executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    try {
      TimeUnit.SECONDS.sleep(5);
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
    shutdownExecutor(executor);
  }

  static void example9() {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    Runnable task = () -> {
      try {
          TimeUnit.SECONDS.sleep(2);
          System.out.println("Scheduling: " + System.nanoTime());
      }
      catch (InterruptedException e) {
          System.err.println("task interrupted");
      }
    };

    executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    try {
      TimeUnit.SECONDS.sleep(10);
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
    shutdownExecutor(executor);
  }


  /****** UTILITY METHODS ******/

  static Callable<String> callable(String result, long sleepSeconds) {
      return () -> {
          TimeUnit.SECONDS.sleep(sleepSeconds);
          return result;
      };
  };

  static void shutdownExecutor(ExecutorService executor) {
    System.out.println("attempt to shutdown executor");
    try {
      executor.shutdown();
      executor.awaitTermination(5, TimeUnit.SECONDS);
    }
    catch (InterruptedException e) {
      System.err.println("tasks interrupted");
    }
    finally {
      if (!executor.isTerminated()) {
          System.err.println("cancel non-finished tasks");
      }
      executor.shutdownNow();
      System.out.println("shutdown finished");
    }
  }
}
