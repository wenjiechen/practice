package algorithms4graph;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;

public class GraphTest {

  private static Graph G;
  
  private static void testLibCode() {
    In in = new In("D:\\algorithm4JARandData\\algs4-data\\tinyG.txt");
    edu.princeton.cs.algs4.Graph G = new edu.princeton.cs.algs4.Graph(in);
    StdOut.println(G);
  }
  
  private static void testGraph(){
    In in = new In("D:\\algorithm4JARandData\\algs4-data\\tinyG.txt");
    G = new Graph(in);
    System.out.println(G);
  }

  public static void main(String[] args) {
//    testLibCode();
    testGraph();
  }
}
