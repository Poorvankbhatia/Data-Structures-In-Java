/*

solving the
 *  single-source shortest paths problem in edge-weighted directed acyclic
 *  graphs (DAGs). The edge weights can be positive, negative, or zero.

 */

package graphs;

import utility.edgeClasses.DirectedEdge;
import utility.graphClasses.EdgeWeightedDigraph;
import utility.Stack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 21/05/16.
 */
public class AcyclicSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicSP(EdgeWeightedDigraph G,int source) {
        int size = G.getVertexCount();
        distTo = new double[size];
        edgeTo = new DirectedEdge[size];
        for (int v=0;v<size;v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[source] = 0.0;

        Topological ts = new Topological(G);
        if(!ts.hasOrder()) {
            throw new IllegalArgumentException("Digraph not acyclic");
        }
        for (int v : ts.getOrder()) {
            for (DirectedEdge e : G.getAdj(v)) {
                relaxEdge(e);
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPath(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }


    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPath(v)) {
            return null;
        }
        Stack<DirectedEdge> stack = new Stack<>();
        DirectedEdge e = edgeTo[v];
        while (e!=null) {
            stack.push(e);
            e = edgeTo[e.from()];
        }
        return stack;
    }

    private void relaxEdge(DirectedEdge e) {
        int v = e.from(),w=e.to();
        if (distTo[w] > distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
        }
    }

    public static void main(String[] args) {
        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/src/inputFiles/newInput.txt"));
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

            int s =5;
            AcyclicSP sp = new AcyclicSP(G,s);

            for (int t = 0; t < G.getVertexCount(); t++) {
                if (sp.hasPath(t)) {
                    System.out.printf("%d to %d  ", s, t);
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

By relaxing vertices in t opological order, we can solve the singlesource
shortest-paths problem for edge-weighted DAGs in time proportional to
E + V.
Proof: Every edge v->w is relaxed exactly once, when v is relaxed, leaving
distTo[w] <= distTo[v] + e.weight(). This inequality holds until the algorithm
completes, since distTo[v] never changes (because of the topological order,
no edge pointing to v will be processed after v is relaxed) and distTo[w] can
only decrease (any relaxation can only decrease a distTo[] value). Thus, after all
vertices reachable from s have been added to the tree, the shortest-paths optimality
conditions hold, and Proposition Q applies. topological sort takes time proportional
to E + V, and the second relaxation pass completes the job by relaxing each edge
once, again in time proportional to E + V.


For shortest paths, the topological-sort-based method
is faster than Dijkstra’s algorithm by a factor proportional to the cost of the priorityqueue
operations in Dijkstra’s algorithm. Moreover, the proof of Proposition S does
not depend on the edge weights being nonnegative, so we can remove that restriction
for edge-weighted DAGs. Next, we consider implications of this ability to allow negative
edge weights, by considering the use of the shortest-paths model to solve two other
problems, one of which seems at first blush to be quite removed from graph processing.

**************************************IMPORTANT******************************************

Longest paths. Consider the problem of finding the longest path in an edge-weighted
DAG with edge weights that may be positive or negative.

Single-source longest paths in edge-weighted DAGs. Given an edge-weighted
DAG (with negative weights allowed) and a source vertex s, support queries of the
form: Is there a directed path from s to a given target vertex v? If so, find a longest
such path (one whose total weight is maximal).

The algorithm just considered provides a quick solution to this problem:
Proposition T. We can solve the longest-paths problem in edge-weighted DAGs in
time proportional to E + V.

Proof: Given a longest-paths problem, create a copy of the given edge-weighted
DAG that is identical to the original, except that all edge weights are negated. Then
the shortest path in this copy is the longest path in the original. To transform the
solution of the shortest-paths problem to a solution of the longest-paths problem,
negate the weights in the solution. The running time follows immediately from
Proposition S.

Using this transformation to develop a class AcyclicLP that finds longest paths in
edge-weighted DAGs is straightforward. An even simpler way to implement such a
class is to copy AcyclicSP, then switch the distTo[] initialization to Double.NEGATIVE_
INFINITY and switch the sense of the inequality in relax(). Either way, we get
an efficient solution to the longest-paths problem in edge-weighted DAGs. This result is
to be compared with the fact that the best known algorithm for finding longest simple
paths in general edge-weighted digraphs (where edge weights may be negative) requires
exponential time in the worst case

 */