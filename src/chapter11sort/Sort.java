package chapter11sort;

public class Sort {

  /**
   * question 11.1 merge to sorted array a and b into a.
   * 
   * @param a
   * @param b
   * @param lengthA
   * @param lengthB
   */
  public static void mergeAB(int[] a, int[] b, int lengthA, int lengthB) {
    int lastA = lengthA - 1;
    int lastB = lengthB - 1;
    int lastIndex = lengthA + lengthB - 1;
    int i = lastA;
    int j = lastB;
    int k = lastIndex;
    while (i >= 0 && j >= 0) {
      if (a[i] > b[j])
        a[k--] = a[i--];
      else
        a[k--] = b[j--];
    }
    while (i >= 0) {
      a[k--] = a[i--];
    }
    while (j >= 0) {
      a[k--] = b[j--];
    }
  }

}
