package chapter4TreeGraph;

import java.util.concurrent.TimeUnit;

public class TestSleep {
  
  
  
  
  public static void main(String[] args) throws InterruptedException{
    int i = 0;
    while(i< 10){
      System.out.println(i);
      i++;
//      TimeUnit.MICROSECONDS.sleep(1000);
      Thread.sleep(1000);
    }
  }
}
