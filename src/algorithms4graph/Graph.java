package algorithms4graph;

import java.util.LinkedList;
import edu.princeton.cs.introcs.In;

public class Graph {
  private final int V;
  private int E;
  private LinkedList<Integer>[] adj;

  public Graph(int V) {
    this.V = V;
    this.E = 0;
    adj = (LinkedList<Integer>[]) new LinkedList[V];
    for (int i = 0; i < adj.length; i++) {
      adj[i] = new LinkedList<Integer>();
    }
  }

  public Graph(In in){
  	this(in.readInt());
  	int E = in.readInt();
  	for(int i = 0; i< E; i++){
  		// add a edge
  		int v = in.readInt();
  		int w = in.readInt();
  		addEdge(v,w);
  	}
  }

  public int V(){
  	return V;
  }

  public int E(){
  	return E;
  }

  public void addEdge(int v, int w){
  	adj[v].add(w);
  	adj[w].add(v);
  	E++;
  }

  public Iterable<Integer> adj(int v){
  	return adj[v];
  }

  public int degree(int v){
  	int degree = 0;
  	for(int vi : adj(v)){
  		degree++;
  	}
  	return degree;
  }

  public int maxDegree(){
  	int maxDegree = 0;
  	int vi = 0;
  	while(vi < V){
  		if(maxDegree < degree(vi))
  			maxDegree = degree(vi);
  	}
  	return maxDegree;
  }

  public int avgDegree(){
  	return E/V;
  }

  public int numberOfSelfLoops(){
  	int count = 0 ;
  	for(int v= 0; v<V; v++){
  		for(int w: adj(v)){
  			if(v==w) count++;
  		}
  	}
  	return count/2;
  }

  public String toString(){
  	String s  = V + " vertices, " + E + " edges\n";
  	for(int v = 0; v<V; v++){
  		s += v+ ": ";
  		for(int w : adj(v))
  			s += w + " ";
  		s += "\n";
  	}
  	return s;
  }

  public static void main(String[] args) {
  	
  }
}
