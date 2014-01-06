package leetcode;

public class TrappingRainWater {

  class Solution {
    public int elevate(int[] A, int start, int end) {
      int sum = 0;
      int bar = (A[start] < A[end]) ? A[start] : A[end];
      for (int i = start + 1; i < end; i++) {
        sum += (bar - A[i]);
      }
      return sum;
    }

    public int maxInt(int[] A, int start) {
      int max = -1;
      for (int i = start; i < A.length; i++) {
        if (max < A[i]) {
          max = A[i];
        }
      }
      return max;
    }

    public int trap(int[] A) {
      int start = 0;
      int end = 0;
      int sum = 0;
      int i = 0;
      while (i < A.length) {
        if (start == 0 && A[i] != 0) {
          start = i++;
        }
        /*
         * if(A[start] <= A[i] && start != i){ end = i; sum +=
         * elevate(A,start,end); start = i+1; }
         */
        else {
          int max = maxInt(A, start);
          if (A[start] >= max) {
            if (A[i] != max) {
              i++;
              continue;
            } else { // A[i]=max
              end = i;
              sum += elevate(A, start, end);
              start = i++;
            }
          } else {
            if (A[i] < A[start]) {
              i++;
            } else {
              end = i;
              sum += elevate(A, start, end);
              start = i++;
            }
          }
        }
      }
      return sum;
    }
  }

}
