/*

Given a digraph, put the vertices in
order such that all its directed edges point from a vertex
earlier in the order to a vertex later in the order (or
report that doing so is not possible).

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
 vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

for 6 vertices and edges as 
5,0
5,2
4,0
4,1
2,3
3,1

5 4 2 3 1 0

 */

package graphs;

import utility.Digraph;
import utility.Stack;

/**
 * Created by poorvank on 4/7/15.
 */
public class TopologicalSort {

    private Iterable<Integer> order;

    public static void main(String[] args) {


        TopologicalSort ts = new TopologicalSort();
        Digraph digraph = new Digraph(6);
        digraph.addEdge(5,0);
        digraph.addEdge(5,2);
        digraph.addEdge(4,0);
        digraph.addEdge(4,1);
        digraph.addEdge(2,3);
        digraph.addEdge(3,1);


        if(!DetectCycleGraph.isCyclic(digraph)) {
            DepthFirstOrders depthFirstOrders = new DepthFirstOrders(digraph);
            ts.order = depthFirstOrders.getReversePost();
        }

        for (Integer v : ts.order) {
            System.out.print(v + " ");
        }


    }


}


/*

In topological sorting, we use a temporary stack. We don’t print the vertexToConsider immediately, we first recursively call
topological sorting for all its adjacent vertices, then push it to a stack.
Finally, print contents of stack. Note that a vertexToConsider is pushed to stack only
when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.

Cycles in digraphs. If job x must be completed before job y, job y before job z, and
job z before job x, then someone has made a mistake, because those three constraints
cannot all be satisfied. In general, if a precedence-constrained scheduling problem has
a directed cycle, then there is no feasible solution. To check for such errors, we need to
be able to solve the following problem:

Proposition E. A digraph has a topological order if and only if it is a DAG.
Proof: If the digraph has a directed cycle, it has no topological order. Conversely,
the algorithm that we are about to examine computes a topological order for any
given DAG.



 */