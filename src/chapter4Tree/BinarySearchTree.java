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
   * insert a new value to the binary search tree
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

  public Node find(int key) throws Exception {
    Node cur = root;
    while (cur != null) {
      if (cur.data == key)
        return cur;
      else if (key < cur.data)
        cur = cur.leftChild;
      else
        cur = cur.rightChild;
    }
    throw new Exception("don't have node");
  }


  public void inOrder(){
    inOrderHelper(root);
  }
  
  @Override
  public void visit(Node node) {
    System.out.println(node);
  }

  /**
   * traversing a tree
   * 
   * @param localRoot
   */
  public void inOrderHelper(Node localRoot) {
    if (localRoot != null) {
      inOrderHelper(localRoot.leftChild);
      visit(localRoot);
      inOrderHelper(localRoot.rightChild);
    }
  }

  
  public void preOrder(){
    preOrderHelper(root);
  }
  
  public void preOrderHelper(Node localRoot){
    visit(localRoot);
    preOrderHelper(localRoot.leftChild);
    preOrderHelper(localRoot.rightChild);
  }
  
  
}
