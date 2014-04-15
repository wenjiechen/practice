package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Chef implements Runnable {
  private BlockingQueue<Meal> queue;
  private static int count = 0;
  final int id;

  public Chef(BlockingQueue<Meal> queue) {
    this.queue = queue;
    id = ++count;
  }

  public void run() {
    try {
      while (!Thread.interrupted()) {
        Meal m = new Meal();
        queue.put(m);
        System.out.println("chef " + id + " make meal " + m.orderNum);
//        TimeUnit.MILLISECONDS.sleep(300);
      }
    } catch (InterruptedException e) {
      System.err.println("Chef interruped");
    }
  }
}
