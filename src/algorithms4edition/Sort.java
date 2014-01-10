package algorithms4edition;

import java.util.Random;

public class Sort {

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
    case "merge":
      System.out.println("using merge sort");
      mergesort(a);
      break;
    case "mergeBU":
      System.out.println("using merge sort bottom-up");
      mergesortBU(a);
      break;
    case "quickSort":
      System.out.println("using quicksort");
      quickSort(a);
      break;
    default:
      System.out.println("no match sort method");
    }
  }

  private static Comparable[] aux;

  public static void mergesort(Comparable[] a) {
    aux = new Comparable[a.length];
    mergesort(a, 0, a.length - 1);
  }

  /**
   * traditional merge sort
   * 
   * @param a
   * @param lo
   * @param hi
   */
  private static void mergesort(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int mid = lo + (hi - lo) / 2;
    mergesort(a, lo, mid);
    mergesort(a, mid + 1, hi);
    merge(a, lo, mid, hi);
  }

  /**
   * in-place merge using an aux array
   * 
   * @param a
   * @param lo
   * @param mid
   * @param hi
   */
  public static void merge(Comparable[] a, int lo, int mid, int hi) {
    // merge a[lo...mid] with a[mid+1...hi]
    int i = lo;
    int j = mid + 1;

    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }
    // merge
    for (int k = lo; k <= hi; k++) {
      if (i > mid)
        a[k] = aux[j++];
      else if (j > hi)
        a[k] = aux[i++];
      else if (less(aux[i], aux[j]))
        a[k] = aux[i++];
      else
        a[k] = aux[j++];
    }
  }

  /**
   * bottom up merge sort
   * 
   * @param a
   */
  public static void mergesortBU(Comparable[] a) {
    int N = a.length;
    aux = new Comparable[N];
    for (int sz = 1; sz < N; sz = sz + sz) {
      for (int lo = 0; lo < N - sz; lo += sz + sz) {
        merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
      }
    }
  }

  public static void quickSort(Comparable[] a) {
    quickSort(a, 0, a.length - 1);
  }

  private static void quickSort(Comparable[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int j = partition2(a, lo, hi);
    quickSort(a, lo, j - 1);
    quickSort(a, j + 1, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = hi + 1;
    Comparable v = a[lo];
    while (true) {
      while (less(a[++i], v) && i < hi)
        ;
      while (less(v, a[--j]) && j > lo)
        ;
      if (i >= j)
        break;
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  /**
   * find the element greater than v in the left, find the element less than v
   * in right, then exchange two elements.
   * 
   * @param a
   * @param lo
   * @param hi
   * @return
   */
  private static int partition2(Comparable[] a, int lo, int hi) {
    int i = lo + 1;
    int j = hi;
    // randomly chose a item as a pivot
    if (hi - lo > 1) {
      Random rand = new Random();
      int pivot = lo + rand.nextInt(hi - lo + 1);
      exch(a, lo, pivot);
    }
    Comparable v = a[lo];
    while (i < j) {
      while (less(a[i], v) && i < hi)
        i++;
      while (less(v, a[j]) && j > lo)
        j--;
      // ***important, if i is greater than j, can't exchange two elements
      if (i >= j)
        break;
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }

  public static void main(String[] args) {
    Random rand = new Random(47);
    Integer[] test = new Integer[rand.nextInt(50)];
    for (int i = 0; i < test.length; i++) {
      test[i] = rand.nextInt(50);
    }
    show(test);
    sortMethod("quickSort", test);
    show(test);
    System.out.println(isSorted(test));
  }
}
