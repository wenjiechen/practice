package chapter4Tree;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinarySearchTree implements Visitor {

  static class Node {
    int data;
    Node leftChild = null;
    Node rightChild = null;
    Node parent = null;

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
      root.parent = null;
    } else {
      Node cur = root;
      Node parent = cur;
      while (true) {
        parent = cur;
        if (data < cur.data) {
          cur = cur.leftChild;
          if (cur == null) {
            parent.leftChild = newNode;
            newNode.parent = parent;
            return;
          }
        } else {
          cur = cur.rightChild;
          if (cur == null) {
            parent.rightChild = newNode;
            newNode.parent = parent;
            return;
          }
        }
      }
    }
  }

  public Node getParent(Node lroot) {
    if (lroot == null || lroot == root)
      return null;
    return lroot.parent;
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
   * Question 4.5 use a wrapper integer class to mimic passing by
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
   * 
   * @param lroot
   * @param min
   * @param max
   * @return
   */
  public boolean checkBST3(Node lroot, int min, int max) {
    if (lroot == null)
      return true;

    if (lroot.data > max || lroot.data < min)
      return false;

    if (checkBST3(lroot.leftChild, min, lroot.data) == false
        || checkBST3(lroot.rightChild, lroot.data, max) == false)
      return false;

    return true;
  }

  public boolean checkBST3() {
    return checkBST3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  /**
   * Question 4.6, find in-order traverse successor.
   * 
   * @param lroot
   *          the current traverse node
   * @param key
   *          find the successor of 'key'
   * @param lastEle
   *          the last traverse node of lroot node
   * @param ret
   *          represent the found successor. Have to copy data to ret. can't
   *          just copy lroot's reference to ret, because java's parameter is
   *          passed by value. if assign ret = lroot, the new ret can't be
   *          passed back.
   */
  public void inorderSucc(Node lroot, int key, Node lastEle, Node ret) {
    if (lroot == null) {
      return;
    }
    inorderSucc(lroot.leftChild, key, lastEle, ret);
    // found the successor
    if (lastEle.data == key) {
      System.out.println("successor: " + lroot.data);
      // have to copy data to ret. can't just copy lroot's reference to ret.
      // because java's parameter is passed by value. if assign ret = lroot, the
      // new ret can't be passed back.
      ret.data = lroot.data;
      lastEle.data = lroot.data;
      return;
    }
    lastEle.data = lroot.data;
    inorderSucc(lroot.rightChild, key, lastEle, ret);
  }

  /**
   * If a node has a reference to it's parent, use iteration to find in order
   * successor. If the node has right child, find the left most node in its
   * right child, otherwise find its first ancestor the node which is the
   * 
   * @param lroot
   * @return
   */
  public static Node inorderSucc(Node lroot) {
    if (lroot == null)
      return null;
    if (lroot.rightChild != null) {
      Node cur = lroot.rightChild;
      Node parent = cur;
      while (cur != null) {
        parent = cur;
        cur = cur.leftChild;
      }
      return parent;
    } else {
      Node cur = lroot;
      Node parent = lroot.parent;
      while (cur != parent.leftChild && parent != null) {
        cur = cur.parent;
        parent = parent.parent;
      }
      if (parent != null) {
        return parent;
      }
      return null;
    }
  }

  public boolean findNode(Node lroot, int key) {
    if (lroot == null) {
      return false;
    }
    if (lroot.data == key)
      return true;
    if (key < lroot.data) {
      return findNode(lroot.leftChild, key);
    } else {
      return findNode(lroot.rightChild, key);
    }
  }

  /**
   * For Binary search Tree, post-order traverse to find the common ancestor.
   * 
   * @param lroot
   * @param key1
   * @param key2
   * @return
   */
  public Node commonAncestor(Node lroot, int key1, int key2) {
    if (lroot == null)
      return null;
    Node ret = commonAncestor(lroot.leftChild, key1, key2);
    if (ret == null) {
      ret = commonAncestor(lroot.rightChild, key1, key2);
    }
    if (ret == null) {
      if (findNode(lroot, key1) && findNode(lroot, key2))
        ret = lroot;
    }
    return ret;
  }

  private boolean covers(Node lroot, int key) {
    if (lroot == null)
      return false;
    if (lroot.data == key)
      return true;
    return covers(lroot.leftChild, key) || covers(lroot.rightChild, key);
  }

  /**
   * find two nodes' common ancestor in a binary tree(not binary search tree).
   * If key1 and key2 both are in the left side of lroot, their common ancestor
   * is in the left side of lroot. If key1 and key2 are in the different sides
   * of lroot, lroot is their common ancestor.
   * 
   * @param lroot
   * @param key1
   * @param key2
   * @return
   */
  public Node commonAncestorForNotBST(Node lroot, int key1, int key2) {
    if (lroot == null)
      return null;
    if (covers(lroot, key1) == false || covers(lroot, key2) == false)
      return null;
    if (covers(lroot.leftChild, key1) && covers(lroot.leftChild, key2))
      return commonAncestor(lroot.leftChild, key1, key2);
    else if (covers(lroot.rightChild, key1) && covers(lroot.rightChild, key2))
      return commonAncestor(lroot.rightChild, key1, key2);
    else
      return lroot;
  }

  /**
   * find the path from root to the node. save the path in a stack
   * 
   * @param lroot
   * @param key
   * @param stack
   * @return
   */
  public boolean findPath(Node lroot, int key, ArrayList<Node> stack) {
    if (lroot == null)
      return false;
    if (findPath(lroot.leftChild, key, stack)
        || findPath(lroot.rightChild, key, stack)) {
      stack.add(lroot);
      return true;
    }
    if (lroot.data == key) {
      stack.add(lroot);
      return true;
    } else {
      return false;
    }
  }

}
