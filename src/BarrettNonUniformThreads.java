//import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
//import java.util.Scanner;
/**
 * Class to benchmark the 3 sorting algorithms.
 * @author Christopher Parker Barrett
 * @version 6/13
 * 
 */

public class BarrettNonUniformThreads implements Runnable {
  private Integer[] dataSet1;
  private Integer[] dataSet2;
  private Integer[] dataSet3;

  private Integer[] extraDataSet1;
  private Integer[] extraDataSet2;
  private Integer[] extraDataSet3;

  private String test;
  //public ReentrantLock lock = new ReentrantLock();

  public BarrettNonUniformThreads(int input, String test) {

    //N
    this.dataSet1 = dataOne(input);
    this.dataSet2 = dataTwo(input);
    this.dataSet3 = dataThree(input);

    //2N
    input *= 2;
    this.extraDataSet1 = dataOne(input);
    this.extraDataSet2 = dataTwo(input);
    this.extraDataSet3 = dataThree(input);

    this.test = test;

  }

  //returns an array with half 0s and half 1s
  private Integer[] dataOne(int size) {
    Integer[] data = new Integer[size];

    for (int i = 0; i < size / 2; i++) {
      data[i] = 0;
    }
    for (int i = size - 1; i > size / 2 - 1; i--) {
      data[i] = 1;
    }

    shuffle(data);

    return data;
  }
  //returns an array with half 0s and half 1s

  private Integer[] dataTwo(int size) {
    Integer[] data = new Integer[size];

    for (int i = 0; i < size / 2; i++) {
      data[i] = 0;
    }
    for (int i = size - 1; i > size * 3 / 4 - 1; i--) {
      data[i] = 1;
    }
    for (int i = size * 3 / 4 - 1; i > size * 5 / 8 - 1; i--) {
      data[i] = 2;
    }
    for (int i = size * 5 / 8 - 1; i > size / 2 - 1; i--) {
      data[i] = 3;
    }

    shuffle(data);

    return data;
  }
  //returns an array with half 0s and half random integers

  private Integer[] dataThree(int size) {
    Integer[] data = new Integer[size];
    Random random = new Random();

    for (int i = 0; i < size / 2; i++) {
      data[i] = 0;
    }
    for (int i = size - 1; i > size / 2 - 1; i--) {
      data[i] = random.nextInt();
    }

    shuffle(data);

    return data;
  }

  //en.wikipedia.org/wiki/Fisher-Yates_shuffle
  private void shuffle(Integer[] data) {
    int index;
    int temp;
    Random random = new Random();
    for (int i = data.length - 1; i > 0; i--) {
      index = random.nextInt(i + 1);
      temp = data[index];
      data[index] = data[i];
      data[i] = temp;
    }
  }

  //returns the runtime of Selection Sort
  private long timedSelectionSort(Integer[] data) {
    long start = System.nanoTime();
    SelectionSort.sort(data);
    long end = System.nanoTime();

    long runTime = (end - start);
    //System.out.print("ET: " + runTime);

    return runTime;
  }

  //returns the runtime of Insertion Sort
  private long timedInsertionSort(Integer[] data) {
    long start = System.nanoTime();
    InsertionSort.sort(data);
    long end = System.nanoTime();

    long runTime = (end - start);
    //System.out.print("ET: " + runTime);

    return runTime;
  }

  //returns the runtime of Shell Sort
  private long timedShellSort(Integer[] data) {
    long start = System.nanoTime();
    ShellSort.sort(data);
    long end = System.nanoTime();

    long runTime = (end - start);
    //System.out.print("ET: " + runTime);

    return runTime;
  }

  //computes the result of the doubling formula
  private String doubling(long runTime1, long runTime2) {
    double b = 0;
    double elapsedTime = (runTime2 - runTime1) / Math.pow(10,9);

    b = Math.log(runTime2 / runTime1);

    return b + " ET: " + elapsedTime + "s";
  }

  //prints out a list of the current test data object
  private String toString(Integer[] data) {
    String current = "[";
    for (int i = 0; i < data.length - 1; i++) {
      current += data[i] + ", ";
    }
    current += data[data.length - 1] + "]";
    return current;
  }

  public void run() {
    //lock.lock();
    //System.out.println(test + " " + " Sort Tests:");

    if (this.test.contains("selection")) {
      this.selectionSortTests();
    } else if (this.test.contains("insertion")) {
      this.insertionSortTests();
    } else if (this.test.contains("shell")) {
      this.shellSortTests();
    }

    //lock.unlock();
  }

  public void selectionSortTests() {
    //timed sorts with final b value
    System.out.println(this.test + " " + "Data 1, b = " + doubling(timedSelectionSort(dataSet1), timedSelectionSort(extraDataSet1)));
    System.out.println(this.test + " " + "Data 2, b = " + doubling(timedSelectionSort(dataSet2), timedSelectionSort(extraDataSet2)));
    System.out.println(this.test + " " + "Data 3, b = " + doubling(timedSelectionSort(dataSet3), timedSelectionSort(extraDataSet3)));
  }

  public void insertionSortTests() {
    //timed sorts with final b value
    System.out.println(this.test + " " + "Data 1, b = " + doubling(timedInsertionSort(dataSet1), timedInsertionSort(extraDataSet1)));
    System.out.println(this.test + " " + "Data 2, b = " + doubling(timedInsertionSort(dataSet2), timedInsertionSort(extraDataSet2)));
    System.out.println(this.test + " " + "Data 3, b = " + doubling(timedInsertionSort(dataSet3), timedInsertionSort(extraDataSet3)));
  }

  public void shellSortTests() {
    //timed sorts with final b value
    System.out.println(this.test + " " + "Data 1, b = " + doubling(timedShellSort(dataSet1), timedShellSort(extraDataSet1)));
    System.out.println(this.test + " " + "Data 2, b = " + doubling(timedShellSort(dataSet2), timedShellSort(extraDataSet2)));
    System.out.println(this.test + " " + "Data 3, b = " + doubling(timedShellSort(dataSet3), timedShellSort(extraDataSet3)));
  }
}
