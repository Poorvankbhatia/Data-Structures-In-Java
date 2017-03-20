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

import utility.graphClasses.Digraph;
import utility.graphClasses.EdgeWeightedDigraph;

/**
 * Created by poorvank on 4/7/15.
 */
public class Topological {

    private Iterable<Integer> order;
    private int[] rank;


    public Topological(Digraph G) {
        int size = G.getVertexCount();
        rank = new int[size];
        if(!DetectCycleDiGraph.isCyclic(G)) {
            DepthFirstOrders depthFirstOrders = new DepthFirstOrders(G);
            order= depthFirstOrders.getReversePost();
            int i =0;
            for (int v: order) {
                rank[v] = i++;
            }
        }
    }

    public Topological(EdgeWeightedDigraph G) {
        int size = G.getVertexCount();
        rank = new int[size];
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if(!finder.hasCycle()) {
            DepthFirstOrders depthFirstOrders = new DepthFirstOrders(G);
            order= depthFirstOrders.getReversePost();
            int i =0;
            for (int v: order) {
                rank[v] = i++;
            }
        }
    }

    public int getRank(int v) {
        return rank[v];
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public boolean hasOrder() {
        return order!=null;
    }

    private void validateVertex(int v) {
        if(v<0 || v>=rank.length) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (rank.length-1));
        }
    }

    public int rank(int v) {
        validateVertex(v);
        if(hasOrder()) {
            return rank[v];
        }
        return -1;
    }

     public static void main(String[] args) {



        Digraph digraph = new Digraph(6);
        digraph.addEdge(5,0);
        digraph.addEdge(5,2);
        digraph.addEdge(4,0);
        digraph.addEdge(4,1);
        digraph.addEdge(2,3);
        digraph.addEdge(3,1);

        Topological ts = new Topological(digraph);
        for (int vertex : ts.getOrder()) {
            System.out.print(vertex + " ");
        }

         System.out.println("\nRank of 4 is - " + (ts.getRank(4)+1));

    }




}


/*

 The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).Not dependent on anyone.

In topological sorting, we use a temporary stack. We don’t print the vertexToConsider immediately, we first recursively call
topological sorting for all its adjacent vertices, then push it to a stack.
Finally, print contents of stack. Note that a vertexToConsider is pushed to stack only
when all of its adjacent vertices (and their adjacent vertices and so on) are already in stack.

Cycles in digraphs. If job x must be completed before job y, job y before job z, and
job z before job x, then someone has made a mistake, because those three constraints
cannot all be satisfied. In general, if a precedence-constrained scheduling problem has
a directed cycle, then there is no feasible solution. To check for such errors, we need to
be able to solve the following problem.

topological sorting and cycle detection
go hand in hand, with cycle detection playing
the role of a debugging tool. For example, in
a job-scheduling application, a directed cycle in
the underlying digraph represents a mistake that
must be corrected, no matter how the schedule was
formulated. Thus, a job-scheduling application is
typically a three-step process:
1. Specify the tasks and precedence constraints.
2. Make sure that a feasible solution exists, by
detecting and removing cycles in the underlying
digraph until none exist.
3. Solve the scheduling problem, using topological
sort.
Similarly, any changes in the schedule can be
checked for cycles (using DirectedCycle), then a
new schedule computed (using Topological).

Proposition E. A digraph has a topological order if and only if it is a DAG.
Proof: If the digraph has a directed cycle, it has no topological order. Conversely,
the algorithm that we are about to examine computes a topological order for any
given DAG.


Reverse postorder in a DAG is a topological sort.
Proof: Consider any edge v->w. One of the following three cases must hold when
dfs(v) is called :
1. dfs(w) has already been called and has returned (w is marked).
2. dfs(w) has not yet been called (w is unmarked), so v->w will cause dfs(w) to
be called (and return), either directly or indirectly, before dfs(v) returns.
3. dfs(w) has been called and has not yet returned when dfs(v) is called. The
key to the proof is that this case is impossible in a DAG, because the recursive
call chain implies a path from w to v and v->w would complete a directed
cycle.
In the two possible cases, dfs(w) is done before dfs(v), so w appears before v in
postorder and after v in reverse postorder. Thus, each edge v->w points from a vertex
earlier in the order to a vertex later in the order, as desired.



 */