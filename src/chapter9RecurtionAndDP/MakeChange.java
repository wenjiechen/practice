package chapter9RecurtionAndDP;

public class MakeChange {

  int[] coins = { 25, 10, 5, 1 };

  public int makeChange2(int money, int sum, int start) {
    if (sum > money)
      return 0;
    if (sum == money)
      return 1;
    int ways = 0;
    for (int i = start; i < coins.length; i++) {
      sum += coins[i];
      ways += makeChange2(money, sum, i);
      sum -= coins[i];
    }
    return ways;
  }

  public int makeChange(int n, int denom) {
    int next_denom = 0;
    switch (denom) {
    case 25:
      next_denom = 10;
      break;
    case 10:
      next_denom = 5;
      break;
    case 5:
      next_denom = 1;
      break;
    case 1:
      return 1;
    }

    int ways = 0;
    for (int i = 0; i * denom <= n; i++) {
      ways += makeChange(n - i * denom, next_denom);
    }
    return ways;
  }

  public static void main(String[] args) {
    MakeChange m = new MakeChange();
    System.out.println(m.makeChange(100, 25));
    System.out.println(m.makeChange2(100, 0, 0));
  }

}
