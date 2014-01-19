package algorithms4graph;

import java.util.LinkedList;

public class CC {
  // connected components
  private boolean[] marked;
  // id for a connected component
  private int[] id;
  // the number of connected component
  private int count;

  public CC(Graph G) {
    marked = new boolean[G.V()];
    id = new int[G.V()];
    for (int s = 0; s < G.V(); s++) {
      if (!marked[s]) {
        dfs(G, s);
        count++;
      }
    }
  }

  private void dfs(Graph G, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : G.adj(v)) {
      if (!marked[w])
        dfs(G, w);
    }
  }

  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }

  /**
   * 
   * @param v
   * @return the id of connected component of vertex v
   */
  public int id(int v) {
    return id[v];
  }

  public int count() {
    System.out.print("connected component number: ");
    return count;
  }

  public LinkedList<Integer>[] components(Graph G) {
    LinkedList<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[count];
    for (int i = 0; i < count; i++)
      components[i] = new LinkedList<Integer>();
    for (int v = 0; v < G.V(); v++)
      components[id[v]].add(v);
    return components;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
