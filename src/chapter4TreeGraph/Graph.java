package chapter4TreeGraph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
  private int[][] adjM;
  private Node[] adjL;

  static class Node {
    int vertex;
    Node next;
  }

  public Graph() {
    adjM = new int[][] { { 0, 1, 1, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 },
        { 1, 0, 1, 0 } };
    for (int i = 0; i < 4; i++) {

    }
  }

  public void BFS(int v) {
    boolean[] visited = { false, false, false, false };
    Queue<Integer> q = new LinkedList<Integer>();
    q.add(v);
    while (q.isEmpty() == false) {
      int cur = q.poll();
      if (visited[cur] == false) {
        System.out.println("visit " + cur);
        visited[cur] = true;
      }
      for (int j = 0; j < 4; j++) {
        if (adjM[cur][j] != 0 && visited[j] == false) {
          q.add(j);
        }
      }
    }
  }

  private boolean[] visited = { false, false, false, false };

  public void DFS(int v) {
    System.out.println(v);
    visited[v] = true;
    for(int i = 0; i< adjM[0].length; i++){
      if(adjM[v][i] != 0 && visited[i]==false){
        DFS(i);
      }
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
//    g.BFS(3);
    g.DFS(3);
  }

}
