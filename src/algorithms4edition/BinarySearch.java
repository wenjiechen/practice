package algorithms4edition;

import java.util.Arrays;

public class BinarySearch {

  // recursion
  public static int binarySearch(int[] a, int key, int l, int h) {
    if (l > h)
      return -1;
    int mid = (h + l) / 2;
    if (a[mid] == key)
      return mid;
    else if (key < a[mid])
      return binarySearch(a, key, l, mid - 1);
    else
      return binarySearch(a, key, mid + 1, h);
  }

  // binary search no-recursion
  public static int rank(int key, int[] a) {
    int l = 0;
    int h = a.length - 1;
    while (l <= h) {
      // **** how to define the mid
      int mid = l + (h - l) / 2;
      if (key < a[mid])
        h = mid - 1;
      else if (key > a[mid])
        l = mid + 1;
      else
        return mid;
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] a = { 12, 23, 32, 123, 45, 98, 7, 8, 923, 345, 78 };
    Arrays.sort(a);
    System.out.println(binarySearch(a, 345, 0, a.length - 1));
    System.out.println(rank(98, a));
  }
}
