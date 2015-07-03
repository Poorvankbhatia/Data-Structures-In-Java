/*

Given a directed graph, find out whether the graph is strongly connected or not.
 A directed graph is strongly connected if there is a path between any two pair of vertices. 
 */

package graphPrograms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/13/15.
 */

class ConnectivityNode {

    public int info;
    public List<Integer> adjacentVertices;

    public ConnectivityNode(int info) {

        this.info = info;
        adjacentVertices = new ArrayList<>();
    }

}

class ConnectivityGraph {

    public int vCount;
    ConnectivityNode[] nodeList;

    public ConnectivityGraph(int vCount) {

        this.vCount = vCount;
        nodeList = new ConnectivityNode[vCount];
        for (int i = 0; i < vCount; i++) {
            nodeList[i] = new ConnectivityNode(i);
        }

    }

    public void addEdge(int a, int b) {

        nodeList[a].adjacentVertices.add(b);

    }


    public void dfsUtil(boolean[] visited, int i, ConnectivityNode[] nodes) {

        visited[i] = true;

        if (nodes[i] != null) {

            for (int a : nodes[i].adjacentVertices) {
                if (!visited[a]) {
                    dfsUtil(visited, a, nodes);
                }
            }

        }

        return;

    }

    public ConnectivityNode[] transpose() {

        ConnectivityNode[] list = new ConnectivityNode[vCount];

        for (ConnectivityNode node : nodeList) {

            for (int v : node.adjacentVertices) {

                if (list[v] != null) {

                    ConnectivityNode cn = list[v];
                    cn.adjacentVertices.add(node.info);
                    continue;

                }

                ConnectivityNode cn = new ConnectivityNode(v);

                cn.adjacentVertices.add(node.info);

                list[v] = cn;

            }

        }

        return list;

    }

    public boolean isSC() {

        boolean[] visited = new boolean[vCount];


        dfsUtil(visited, 0, nodeList);

        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }


        ConnectivityNode[] transposeList = transpose();


        dfsUtil(visited, 0, transposeList);

        for (boolean b : visited) {
            if (!b) {
                return false;
            }
        }

        return true;


    }

}

public class ConnectivityDG {

    public static void main(String[] args) {

        ConnectivityGraph g1 = new ConnectivityGraph(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        g1.addEdge(2, 4);
        g1.addEdge(4, 2);
        System.out.println(g1.isSC());

        ConnectivityGraph g2 = new ConnectivityGraph(4);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        System.out.println(g2.isSC());

    }

}


/*

Time Complexity: Time complexity of above implementation is sane as Depth First Search which is O(V+E) 
if the graph is represented using adjacency matrix representation.


A simple idea is to use a all pair shortest path algorithm like Floyd Warshall or find Transitive Closure of graph. 
Time complexity of this method would be O(v3).

We can also do DFS V times starting from every vertex. If any DFS, doesn’t visit all vertices, then graph is not 
strongly connected. This algorithm takes O(V*(V+E)) time which can be same as transitive closure for a dense graph.

A better idea can be Strongly Connected Components (SCC) algorithm. We can find all SCCs in O(V+E) time. 
If number of SCCs is one, then graph is strongly connected. The algorithm for SCC does extra work as it finds all SCCs.

Following is Kosaraju’s DFS based simple algorithm that does two DFS traversals of graph:
1) Initialize all vertices as not visited.

2) Do a DFS traversal of graph starting from any arbitrary vertex v. If DFS traversal doesn’t visit all vertices, 
then return false.

3) Reverse all arcs (or find transpose or reverse of graph)

4) Mark all vertices as not-visited in reversed graph.

5) Do a DFS traversal of reversed graph starting from same vertex v (Same as step 2). If DFS traversal doesn’t 
visit all vertices, then return false. Otherwise return true.

The idea is, if every node can be reached from a vertex v, and every node can reach v, then the graph is strongly 
connected. In step 2, we check if all vertices are reachable from v. In step 4, we check if all vertices can reach v 
(In reversed graph, if all vertices are reachable from v, then all vertices can reach v in original graph).



 */