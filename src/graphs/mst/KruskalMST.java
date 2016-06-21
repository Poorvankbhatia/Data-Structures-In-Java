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

import graphs.unionFind.UF;
import utility.GetInputFile;
import utility.Queue;
import utility.edgeClasses.Edge;
import utility.graphClasses.EdgeWeightedGraph;
import utility.priorityQueueClasses.MinPriorityQueue;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by poorvank on 2/13/16.
 */


public class KruskalMST {

    private double weight;
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph G) {
        MinPriorityQueue<Edge> pq = new MinPriorityQueue<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.getVertexCount());
        while (!pq.isEmpty() && mst.getSize()<G.getVertexCount()-1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(!uf.isConnected(v,w)) {
                uf.union(v,w);
                mst.enqueue(e);
                weight += e.getWeight();
            }
        }

    }


    public double getWeight() {
        return weight;
    }

    public Iterable<Edge> getEdges() {
        return mst;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(GetInputFile.getFile("mediumEWG.txt")));
            int vertexCount = Integer.parseInt(br.readLine());
            int lineCount = Integer.parseInt(br.readLine());

            EdgeWeightedGraph G = new EdgeWeightedGraph(vertexCount);
            for (int i = 0; i < lineCount; i++) {
                String[] input = (br.readLine()).split(" ");
                double weight = Double.parseDouble(input[2]);
                int v = Integer.parseInt(input[0]);
                int w = Integer.parseInt(input[1]);
                Edge e = new Edge(v, w, weight);
                G.addEdge(e);
            }

            KruskalMST mst = new KruskalMST(G);


            System.out.println("MST weight = " + mst.getWeight());

            for (Edge e : mst.getEdges()) {
                System.out.println(e.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



/*

O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting, we iterate through all edges and apply 
find-union algorithm. The find and union operations can take atmost O(LogV) time. So overall complexity is O(ELogE + ELogV) time. 
The value of E can be atmost V^2, so O(LogV) are O(LogE) same. Therefore, overall time complexity is O(ElogE) or O(ElogV)


The second MST algorithm
that we consider in detail is to process the edges in order of
their weight values (smallest to largest), taking for the MST
(coloring black) each edge that does not form a cycle with
edges previously added, stopping after adding V1 edges
have been taken. The black edges form a forest of trees that
evolves gradually into a single tree, the MST. This method
is known as Kruskal’s algorithm:


Proposition O. Kruskal’s algorithm computes the
MST of any edge-weighted connected graph.


Proof: Immediate from Proposition K. If the next
edge to be considered does not form a cycle with black
edges, it crosses a cut defined by the set of vertices
connected to one of the edge’s vertices by tree edges
(and its complement). Since the edge does not create a
cycle, it is the only crossing edge seen so far, and since
we consider the edges in sorted order, it is a crossing
edge of minimum weight. Thus, the algorithm is successively
taking a minimal-weight crossing edge, in accordance
with the greedy algorithm.


Proposition N (continued). Kruskal’s algorithm uses space proportional to E and
time proportional to E log E (in the worst case) to compute the MST of an edgeweighted
connected graph with E edges and V vertices.

Proof: The implementation uses the priority-queue constructor that initializes the
priority queue with all the edges, at a cost of at most E compares.
After the priority queue is built, the argument is the same as for Prim’s algorithm.
The number of edges on the priority queue is at most E, which gives the space
bound, and the cost per operation is at most 2 lg E compares, which gives the time
bound. Kruskal’s algorithm also performs up to E find() and V union() operations,
but that cost does not contribute to the E log E order of growth of the total
running time


 */
