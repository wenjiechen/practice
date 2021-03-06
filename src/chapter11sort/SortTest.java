package chapter11sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortTest {
  private static Random rand = new Random(47);

  private static void println(Object x) {
    System.out.println(x);
  }

  private static void print(Object x) {
    System.out.print(x);
  }

  private static boolean isSorted(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[i - 1])
        return false;
    }
    return true;
  }

  private static void test1() {
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

  private static void test2() {
    println("question 11.2");
    String[] strs = { "abcd", "fghe", "efgh", "dbca", "ghfe", "cbad", };
    println("original array");
    println(Arrays.toString(strs));
    println("sorted array by anagrams");
    Sort.sortByAnagrams2(strs);
    println(Arrays.toString(strs));

  }

  private static void test3() {
    int[] test = { 5, 6, 7, 1, 2, 3, 4 };
    println(Sort.searchRotatedArray(test, 0, test.length - 1, 4));
  }

  private static void test5() {
    println("question 11.5");
    String[] a = { null, null, null, null, null, "c", "d", null, null, null,
        "e", "f", "g", null, null, "h" };
    println("string array:");
    println(Arrays.toString(a));
    println(Sort.searchString(a, "c"));
  }

  private static void test6() {
    println("question 11.6");
    int[][] matrix = { { 1, 2, 3, 4, 5 }, { 11, 12, 13, 14, 15 },
        { 21, 22, 23, 24, 25 }, { 31, 32, 33, 34, 35 } };
    println(Sort.findElement(matrix, 15));
  }

  private static void test7() {
    println("question 7");
    Random rand = new Random(47);
    ArrayList<Sort.Person> persons = new ArrayList<Sort.Person>();
    for (int i = 0; i < 10; i++) {
      Sort.Person p = new Sort.Person(rand.nextInt(30), rand.nextInt(30));
      persons.add(p);
    }
    println("persons array");
    println(persons);
    ArrayList<Sort.Person> longestSequence = Sort
        .getLongestSequence(persons);
    println("longest subsequence");
    println(longestSequence);
  }

  public static void main(String[] args) {
    // test1();
    // test2();
    // test3();
    // test5();
    // test6();
    test7();
  }
}
