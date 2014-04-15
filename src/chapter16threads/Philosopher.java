package chapter16threads;

public class Philosopher extends Thread {
  private int bites = 1;
  private Chopstick left;
  private Chopstick right;
  private String name;

  public Philosopher(String name, Chopstick left, Chopstick right) {
    this.left = left;
    this.right = right;
    this.name = name;
  }

  public void eat() {
    if (pickUp()) {
      chew();
      putDown();
    }
  }

  public boolean pickUp() {
    if (left.pickUp() == false) {
      return false;
    }
    if (right.pickUp() == false) {
      left.putdown();
      return false;
    }
    return true;
  }

  public void chew() {
    System.out.println("Philosopher " + name + " is eating bites "+bites);
    try{
      Thread.sleep(500);
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  public void putDown() {
    left.putdown();
    right.putdown();
  }

  public void run(){
    while(bites<=5){
      eat();
      bites++;
      Thread.yield();
    }
  }
}
