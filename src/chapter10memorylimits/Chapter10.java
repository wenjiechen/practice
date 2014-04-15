package chapter10memorylimits;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Chapter10 {

  // question 10.3
  static int findMissingNum() throws FileNotFoundException {
    byte[] bitfields = new byte[0xff / 8];

    Scanner in = new Scanner(new FileReader("nums.txt"));
    // mark every num in file
    while (in.hasNextInt()) {
      int num = in.nextInt();
      bitfields[num / 8] |= (1 << (num % 8));
    }
    // find missing num
    for (int i = 0; i < bitfields.length; i++)
      for (int j = 0; j < 8; j++) {
        if ((bitfields[i] & (1 << j)) == 0)
          return (i * 8 + j);
      }
    return -1;
  }

  static void test3() throws FileNotFoundException {
    System.out.println(findMissingNum());
  }

  /**
   * @param args
   * @throws FileNotFoundException 
   */
  public static void main(String[] args) throws FileNotFoundException {
    test3();
  }

}
