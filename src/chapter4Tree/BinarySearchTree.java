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

  public BinarySearchTree() {

  }

  public BinarySearchTree(Node root) {
    this.root = root;
  }

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

  public void inOrder() {
    inOrderHelper(root);
  }

  @Override
  public void visit(Node node) {
    System.out.println(node);
  }

  public void inOrderHelper(Node localRoot) {
    if (localRoot == null) {
      return;
    }
    inOrderHelper(localRoot.leftChild);
    visit(localRoot);
    inOrderHelper(localRoot.rightChild);
  }

  public void preOrder() {
    preOrderHelper(root);
  }

  private void preOrderHelper(Node localRoot) {
    if (localRoot == null) {
      return;
    }
    visit(localRoot);
    preOrderHelper(localRoot.leftChild);
    preOrderHelper(localRoot.rightChild);
  }

  public void postOrder(Node localRoot) {
    if (localRoot == null) {
      return;
    }
    postOrder(localRoot.leftChild);
    postOrder(localRoot.rightChild);
    visit(localRoot);
  }

  public Node getRoot() {
    return root;
  }

  public Node minimum(Node lroot) {
    Node cur = lroot;
    Node parent = cur;
    while (cur != null) {
      parent = cur;
      cur = cur.leftChild;
    }
    return parent;
  }

  public Node maximum(Node lroot) {
    Node cur = lroot;
    Node parent = cur;
    while (cur != null) {
      parent = cur;
      cur = cur.rightChild;
    }
    return parent;
  }

  public boolean delete(int key) {
    System.out.println("delete node: " + key);
    Node cur = root;
    Node parent = cur;
    boolean isLeftChild = true;

    while (cur.data != key) {
      parent = cur;
      if (key < cur.data) {
        isLeftChild = true;
        cur = cur.leftChild;
      } else {
        isLeftChild = false;
        cur = cur.rightChild;
      }
      if (cur == null) {
        return false;
      }
    }

    // deleted node has no children
    Node delNode = cur;
    if (delNode.leftChild == null || delNode.rightChild == null) {
      if (delNode == root)
        root = null;
      if (isLeftChild)
        parent.leftChild = null;
      else
        parent.rightChild = null;
    }
    // if has no right child, replace with it's left child
    else if (delNode.rightChild == null) {
      if (delNode == root)
        root = delNode.leftChild;
      else if (isLeftChild) {
        parent.leftChild = delNode.leftChild;
      } else {
        parent.rightChild = delNode.leftChild;
      }
    }
    // if has no left child, replace with it's right child
    else if (delNode.leftChild == null) {
      if (delNode == root)
        root = delNode.rightChild;
      else if (isLeftChild) {
        parent.leftChild = delNode.rightChild;
      } else {
        parent.rightChild = delNode.rightChild;
      }
    }
    // it has two children
    else {
      Node successor = getSuccessor(delNode);
      if (delNode == root) {
        root = successor;
      } else if (isLeftChild) {
        parent.leftChild = successor;
      } else {
        parent.rightChild = successor;
      }
      successor.leftChild = delNode.leftChild;
    }

    return true;
  }

  /**
   * Helper method for deletion. If successor is not the right child of delNode,
   * replace successor's right child to successor's parent's left child.
   * 
   * @param delNode
   * @return the successor of delNode.
   */
  private Node getSuccessor(Node delNode) {
    Node successor = delNode;
    Node successorParent = successor;
    Node cur = delNode.rightChild;
    while (cur != null) {
      successorParent = successor;
      successor = cur;
      cur = cur.leftChild;
    }

    if (successor != delNode.rightChild) {
      successorParent.leftChild = successor.rightChild;
      successor.rightChild = delNode.rightChild;
    }
    return successor;
  }

  public int height(Node localRoot) {
    if (localRoot == null) {
      return 0;
    }
    int leftHeight = height(localRoot.leftChild);
    int rightHeight = height(localRoot.rightChild);
    return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
  }

  /**
   * Question 4.1
   * 
   * @param lroot
   * @return
   */
  public boolean isBlanced(Node lroot) {
    if (lroot == null) {
      return true;
    }
    int heightDiff = height(lroot.leftChild) - height(lroot.rightChild);
    if (Math.abs(heightDiff) <= 1) {
      return isBlanced(lroot.leftChild) && isBlanced(lroot.rightChild);
    } else {
      return false;
    }
  }

  public static int checkHeight(Node lroot) {
    if (lroot == null) {
      return 0;
    }
    int left = checkHeight(lroot.leftChild);
    if (left == -1)
      return -1;
    int right = checkHeight(lroot.rightChild);
    if (right == -1)
      return -1;
    int heightDiff = Math.abs(left - right);
    if (heightDiff > 1)
      return -1;
    return Math.max(left, right) + 1;
  }

  /**
   * improved method, run time is O(N), space is O(log N)
   * 
   * @return
   */
  public boolean isBlanced() {
    if (checkHeight(root) == -1)
      return false;
    return true;
  }

  /**
   * create minimal height BST
   * 
   * @param arr
   * @param start
   * @param end
   * @return
   */
  public static Node createBST(int[] arr, int start, int end) {
    if (end < start) {
      return null;
    }
    int mid = (start + end) / 2;
    Node node = new Node(arr[mid]);
    node.leftChild = createBST(arr, start, mid - 1);
    node.rightChild = createBST(arr, mid + 1, end);
    return node;
  }
  
  

}