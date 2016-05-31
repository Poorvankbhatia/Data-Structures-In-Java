/*

A directed graph is strongly connected if there is a path between all pairs of vertices.
 A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.

  Definition. Two vertices v and w are strongly connected if they are
mutually reachable: that is, if there is a directed path from v to w
and a directed path from w to v. A digraph is strongly connected if all
its vertices are strongly connected to one another. Two vertices are strongly connected if and only if there exists a
general directed cycle that contains them both

 */

package graphs;

import utility.Bag;
import utility.graphClasses.Digraph;

/**
 * Created by poorvank on 4/9/15.
 */
public class SCCKosaraju {

    private boolean[] marked;
    private int[] id;
    private int count;

    public SCCKosaraju(Digraph G) {

        int size = G.getVertexCount();

        marked = new boolean[size];
        id = new int[size];
        Digraph reverseDigraph = G.reverse();
        DepthFirstOrders order = new DepthFirstOrders(reverseDigraph);
        Iterable<Integer>  reversePostOrder = order.getReversePost();
        for (Integer v : reversePostOrder) {
            if(!marked[v]) {
                dfs(G,v);
                count++;
            }
        }

    }

    private void dfs(Digraph G,int v) {

        marked[v] = true;
        id[v] = count;
        for (Integer vertex : G.getAdj(v)) {
             if(!marked[vertex]) {
                 dfs(G,vertex);
             }
        }

    }

    public int numberOfCC() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean isStronglyConnected(int v,int w) {
        return id[v]==id[w];
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Digraph g = new Digraph(5);
        g.addEdge(1,0);
        g.addEdge(0,2);
        g.addEdge(2,1);
        g.addEdge(0,3);
        g.addEdge(3,4);

        SCCKosaraju scc= new SCCKosaraju(g);

        System.out.println("No of connected components = " + scc.numberOfCC());

        Bag<Integer>[] component = (Bag<Integer>[]) new Bag[scc.numberOfCC()];

        for (int i=0;i<scc.numberOfCC();i++) {
            component[i] = new Bag<>();
        }

        for (int i=0;i<5;i++) {
            component[scc.id(i)].addToBag(i);
        }

        System.out.println(" Connected components are : ");

        for (int i=0;i<component.length;i++) {
            for (Integer integer : component[i]) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

    }

}


/*

Read Notes:


Proposition H. In a DFS of a digraph G where marked vertices are considered in
reverse postorder given by a DFS of the digraph’s reverse G R (Kosaraju’s algorithm),
the vertices reached in each call of the recursive method from the constructor are
in a strong component.
Proof: First, we prove by contradiction that every vertex v that is strongly connected
to s is reached by the call dfs(G, s) in the constructor. Suppose a vertex v that is
strongly connected to s is not reached by dfs(G, s). Since there is a path from s to
v, v must have been previously marked. But then, since there is a path from v to s, s
would have been marked during the call dfs(G, v) and the constructor would not
call dfs(G, s), a contradiction.
Second, we prove that every vertex v reached by the call dfs(G, s) in the constructor
is strongly connected to s. Let v be a vertex reached by the call dfs(G, s). Then,
there is a path from s to v in G, so it remains only to prove that there is a path from
v to s in G. This statement is equivalent to saying that there is a path from s to v in
GR, so it remains only to prove that there is a path from s to v in GR.
The crux of the proof is that the reverse postorder construction implies that
dfs(G, v) must have been done before dfs(G, s) during the DFS of GR, leaving
just two cases to consider for dfs(G, v): it might have been called
■ before dfs(G, s) was called (and also done before dfs(G, s) was called)
■
after dfs(G, s) was called (and done before dfs(G, s) was done)
The first of these is not possible because there is a path from v to s in GR; the second
implies that there is a path from s to v in GR, completing the proof.


Proposition I. Kosaraju’s algorithm uses preprocessing time and space proportional
to V + E to support constant-time strong connectivity queries in a digraph.
Proof: The algorithm computes the reverse of the digraph and does two depth-first
searches. Each of these three steps takes time proportional to VE. The reverse copy
of the digraph uses space proportional to V +E.



 */