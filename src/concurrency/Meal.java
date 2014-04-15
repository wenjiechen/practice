package concurrency;

public class Meal {
  final int orderNum;
  private static int count = 0;

  public Meal() {
    this.orderNum = ++count;
  }

  public String toString() {
    return "Meal" + orderNum;
  }
}
