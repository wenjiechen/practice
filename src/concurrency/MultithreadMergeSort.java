package concurrency;

import java.util.Arrays;

public class MultithreadMergeSort implements Runnable {
  private static int[] A;
  private static int[] aux;

  public MultithreadMergeSort(int[] A) {
    this.A = A;
  }

  private static void mergesort(int[] a, int lo, int hi) {
    if (hi <= lo)
      return;
    int mid = lo + (hi - lo) / 2;
    mergesort(a, lo, mid);
    mergesort(a, mid + 1, hi);
    merge(a, lo, mid, hi);
  }

  @Override
  public void run() {
    aux = new int[A.length];
    mergesort(A, 0, A.length - 1);
  }

  public static void merge(int[] a, int lo, int mid, int hi) {
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
      else if (aux[i] < aux[j])
        a[k] = aux[i++];
      else
        a[k] = aux[j++];
    }
  }

  public static void main(String[] a) {
    int[] arr = { 5, 7, 4, 9, 2 };
    MultithreadMergeSort m = new MultithreadMergeSort(arr);
    m.run();
    System.out.println(Arrays.toString(arr));
  }

}
