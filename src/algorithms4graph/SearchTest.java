package algorithms4graph;

import java.util.LinkedList;

import edu.princeton.cs.introcs.In;

public class SearchTest {

  private static Graph G;

  private static void createGraph() {
    //window path
//  In in = new In("D:\\algorithm4JARandData\\algs4-data\\tinyG.txt");
//  In in = new In("D:\\algorithm4JARandData\\algs4-data\\mediumG.txt");
        
//  ubuntu path
    In in = new In("/home/wenjie/algorithm4jar_dataset/algs4-data/tinyG.txt");
    G = new Graph(in);
    System.out.println(G);
  }

  private static void testDfsPath() {
    System.out.println("test dfs path");
    DepthFirstPaths dfsp = new DepthFirstPaths(G, 9);
    System.out.println(dfsp.pathTo(12));
  }

  private static void testBfs() {
    System.out.println("bsf test");
    BreadthFirstPaths bfs = new BreadthFirstPaths(G, 0);
    System.out.println(bfs.pathTo(3));
  }

  private static void testCC() {
    System.out.println("connected component");
    CC cc = new CC(G);
    System.out.println(cc.count());
    for (LinkedList<Integer> cl : cc.components(G)) {
      System.out.println(cl);
    }
  }

  private static void testCycle() {
    System.out.println("test has cycle of a graph");
    Cycle c = new Cycle(G);
    System.out.println(c.hasCycle());
    for (Cycle.Pair cp : c.cyclePairs()) {
      System.out.println(cp);
    }
    for(LinkedList<Integer> cp : c.cyclePaths()){
      System.out.println(cp);
    }
  }

  public static void main(String[] args) {
    createGraph();
//     testDfsPath();
    // testBfs();
    // testCC();
    testCycle();
  }
}
