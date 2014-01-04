package chapter4Tree;

import java.util.LinkedList;

import chapter4Tree.BinarySearchTree.Node;

public class TreeApp {
  private static void sop(Object x) {
    System.out.println(x);
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

    sop("inorder:");
    tree.inOrder();
//    sop(tree.delete(80));
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
    int level = 0;
    for (LinkedList<Node> list : tree.createLevelLinkedList()) {
      System.out.print("level " + (level++) + ":");
      for(Node n : list){
        System.out.print(", " + n);
      }
      sop("");
      
    }

  }
}
















