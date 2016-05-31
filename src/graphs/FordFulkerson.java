/*

Ford-Fulkerson maxflow algorithm. Start with zero flow everywhere. Increase the
flow along any augmenting path from source to sink (with no full forward edges or
empty backward edges), continuing until there are no such paths in the network.



 */

package graphs;

import utility.EdgeClasses.FlowEdge;
import utility.graphClasses.FlowNetwork;
import utility.Queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 3/16/16.
 */
public class FordFulkerson {

    private boolean[] marked;   // true iff s->v path in residual graph
    private FlowEdge[] edgeTo;  // last edge on shortest residual s->v path
    private double value;       // current value of max flow

    public FordFulkerson(FlowNetwork G, int source,int destination) {
        int size = G.getVertexCount();
        validateVertex(source,size);
        validateVertex(destination,size);
        value = 0.0;

        if(source==destination) {
            throw new IllegalArgumentException("Source equals Sink!");
        }

        while (hasAugmentingPath(G,source,destination)) {

            double bottle = Double.POSITIVE_INFINITY;
            for (int v = destination;v!=source;v=edgeTo[v].other(v)) {
                bottle = Math.min(bottle,edgeTo[v].residualCapacityTo(v));
            }

            for (int v = destination;v!=source;v=edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v,bottle);
            }

            value += bottle;

        }


    }

    public boolean inCut(int v)  {
        validateVertex(v, marked.length);
        return marked[v];
    }

    public double getValue() {
        return value;
    }

    public boolean hasAugmentingPath(FlowNetwork G, int source, int destination) {
        int size = G.getVertexCount();
        marked = new boolean[size];
        edgeTo = new FlowEdge[size];

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(source);
        marked[source] = true;
        while (!queue.isEmpty() && !marked[destination]) {

            int v = queue.dequeue();
            for (FlowEdge e : G.getAdj(v)) {
                int w = e.other(v);

                if(e.residualCapacityTo(w)>0) {
                    if(!marked[w]) {
                        queue.enqueue(w);
                        edgeTo[w] = e;
                        marked[w] = true;
                    }
                }
            }

        }

        //is there an augmenting path?
        return marked[destination];

    }


    private void validateVertex(int v,int vertexCount) {
        if(v<0 && v>=vertexCount) {
            throw new IndexOutOfBoundsException("vertex should be between 0 " + " and " + (vertexCount-1));
        }
    }

    public static void main(String[] args) {

        try {
            String path = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(path + "/src/inputFiles/FordFulkersonInput"));
            int vertexCount = Integer.parseInt(br.readLine());
            int source = 0,destination = vertexCount-1;
            FlowNetwork network = new FlowNetwork(vertexCount);
            String line = "";
            while ((line = br.readLine())!=null) {
                String[] split = line.split(" ");
                int v = Integer.parseInt(split[0]);
                int w = Integer.parseInt(split[1]);
                double capacity = Double.parseDouble(split[2]);
                double flow = Double.parseDouble(split[3]);
                FlowEdge e = new FlowEdge(v,w,capacity,flow);
                network.addEdge(e);
            }

            FordFulkerson maxFlow = new FordFulkerson(network,source,destination);

            System.out.println("Max flow is = " + maxFlow.getValue());

        } catch (Exception e) {
            System.err.println("Error occurred in main");
            e.printStackTrace();
        }

    }

}


