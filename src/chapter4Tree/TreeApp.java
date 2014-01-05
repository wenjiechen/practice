package chapter4Tree;

import java.util.LinkedList;

import chapter4Tree.BinarySearchTree.Node;

public class TreeApp {
  private static void sop(Object x) {
    System.out.println(x);
  }

  public static void printTreeLevel(BinarySearchTree tree) {
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
    BinarySearchTree tree = new BinarySearchTree();
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
    BinarySearchTree t2 = new BinarySearchTree(BinarySearchTree.createBST(arr,
        0, arr.length - 1));
    sop("inorder t2");
    t2.inOrder();
    sop("create level linked list");
    printTreeLevel(tree);
    sop("check BST");
    // sop(BinarySearchTree.checkBST(tree.getRoot()));
    // sop(tree.checkBST2Helper());
    sop(tree.checkBST3());

    BinarySearchTree.Node root = new BinarySearchTree.Node(100);
    BinarySearchTree.Node child1 = new BinarySearchTree.Node(50);
    BinarySearchTree.Node child2 = new BinarySearchTree.Node(40);
    BinarySearchTree.Node child3 = new BinarySearchTree.Node(60);
    root.leftChild = child1;
    root.rightChild = child3;
    child1.leftChild = child2;
    sop("create a no binary-search tree");
    BinarySearchTree notBST = new BinarySearchTree(root);
    printTreeLevel(notBST);
    sop("check BST");
    // sop(BinarySearchTree.checkBST(notBST.getRoot()));
    // sop(notBST.checkBST2Helper());
    sop(notBST.checkBST3());

    sop("==============");
    sop("inorder");
    tree.inOrder();
    sop("==============");
    BinarySearchTree.Node ret = new BinarySearchTree.Node(0);
    tree.inorderSucc(tree.getRoot(), 58, new BinarySearchTree.Node(0), ret);
    sop(ret);

  }
}
