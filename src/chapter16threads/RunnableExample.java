package chapter16threads;

public class RunnableExample implements Runnable {
  public int count = 0;

  public void run() {
    System.out.println("RunnableExample starting");
    try {
      while (count < 5) {
        Thread.sleep(500);
        System.out.println(count++);
      }
    } catch (InterruptedException e) {
      System.out.println("RunnableExample Interrupted");
    }
    System.out.println("RunnableExample terminated");
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    RunnableExample runnableInstance = new RunnableExample();
    Thread thread = new Thread(runnableInstance);
    thread.start();
    while (runnableInstance.count != 5) {
      try {
        Thread.sleep(250);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}