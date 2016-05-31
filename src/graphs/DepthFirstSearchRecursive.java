package graphs;

import utility.graphClasses.Graph;

/**
 * Created by poorvank on 4/1/15.
 */
public class DepthFirstSearchRecursive {

    public static void main(String[] args) {

        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        recursive(graph,0);

    }

    private static void recursive(Graph graph, int v) {

        boolean[] visited = new boolean[graph.getVertexCount()];

        // Call the recursive helper function to print DFS traversal
        // starting from all vertices one by one    
        // Since we do not know if the graph is connected or not

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[v]) {
                recurUtil(graph, v, visited);
            }
        }

    }

    private static void recurUtil(Graph graph, int v, boolean[] visited) {

        visited[v] = true;
        System.out.print(v + " ");

        for (Integer vertexNo : graph.getAdj(v)) {

            if (!visited[vertexNo]) {
                recurUtil(graph, vertexNo, visited);
            }
        }

    }

}


/*

Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.

DFS marks all the vertices connected to a
given source in time proportional to the sum of their degrees.

Proof: First, we prove that the algorithm marks all the vertices
connected to the source s (and no others).

Every marked
vertex is connected to s, since the algorithm finds vertices
only by following edges.

Now, suppose that some unmarked
vertex w is connected to s. Since s itself is marked, any path
from s to w must have at least one edge from the set of marked
vertices to the set of unmarked vertices, say v-x. But the algorithm
would have discovered x after marking v, so no such
edge can exist, a contradiction. The time bound follows because
marking ensures that each vertex is visited once (taking
time proportional to its degree to check marks).

 */