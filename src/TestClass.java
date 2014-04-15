import java.util.ArrayList;

public class TestClass {

  public static int removeDuplicates(int[] A) {
    if (A.length <= 1)
      return A.length;
    int copyPos = 1;
    int i = 0;
    int j = 1;
    while (j < A.length) {
      while (j < A.length && A[i] == A[j])
        j++;
      if (j < A.length) {
        A[copyPos++] = A[j];
        i = j++;
      }
    }
    return copyPos;
  }

  public void test() {
    System.out.println(18 / 10);
    System.out.println(28 % 10);
    ArrayList<Integer> arr = new ArrayList<Integer>();
    arr.add(10);
    arr.add(20);
    System.out.println(arr.get(1));
    arr.set(1, 30);
    System.out.println(arr.get(1));
  }

  public final static void test2() {
    int[] arr = { 1, 1 };
    System.out.println(removeDuplicates(arr));
    try {

    } finally {

    }
  }

  public static void main(String[] args) {
    test2();
  }
}
