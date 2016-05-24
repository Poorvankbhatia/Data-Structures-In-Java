package graphs;

import java.util.Scanner;
import utility.Stack;

/**
 * Created by poorvank on 4/7/15.
 */

public class ShortestPathDAG {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {

        DirectedVertex[] vArray = Input.directedGraphInput();
        boolean[] visited = new boolean[vArray.length];


        topologicalSort(vArray, visited);
    }

    public static void topologicalSort(DirectedVertex[] vArray, boolean[] visited) {

        Stack stack = new Stack();


        //Topological Sort in stack object
        for (int i = 0; i < vArray.length; i++) {
            if (!visited[i]) {
                sortUtil(vArray, visited, stack, i);
            }
        }

        System.out.println("Enter source : ");
        int source = new Scanner(System.in).nextInt();
        shortestPath(vArray, visited, stack, source);

    }

    private static void sortUtil(DirectedVertex[] vArray, boolean[] visited, Stack stack, int i) {

        visited[i] = true;

        for (DirectedVertex v : vArray[i].adjacentVertices) {
            if (!visited[v.info]) {
                sortUtil(vArray, visited, stack, v.info);
            }
        }

        stack.push(vArray[i]);
    }


    private static void shortestPath(DirectedVertex[] vArray, boolean[] visited, Stack stack, int source) {

        int[] dist = new int[vArray.length];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = INF;
        }

        dist[source] = 0;

        while (!stack.isEmpty()) {

            DirectedVertex u = (DirectedVertex) stack.pop();

            if (dist[u.info] != INF) {

                for (DirectedVertex i : u.adjacentVertices) {

                    if (dist[i.info] > dist[u.info] + i.weight) {

                        dist[i.info] = dist[u.info] + i.weight;

                    }

                }

            }

        }

        System.out.println("Distance from source " + source);

        for (int i = 0; i < vArray.length; i++) {

            System.out.print(dist[i] == INF ? "INF" : dist[i] + " ");

        }


    }

}


/*

We initialize distances to all vertices as infinite and distance to source as 0, then we find a topological sorting
of the graph. Topological Sorting of a graph represents a linear ordering of the graph 
Once we have topological order (or linear representation), 
we one by one process all vertices in topological order.
For every vertexToConsider being processed, we update distances of its adjacent using distance of current vertexToConsider.


Following is complete algorithm for finding shortest distances.
1) Initialize dist[] = {INF, INF, ….} and dist[s] = 0 where s is the source vertexToConsider.
2) Create a toplogical order of all vertices.
3) Do following for every vertexToConsider u in topological order.
………..Do following for every adjacent vertexToConsider v of u
………………if (dist[v] > dist[u] + weight(u, v))
………………………dist[v] = dist[u] + weight(u, v)
 */