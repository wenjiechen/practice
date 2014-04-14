package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class LiftOffRunner implements Runnable {
  private BlockingQueue<LiftOff> rockets;

  public LiftOffRunner(BlockingQueue<LiftOff> queue) {
    rockets = queue;
  }

  public void add(LiftOff lo) {
    try {
      rockets.put(lo);
    } catch (InterruptedException e) {
      System.out.println("Interrupted during put()");
    }
  }

  public void run() {
    try {
      while (!Thread.interrupted()) {
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
  static void test(BlockingQueue<LiftOff> queue) throws InterruptedException {
    LiftOffRunner runner = new LiftOffRunner(queue);
    Thread t = new Thread(runner);
    t.start();
    for (int i = 0; i < 10; i++) {
      runner.add(new LiftOff(5));
//      TimeUnit.MILLISECONDS.sleep(200);
    }
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t.interrupt();
  }

  public static void main(String[] args) throws InterruptedException {
    test(new LinkedBlockingQueue<LiftOff>());
  }
}


