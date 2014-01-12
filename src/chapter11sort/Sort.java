package chapter11sort;

import java.util.Arrays;
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

  private class pos {
    int x;
    int y;

    pos(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static pos searchMatrix(int[][] a, int key, int left, int right,
      int up, int down) {
    if (left > right || up > down)
      return new pos(-1, -1);
    int mlevel = (left + right)/2;
    int mvertical = (up + down)/2;
    if(a[mlevel][mvertical] == key)
      return new pos(mlevel,mvertical);
    else if(key < )
  }
}
