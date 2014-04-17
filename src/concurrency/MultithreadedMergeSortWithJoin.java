package concurrency;

import java.util.Arrays;

public class MultithreadedMergeSortWithJoin implements Runnable {

  private int[] Arr;
  private int begin;
  private int end;

  public void run() {
    // Base Case - Insertion sort
    if (this.end - this.begin <= 5) {
      insertionSort();
    } else {
      int left_start = this.begin;
      int left_end = this.begin + (this.end - this.begin) / 2;
      int right_start = this.begin + (this.end - this.begin) / 2 + 1;
      int right_end = this.end;

      Thread t1 = new Thread(new MultithreadedMergeSortWithJoin(this.Arr,
          left_start, left_end));
      Thread t2 = new Thread(new MultithreadedMergeSortWithJoin(this.Arr,
          right_start, right_end));
      t1.start();
      t2.start();

      try {
        t1.join();
        t2.join();

        merge(left_start, left_end, right_start, right_end);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public MultithreadedMergeSortWithJoin(int[] A, int begin, int end) {
    this.Arr = A;
    this.begin = begin;
    this.end = end;
    System.out.println("New thread: " + this.begin + " " + this.end);
  }


  private void insertionSort() {
    for (int x = this.begin; x < this.end; x++) {
      int currentNum = this.Arr[x];
      int hole = x;
      while ((hole > 0) && (this.Arr[hole - 1] > currentNum)) {
        this.Arr[hole] = this.Arr[hole - 1];
        hole--;
      }
      this.Arr[hole] = currentNum;
    }
  }

  private void merge(int left_start, int left_end, int right_start,
      int right_end) {

    int length = right_end - left_start;

    int[] temp = new int[length];
    int leftP = left_start;
    int rightP = right_start;
    int index = 0;

    while ((leftP <= left_end) && (rightP < right_end)) {
      if (Arr[leftP] < Arr[rightP]) {
        temp[index++] = Arr[leftP++];
      } else {
        temp[index++] = Arr[rightP++];
      }
    }
    while (leftP <= left_end) {
      temp[index++] = Arr[leftP++];
    }
    while (rightP < right_end) {
      temp[index++] = Arr[rightP++];
    }

    System.arraycopy(temp, 0, Arr, left_start, length);

  }

  public boolean checkOrder(int[] A) {
    for (int i = 1; i < A.length; i++) {
      if (A[i] < A[i - 1])
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    int[] A = { 5, 45, 68, 68, 3, 6, 9, 8, 7, 74, 65, 159, 65, 84, 984 };
    MultithreadedMergeSortWithJoin m = new MultithreadedMergeSortWithJoin(A, 0,
        A.length);
    m.run();
    System.out.println(Arrays.toString(A));
    System.out.println(m.checkOrder(A));
  }
}