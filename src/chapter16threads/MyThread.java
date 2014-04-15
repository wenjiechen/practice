package chapter16threads;

public class MyThread extends Thread {
  private String name;
  private MyObject myObj;

  public MyThread(MyObject obj, String n) {
    name = n;
    myObj = obj;
  }

  public void run(){
    myObj.foo(name);
  }
  
}
