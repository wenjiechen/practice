package algorithms4graph;

import java.util.LinkedList;

public class BreadthFirstPaths {

  private boolean[] marked;
  private int[] edgeTo;
  private final int s;

  public BreadthFirstPaths(Graph G, int s) {
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    this.s = s;
    bfs(G, s);
  }

  private void bfs(Graph G, int s) {
    marked[s] = true;
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.addLast(s);
    while (!queue.isEmpty()) {
      int v = queue.removeFirst();
      for (int w : G.adj(v)) {
        if (!marked[w]) {
          marked[w] = true;
          edgeTo[w] = v;
          queue.addLast(w);
        }
      }
    }
  }

  public boolean hasPahtTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!marked[v])
      return null;
    LinkedList<Integer> path = new LinkedList<Integer>();
    for (int x = v; x != s; x = edgeTo[x]) {
      path.addFirst(x);
    }
    path.addFirst(s);
    return path;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
