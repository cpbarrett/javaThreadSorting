import java.util.Scanner;

public class SingleThread {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("For Data Size N, Enter a power of 2: ");
    int input = scan.nextInt();
    scan.close();
    input = (int) Math.pow(2, input);
    Stopwatch timer = new Stopwatch();

    BarrettNonUniformThreads insertion = new BarrettNonUniformThreads(input, "insertion");
    BarrettNonUniformThreads selection = new BarrettNonUniformThreads(input, "selection");
    BarrettNonUniformThreads shell = new BarrettNonUniformThreads(input, "shell");

    insertion.insertionSortTests();
    selection.selectionSortTests();
    shell.shellSortTests();

    System.out.println("Total Runtime: " + timer.elapsedTime() + " seconds.");

  }
}