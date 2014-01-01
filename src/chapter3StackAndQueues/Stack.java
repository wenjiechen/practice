package chapter3StackAndQueues;

public class Stack {

  Node top;
  Node end;

  public static class Node {
    int data;
    Node next;

    public Node(int d) {
      data = d;
      next = null;
    }
  }

  public Stack() {
    top = null;
    end = null;
  }

  public void push(Node n) {
    if (end == null && top == null) {
      end = n;
      top = n;
    } else {
      n.next = top;
      top = n;
    }
  }

  public boolean isEmpty() {
    return (top == null && end == null);
  }

  public Node pop() {
    if (isEmpty()) {
      return null;
    }
    if (top == end) {
      // last element
      Node ret = top;
      ret.next = null;
      top = null;
      end = null;
      return ret;
    }
    Node ret = top;
    top = top.next;
    ret.next = null;
    return ret;
  }

  public void printstack() {
    Node cur = top;
    while (cur != null) {
      print(cur.data + " ");
      cur = cur.next;
    }
  }

  static void print(Object x) {
    System.out.println(x);
  }
  
  public int peek(){
    return top.data;
  }
  
  public void shift(Stack s1,Stack s2){
    while(s1.isEmpty() == false){
      Node n = s1.pop();
      s2.push(n);
    }
  }
  
  public void sortStack(){
    Stack small = new Stack();
    Stack large = new Stack();
    while(isEmpty()==false){
      Node n = pop();
      if(small.isEmpty() == true){
        small.push(n);
      }
      else if(n.data > small.peek()){
        small.push(n);        
      }
      else{
        while(small.isEmpty() == false){
          if(small.peek() > n.data){
            large.push(small.pop());
          }else{
            break;
          }
        }
        small.push(n);
        shift(large,small);
      }
//      print("stack is");
//      this.printstack();
//      print("small is ");
//      small.printstack();
//      print("large is ");
//      large.printstack();
    }
    shift(small,large);
    shift(large,this);
  }

  public static void main(String... args) {
    Stack s = new Stack();
    Node n = new Node(3);
    s.push(n);
    n = new Node(1);
    s.push(n);
    n = new Node(5);
    s.push(n);
    n = new Node(2);
    s.push(n);
    n = new Node(4);
    s.push(n);
    s.printstack();
    print("======");
    s.sortStack();
    s.printstack();
  }

}
