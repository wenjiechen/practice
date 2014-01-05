package chapter4Tree;

import java.util.ArrayList;
import java.util.LinkedList;

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

  /**
   * create a linked list at each depth. using pre-order traverse visit
   * 
   * @param lroot
   * @param lists
   * @param level
   */
  public static void createLevelLinkedList(Node lroot,
      ArrayList<LinkedList<Node>> lists, int level) {
    if (lroot == null)
      return;
    LinkedList<Node> list = null;
    // is the first time to get this level?
    if (lists.size() == level) {
      list = new LinkedList<Node>();
      lists.add(list);
    } else {
      list = lists.get(level);
    }
    list.add(lroot);
    createLevelLinkedList(lroot.leftChild, lists, level + 1);
    createLevelLinkedList(lroot.rightChild, lists, level + 1);
  }

  /**
   * helper function for pre-order create linked list function
   * 
   * @return
   */
  public ArrayList<LinkedList<Node>> createLevelLinkedList() {
    ArrayList<LinkedList<Node>> ret = new ArrayList<LinkedList<Node>>();
    createLevelLinkedList(root, ret, 0);
    return ret;
  }

  /**
   * Question 4.4 use BSF like to build linked list level by level
   * 
   * @param lroot
   * @return
   */

  public ArrayList<LinkedList<Node>> createLevelLinkedList(Node lroot) {
    if (lroot == null) {
      return null;
    }
    ArrayList<LinkedList<Node>> levelLists = new ArrayList<LinkedList<Node>>();
    LinkedList<Node> curLevel = new LinkedList<Node>();
    curLevel.add(lroot);
    while (curLevel.size() > 0) {
      LinkedList<Node> parentLevel = curLevel;
      levelLists.add(parentLevel);
      curLevel = new LinkedList<Node>();
      for (Node node : parentLevel) {
        if (node.leftChild != null) {
          curLevel.add(node.leftChild);
        }
        if (node.rightChild != null) {
          curLevel.add(node.rightChild);
        }
      }
    }
    return levelLists;
  }

  /**
   * Question 4.5 check if a tree is a binary search tree.
   * 
   * @param lroot
   * @param arr
   */
  public static void copyBST(Node lroot, ArrayList<Node> arr) {
    if (lroot == null) {
      return;
    }
    copyBST(lroot.leftChild, arr);
    arr.add(lroot);
    copyBST(lroot.rightChild, arr);
  }

  public static boolean checkBST(Node lroot) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    copyBST(lroot, nodes);
    for (int i = 0; i < nodes.size() - 1; i++) {
      if (nodes.get(i).data > nodes.get(i + 1).data) {
        return false;
      }
    }
    return true;
  }

  /**
   * Question 4.5
   * use a wrapper integer class to mimic passing by
   * reference,because primitive type is passed by value.
   * 
   * @param lroot
   * @return
   */
  private static class IntWrapper {
    int value = Integer.MIN_VALUE;
  }

  public static boolean checkBST2(Node lroot, IntWrapper lastEle) {
    if (lroot == null) {
      return true;
    }

    if (checkBST2(lroot.leftChild, lastEle) == false) {
      return false;
    }

    if (lroot.data < lastEle.value) {
      return false;
    }
    lastEle.value = lroot.data;

    if (checkBST2(lroot.rightChild, lastEle) == false) {
      return false;
    }

    return true;
  }

  public boolean checkBST2Helper() {
    return checkBST2(root, new IntWrapper());
  }
  
  /**
   * Question 4.5. passing down min, max to check the range
   * @param lroot
   * @param min
   * @param max
   * @return
   */
  public boolean checkBST3(Node lroot, int min, int max){
    if(lroot == null)
      return true;
    
    if(lroot.data > max || lroot.data < min)
      return false;
    
    if(checkBST3(lroot.leftChild, min, lroot.data) == false ||
        checkBST3(lroot.rightChild, lroot.data, max) == false)
      return false;
    
    return true;
  }

  public boolean checkBST3(){
    return checkBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
}
