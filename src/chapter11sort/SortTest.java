package chapter11sort;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
  private static void println(Object x) {
    System.out.println(x);
  }

  private static void print(Object x) {
    System.out.print(x);
  }

  private static boolean isSorted(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[i-1])
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Random rand = new Random(47);
    int lengthB = rand.nextInt(20);
    int lengthA = rand.nextInt(15);
    int[] a = new int[lengthA];
    int[] b = new int[lengthB];
    for (int i = 0; i < lengthA; i++) {
      a[i] = rand.nextInt(50);
    }
    Arrays.sort(a);
    println("array a");
    println(Arrays.toString(a));
    for (int i = 0; i < lengthB; i++) {
      b[i] = rand.nextInt(100);
    }
    Arrays.sort(b);
    println("array b");
    println(Arrays.toString(b));
    int[] c = new int[lengthA + lengthB];
    for (int i = 0; i < lengthA; i++) {
      c[i] = a[i];
    }
    a = c;
    Sort.mergeAB(a, b, lengthA, lengthB);
    println("merge ab");
    println(Arrays.toString(a));
    println(isSorted(a));
  }
}
