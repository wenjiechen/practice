package chapter4Tree;

public class BinarySearchTree implements Visitor {

  static class Node {
    int data;
    Node leftChild = null;
    Node rightChild = null;

    public Node(int data) {
      this.data = data;
    }

    public String toString() {
      return " " + data;
    }
  }

  private Node root = null;

  /**
   * insert a new value to the baniry search tree
   * 
   * @param data
   *          of Node
   */
  public void insert(int data) {
    Node newNode = new Node(data);
    if (root == null) {
      root = newNode;
    } else {
      Node cur = root;
      Node parent = cur;
      while (true) {
        parent = cur;
        if (data < cur.data) {
          cur = cur.leftChild;
          if (cur == null) {
            parent.leftChild = newNode;
            return;
          }
        } else {
          cur = cur.rightChild;
          if (cur == null) {
            parent.rightChild = newNode;
            return;
          }
        }
      }
    }
  }

  @Override
  public void visit(Node node) {
    System.out.println(node);
  }

  public void inOrder(Node localRoot) {
    if(localRoot != null){
      inOrder(localRoot.leftChild);
      visit(localRoot);
      inOrder(localRoot.rightChild);
    }
  }

}