/*

Consider any directed path from source to sink through
an st-flow network. Let x be the minimum of the unused capacities of
the edges on the path. We can increase the network’s flow value by at
least x by increasing the flow in all edges on the path by that amount.


Now, for any path with no full forward edges and no empty
backward edges, we can increase the amount of flow in the network
by increasing flow in forward edges and decreasing flow in backward
edges. The amount by which the flow can be increased is limited by
the minimum of the unused capacities in the forward edges and the
flows in the backward edges. Such a path is called an augmenting path.


a cut in a graph is a partition of the vertices
into two disjoint sets, and a crossing edge is an edge that connects a vertex in one set to
a vertex in the other set.

Definition. An s in one of its sets and vertex t in
the other.
Each crossing edge corresponding to an st-cut is either an st-edge that goes from a vertex
in the set containing s to a vertex in the set containing t, or a ts-edge that goes in
the other direction. We sometimes refer to the set of crossing st-edges as a cut set. The
capacity of an st-cut in a flow network is the sum of the capacities of that cut’s st-edges,
and the flow across an st-cut is the difference between the sum of the flows in that cut’s
st-edges and the sum of the flows in that cut’s ts-edges.

Minimum st-cut. Given an st-network, find an st-cut such that the capacity of
no other cut is smaller. For brevity, we refer to such a cut as a mincut and to the
problem of finding one in a network as the mincut problem.

Proposition E. For any st-flow, the flow across each st-cut
is equal to the value of the flow.
Proof: Let Cs be the vertex set containing s and Ct the
vertex set containing t. This fact follows immediately by
induction on the size of Ct. The property is true by definition
when Ct is t and when a vertex is moved from Cs to
Ct , local equilibrium at that vertex implies that the stated
property is preserved. Any st-cut can be created by moving
vertices in this way.

Corollary. The outflow from s is equal to the inflow to t (the value of the st-flow).
Proof: Let Cs be {s}.


Corollary. No st-fl ow’s value can exceed the capacity of any st-cut.

( Maxflow-mincut theorem) Let f be an st-flow. The following three
conditions are equivalent:
i. There exists an st-cut whose capacity equals the value of the flow f.
ii. f is a maxflow.
iii. There is no augmenting path with respect to f.
Proof: Condition i. implies condition ii. by the corollary to Proposition E. Condition
ii. implies condition iii. because the existence of an augmenting path implies
the existence of a flow with a larger flow value, contradicting the maximality of f.
It remains to prove that condition iii. implies condition i. Let Cs be the set of all
vertices that can be reached from s with an undirected path that does not contain a
full forward or empty backward edge, and let Ct be the remaining vertices. Then, t
must be in Ct , so (Cs , Ct) is an st-cut, whose cut set consists entirely of full forward
or empty backward edges. The flow across this cut is equal to the cut’s capacity
(since forward edges are full and the backward edges are empty) and also to the
value of the network flow

Corollary. ( Integrality property) When capacities are integers, there exists an integer-
valued maxflow, and the Ford-Fulkerson algorithm finds it.
Proof: Each augmenting path increases the flow by a positive integer (the minimum
of the unused capacities in the forward edges and the flows in the backward
edges, all of which are always positive integers).

Definition. Given a st-flow network and an st-flow, the residual network for the
flow has the same vertices as the original and one or two edges in the residual network
for each edge in the original, defined as follows: For each edge e from v to w in
the original, let fe be its flow and ce its capacity. If fe is positive, include an edge w->v
in the residual with capacity fe ; and if fe is less than ce, include an edge v->w in the
residual with capacity ce  fe .

If an edge e from v to w is empty (fe is equal to 0), there is a single corresponding edge
v->w with capacity ce in the residual; if it is full (fe is equal to ce), there is a single corresponding
edge w->v with capacity fe in the residual; and if it is neither empty nor full,
both v->w and w->v are in the residual with their respective capacities. An example is
shown at the bottom of this page. At first, the residual network representation is a bit
confusing because the edges corresponding to flow go in the opposite direction of the
flow itself. The forward edges represent the remaining capacity (the amount of flow we
can add if traversing that edge); the backward edges represent the flow (the amount of
flow we can remove if traversing that edge).

The number of augmenting paths needed in the shortest-augmenting-
path implementation of the Ford-Fulkerson maxflow algorithm for a flow network
with V vertices and E edges is at most EV /2.


Proof sketch: Every augmenting path has a critical edge—an edge that is deleted
from the residual network because it corresponds either to a forward edge that becomes
filled to capacity or a backward edge that is emptied. Each time an edge is a
critical edge, the length of the augmenting path through it must increase by 2.
Since an augmenting path is of length at most V each edge can be
on at most V/2 augmenting paths, and the total number of augmenting paths is at
most EV/2

The shortest-augmenting-path implementation of the Ford-Fulkerson
maxflow algorithm takes time proportional to square(VE)/2 in the worst case.
Proof: Breadth-first search examines at most E edges.

https://www.youtube.com/watch?v=AGDj-OWYNs8&spfreload=1

 */