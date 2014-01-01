package chapter2;

public class LinkListTest {
  public static void println(Object x) {
    System.out.println(x);
  }

  public static void main(String[] args) {
    /*
     * LinkedList ls = new LinkedList(); ls.insertFirst(1); ls.insertFirst(2);
     * ls.insertFirst(5); ls.insertFirst(3); ls.insertFirst(1);
     * ls.insertFirst(4); ls.insertFirst(2); ls.insertFirst(5);
     * ls.insertFirst(6); ls.insertFirst(4); println("=============soul1");
     * ls.displayList(); // ls.deleteDups(); ls.deleteDups2(); ls.displayList();
     * println("=============soul2"); Node ret=null;
     * ls.nthToLast(ls.getHead(),2,ret); System.out.println(ret);
     * ls.nthToLast(ls.getHead(),2); ret = ls.nthToLast(ls.getHead(),2,ls.new
     * IntWrapper()); ret.displayNode(); println("=============soul3"); Node n =
     * ls.find(2); ls.deleteNode(n); ls.displayList();
     * println("=============soul4"); LinkedList ls2 = new LinkedList();
     * ls2.insertFirst(3); ls2.insertFirst(12); ls2.insertFirst(5);
     * ls2.insertFirst(7); ls2.insertFirst(13); // ls2.pratition(10);
     * ls2.pratition2(10); ls2.displayList();
     */
    LinkedList l1 = new LinkedList();
    l1.insertLast(9);
    l1.insertLast(9);
    l1.insertLast(9);
    l1.insertLast(9);

    LinkedList l2 = new LinkedList();
    l2.insertLast(5);
    l2.insertLast(5);
    l2.insertLast(5);

    LinkedList ret = LinkedList.addTwoNum(l1, l2);
    ret.displayList();

    LinkedList ret2 = new LinkedList().setHead(LinkedList.addLists(
        l1.getHead(), l2.getHead(), 0));
    
    ret2.displayList();
  }

}
