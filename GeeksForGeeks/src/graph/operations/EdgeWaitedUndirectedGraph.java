/**
 * 
http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-minimum-spanning-tree-mst-2/
http://www.geeksforgeeks.org/greedy-algorithms-set-5-prims-mst-for-adjacency-list-representation/
TODO understand time complexity
 * 
 */
package graph.operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge{
	int u;
	int v;
	int length;
	
	Edge(int u,int v,int length){
		this.u=u;
		this.v=v;
		this.length=length;
	}
}

class EdgeLenComparator implements Comparator<Edge>{
	public int compare(Edge e1,Edge e2){
		if(e1.length<e2.length) return -1;
		else if(e1.length > e2.length) return 1;
		else return 0;
	}
}

public class EdgeWaitedUndirectedGraph {
	
	ArrayList<Edge>[] adj; 
	int n;
	int e;

	
	EdgeWaitedUndirectedGraph(String path){

		File file = new File(path);
		Scanner scan=null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		n = scan.nextInt();
		e = scan.nextInt();
		adj = (ArrayList<Edge>[])new ArrayList[n+1];
		for(int i=0;i<=n;i++){
			adj[i]= new ArrayList<Edge>();
		}
	
		for(int i=0;i<e;i++){
			int u=scan.nextInt();
			int v=scan.nextInt();
			int l=scan.nextInt();
			adj[u].add(new Edge(u,v,l));
			//adj[v].add(new Edge(u,v,l));
		}
	}
	
	void print(){
		for(int i =0;i<n;i++){
			System.out.print(i+" -> ");
			for(Edge e:adj[i]){
				System.out.print(e.u+","+e.v+","+e.length+" | ");
			}
			System.out.println();
		}
	}
	
	int getNxtEdge(Edge e,int s){
		int u=e.u;
		int v=e.v;
		return u==s?v:u;
	}
	
	void addEdgeToPQ(PriorityQueue<Edge> p,int start,boolean[] visited){
		for(Edge e: adj[start]){
			if(!visited[getNxtEdge(e,start)]){
				p.add(e);
			}else {
				continue;
			}
		}
	}
	void prim(int s){
		Comparator<Edge> comp = new EdgeLenComparator();
		PriorityQueue<Edge> p= new PriorityQueue<Edge>(e,comp);
		List<Edge> mst = new ArrayList<Edge>();
		boolean visited[]= new boolean[n+1];
		
		int start=s;
		visited[start]=true;
		addEdgeToPQ(p,start,visited);
		
		while(mst.size()!=n-1){
			Edge min = p.remove();
			if(visited[min.u] && visited[min.v]) continue;
			if(!visited[min.u]) {
				visited[min.u]=true;
				addEdgeToPQ(p,min.u,visited);
				}
			if(!visited[min.v]) {
				mst.add(min);
				visited[min.v]=true;
				addEdgeToPQ(p,min.v,visited);
			}
		}
		
		for(Edge e:mst){
			System.out.println(e.u+","+e.v+","+e.length);
		}
	}
	
	int find(int parent[],int i){
		if(parent[i]==-1)
			return i;
		return find(parent,parent[i]);
	}
	
	int union (int parent[] , int x,int y){
		int xval=find(parent,x);
		int yval = find(parent,y);
		if(xval==yval) return 1;
		else {
			parent[xval] = yval;
			return 0;
		}
	}

	int detectCycle(){
		int parent[]= new int[n+1];
		for(int i=0;i<n+1;i++){
			parent[i]=-1;
		}
		int val=0;
		for(int i=0;i<n+1;i++){
			for(Edge e: adj[i]){
				val = union(parent,e.u,e.v);
				if(val==1) break;
			}
			if(val==1) break;
		}
		return val;
	}
	
	int detectCycleForMst(List<Edge> mst){
		int parent[]= new int[n+1];
		for(int i=0;i<n+1;i++){
			parent[i]=-1;
		}
		int val=0;
		for(Edge e: mst){
			val = union(parent,e.u,e.v);
			if(val==1) break;
		} 
		return val;
	}
	
	void sortEdges(List<Edge> sort){
		
	}
	void kruskal(){
		List<Edge> mst = new ArrayList<Edge>();
		List<Edge> sorted = new ArrayList<Edge>();
		for(int i=0;i<n+1;i++)
			for(Edge e:adj[i])
				sorted.add(e);
		Comparator<Edge> comp = new EdgeLenComparator();
		Collections.sort(sorted,comp);
		
		for(Edge e: sorted){
			if(mst.size() == n-1) break;
			mst.add(e);
			if(detectCycleForMst(mst)==1) mst.remove(e);
		}
		
		for(Edge e:mst){
			System.out.println(e.u + "--"+ e.v);
		}
	}
	
	void testPrim(int val){
		prim(val);
	}
	
	void testCycle(){
		detectCycle();
	}
	
	void testKruskal(){
		kruskal();
	}
	public static void main(String[] args) {
		EdgeWaitedUndirectedGraph g1= new EdgeWaitedUndirectedGraph("edgeWeightedGraph.txt");
		g1.testPrim(1);
	}

}
