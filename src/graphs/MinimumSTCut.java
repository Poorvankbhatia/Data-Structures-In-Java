/*

Find minimum s-t cut in a flow network
In a flow network, an s-t cut is a cut that requires the source ‘s’ and the sink ‘t’ to be in different subsets, 
and it consists of edges going from the source’s side to the sink’s side. The capacity of an s-t cut is defined by the sum of 
capacity of each edge in the cut-set. (Source: Wiki)
The problem discussed here is to find minimum capacity s-t cut of the given network. Expected output is all edges of the minimum cut.

The max-flow min-cut theorem states that in a flow network, the amount of maximum flow is equal to capacity of the minimum cut.
See network-problem-pdf in notes.


Following are steps to print all edges of minimum cut.

1) Run Ford-Fulkerson algorithm and consider the final residual graph.

2) Find the set of vertices that are reachable from source in the residual graph.

3) All edges which are from a reachable vertex to non-reachable vertex are minimum cut edges. Print all such edges.

 */

package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 3/14/16.
 */

class FFAlgorithm {
    
    private static int vertexCount = 6;


    private static boolean bfs(int[][] graph,int[] parent,int source,int destination) {

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
    
    private static void  dfs(int[][] residualGraph,int source,boolean[] visited) {
        
        visited[source] = true;
        for (int i=0;i<vertexCount;i++) {
            if(!visited[i] && residualGraph[source][i]>0) {
                dfs(residualGraph,i,visited);
            }
        }
        
    }
    
    public static void findMinCutEdges(int[][] graph,int source,int destination) {

        int[] parent = new int[vertexCount];

        int[][] residualGraph = new int[vertexCount][vertexCount];

        for (int i=0;i<vertexCount;i++) {
            for (int j=0;j<vertexCount;j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }
        
        //Construct the residual graph
        while (bfs(residualGraph,parent,source,destination)) {

            int pathFlow = Integer.MAX_VALUE;

            for (int v=destination;v!=source;v=parent[v]) {

                int u = parent[v];
                pathFlow = Math.min(pathFlow,residualGraph[u][v]);

            }

            for (int v=destination;v!=source;v=parent[v]) {

                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            
        }
        
        boolean[] visited = new boolean[vertexCount];
        dfs(residualGraph,source,visited);
        
        System.out.println("Min Cut Edges are:");
        //Print Minimum cut edges
        for (int i=0;i<vertexCount;i++) {
            for (int j=0;j<vertexCount;j++) {
                if(visited[i] && !visited[j] && graph[i][j]>0) {
                    System.out.println(i+ "  " +j);
                }
            }
        }
        
    }
    
}

public class MinimumSTCut {
    
    public static void main(String[] args) {

        int[][] graph = { {0, 16, 13, 0, 0, 0},
                          {0, 0, 10, 12, 0, 0},
                          {0, 4, 0, 0, 14, 0},
                          {0, 0, 9, 0, 0, 20},
                          {0, 0, 0, 7, 0, 4},
                          {0, 0, 0, 0, 0, 0}};
        
        
        FFAlgorithm.findMinCutEdges(graph,0,5);
        
    }
    
}


/*

Another form question:

You are staying in a housing society and there are many people staying in different flats. 
Flats are connected to each other in many ways. There is a thief in one of the flats. You need to close down all the routes for
the theif to reach your flat. What is the minimum cost using which this can be done?

 */