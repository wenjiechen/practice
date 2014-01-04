package chapter4Tree;

public class TreeApp {
  private static void sop(Object x){
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
    sop(tree.delete(80));
    sop("inorder:");
    tree.inOrder();
    
    
  }

}