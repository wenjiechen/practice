package chapter4Tree;

import java.util.ArrayList;
import java.util.LinkedList;

import chapter4Tree.BST.Node;

public class TreeApp {
  private static void sop(Object x) {
    System.out.println(x);
  }

  public static void printTreeLevel(BST tree) {
    int level = 0;
    for (LinkedList<Node> list : tree.createLevelLinkedList(tree.getRoot())) {
      System.out.print("level " + (level++) + ":");
      for (Node n : list) {
        System.out.print(", " + n);
      }
      sop("");
    }
  }

  public static void main(String[] args) {
    BST tree = new BST();
    tree.insert(50);
    tree.insert(45);
    tree.insert(30);
    tree.insert(80);
    tree.insert(60);
    tree.insert(55);
    tree.insert(58);
    tree.insert(75);
    tree.insert(100);
    tree.insert(120);
    tree.insert(110);
    tree.insert(130);
    tree.insert(46);
    tree.insert(47);
    tree.insert(48);
    tree.insert(49);

    BST tree2 = new BST();
    tree2.insert(60);
    tree2.insert(55);
    tree2.insert(58);
    tree2.insert(75);

    BST tree3 = new BST();
    tree3.insert(80);
    tree3.insert(60);
    tree3.insert(55);
    tree3.insert(58);
    tree3.insert(75);

    sop("inorder:");
    tree.inOrder();
    // sop(tree.delete(80));
    sop("inorder:");
    tree.inOrder();
    sop("height");
    sop(tree.height(tree.getRoot()));
    sop("isBlanced");
    sop(tree.isBlanced(tree.getRoot()));
    sop("create a tree using array");
    int[] arr = { 12, 20, 26, 32, 48, 55, 87, 95, 100 };
    BST t2 = new BST(BST.createBST(arr, 0, arr.length - 1));
    sop("inorder t2");
    t2.inOrder();
    sop("create level linked list");
    printTreeLevel(tree);
    sop("check BST");
    // sop(BinarySearchTree.checkBST(tree.getRoot()));
    // sop(tree.checkBST2Helper());
    sop(tree.checkBST3());

    BST.Node root = new BST.Node(100);
    BST.Node child1 = new BST.Node(50);
    BST.Node child2 = new BST.Node(40);
    BST.Node child3 = new BST.Node(60);
    root.leftChild = child1;
    root.rightChild = child3;
    child1.leftChild = child2;
    sop("create a no binary-search tree");
    BST notBST = new BST(root);
    printTreeLevel(notBST);
    sop("check BST");
    sop(notBST.checkBST3());

    sop("==============");
    sop("inorder");
    tree.inOrder();
    sop("==============");
    BST.Node ret = new BST.Node(0);
    tree.inorderSucc(tree.getRoot(), 58, new BST.Node(0), ret);
    sop(ret);

    // ===Question 4.6
    sop("find parent");
    try {
      sop(tree.getParent(tree.find(49)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    sop("find parent2");
    try {
      sop(BST.inorderSucc(tree.find(49)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    // =====Question 4.7
    sop("find common ancestor");
    sop(tree.commonAncestor(tree.getRoot(), 75, 58));
    sop("in not BST find common ancestor");
    sop(tree.commonAncestorForNotBST(tree.getRoot(), 75, 58));

    sop("find path to root");
    ArrayList<BST.Node> stack = new ArrayList<BST.Node>();
    tree.findPath(tree.getRoot(), 49, stack);
    for (BST.Node n : stack) {
      sop(n);
    }

    sop("find common ancestor");
    sop(tree.commonAncestorHelper(75, 111));
    // ==================question 4.8
    sop("is tree2 a subtree of tree1");
    sop(tree.isSubtree(tree.getRoot(), tree2.getRoot()));
    sop("is tree3 a subtree of tree1");
    sop(tree.isSubtree(tree.getRoot(), tree3.getRoot()));
    sop("is tree2 a subtree of tree3");
    sop(tree.isSubtree(tree3.getRoot(), tree2.getRoot()));
    sop("============substring");
    sop("abcdefghijklmn".indexOf("efg"));
    sop("abcdefghijklmn".contains("efg"));

    // ==question4.9
    sop("find sum path");
    BST sumPathTree = new BST();
    sumPathTree.insert(100);
    sumPathTree.insert(40);
    sumPathTree.insert(30);
    sumPathTree.insert(20);
    sumPathTree.insert(10);
    sumPathTree.insert(50);
    sumPathTree.insert(60);
    sumPathTree.insert(70);
    sumPathTree.insert(120);

    System.out.println("find the sum of path equals 220");
//    sumPathTree.findSumPathes(sumPathTree.getRoot(), 220);
    sumPathTree.findSumPath2(220);
  }
}
