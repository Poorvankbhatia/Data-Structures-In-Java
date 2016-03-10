/*

Ford-Fulkerson Algorithm for Maximum Flow Problem
Given a graph which represents a flow network where every edge has a capacity. Also given two vertices source ‘s’ and sink ‘t’ in 
the graph, find the maximum possible flow from s to t with following constraints:

a) Flow on an edge doesn’t exceed the given capacity of the edge.

b) Incoming flow is equal to outgoing flow for every vertex except s and t.


Ford-Fulkerson Algorithm 
The following is simple idea of Ford-Fulkerson algorithm:
1) Start with initial flow as 0.
2) While there is a augmenting path from source to sink. 
           Add this path-flow to flow.
3) Return flow.
Time Complexity: Time complexity of the above algorithm is O(max_flow * E). We run a loop while there is an augmenting path. 
In worst case, we may add 1 unit flow in every iteration. Therefore the time complexity becomes O(max_flow * E).

How to implement the above simple algorithm? 
Let us first define the concept of Residual Graph which is needed for understanding the implementation.
Residual Graph of a flow network is a graph which indicates additional possible flow. If there is a path from source to sink in 
residual graph, then it is possible to add flow. Every edge of a residual graph has a value called residual capacity which is equal 
to original capacity of the edge minus current flow. Residual capacity is basically the current capacity of the edge.
Let us now talk about implementation details. Residual capacity is 0 if there is no edge between to vertices of residual graph. 
We can initialize the residual graph as original graph as there is no initial flow and initially residual capacity is equal to 
original capacity. To find an augmenting path, we can either do a BFS or DFS of the residual graph. We have used BFS in below 
implementation. Using BFS, we can find out if there is a path from source to sink. BFS also builds parent[] array. Using the 
parent[] array, we traverse through the found path and find possible flow through this path by finding minimum residual capacity 
along the path. We later add the found path flow to overall flow.
The important thing is, we need to update residual capacities in the residual graph. We subtract path flow from all edges along 
the path and we add path flow along the reverse edges We need to add path flow along reverse edges because may later need to send 
flow in reverse direction

 */


package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 3/10/16.
 */
public class FordFulkersonMaxFlow {
    
    private static int vertexCount = 6;
    
    private boolean bfs(int[] parent,int source,int destination,int[][] graph) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = -1;
        boolean[] visited = new boolean[vertexCount];
        
        while (!queue.isEmpty()) {
            int current = queue.remove();
            visited[current] =true;
            
            for (int v=0;v<vertexCount;v++) {
                if(graph[current][v]>0 && !visited[v]) {
                    parent[v] = current;
                    visited[v] =true;
                    queue.add(v);
                }
            }
            
        }
        
        return visited[destination];
        
    }
    
    
    private int maxFlow(int[][] graph,int source,int destination) {
        
        int[] parent = new int[vertexCount];
        
        int[][] residualGraph = new int[vertexCount][vertexCount];
        
        for (int i=0;i<vertexCount;i++) {
            for (int j=0;j<vertexCount;j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }
        
        int maxFlow = 0;
        
        while (bfs(parent,source,destination,residualGraph)) {
            
            int pathFlow = Integer.MAX_VALUE;
            
            //Find minimum path flow
            for (int v = destination;v!=source;v= parent[v]) {
                
                int u = parent[v];
                pathFlow = Math.min(pathFlow,residualGraph[u][v]);
            }
            
            //Update Residual capacities of edges and reverse edges
            for (int v=destination;v!=source;v=parent[v]) {

                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            
            maxFlow +=pathFlow;
            
        }
        
        return maxFlow;
    }
    
    public static void main(String[] args) {
        
        int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
                                     {0, 0, 10, 12, 0, 0},
                                     {0, 4, 0, 0, 14, 0},
                                     {0, 0, 9, 0, 0, 20},
                                     {0, 0, 0, 7, 0, 4},
                                     {0, 0, 0, 0, 0, 0}  };
        
        FordFulkersonMaxFlow fordFulkerson = new FordFulkersonMaxFlow();
        
        System.out.println("Maximum flow is = " + fordFulkerson.maxFlow(graph,0,5));
        
    }
    
}


/*

Youtube Links to be visited:
https://www.youtube.com/watch?v=Tl90tNtKvxs
https://www.youtube.com/watch?v=GiN3jRdgxU4
https://www.youtube.com/watch?v=-8MwfgB-lyM

Concept of backward edge:


Suppose you have a source S, a sink T, and two intermediate nodes A and B, and paths from S to A and A to T, 
and from S to B and B to T of capacity 1.

  A
 / \
S   T
 \ /
  B
Obviously there is a flow of weight 2 using each edge. Now, add an edge from A to B of capacity 1.

  A
 /|\
S V T
 \|/
  B
This doesn't increase the maximum flow, but it gives you a chance to mess up when you create a flow incrementally. 
You could start with S->A->B->T.

  A
 /|
S V T
  |/
  B
In order to find the maximum flow, you need to be able to decrease the flow from A to B. You can do this by increasing the 
flow along S->B->A->T.

  A         A         A
 /|         |\       / \
S V T  +  S ^ T  =  S   T
  |/       \|        \ /
  B         B         B
Going backwards along A->B means you decrease the flow from A to B.

 */