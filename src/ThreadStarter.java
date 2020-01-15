import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadStarter {
  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
    Scanner scan = new Scanner(System.in);

    System.out.println("For Data Size N, Enter a power of 2: ");
    int input = scan.nextInt();
    scan.close();
    input = (int) Math.pow(2, input);
    Stopwatch timer = new Stopwatch();

    BarrettNonUniformThreads insertion = new BarrettNonUniformThreads(input, "insertion");
    BarrettNonUniformThreads selection = new BarrettNonUniformThreads(input, "selection");
    BarrettNonUniformThreads shell = new BarrettNonUniformThreads(input, "shell");

    ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(3);

    eventPool.execute(insertion);
    eventPool.execute(selection);
    eventPool.execute(shell);

    // System.out.println("Number of threads: " + Thread.activeCount());
    // Thread[] listOfThreads = new Thread[Thread.activeCount()];

    // Thread.enumerate(listOfThreads);

    // for(Thread i : listOfThreads){
    //System.out.println(i.getName());
    //System.out.println(i.getPriority());
    // }

    eventPool.shutdown();

    while (!eventPool.isTerminated()){

    }

    System.out.println("Total Time: " + timer.elapsedTime());

  }
}