package chapter4TreeGraph;

public class Tree {

  private Node root;

  public void insert(int d) {
    if (root == null) {
      root = new Node(d);
      return;
    } else {
      Node cur = root;
      Node par = cur;
      while (cur != null) {
        par = cur;
        if (d < cur.data) {
          cur = cur.leftChild;
          if (cur == null) {
            Node n = new Node(d);
            par.leftChild = n;
            return;
          }
        } else {
          cur = cur.rightChild;
          if (cur == null) {
            Node n = new Node(d);
            par.rightChild = n;
            return;
          }
        }
      }
    }
  }

  public void inOrderVisit(Node cur) {
    if (cur != null) {
      inOrderVisit(cur.leftChild);
      System.out.println(cur.data);
      inOrderVisit(cur.rightChild);
    }
  }

  public void preOrder(Node node) {
    if (node != null) {
      System.out.println(node.data);
      preOrder(node.leftChild);
      preOrder(node.rightChild);
    }
  }

  public void postOrder(Node node) {
    if (node != null) {
      postOrder(node.leftChild);
      postOrder(node.rightChild);
      System.out.println(node.data);
    }
  }

  public Node getMin() {
    Node cur = root;
    while (cur != null && cur.leftChild != null) {
      cur = cur.leftChild;
    }
    return cur;
  }

  public Node getMax() {
    Node cur = root;
    while (cur != null && cur.rightChild != null) {
      cur = cur.rightChild;
    }
    return cur;
  }

  public Node getRoot() {
    return root;
  }

  public Node getSuccessor(Node node) {
    if (node == null || node.rightChild == null) {
      return null;
    }
    Node cur = node.rightChild;
    Node parent = cur;
    while (cur != null) {
      parent = cur;
      cur = cur.leftChild;
    }
    return parent;
  }

  public int height(Node n) {
    if (n == null) {
      return 0;
    }
    return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
  }

  public boolean isBalanced(Node root) {
    if (root == null)
      return true;
    int diff = Math.abs(height(root.leftChild) - height(root.rightChild));
    if(diff > 1)
      return false;
    else
      return isBalanced(root.leftChild) && isBalanced(root.rightChild);
  }

  public static void main(String... args) {
    Tree t = new Tree();
    t.insert(7);
    t.insert(3);
    t.insert(9);
    t.insert(5);
    t.insert(8);
    t.insert(1);
    t.insert(12);

    t.inOrderVisit(t.getRoot());
    System.out.println("==========");
    t.getMin().displayNode();
    t.getMax().displayNode();
    t.getSuccessor(t.getRoot()).displayNode();
    System.out.println("==========pre");
    t.preOrder(t.getRoot());
    System.out.println("==========post");
    t.postOrder(t.getRoot());

  }

}