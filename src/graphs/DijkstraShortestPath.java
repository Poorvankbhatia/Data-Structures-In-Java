package graphs;

import utility.EdgeClasses.DirectedEdge;
import utility.graphClasses.EdgeWeightedDigraph;
import utility.PriorityQueueClasses.IndexMinPriorityQueue;
import utility.Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 21/05/16.
 */
public class DijkstraShortestPath {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPriorityQueue<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph G,int source) {

        int size = G.getVertexCount();
        edgeTo = new DirectedEdge[size];
        distTo = new double[size];
        pq = new IndexMinPriorityQueue<>(size);

        for (int i=0;i<size;i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        pq.insert(source,0.0);

        while (!pq.isEmpty()) {
            int v = pq.deleteMinimum();
            for (DirectedEdge e : G.getAdj(v)) {
                  relaxEdge(e);
            }
        }

    }

    private void relaxEdge(DirectedEdge e) {
        int v = e.from(),w=e.to();
        if(distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
            if(pq.containsIndex(w)) {
                pq.changeKey(w,distTo[w]);
            }
            else {
                pq.insert(w,distTo[w]);
            }
        }
    }

    public double getDistTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {

        if(!hasPathTo(v)) {
            return null;
        }
        DirectedEdge lastEdge = edgeTo[v];
        Stack<DirectedEdge> stack = new Stack<>();
        while (lastEdge!=null) {
            stack.push(lastEdge);
            lastEdge = edgeTo[lastEdge.from()];
        }

        return stack;
    }

    public static void main(String[] args) {
        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/src/inputFiles/largeEWG.txt"));
            String line = null;
            int count = 0;
            int vertexCount = Integer.parseInt(br.readLine());
            int lineCount = Integer.parseInt(br.readLine());

            EdgeWeightedDigraph G = new EdgeWeightedDigraph(vertexCount);
            for (int i=0;i<lineCount;i++) {
                String[] input = (br.readLine()).split(" ");
                double weight = Double.parseDouble(input[2]);
                int v = Integer.parseInt(input[0]);
                int w = Integer.parseInt(input[1]);
                DirectedEdge e = new DirectedEdge(v,w,weight);
                G.addEdge(e);
            }

            int s =0;
            DijkstraShortestPath sp = new DijkstraShortestPath(G,s);

            for (int t = 0; t < G.getVertexCount(); t++) {
                if (sp.hasPathTo(t)) {
                    System.out.printf("%d to %d (%.2f)  ", s, t, sp.getDistTo(t));
                    for (DirectedEdge e : sp.pathTo(t)) {
                        System.out.print(e + "   ");
                    }
                    System.out.println();
                }
                else {
                    System.out.printf("%d to %d no path present\n", s, t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*

Dijkstra’s algorithm
is an analogous scheme to compute an SPT. We begin by initializing dist[s] to
0 and all other distTo[] entries to positive infinity, then we relax and add to the tree a
non-tree vertex with the lowest distTo[] value, continuing until all vertices are on the tree
or no non-tree vertex has a finite distTo[] value.

Dijkstra’s algorithm uses extra space proportional to V
and time proportional to E log V (in the worst case) to compute the SPT rooted at
a given source in an edge-weighted digraph with E edges and V vertices.

Both algorithms Prims and above
build a rooted tree by adding an edge to a growing tree: Prim’s adds next the non-tree
vertex that is closest to the tree; Dijkstra’s adds next the non-tree vertex that is closest
to the source. The marked[] array is not needed, because the condition !marked[w]
is equivalent to the condition that distTo[w] is infinite. In other words, switching to
undirected graphs and edges and omitting the references to distTo[v] in the relax()


Prims is for undirected and dijkstra is for directed graph


_____________________________-------------------------________________________________


***********************************IMPORTANT*******************************************

All-pairs shortest paths. Given an edge-weighted digraph, support queries of the
form Given a source vertex s and a target vertex t, is there a path from s to t? If so,
find a shortest such path (one whose total weight is minimal).
The surprisingly compact implementation at right below solves the all-pairs shortest
paths problem, using time and space proportional to E V log V. It builds an array of
DijkstraSP objects, one for each vertex as the source. To answer a client query, it uses
the source to access the corresponding single-source shortest-paths object and then
passes the target as argument to the query.


public class DijkstraAllPairsSP
{
    private DijkstraSP[] all;
    DijkstraAllPairsSP(EdgeWeightedDigraph G)
    {
        all = new DijkstraSP[G.V()]
        for (int v = 0; v < G.V(); v++)
        all[v] = new DijkstraSP(G, v);
    }

    Iterable<Edge> path(int s, int t)
    { return all[s].pathTo(t); }

    double dist(int s, int t) {
    return all[s].distTo(t);
    }
}


 */