package leetcode;

import java.util.Arrays;

public class TrappingRainWater {

  static class Solution3 {
    /**
     * for each bar, find the max height bar on the left and right. then for
     * this bar it can hold min(max_left, max_right) - height. We can scan the
     * whole map twice to get the max height on right and left, respectively.
     * Finally scan the map again to get the water trapped of all.
     * 
     * @param A
     * @return
     */
    public static int trap(int[] A) {

      int[] maxleft = new int[A.length];
      int[] maxright = new int[A.length];

      for (int i = 1; i < A.length; i++) {
        maxleft[i] = Math.max(maxleft[i - 1], A[i - 1]);
      }

      for (int i = A.length - 2; i >= 0; i--) {
        maxright[i] = Math.max(maxright[i + 1], A[i + 1]);
      }

      int water = 0;
      for (int i = 0; i < A.length; i++) {
        if (Math.min(maxleft[i], maxright[i]) - A[i] > 0)
          water += Math.min(maxleft[i], maxright[i]) - A[i];
      }
      return water;
    }

  }

  public static void main(String[] args) {
    int[] A = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
    System.out.println(Arrays.toString(A));
    System.out.println(Solution3.trap(A));
  }

}
