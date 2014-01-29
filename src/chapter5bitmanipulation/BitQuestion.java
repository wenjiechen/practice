package chapter5bitmanipulation;

public class BitQuestion {
  /**
   * question 5.1
   */
  public static int updateBits(int n, int m, int i, int j) {
    // create 1111000000 for j
    int left = ~((1 << (j + 1)) - 1);
    // create 0000000111 for i
    int right = ~((1 << (i)) - 1);
    // create 111100011
    int maskji = left | right;
    // clean bit i to j in n
    int tmp = n & maskji;
    // shife m << i
    m = m << i;
    return n & m;
  }

  // question 5.2
  public static String printBinary(double num) {
    if (num >= 1 || num <= 0)
      return "Error";

    StringBuilder binary = new StringBuilder("0.");
    while (num > 0) {
      if (binary.length() > 100)
        return "Error";

      num = num * 2;
      if (num >= 1) {
        binary.append(1);
        num = num - 1;
      } else {
        binary.append(0);
      }
    }
    return binary.toString();
  }

  // question 5.2 method 2
  public static String printBinary2(double num) {
    if (num >= 1 || num <= 0)
      return "Error";

    StringBuilder binary = new StringBuilder("0.");
    double frac = 0.5;
    while (num > 0) {
      if (binary.length() > 100)
        return "Error";
      if (num >= frac) {
        binary.append(1);
        num -= frac;
      } else {
        binary.append(0);
      }
      frac /= 2;
    }
    return binary.toString();
  }

  public static void test2() {
    double num = 0.72;
    System.out.println(printBinary(num));
    System.out.println(printBinary2(num));

  }

  public static void main(String[] args) {
    test2();
  }
}
