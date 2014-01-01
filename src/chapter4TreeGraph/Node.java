package chapter4TreeGraph;

public class Node {
  int data;
  Node leftChild;
  Node rightChild;

  Node(int d) {
    data = d;
    leftChild = null;
    rightChild = null;
  }

  void displayNode() {
    System.out.print(data + " ");
  }
}
