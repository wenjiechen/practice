package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class WaitPerson implements Runnable {
  private BlockingQueue<Meal> queue;
  private final int id;
  static private int count = 0;

  public WaitPerson(BlockingQueue<Meal> queue) {
    this.queue = queue;
    id = ++count;
  }

  public void run() {
    try {
      while (!Thread.interrupted()) {
        Meal m = queue.take();
        System.out.println("wait person " + id + " take meal" + m.orderNum
            + " from queue");
//        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      System.err.println("waitperson interrupted");
    }
  }
}
