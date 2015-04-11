/*

Eulerian Path is a path in graph that visits every edge exactly once.
Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.

 */

package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/9/15.
 */

class EUNode {

    int info;
    List<Integer> adjacentVertices;

    public EUNode(int info) {
        this.info = info;
        adjacentVertices = new ArrayList<>();
    }

}

class EUGraph {

    int vCount;
    List<EUNode> nodeList;

    public EUGraph(int vCount) {

        this.vCount = vCount;
        nodeList = new ArrayList<>();

        for (int i = 0; i < vCount; i++) {
            nodeList.add(new EUNode(i));
        }

    }

    public void addEdge(int a, int b) {

        //Undirected Graph
        nodeList.get(a).adjacentVertices.add(b);
        nodeList.get(b).adjacentVertices.add(a);

    }

}

public class EulerianUndirected {

    public static void main(String[] args) {

        EUGraph g1 = new EUGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        isEulerian(g1);

        EUGraph g2 = new EUGraph(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        isEulerian(g2);

        EUGraph g3 = new EUGraph(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        isEulerian(g3);

        // Let us create a graph with 3 vertices
        // connected in the form of cycle
        EUGraph g4 = new EUGraph(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        isEulerian(g4);

        // Let us create a graph with all veritces
        // with zero degree
        EUGraph g5 = new EUGraph(3);
        isEulerian(g5);

    }

    private static void isEulerian(EUGraph graph) {

        if (isConnected(graph)) {

            int odd = 0;

            for (int i = 0; i < graph.vCount; i++) {
                if (graph.nodeList.get(i).adjacentVertices.size() % 2 != 0) {
                    odd++;
                }
            }

            if (odd > 2) {
                System.out.println("Not eulerian");
            } else {
                if (odd == 0) {
                    System.out.println("Eulerian");
                } else {
                    System.out.println("Semi Eulerian");
                }
            }
        }


    }

    private static boolean isConnected(EUGraph graph) {

        int i = 0;
        for (EUNode node : graph.nodeList) {
            if (node.adjacentVertices.size() > 0) {
                break;
            }
            i++;
        }

        //If there are no edges in the graph
        if (i == graph.vCount) {
            return true;
        }

        boolean[] visited = new boolean[graph.vCount];

        dfsUtil(i, graph, visited);

        for (int j = 0; j < graph.vCount; j++) {

            if (!visited[i] && graph.nodeList.get(i).adjacentVertices.size() > 0) {
                return false;
            }

        }
        return true;


    }

    private static void dfsUtil(int i, EUGraph graph, boolean[] visited) {

        visited[i] = true;

        for (int v : graph.nodeList.get(i).adjacentVertices) {
            if (!visited[v]) {
                dfsUtil(v, graph, visited);
            }
        }

    }

}


/*

How to find whether a given graph is Eulerian or not?
The problem is same as following question. “Is it possible to draw a given graph without lifting pencil from the paper
 and without tracing any of the edges more than once”.

A graph is called Eulerian if it has an Eulerian Cycle and called Semi-Eulerian if it has an Eulerian Path. 
The problem seems similar to Hamiltonian Path which is NP complete problem for a general graph. Fortunately, 
we can find whether a given graph has a Eulerian Path or not in polynomial time. In fact, we can find it in O(V+E) time.

Following are some interesting properties of undirected graphs with an Eulerian path and cycle.
 We can use these properties to find whether a graph is Eulerian 
 
Eulerian Cycle
An undirected graph has Eulerian cycle if following two conditions are true.
….a) All vertices with non-zero degree are connected. We don’t care about vertices with zero degree because they don’t belong to Eulerian Cycle or Path (we only consider all edges).
….b) All vertices have even degree.

Eulerian Path
An undirected graph has Eulerian Path if following two conditions are true.
….a) Same as condition (a) for Eulerian Cycle
….b) If zero or two vertices have odd degree and all other vertices have even degree. Note that only one vertex with
 odd degree is not possible in an undirected graph (sum of all degrees is always even in an undirected graph)

Note that a graph with no edges is considered Eulerian because there are no edges to traverse.

How does this work?
In Eulerian path, each time we visit a vertex v, we walk through two unvisited edges with one end point as v. 
Therefore, all middle vertices in Eulerian Path must have even degree. For Eulerian Cycle, any vertex can be middle vertex,
therefore all vertices must have even degree.


 */