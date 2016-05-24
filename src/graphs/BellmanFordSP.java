/*
Given a graph and a source vertex src in graph,
find shortest paths from src to all vertices in the given graph. The graph may contain negative weight edges.

 */

package graphs;

import utility.*;
import utility.Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 24/05/16.
 */
public class BellmanFordSP {

    private boolean[] onQueue;                  //currently on queue
    private double[] distTo;                    //dist[v]=shortest path from s to v
    private DirectedEdge[] edgeTo;              //last s-v shortest path edge
    private Queue<Integer> queue;               //vertices to relax
    private int cost;                           //Number of passes/calls to relax
    private Iterable<DirectedEdge> cycle;       //Negative Cycle(If no such, it is null)

    public BellmanFordSP(EdgeWeightedDigraph G, int source) {
        int size = G.getVertexCount();
        edgeTo = new DirectedEdge[size];
        distTo = new double[size];
        onQueue = new boolean[size];
        cost = 0;
        for (int i=0;i<size;i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;
        queue = new Queue<>();
        queue.enqueue(source);

        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQueue[v] = false;
            relaxEdge(G,v);
        }

    }


    private void relaxEdge(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.getAdj(v)) {
            int w = e.to();
            if(distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if(!onQueue[w]) {
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if(cost++ % G.getVertexCount()==0) {
                findCycle();
                if(hasNegativeCycle()){
                    return;
                }
            }
        }
    }


    public boolean hasNegativeCycle() {
        return cycle!=null;
    }

    public Iterable<DirectedEdge> getCycle() {
        return cycle;
    }

    public double distTo(int v) {
        if(hasNegativeCycle()) {
            throw new UnsupportedOperationException("Has a negative cycle");
        }
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(hasNegativeCycle()) {
            throw new UnsupportedOperationException("Has negative cycle");
        }
        Stack<DirectedEdge> path = new Stack<>();
        DirectedEdge e = edgeTo[v];
        while (e!=null) {
            path.push(e);
            e = edgeTo[e.from()];
        }
        return path;
    }

    private void findCycle() {
        int newVertexCount = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(newVertexCount);
        for (int v=0;v<newVertexCount;v++) {
            if(edgeTo[v]!=null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.getCycle();
    }

    public static void main(String[] args) {


        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/src/inputFiles/BFInput"));
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
            int s = 0;
            BellmanFordSP sp = new BellmanFordSP(G, s);


            if (sp.hasNegativeCycle()) {
                System.out.println("Has a negative cycle \n");
                for (DirectedEdge e : sp.getCycle())
                    System.out.println(e);
            }

            else {
                for (int v = 0; v < G.getVertexCount(); v++) {
                    if (sp.hasPathTo(v)) {
                        System.out.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                        for (DirectedEdge e : sp.pathTo(v)) {
                            System.out.print(e + "   ");
                        }
                        System.out.println();
                    }
                    else {
                        System.out.printf("%d to %d           no path\n", s, v);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


/*

solves the singlesource
shortest-paths problem from a given source s for any edge-weighted digraph
with V vertices and no negative cycles reachable from s: Initialize distTo[s]
to 0 and all other distTo[] values to infinity. Then, considering the digraph’s edges
in any order, relax all edges. Make V such passes.
Proof: For any vertex t that is reachable from s consider a specific shortest path
from s to t: v0->v1->...->vk, where v0 is s and vk is t. Since there are no negative
cycles, such a path exists and k can be no larger than V1. We show by induction
on i that after the ith pass the algorithm computes a shortest path from s to vi. The
base case (i = 0) is trivial. Assuming the claim to be true for i, v0->v1->...->vi
is a shortest path from s to vi, and distTo[vi] is its length. Now, we relax every
vertex in the ith pass, including vi, so distTo[vi+1] is no greater than distTo[vi]
plus the weight of vi->vi+1. Now, after the ith pass, distTo[vi+1] must be equal
to distTo[vi] plus the weight of vi->vi+1. It cannot be greater because we relax
every vertex in the ith pass, in particular vi, and it cannot be less because that is
the length of v0->v1->...->vi+1, a shortest path. Thus the algorithm computes a
shortest path from s to vi+1 after the (i+1)st pass.

Proposition W (continued). The Bellman-Ford algorithm takes time proportional
to EV and extra space proportional to V.
Proof: Each of the V passes relaxes E edges.
This method is very general, since it does not specify the order in which the edges are
relaxed. We now restrict attention to a less general method where we always relax all
the edges leaving any vertex (in any order). The following code exhibits the simplicity
of the approach:

for (int pass = 0; pass < G.V(); pass++)
    for (v = 0; v < G.V(); v++)
        for (DirectedEdge e : G.adj(v))
            relax(e);


The only edges that could lead to a change in distTo[] are
those leaving a vertex whose distTo[] value
changed in the previous pass. To keep track of
such vertices, we use a FIFO queue.


Proposition Y. The queue-based implementation of the Bellman-Ford algorithm
solves the shortest-paths problem from a given source s (or finds a negative cycle
reachable from s) for any edge-weighted digraph with V vertices, in time proportional
to EV and extra space proportional to V, in the worst case.
Proof: If there is no negative cycle reachable from s, the algorithm terminates after
relaxations corresponding to the (V–1)st pass of the generic algorithm described in
Proposition X (since all shortest paths have fewer than V–1 edges). If there does
exist a negative cycle reachable from s, the queue never empties. After relaxations
corresponding to the Vth pass of the generic algorithm described in Proposition
X the edgeTo[] array has a path with a cycle (connects some vertex w to itself) and
that cycle must be negative, since the path from s to the second occurrence of w
must be shorter that the path from s to the first occurrence of w for w to be included
on the path the second time. In the worst case, the algorithm mimics the general
algorithm and relaxes all E edges in each of V passes.

Negative cycle detection. This implementation BellmanFordSP checks for negative
cycles to avoid an infinite loop. We can apply the code that does this check to provide
clients with the capability to check for and extract negative cycles.

The digraph has a negative cycle reachable from the source if and only if the queue is nonempty after
the Vth pass through all the edges. Moreover, the subgraph of edges in our edgeTo[]
array must contain a negative cycle. Accordingly, to implement negativeCycle() we
build an edge-weighted digraph from the edges in edgeTo[] and look for a cycle in
that digraph.





 */