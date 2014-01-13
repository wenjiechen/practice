package chapter11sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

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

    while (j >= 0) {
      a[k--] = b[j--];
    }
  }

  /**
   * question 11.2, sort a string's content.
   */
  private static String sortChar(String str) {
    char[] content = str.toCharArray();
    Arrays.sort(content);
    // return content.toString();
    return new String(content);
  }

  /**
   * question 11.2,
   * 
   * @param array
   */
  public static void sortByAnagrams(String[] array) {
    HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
    for (String s : array) {
      String key = sortChar(s);
      if (map.containsKey(key) == false) {
        map.put(key, new LinkedList<String>());
      }
      LinkedList<String> list = map.get(key);
      list.add(s);
    }

    int i = 0;
    for (String key : map.keySet()) {
      LinkedList<String> list = map.get(key);
      for (String s : list) {
        array[i++] = s;
      }
    }
  }

  static class AnagramComparator implements Comparator<String> {

    public String sortChar(String s) {
      char[] content = s.toCharArray();
      Arrays.sort(content);
      return new String(content);
    }

    @Override
    public int compare(String o1, String o2) {
      return sortChar(o1).compareTo(sortChar(o2));
    }
  }

  /**
   * question 11.2
   * 
   * @param strs
   */
  public static void sortByAnagrams2(String[] strs) {
    Arrays.sort(strs, new AnagramComparator());
  }

  /**
   * question 11.3, assume every element is unique
   * 
   * @param a
   * @param left
   * @param right
   * @param key
   * @return
   */
  public static int searchRotatedArray(int[] a, int left, int right, int key) {
    if (left > right)
      return -1;
    int mid = (left + right) >>> 1;
    if (key == a[mid])
      return mid;
    // left is normally ordered
    else if (a[left] < a[mid]) {
      // ">=", can't forget "=" condition
      if (key >= a[left] && key <= a[mid])
        return searchRotatedArray(a, left, mid - 1, key);
      else
        return searchRotatedArray(a, mid + 1, right, key);
    }
    // right is normally ordered
    else if (a[mid] < a[right]) {
      if (key >= a[mid] && key <= a[right])
        return searchRotatedArray(a, mid + 1, right, key);
      else
        return searchRotatedArray(a, left, mid - 1, key);
    }
    // left only has repeat number
    else if (a[left] == a[mid]) {
      if (a[mid] != a[right])
        return searchRotatedArray(a, mid + 1, right, key);
      else {
        int result = searchRotatedArray(a, left, mid - 1, key);
        if (result == -1)
          return result = searchRotatedArray(a, mid + 1, right, key);
        else
          return result;
      }
    }
    return -1;
  }

  /**
   * question 11.5, find a string in a string array, which is interspersed empty
   * string
   * 
   * @param a
   * @param left
   * @param right
   * @param str
   * @return
   */
  private static int searchString(String[] a, int left, int right, String str) {
    if (left > right)
      return -1;
    int mid = (left + right) >>> 1;
    if (a[mid] == null || a[mid].equals("")) {
      int j = mid - 1;
      int i = mid + 1;
      while (j >= left && (a[j] == null || a[j].equals("")))
        j--;
      while (i <= right && (a[i] == null || a[i].equals("")))
        i++;
      // *** important
      if (j < left && i > right)
        return -1;
      if (Math.abs(mid - j) < Math.abs(mid - i))
        mid = j;
      else
        mid = i;
    }
    if (a[mid].equals(str))
      return mid;
    else if (str.compareTo(a[mid]) < 0) {
      return searchString(a, left, mid - 1, str);
    } else {
      return searchString(a, mid + 1, right, str);
    }
  }

  public static int searchString(String[] a, String str) {
    return searchString(a, 0, a.length - 1, str);
  }

  /**
   * question 11.6, coordiante of matrix,
   * 
   * @author wenjie
   * 
   */
  static class Coordinate implements Cloneable {
    public int row;
    public int column;

    public Coordinate(int r, int c) {
      row = r;
      column = c;
    }

    public boolean inbounds(int[][] matrix) {
      return row >= 0 && column >= 0 && row < matrix.length
          && column < matrix[0].length;
    }

    public boolean isBefore(Coordinate p) {
      return row <= p.row && column <= p.column;
    }

    public Object clone() {
      return new Coordinate(row, column);
    }

    public void setToAverage(Coordinate min, Coordinate max) {
      row = (min.row + max.row) >>> 1;
      column = (min.column + max.column) >>> 1;
    }

    @Override
    public String toString() {
      return new String("pos: (" + row + ", " + column + ")");
    }
  }

  /**
   * 
   * @param matrix
   * @param origin
   * @param dest
   * @param pivot
   * @param elem
   * @return
   */
  public static Coordinate partitionAndSearch(int[][] matrix,
      Coordinate origin, Coordinate dest, Coordinate pivot, int elem) {
    Coordinate lowerLeftOrigin = new Coordinate(pivot.row, origin.column);
    Coordinate lowerLeftDest = new Coordinate(dest.row, pivot.column - 1);
    Coordinate upperRightOrigin = new Coordinate(origin.row, pivot.column);
    Coordinate upperRightDest = new Coordinate(pivot.row - 1, dest.column);

    Coordinate lowerLeft = findElement(matrix, lowerLeftOrigin, lowerLeftDest,
        elem);
    if (lowerLeft == null)
      return findElement(matrix, upperRightOrigin, upperRightDest, elem);
    return lowerLeft;
  }

  /**
   * question 11.6,
   * 
   * @param matrix
   * @param origin
   * @param dest
   * @param x
   * @return
   */
  private static Coordinate findElement(int[][] matrix, Coordinate origin,
      Coordinate dest, int x) {
    if (!origin.inbounds(matrix) || !dest.inbounds(matrix))
      return null;
    if (matrix[origin.row][origin.column] == x)
      return origin;
    else if (!origin.isBefore(dest))
      return null;

    Coordinate start = (Coordinate) origin.clone();
    int diagDist = Math.min(dest.row - origin.row, dest.column - origin.column);
    Coordinate end = new Coordinate(start.row + diagDist, start.column
        + diagDist);
    Coordinate mid = new Coordinate(0, 0);

    while (start.isBefore(end)) {
      mid.setToAverage(start, end);
      // if (x == matrix[mid.row][mid.column])
      // return mid;
      if (x > matrix[mid.row][mid.column]) {
        start.row = mid.row + 1;
        start.column = mid.column + 1;
      } else {
        end.row = mid.row - 1;
        end.column = mid.column - 1;
      }
    }

    return partitionAndSearch(matrix, origin, dest, start, x);
  }

  public static Coordinate findElement(int[][] matrix, int x) {
    System.out.println("find element: " + x);
    if (matrix == null)
      return null;
    return findElement(matrix, new Coordinate(0, 0), new Coordinate(
        matrix.length - 1, matrix[0].length - 1), x);
  }

  /**
   * question 11.7,
   * 
   * @author wenjie
   * 
   */
  static class Person implements Comparable {
    int height;
    int weight;

    Person(int h, int w) {
      height = h;
      weight = w;
    }

    @Override
    public int compareTo(Object o) {
      Person oth = (Person) o;
      if (height != oth.height) {
        return ((Integer) height).compareTo(oth.height);
      } else {
        return ((Integer) weight).compareTo(oth.weight);
      }
    }

    public boolean isBefore(Person oth) {
      return height < oth.height && weight < oth.weight;
    }

    @Override
    public String toString() {
      return new String("(" + height + ", " + weight + ")");
    }
  }

  static ArrayList<Person> maxLength(ArrayList<Person> s1, ArrayList<Person> s2) {
    if (s1 == null)
      return s2;
    if (s2 == null)
      return s1;
    return (s1.size() > s2.size()) ? s1 : s2;
  }

  /**
   * find the longest increasing subsequence, keep the original order between
   * elements
   * 
   * @param persons
   * @param solutions
   * @param cur_id
   */
  static void longestIncreasingSubsequence(ArrayList<Person> persons,
      ArrayList<Person>[] solutions, int cur_id) {
    if (cur_id >= persons.size() || cur_id < 0)
      return;
    Person curPerson = persons.get(cur_id);

    /* find the longest sequence which can append current person */
    ArrayList<Person> bestSequence = null;
    for (int i = 0; i < cur_id; i++) {
      if (persons.get(i).isBefore(curPerson)) {
        bestSequence = maxLength(bestSequence, solutions[i]);
      }
    }

    /* append curPerson to bestSequence */
    ArrayList<Person> newSolution = new ArrayList<Person>();
    if (bestSequence != null) {
      newSolution.addAll(bestSequence);
    }
    newSolution.add(curPerson);

    solutions[cur_id] = newSolution;
    longestIncreasingSubsequence(persons, solutions, ++cur_id);
  }

  static ArrayList<Person> longestIncreasingSubsequence(
      ArrayList<Person> persons) {
    ArrayList<Person>[] solutions = new ArrayList[persons.size()];
    longestIncreasingSubsequence(persons, solutions, 0);
    int maxLengthIndex = 0;
    int maxLength = 0;
    for (int i = 0; i < persons.size(); i++) {
      if (solutions[i].size() > maxLength) {
        maxLength = solutions[i].size();
        maxLengthIndex = i;
      }
    }

    return solutions[maxLengthIndex];
  }

  /**
   * question 11.7,
   * 
   * @param persons
   * @return
   */
  public static ArrayList<Person> getLongestSequence(ArrayList<Person> persons) {
    Collections.sort(persons);
    System.out.println("after sort by height");
    System.out.println(persons);
    return longestIncreasingSubsequence(persons);
  }
}
