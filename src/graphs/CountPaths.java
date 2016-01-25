/*

DYNAMIC PROGRAMMING SOLUTION EXISTS

Count all possible walks from a source to a destination with exactly k edges
Given a directed graph and two vertices ‘u’ and ‘v’ in it, count all possible walks from ‘u’ to ‘v’
 with exactly k edges on the walk.

The graph is given as adjacency matrix representation where value of graph[i][j] as 1 
indicates that there is an edge from vertex i to vertex j and a value 0 indicates no edge from i to j.

 */

package graphs;

/**
 * Created by poorvank on 4/6/15.
 */
public class CountPaths {


    public static void main(String[] args) {

        int graph[][] = new int[][]{{0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        System.out.println(countWalks(0, 3, 2, graph));

    }

    private static int countWalks(int source, int destination, int edgeCount, int[][] graph) {

        if (edgeCount < 0) {
            return 0;
        }

        //Reach the destination immediately after source
        if (source == destination && edgeCount == 0) {
            return 1;
        }
        //Reduce an iteration step. If only one count is left, check if current source has its immediate neighbour as destination
        if (edgeCount == 1 && graph[source][destination] == 1) {
            return 1;
        }

        int count = 0;

        for (int i = 0; i < 4; i++) {
            if (graph[source][i] == 1) {
                count += countWalks(i, destination, edgeCount - 1, graph);
            }
        }

        return count;
    }

}


/*

A simple solution is to start from u, go to all adjacent vertices and recur for adjacent vertices with k as k-1,
 source as adjacent vertexToConsider and destination as v.
 
 The worst case time complexity of the above function is O(Vk) where V is the number of vertices 
 in the given graph. We can simply analyze the time complexity by drawing recursion tree. 
 The worst occurs for a complete graph. In worst case, every internal node of recursion tree would have exactly n children.

 */