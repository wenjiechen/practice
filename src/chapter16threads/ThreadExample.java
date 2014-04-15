package chapter16threads;

public class ThreadExample extends Thread {

  public int count = 0;

  @Override
  public void run() {
    System.out.println("threadexample starting");
    while (count < 5) {
      try {
        Thread.sleep(500);
        System.out.println(count++);

      } catch (InterruptedException e) {
        System.out.println("threadExample is interrupted");
      }
    }
    System.out.println("threadexample terminate");
  }

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
