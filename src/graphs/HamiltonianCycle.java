/*

Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once. 
A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge (in graph) 
from the last vertex to the first vertex of the Hamiltonian Path. Determine whether a given graph contains 
Hamiltonian Cycle or not. If it contains, then print the path. Following are the input and output of the required function.

Input:
A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation 
of the graph. A value graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j] is 0.

Output:
An array path[V] that should contain the Hamiltonian Path. path[i] should represent the ith vertex in the Hamiltonian Path. 
The code should also return false if there is no Hamiltonian Cycle in the graph.

For example, a Hamiltonian Cycle in the following graph is {0, 1, 2, 4, 3, 0}. There are more Hamiltonian Cycles in the graph 
like {0, 3, 4, 2, 1, 0}

(0)--(1)--(2)
 |   / \   |
 |  /   \  | 
 | /     \ |
(3)-------(4)
And the following graph doesn’t contain any Hamiltonian Cycle.

(0)--(1)--(2)
 |   / \   |
 |  /   \  | 
 | /     \ |
(3)      (4) 

 */

package graphs;

/**
 * Created by poorvank on 11/12/15.
 */
public class HamiltonianCycle {

    private static int VERTEX_COUNT = 0;

    public static void main(String[] args) {

        int graph[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0}};
        VERTEX_COUNT = graph.length;

        detectCycle(graph);
    }

    private static void printSolution(int path[]) {
        System.out.println("Solution Exists: Following" +
                " is one Hamiltonian Cycle");
        for (int i = 0; i < path.length; i++)
            System.out.print(" " + path[i] + " ");

        System.out.println(" " + path[0] + " ");
    }

    private static boolean detectCycle(int[][] graph) {

        int[] path = new int[graph.length];
        for (int i = 0; i < path.length; i++) {
            path[i] = -1;
        }

        path[0] = 0;

        if (!detectCycleUtil(graph, path, 1)) {
            System.out.print("No hamiltonian cycle exists ");
            return false;
        }
        printSolution(path);
        return true;

    }

    private static boolean detectCycleUtil(int[][] graph, int[] path, int pos) {

        if (pos == graph.length) {

            return graph[path[pos - 1]][path[0]] == 1;

        }
        for (int v = 1; v < graph.length; v++) {

            if (isSafe(v, pos, path, graph)) {

                path[pos] = v;

                if (detectCycleUtil(graph, path, pos + 1)) {
                    return true;
                }

                path[pos] = -1;
            }

        }

        return false;

    }

    private static boolean isSafe(int v, int pos, int[] path, int[][] graph) {

        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

}

/*

Backtracking Algorithm
Create an empty path array and add vertexToConsider 0 to it. Add other vertices, starting from the vertexToConsider 1. Before adding a vertexToConsider,
 check for whether it is adjacent to the previously added vertexToConsider and not already added. If we find such a vertexToConsider, we add the
 vertexToConsider as part of the solution. If we do not find a vertexToConsider then we return false.

 */