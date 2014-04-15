package chapter16threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosopher {
  public static void main(String[] args) {
    Chopstick chopstick1 = new Chopstick(1);
    Chopstick chopstick2 = new Chopstick(2);
    Chopstick chopstick3 = new Chopstick(3);
    Chopstick chopstick4 = new Chopstick(4);
    Chopstick chopstick5 = new Chopstick(5);

    Philosopher ph1 = new Philosopher("a", chopstick1, chopstick2);
    Philosopher ph2 = new Philosopher("b", chopstick2, chopstick3);
    Philosopher ph3 = new Philosopher("c", chopstick3, chopstick4);
    Philosopher ph4 = new Philosopher("d", chopstick4, chopstick5);
    Philosopher ph5 = new Philosopher("e", chopstick5, chopstick1);
    Philosopher[] phs = { ph1, ph2, ph3, ph4, ph5 };
    // ph3.start();
    // ph5.start();
    // ph1.start();
    // ph4.start();
    // ph2.start();

    ExecutorService exec = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 5; i++) {
      exec.execute(phs[i]);
    }
    exec.shutdown();

  }
}
