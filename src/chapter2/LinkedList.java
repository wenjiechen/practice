package chapter2;

import java.util.HashSet;
import java.util.Set;

class LinkedList {
  private Node first = null;
  int length = 0;

  public boolean isEmpty() {
    if (first == null) {
      return true;
    }
    return false;
  }

  public void insertFirst(int data) {
    Node newNode = new Node(data);
    newNode.next = first;
    first = newNode;
    length++;
  }

  public Node deleteFirst() {
    if (isEmpty() == false) {
      Node ret = first;
      first = first.next;
      length--;
      return ret;
    }
    return null;
  }

  public void displayList() {
    Node current = first;
    while (current != null) {
      current.displayNode();
      current = current.next;
      System.out.print(" -> ");
    }
    System.out.println();
  }

  public Node find(int x) {
    Node current = first;
    while (current != null) {
      if (current.data == x) {
        return current;
      }
      current = current.next;
    }
    return null;
  }

  public Node delete(int key) {
    Node current = first;
    Node pre = current;
    while (current != null) {
      if (current.data != key) {
        pre = current;
        current = current.next;
      } else {
        Node ret = current;
        pre.next = current.next;
        length--;
        return ret;
      }
    }
    return null;
  }

  public void length() {
    System.out.println(length);
  }

  // delete duplicates using Set
  public void deleteDups() {
    Set<Integer> set = new HashSet<Integer>();
    Node current = first;
    Node pre = current;
    while (current != null) {
      if (set.contains(current.data)) {
        pre.next = current.next;
      } else {
        set.add(current.data);
        pre = current;
      }
      current = current.next;
    }
  }

  // delete duplicates using runner cursor
  public void deleteDups2() {
    Node current = first;
    while (current != null) {
      Node runner = current;
      while (runner.next != null) {
        if (runner.next.data == current.data) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
      current = current.next;
    }
  }

  // parameters can't return value in recursion.
  public int nthToLast(Node head, int k, Node ret) {
    if (head == null) {
      return 0;
    }

    int i = nthToLast(head.next, k, ret) + 1;
    if (i == k) {
      ret = head;
    }
    return i;
  }

  public int nthToLast(Node head, int k) {
    if (head == null) {
      return 0;
    }
    int i = nthToLast(head.next, k) + 1;
    if (i == k) {
      System.out.println("found i = " + head.data);
    }
    return i;
  }

  class IntWrapper {
    public int value = 0;
  }

  // using wrapper class to realize
  public Node nthToLast(Node head, int k, IntWrapper i) {
    if (head == null) {
      return null;
    }
    Node nd = nthToLast(head.next, k, i);
    i.value = i.value + 1;
    if (i.value == k) {
      return head;
    }
    return nd;
  }

  // using Integer class can't realize recursion
  public Node nthToLast(Node head, int k, Integer i) {
    if (head == null) {
      return null;
    }
    Node nd = nthToLast(head.next, k, i);
    // Integer Object's value can't be modify
    // so i has to refer to a new object, which lead to
    // recursion fail.
    i = new Integer(i.intValue() + 1);
    if (i.intValue() == k) {
      return head;
    }
    return nd;
  }

  public Node getHead() {
    return first;
  }

  // solution 3: delete Node n, but can't access other node.
  public boolean deleteNode(Node n) {
    if (n == null || n.next == null) {
      return false;
    }
    Node next = n.next;
    n.data = next.data;
    n.next = next.next;
    return true;
  }

  // solution 4: partition for n, all nodes less than n
  // before node greater than n
  // iterate the list, insert the nodes which are less than n into the first
  public void pratition(int k) {
    Node cur = first;
    Node pre = cur;
    while (cur != null) {
      Node next = cur.next;
      if (cur.data < k) {
        // insert first
        pre.next = cur.next;
        cur.next = first;
        first = cur;
        cur = next;
      } else {
        pre = cur;
        cur = next;
      }
    }
  }

  public Node getLastNode() {
    Node cur = first;
    if (cur == null) {
      return null;
    }
    while (cur.next != null) {
      cur = cur.next;
    }
    return cur;
  }

  public void insertFirst(Node n) {
    n.next = first;
    first = n;
  }

  public void pratition2(int k) {
    LinkedList less = new LinkedList();
    LinkedList greater = new LinkedList();
    Node cur = first;
    while (cur != null) {
      if (cur.data < k) {
        less.insertFirst(cur.data);
      } else {
        greater.insertFirst(cur.data);
      }
      cur = cur.next;
    }
    // link the last node of less to greater's head
    greater.insertFirst(less.getLastNode());
    first = less.getHead();
  }

  public void insertLast(int data) {
    if (length == 0) {
      first = new Node(data);
      length++;
      return;
    }

    Node cur = first;
    while (cur != null && cur.next != null) {
      cur = cur.next;
    }
    cur.next = new Node(data);
    length++;
  }

  public static LinkedList addTwoNum(LinkedList l1, LinkedList l2) {
    Node cur1 = l1.getHead();
    Node cur2 = l2.getHead();
    LinkedList ret = new LinkedList();
    int carry = 0;

    while (cur1 != null && cur2 != null) {
      int sum = (cur1.data + cur2.data) + carry;
      carry = sum / 10;
      ret.insertLast(sum % 10);
      cur1 = cur1.next;
      cur2 = cur2.next;
    }

    Node cur = (cur1 != null) ? cur1 : cur2;

    while (cur != null) {
      int sum = carry + cur.data;
      carry = sum / 10;
      ret.insertLast(sum % 10);
      cur = cur.next;
    }

    if(carry != 0){
      ret.insertLast(carry);
    }
    
    return ret;
  }
  
  public static Node addLists(Node l1, Node l2, int carry){
    if(l1==null && l2==null && carry==0){
      return null;
    }
  }
  
  
}