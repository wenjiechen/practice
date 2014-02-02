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

  // question 5.5
  public static int bitSwapRequired(int m, int n) {
    int count = 0;
    for (int c = m ^ n; c != 0; c = c & (c - 1)) {
      count++;
    }
    return count;
  }

  public static void test5() {
    int m = 0x44;
    int n = 0x41;
    System.out.println(Integer.toBinaryString(m));
    System.out.println(Integer.toBinaryString(n));
    System.out.println(bitSwapRequired(m, n));
  }

  // question 5.6
  static void swapOddEvenBits(int n) {
    System.out.println(n);
    System.out.println(Integer.toBinaryString(n));
    int odd = n & 0xAAAAAAAA;
    int even = n & 0x55555555;
    int ret = (odd >> 1) | (even << 1);
    System.out.println(ret);
    System.out.println(Integer.toBinaryString(ret));

  }

  static void test6() {
    swapOddEvenBits(0x55);
  }

  public static void main(String[] args) {
    // test2();
//    test5();
    test6();
  }
}
