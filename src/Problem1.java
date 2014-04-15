import java.util.HashMap;

public class Problem1 {
  public static void problem1(String str) {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (char c : str.toCharArray()) {
      if (map.get(c) == null) {
        map.put(c, new Integer(1));
      }
      map.put(c, map.get(c) + 1);
    }
    int len = str.length() / 2;
    for (char c : map.keySet()) {
      if (map.get(c) > len) {
        System.out.println(c);
        return;
      }
    }
    System.out.println("null");
  }
  
  public static void main(String[] args) {
    System.out.println("problem1:");
    String str = "abadacababaaaa";
    String str2 = "abcdeabbad";
    problem1(str);
    problem1(str2);
  }
}
