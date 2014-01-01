package chapter2;

class Node {
  int data;
  Node next;
  
  public Node(int data){
    this.data = data;
    next = null;
  }
  
  public void displayNode(){
    System.out.print(" " + data);
  }
}
