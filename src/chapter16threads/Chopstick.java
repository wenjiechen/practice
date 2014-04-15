package chapter16threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
  private Lock lock;
  private int num;
  
  public Chopstick(int num){
    lock = new ReentrantLock();
    this.num = num;
  }
  
  public boolean pickUp(){
     System.out.println("try pickup chopstick " + num);
    return lock.tryLock();
  }
  
  public void putdown(){
//     System.out.println("putdown chopstick " + num);
    lock.unlock();
  }
}
