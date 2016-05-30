package graphs;

import utility.Graph;

/**
 * Created by poorvank on 4/1/15.
 */
public class DFS_rec {

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
        //Since we do not know if the graph is connected or not

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

 */