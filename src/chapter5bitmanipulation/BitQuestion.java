package chapter5bitmanipulation;

public class BitQuestion {
  /**
   * question 5.1
   */
  public static int updateBits(int n, int m, int i, int j){
  	// create 1111000000 for j
  	int left = ~((1<<(j+1)) -1);
  	// create 0000000111 for i
  	int right = ~((1<<(i))-1);
  	//create 111100011
  	int maskji = left | right;
  	//clean bit i to j in n
  	int tmp = n & maskji;
  	//

  }

}
