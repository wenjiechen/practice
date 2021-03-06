package concurrency;

import java.util.concurrent.TimeUnit;

public class LiftOff implements Runnable {
  protected int countDown = 10;
  private static int taskCount = 0;
  private final int id = taskCount++;

  public LiftOff() {
  }

  public LiftOff(int countDown) {
    this.countDown = countDown;
  }

  public void run() {
    while (countDown-- > 0) {
      System.out.println(status());
      Thread.yield();
      try{
        TimeUnit.MILLISECONDS.sleep(200);
      }
      catch(InterruptedException e){
       System.err.println("leftoff interrupted");
      }
    }
  }

  public String status() {
    return id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "),";
  }
}
