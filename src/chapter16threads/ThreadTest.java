package chapter16threads;

public class ThreadTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    ThreadExample instance = new ThreadExample();
    instance.start();

    while (instance.count != 5) {
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
