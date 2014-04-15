package chapter16threads;

import java.util.concurrent.Semaphore;

public class SemaphoreExample implements Runnable{
  public Semaphore sem1;
  public Semaphore sem2;
  public Semaphore sem3;

  public SemaphoreExample() {
    sem1 = new Semaphore(1);
    sem2 = new Semaphore(1);
    // sem3 = new Semaphore(1);
    try {
      sem1.acquire();
      sem2.acquire();
      // sem3.acquire();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void first() {
    System.out.println("enter first");
    sem1.release();
  }

  public void second() {
    System.out.println("enter second");
    try {
      sem1.acquire();
      sem1.release();
      System.out.println("run second");
      sem2.release();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void third(){
    try{
      System.out.println("enter third");
      sem2.acquire();
      sem2.release();
      System.out.println("run third");
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void run(){
    
  }

  public static void main(String[] args){

  }
}
