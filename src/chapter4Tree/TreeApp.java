package chapter4Tree;

public class TreeApp {

  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree();                   
    tree.insert(10);
    tree.insert(5);
    tree.insert(12);
    tree.insert(7);
    tree.insert(3);
    tree.insert(6);
    tree.insert(11);
    
    tree.inOrder();
   
    try {
      System.out.println(tree.find(11).data == 11);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
