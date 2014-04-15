package algorithms4edition;

public class Tree {
  static class Node {
    Comparable data;
    

  }
  
  private  boolean less(Node a, Node b) {
    return a.data.compareTo(b.data) < 0;
  }

  public boolean f(Node a, Node b){
    return less(a,b);
  }
}
