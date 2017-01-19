package graphs.mst;

import utility.*;
import utility.edgeClasses.Edge;
import utility.priorityQueueClasses.MinPriorityQueue;
import utility.graphClasses.EdgeWeightedGraph;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by poorvank on 16/05/16.
 */
public class LazyPrimMST {

    private Queue<Edge> mst;
    private double weight;
    private MinPriorityQueue<Edge> pq;
    private boolean[] marked;

    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        pq = new MinPriorityQueue<>();
        marked = new boolean[G.getVertexCount()];
        weight = 0;
        for (int v=0;v<G.getVertexCount();v++) {
              if(!marked[v]) {
                  prim(G,v);
              }
        }

    }

    private void prim(EdgeWeightedGraph G,int s) {
        scanVertex(G,s);
        while (!pq.isEmpty()) {
            Edge min = pq.delMin();
            int v = min.either();
            int w = min.other(v);
            if(marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(min);
            weight += min.getWeight();
            if(!marked[v]) {
                scanVertex(G,v);
            }
            if(!marked[w]) {
                scanVertex(G,w);
            }

        }
    }

    // add all edges e incident to v onto pq if the other endpoint has not yet been scanned
    private void scanVertex(EdgeWeightedGraph G,int v) {
        if(!marked[v]) {
            marked[v] = true;
            for (Edge e : G.adj(v)) {
                if(!marked[e.other(v)]) {
                    pq.insert(e);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(GetInputFile.getFile("mediumEWG.txt")));
            String line = null;
            int count = 0;
            int vertexCount = Integer.parseInt(br.readLine());
            int lineCount = Integer.parseInt(br.readLine());

            EdgeWeightedGraph G = new EdgeWeightedGraph(vertexCount);
            for (int i=0;i<lineCount;i++) {
                String[] input = (br.readLine()).split(" ");
                double weight = Double.parseDouble(input[2]);
                int v = Integer.parseInt(input[0]);
                int w = Integer.parseInt(input[1]);
                Edge e = new Edge(v,w,weight);
                G.addEdge(e);
            }

            LazyPrimMST mst = new LazyPrimMST(G);

            System.out.println("MST weight = " + mst.weight);

            for (Edge e : mst.edges()) {
                System.out.println(e.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


/*

attach a new edge to a single growing tree at each step. Start with any vertex as a single-vertex
tree; then add V - 1 edges to it, always taking next (coloring black) the minimumweight
edge that connects a vertex on the tree to a vertex not yet
on the tree (a crossing edge for the cut defined by tree vertices).


Proposition L. Prim’s algorithm computes the MST of any
connected edge-weighted graph.
Proof: Immediate from Proposition K. The growing tree
defines a cut with no black edges; the algorithm takes the
crossing edge of minimal weight, so it is successively coloring
edges black in accordance with the greedy algorithm.


Proposition M. The lazy version of Prim’s algorithm uses space proportional to E
and time proportional to E log E (in the worst case) to compute the MST of a connected
edge-weighted graph with E edges and V vertices.
Proof: The bottleneck in the algorithm is the number of edge-weight comparisons
in the priority-queue methods insert() and delMin(). The number of edges on
the priority queue is at most E, which gives the space bound. In the worst case,
the cost of an insertion is ~lg E and the cost to delete the minimum is ~2 lg E. Since at most E edges are inserted and at most E are
deleted, the time bound follows.


 */
