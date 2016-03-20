/*

Bridges in a graph
An edge in an undirected connected graph is a bridge iff removing it disconnects the graph. 
For a disconnected undirected graph, definition is similar, a bridge is an edge removing which increases number of connected components.
Like Articulation Points,bridges represent vulnerabilities in a connected network and are useful for designing reliable networks. 
For example, in a wired computer network, an articulation point indicates the critical computers and a bridge indicates the
critical wires or connections.


A O(V+E) algorithm to find all Bridges
The idea is similar to O(V+E) algorithm for Articulation Points. We do DFS traversal of the given graph. In DFS tree an edge 
(u, v) (u is parent of v in DFS tree) is bridge 
if there does not exit any other alternative to reach u or an ancestor of u from subtree rooted with v. As discussed in the 
previous post, the value low[v] indicates earliest visited vertex reachable from subtree rooted with v. 
The condition for an edge (u, v) to be a bridge is, “low[v] > disc[u]”.

 */

package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 3/11/16.
 */

class BGNode {

    int info;
    List<Integer> adjacencyList;

    public BGNode(int info, ArrayList<Integer> adjacencyList) {
        this.info = info;
        this.adjacencyList = adjacencyList;
    }
}

class BGraph {

    public static int time = 0;
    public int vertexCount;
    public List<BGNode> nodeList = new ArrayList<>();

    public BGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        for (int i = 0; i < vertexCount; i++) {
            nodeList.add(new BGNode(i, new ArrayList<Integer>()));
        }
    }

    public void addEdge(int a, int b) {

        nodeList.get(a).adjacencyList.add(b);
        nodeList.get(b).adjacencyList.add(a);

    }

    public void findBridgeUtil(int source, int[] low, int[] parent, int[] discovery, boolean[] visited) {

        visited[source] = true;

        low[source] = discovery[source] = ++time;

        for (Integer neighbour : nodeList.get(source).adjacencyList) {

            if (!visited[neighbour]) {

                parent[neighbour] = source;
                findBridgeUtil(neighbour, low, parent, discovery, visited);

                low[source] = Math.min(low[source], low[neighbour]);

                if (low[neighbour] > discovery[source]) {
                    System.out.println("Bridge is " + neighbour + " - " + source);
                }

            } else if (neighbour != parent[source]) {
                low[source] = Math.min(low[source], discovery[neighbour]);
            }

        }

    }

    public void findBridges() {

        int[] low = new int[vertexCount];
        int[] discovery = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                findBridgeUtil(i, low, parent, discovery, visited);
            }
        }

    }

}

public class Bridges {

    public static void main(String[] args) {

        BGraph g1 = new BGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.findBridges();

    }

}

/*

Time Complexity: The above function is simple DFS with additional arrays.
So time complexity is same as DFS which is O(V+E) for adjacency list representation of graph.

 */