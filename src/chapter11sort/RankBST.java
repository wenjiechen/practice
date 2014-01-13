package chapter11sort;

public class RankBST {

  static class RankNode {
    int data = 0;
    int leftSize = 0;
    RankNode left;
    RankNode right;

    public RankNode(int d) {
      this.data = d;
    }

    @Override
    public String toString() {
      return new String("(data: " + data + ", leftSize: " + leftSize + ")");
      // return new String("left: " + left + "(data: " + data + ", leftSize: "
      // + leftSize + ")" + " right: " + right);
    }
  }

  RankNode root = null;

  public void insert(int d) {
    if (root == null) {
      root = new RankNode(d);
      return;
    }

    RankNode cur = root;
    RankNode parent = cur;
    while (true) {
      if (d <= cur.data) {
        cur.leftSize++;
        parent = cur;
        cur = cur.left;
        if (cur == null) {
          parent.left = new RankNode(d);
          break;
        }
      } else {
        parent = cur;
        cur = cur.right;
        if (cur == null) {
          parent.right = new RankNode(d);
          break;
        }
      }
    }
  }

  public int track(int d) {
    System.out.println("find num: " + d);
    int rank = 0;

    RankNode cur = root;
    while (cur != null) {
      if (cur.data == d) {
        rank += cur.leftSize;
        return rank;
      } else if (d < cur.data) {
        cur = cur.left;
      } else {
        rank += cur.leftSize + 1;
        cur = cur.right;
      }
    }

    return -1;
  }

  private void printTree(RankNode lroot) {
    if (lroot == null)
      return;
    printTree(lroot.left);
    System.out.println(lroot + " ");
    printTree(lroot.right);
  }

  public void printTree() {
    printTree(root);
  }

  public static void main(String[] args) {
    RankBST tree = new RankBST();
    tree.insert(20);
    tree.insert(15);
    tree.insert(10);
    tree.insert(5);
    tree.insert(13);
    tree.insert(25);
    tree.insert(23);
    tree.insert(24);
    tree.printTree();
    System.out.println(tree.track(25));
    System.out.println(tree.track(16));
  }

}
