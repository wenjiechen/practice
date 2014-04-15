import java.util.HashMap;
import java.util.LinkedList;

public class CoreJavaTest {

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

  public static void problem2(String expr) {
    boolean cal = false;
    LinkedList<String> eles = new LinkedList<String>();
    // deal with "*" and "/"
    for (String ele : expr.split("\\s+")) {
      if (ele.equals("*") || ele.equals("/")) {
        eles.add(ele);
        cal = true;
      } else if (cal == true) {
        String operator = eles.removeLast();
        float oper1 = Float.parseFloat(eles.removeLast());
        float oper2 = Float.parseFloat(ele);
        float tmpret = 0;
        switch (operator) {
        case "*":
          tmpret = oper1 * oper2;
          break;
        case "/":
          if (oper2 == 0)
            throw new IllegalArgumentException("Argument 'divisor' is 0");
          tmpret = oper1 / oper2;
          break;
        }
        eles.add(Float.toString(tmpret));
        cal = false;
      } else {
        eles.add(ele);
      }
    }
    // deal with + and -
    float ret = 0;
    String operator = "";
    boolean firstEle = true;
    for (String ele : eles) {
      if (firstEle == true) {
        ret = Float.parseFloat(ele);
        firstEle = false;
      } else {
        if (ele.matches("\\+|-")) {
          operator = ele;
          continue;
        } else {
          switch (operator) {
          case "+":
            ret += Float.parseFloat(ele);
            break;
          case "-":
            ret -= Float.parseFloat(ele);
            break;
          }
        }
      }
    }
    System.out.println(ret);
  }

  public static void main(String[] args) {
    System.out.println("problem1:");
    String str = "abadacababaaaa";
    String str2 = "abcdeabbad";
    problem1(str);
    problem1(str2);
    System.out.println("problem2:");
    String expr = "5.6  / 0.7 *  2 - 3.5 ";
    String expr2 = "-1.3 + 5.1 / 3 - 0.8";
    problem2(expr);
    problem2(expr2);
  }
}
