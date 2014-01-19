package algorithms4graph;

import java.util.Collections;
import java.util.LinkedList;

import edu.princeton.cs.introcs.In;

public class Cycle {
  //how to find cycles path in the graph????????

  static class Pair {
    int start;
    int end;

    public Pair(int s, int e) {
      start = s;
      end = e;
    }

    @Override
    public String toString() {
      return new String("start: " + start + ", end: " + end);
    }
  }

  private boolean[] marked;
  private boolean hasCycle;
  // private Map<Integer, int[]> edgesTo;
  private LinkedList<LinkedList<Integer>> cyclePaths;
  LinkedList<Pair> cyclePairs;

  public Cycle(Graph G) {
    marked = new boolean[G.V()];
    cyclePairs = new LinkedList<Pair>();
    hasCycle = false;
    // edgesTo = new HashMap<Integer, int[]>();
    cyclePaths = new LinkedList<LinkedList<Integer>>();
    for (int s = 0; s < G.V(); s++) {
      if (!marked[s]) {
        dfs(G, s, s, s, new int[G.V()]);
      }
    }
  }

  private void dfs(Graph G, int v, int parent, int source, int[] edgeTo) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        // if (edgesTo.containsKey(source) == false) {
        // int[] edgeTo = new int[G.V()];
        // edgesTo.put(source, edgeTo);
        // }
        // int[] edgeTo = edgesTo.get(source);
        // edgeTo[w] = v;
        edgeTo[w] = v;
        dfs(G, w, v, source, edgeTo);
      } else if (w != parent) {
        hasCycle = true;
        // recode the cycle path
        cyclePairs.add(new Pair(w, v));
        LinkedList<Integer> pathV = pathTo(source, v, edgeTo);
        LinkedList<Integer> pathW = pathTo(source, w, edgeTo);
        Collections.addAll(pathV, pathW.removeFirst());
        cyclePaths.add(pathV);
      }
    }
  }

  private LinkedList<Integer> pathTo(int source, int v, int[] edgeTo) {
    if (!marked[v])
      return null;
    LinkedList<Integer> path = new LinkedList<Integer>();
    for (int x = v; x != source; x = edgeTo[x])
      path.addFirst(x);
    path.addFirst(source);
    System.out.print("path: ");
    System.out.println(path);
    return path;
  }

  public boolean hasCycle() {
    return hasCycle;
  }

  public LinkedList<Pair> cyclePairs() {
    System.out.println("cycle pairs: ");
    if (!hasCycle)
      return null;
    return cyclePairs;
  }

  public LinkedList<LinkedList<Integer>> cyclePaths() {
    System.out.println("cycle paths: ");
    if (!hasCycle)
      return null;
    return cyclePaths;

  }

  private static Graph G;

  private static void createGraph() {
    In in = new In("D:\\algorithm4JARandData\\algs4-data\\tinyG.txt");
    // In in = new In("D:\\algorithm4JARandData\\algs4-data\\mediumG.txt");
    G = new Graph(in);
    System.out.println(G);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

  }

}
