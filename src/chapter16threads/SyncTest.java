package chapter16threads;

public class SyncTest {
  
  public static void test1(){
    MyObject obj1 = new MyObject();
    MyObject obj2 = new MyObject();
    MyThread thread1 = new MyThread(obj1,"1");
    MyThread thread2 = new MyThread(obj2,"2");
    thread1.start();
    thread2.start();
    MyObject obj3 = new MyObject();
    MyObject obj4 = new MyObject();
    MyThread thread3 = new MyThread(obj3,"3");
    MyThread thread4 = new MyThread(obj4,"4");
    thread3.start();
    thread4.start();
  }
  
  static void test2(){
    MyObject obj1 = new MyObject();
    MyThread thread1 = new MyThread(obj1,"1");
    MyThread thread2 = new MyThread(obj1,"2");
    MyThread thread3 = new MyThread(obj1,"3");
    thread1.start();
    thread2.start();
    thread3.start();
  
  }
  
  public static void main(String[] args) {
//    test1();
    test2();
  }
}
