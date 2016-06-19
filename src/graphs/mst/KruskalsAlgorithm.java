/*

What is Minimum Spanning Tree?
Given a connected and undirected graph, a spanning tree of that graph is a subgraph that 
is a tree and connects all the vertices together. A single graph can have many different spanning trees. 
A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is a 
spanning tree with weight less than or equal to the weight of every other spanning tree. The weight of a spanning tree is the 
sum of weights given to each edge of the spanning tree.

How many edges does a minimum spanning tree has?
A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.

Below are the steps for finding MST using Kruskal’s algorithm

1. Sort all the edges in non-decreasing order of their weight.

2. Pick the smallest edge. Check if it forms a cycle with the spanning tree 
formed so far. If cycle is not formed, include this edge. Else, discard it.  

3. Repeat step#2 until there are (V-1) edges in the spanning tree.

The algorithm is a Greedy Algorithm.
The Greedy Choice is to pick the smallest weight edge that does not cause a cycle in the MST constructed so far


 */

package graphs.mst;

import java.util.Arrays;

/**
 * Created by poorvank on 2/13/16.
 */

class KGraph {

    int V, E;
    Edge edge[];


    public KGraph(int vertexCount, int edgeCount) {

        V = vertexCount;
        E = edgeCount;

        edge = new Edge[E];
        for (int i = 0; i < edgeCount; ++i)
            edge[i] = new Edge();

    }

    public int find(Subset[] subsets, int i) {

        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;

    }

    public void union(Subset[] subsets, int x, int y) {

        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else if (subsets[yRoot].rank > subsets[xRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public void KruskalMST() {

        Edge[] result = new Edge[V];

        //If we are not allowed to change the given graph, we
        // can create a copy of array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        Subset subsets[] = new Subset[V];
        for (int i = 0; i < V; ++i)
            subsets[i] = new Subset();

        //e for storing edge result
        //i for sorted edges`
        int e = 0, i = 0;
        // Create V subsets with single elements
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < V - 1) {

            Edge nextEdge = edge[i++];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // If including this edge does't cause cycle, include it
            // in result and increment the index of result for next edge
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }

        }

        System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " +
                    result[i].weight);

    }

    class Edge implements Comparable<Edge> {

        int src, dest, weight;

        public int compareTo(Edge compareEdge) {

            return this.weight - compareEdge.weight;

        }

    }

    class Subset {
        int rank, parent;
    }


}

public class KruskalsAlgorithm {

    public static void main(String[] args) {
        
    
         /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        KGraph graph = new KGraph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        graph.KruskalMST();


    }

}

/*

O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting, we iterate through all edges and apply 
find-union algorithm. The find and union operations can take atmost O(LogV) time. So overall complexity is O(ELogE + ELogV) time. 
The value of E can be atmost V^2, so O(LogV) are O(LogE) same. Therefore, overall time complexity is O(ElogE) or O(ElogV)

 */
