package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
  public static void main(String[] args) {
    BlockingQueue<Meal> queue = new ArrayBlockingQueue<Meal>(5);
    ExecutorService executor = Executors.newCachedThreadPool();
    executor.execute(new WaitPerson(queue));
    executor.execute(new Chef(queue));
    executor.execute(new Chef(queue));
    try {
      TimeUnit.MILLISECONDS.sleep(10);
      executor.execute(new Chef(queue));
      executor.execute(new Chef(queue));
      executor.execute(new Chef(queue));
      executor.execute(new Chef(queue));
      executor.execute(new WaitPerson(queue));
      executor.execute(new WaitPerson(queue));
      executor.execute(new WaitPerson(queue));
      executor.execute(new WaitPerson(queue));
      executor.execute(new WaitPerson(queue));
      executor.execute(new WaitPerson(queue));
      TimeUnit.MILLISECONDS.sleep(10);
      executor.shutdownNow();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
