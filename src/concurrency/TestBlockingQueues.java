package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class LiftOffRunner implements Runnable {
  private BlockingQueue<LiftOff> rockets;

  public LiftOffRunner(BlockingQueue<LiftOff> queue) {
    rockets = queue;
  }

  public void add(LiftOff lo) {
    try {
      rockets.put(lo); // Inserts the specified element into this queue, waiting
                       // if necessary for space to become available.
    } catch (InterruptedException e) {
      System.out.println("Interrupted during put()");
    }
  }

  public void run() {
    try {
      while (!Thread.interrupted()) {
        // Retrieves and removes the head of this queue, waiting if necessary
        // until an element becomes available.
        LiftOff rocket = rockets.take();
        rocket.run();
      }
    } catch (InterruptedException e) {
      System.out.println("waking from take()");
    }
    System.out.println("Exiting LiftOffRunner");
  }
}

public class TestBlockingQueues {
  static void test(BlockingQueue<LiftOff> queue) {
    LiftOffRunner runner = new LiftOffRunner(queue);
    Thread t = new Thread(runner);
    t.start();
    for (int i = 0; i < 5; i++) {
      runner.add(new LiftOff(5));
    }
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t.interrupt();
  }

  public static void main(String[] args) {
    // test(new LinkedBlockingQueue<LiftOff>());
    test(new ArrayBlockingQueue<LiftOff>(3));

  }
}
