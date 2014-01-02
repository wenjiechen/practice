package chapter3StackAndQueues;

public class TwoStackQueue {
  
  Stack newest = new Stack();
  Stack oldest = new Stack();
  
  public void enque(int val){
    Stack.Node node = new Stack.Node(val);
    newest.push(node);
  }
  
  public boolean isEmpty(){
    return newest.isEmpty() && oldest.isEmpty();
  }
  
  public Stack.Node deque() throws Exception{
    if(isEmpty()){
      throw new Exception("no element");
    }
    
    if(oldest.isEmpty()==true){
      Stack.shift(newest, oldest);
    }
    return oldest.pop();
  }
  
  public void displayQueue(){
    
  }
  
  public static void main(String[] args) throws Exception{
    TwoStackQueue q = new TwoStackQueue();
    q.enque(1);
    q.enque(2);
    q.enque(3);
    System.out.println(q.deque().data);
    q.enque(4);
    q.enque(5);
    System.out.println(q.deque().data);
    System.out.println(q.deque().data);
    System.out.println(q.deque().data);
    System.out.println(q.deque().data);
    System.out.println(q.deque().data);
    
    
  }
  

}
