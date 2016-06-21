/*

To improve the LazyPrimMST, we might try to delete ineligible edges from the priority queue, so that the priority queue contains
only the crossing edges between tree vertices and non-tree vertices. But we can eliminate even more edges.
The key is to note that our only interest is in the minimal edge
from each non-tree vertex to a tree vertex. When we add a vertex v to the tree, the only possible change with respect to each nontree
vertex w is that adding v brings w closer than before to the tree. In short, we do not need to keep on the priority queue all
of the edges from w to tree vertices—we just need to keep track of the minimum-weight edge and check whether the addition
of v to the tree necessitates that we update that minimum (because of an edge v-w that has lower weight), which we can do
as we process each edge in v’s adjacency list. In other words, we maintain on the priority queue just one edge for each non-tree vertex w :
the shortest edge that connects it to the tree. Any longer edge connecting w to the tree will become
ineligible at some point, so there is no need to keep it on the priority queue.

Data files:   http://algs4.cs.princeton.edu/43mst/tinyEWG.txt
              http://algs4.cs.princeton.edu/43mst/mediumEWG.txt
              http://algs4.cs.princeton.edu/43mst/largeEWG.txt

 */


package graphs.mst;

import utility.GetInputFile;
import utility.Queue;
import utility.edgeClasses.Edge;
import utility.graphClasses.EdgeWeightedGraph;
import utility.priorityQueueClasses.IndexMinPriorityQueue;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by poorvank on 19/06/16.
 */
public class PrimMst {

    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] distanceTo;
    private IndexMinPriorityQueue<Double> pq;


    public PrimMst(EdgeWeightedGraph G) {
        int vertexCount = G.getVertexCount();
        edgeTo = new Edge[vertexCount];
        distanceTo = new double[vertexCount];
        pq = new IndexMinPriorityQueue<>(vertexCount);
        marked = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            distanceTo[i] = Double.POSITIVE_INFINITY;
        }
        for (int v = 0; v < vertexCount; v++) {
            if (!marked[v]) {
                prim(G, v);
            }
        }

    }


    // run Prim's algorithm in graph G, starting from vertex s
    public void prim(EdgeWeightedGraph G, int s) {

        distanceTo[s] = 0.0;
        pq.insert(s, distanceTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.deleteMinimum();
            scan(G, v);
        }

    }

    // scan vertex v
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {                  // v-w is obsolete edge
                continue;
            }
            if (distanceTo[w] > e.getWeight()) {
                edgeTo[w] = e;
                distanceTo[w] = e.getWeight();
                if (pq.containsIndex(w)) {
                    pq.changeKey(w, distanceTo[w]);
                } else {
                    pq.insert(w, distanceTo[w]);
                }

            }

        }
    }

    public Iterable<Edge> edges() {

        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }


    public Double getWeight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.getWeight();
        }
        return weight;
    }


    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(GetInputFile.getFile("largeEWG.txt")));
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

            PrimMst mst = new PrimMst(G);


            System.out.println("MST weight = " + mst.getWeight());

            for (Edge e : mst.edges()) {
                System.out.println(e.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*

PrimMST  implements Prim’s algorithm using our index
priority queue. It replaces the data
structures marked[] and mst[] in LazyPrimMST by two vertex-indexed arrays edgeTo[]
and distTo[], which have the following properties:
■ If v is not on the tree but has at least one edge connecting it to the tree, then
edgeTo[v] is the shortest edge connecting v to the tree, and distTo[v] is the
weight of that edge.
■ All such vertices v are maintained on the index priority queue, as an index v associated
with the weight of edgeTo[v].
The key implications of these properties is that the minimum key on the priority queue
is the weight of the minimal-weight crossing edge, and its associated vertex v is the next
to add to the tree. The marked[] array is not needed, since the condition !marked[w]
is equivalent to the condition that distTo[w] is infinite (and that edgeTo[w] is null).
To maintain the data structures, PrimMST takes an edge v from the priority queue, then
checks each edge v-w on its adjacency list. If w is marked, the edge is ineligible; if it is not
on the priority queue or its weight is lower than the current best-known edgeTo[w],
the code updates the data structures to establish v-w as the best-known way to connect
v to the tree.

Proposition N. The eager version of P rim’s algorithm uses extra
space proportional to V and time proportional to E log V
(in the worst case) to compute the MST of a connected edgeweighted
graph with E edges and V vertices.

Proof: The number of edges on the priority queue is at most
V, and there are three vertex-indexed arrays, which implies the
space bound. The algorithm uses V insert operations, V delete
the minimum operations, and (in the worst case) E change priority
operations. These counts, coupled with the fact that our
heap-based implementation of the index priority queue implements
all these operations in time proportional to log V , imply the time bound.

 */
