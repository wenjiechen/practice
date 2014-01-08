package algorithms4edition;

import java.util.Random;

public class InsertionSort {

  private static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void show(Comparable[] a) {

    for (Comparable i : a) {
      System.out.print(i + " ");
    }
    System.out.println();
    System.out.println();
  }

  private static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i].compareTo(a[i - 1]) < 0)
        return false;
    }
    return true;
  }

  public static void sort(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = i; j > 0; j--) {
        if (less(a[j], a[j - 1]))
          exch(a, j, j - 1);
      }
    }
  }

  public static void main(String[] args) {
    Random rand = new Random(47);
    Integer[] test = new Integer[rand.nextInt(50)];
    for (int i = 0; i < test.length; i++) {
      test[i] = rand.nextInt(50);
    }
    show(test);
    sort(test);
    show(test);
    System.out.println(isSorted(test));

  }
}
