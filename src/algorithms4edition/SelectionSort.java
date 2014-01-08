package algorithms4edition;

import java.util.Random;

public class SelectionSort {

  private static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void show(Comparable[] a) {
    System.out.println();
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }

  public static void selectionSort(Comparable[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      int min = i;
      for (int j = i; j < N; j++) {
        if (less(a[j], a[min]))
          min = j;
      }
      exch(a, i, min);
    }
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1]))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    String[] a = { "G", "K", "Z", "M", "E", "F" };
    show(a);
    selectionSort(a);
    show(a);
    Random rand = new Random(47);
    Integer[] ints = new Integer[rand.nextInt(50)];
    for (int i = 0; i < ints.length; i++) {
      ints[i] = rand.nextInt(50);
    }
    show(ints);
    selectionSort(ints);
    show(ints);

    // this assertion is for "assert test", use "-ea" options in java command to
    // active assertion
    assert (isSorted(ints) == false) : "should be false";
  }
}
