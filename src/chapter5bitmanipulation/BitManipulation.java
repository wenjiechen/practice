package chapter5bitmanipulation;

public class BitManipulation {

  private static void println(Object x) {
    System.out.println(x);
  }

  /**
   * check the ith postion is 1 or 0
   * 
   * @param num
   * @param i
   *          ith position in binary num
   * @return
   */
  public static boolean getBit(int num, int i) {
    return ((num & (1 << (i - 1))) != 0);
  }

  public static int setBit(int num, int ith) {
    return num | (1 << (ith - 1));
  }

  public static int cleanBit(int num, int ith) {
    System.out.println("clean bit, num: " + num + ", position: " + ith);
    int mask = ~(1 << (ith - 1));
    return num & mask;
  }

  public static int clearBitsToI(int num, int ith) {
    System.out.println("num: " + num + ", pos: " + ith);
    int mask = (1 << ith) - 1;
    return num & mask;
  }

  public static int clearBitsIto0(int num, int ith) {
    System.out.println("num: " + num + ", pos: " + ith);
    int mask = ~((1 << ith) - 1);
    return mask & num;
  }

  public static void test() {
    int num = 0x40;
    System.out.println(getBit(num, 6));
    System.out.println(setBit(0x0, 4));
    System.out.println(cleanBit(0x18, 4));
    System.out.println(clearBitsToI(0x48, 4));
    System.out.println(clearBitsIto0(0x1c, 4));
  }

  public static void main(String[] args) {
    // test();
    println(Integer.valueOf("1000", 2));
  }
}
