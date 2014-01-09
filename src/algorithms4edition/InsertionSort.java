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

  public static void insertionSort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      // insert a[i] into a[i-1],a[i-2]...
      for (int j = i; j > 0; j--) {
        if (less(a[j], a[j - 1]))
          exch(a, j, j - 1);
        else
          // because the left elements of i are in order
          break;
      }
    }
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

  public static void sortMethod(String sort, Comparable[] a) {
    switch (sort) {
    case "insertion":
      System.out.println("using insertion sort");
      insertionSort(a);
      break;
    case "selection":
      System.out.println("using selection sort");
      selectionSort(a);
      break;
    }
  }

  public static void main(String[] args) {
    Random rand = new Random(47);
    Integer[] test = new Integer[rand.nextInt(50)];
    for (int i = 0; i < test.length; i++) {
      test[i] = rand.nextInt(50);
    }
    show(test);
    sortMethod("insertion",test);
    show(test);
    System.out.println(isSorted(test));

  }
}
