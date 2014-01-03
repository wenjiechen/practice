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
    
    System.out.println("inorder:");
    tree.inOrder();
    System.out.println("preorder:");
    tree.preOrder();
    System.out.println("postorder:");
    tree.postOrder(tree.getRoot());
    
    try {
      System.out.println(tree.find(11).data == 11);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
