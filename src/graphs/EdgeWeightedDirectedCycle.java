package graphs;

import utility.*;
import utility.Stack;

/**
 * Created by poorvank on 21/05/16.
 */
public class EdgeWeightedDirectedCycle {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {

        int size = G.getVertexCount();
        marked = new boolean[size];
        edgeTo = new DirectedEdge[size];
        onStack = new boolean[size];

        for (int v=0;v<size;v++) {
            if(!marked[v]) {
                dfs(G,v);
            }
        }

        if(!check(G)) {
            System.out.println("[In Method:EdgeWeightedDirectedCycle] No cycle found");
        }

    }

    private void dfs(EdgeWeightedDigraph G,int v) {
        marked[v] = true;
        onStack[v] = true;

        for (DirectedEdge e : G.getAdj(v)) {
            int w = e.to();

            if(cycle!=null) {
                return;
            }

            else if(!marked[w]) {
                edgeTo[w] = e;
                dfs(G,w);
            }

            else if(onStack[w]) {
                cycle = new Stack<>();
                while (e.from()!=w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
                return;
            }
        }

        onStack[v] = false;

    }

    public boolean hasCycle() {
        return cycle!=null;
    }

    public Iterable<DirectedEdge> getCycle() {
        return cycle;
    }

    private boolean check(EdgeWeightedDigraph G) {

        if(hasCycle()) {

            DirectedEdge first=null,last=null;
            for (DirectedEdge e : cycle) {

                if(first==null) {
                    first = e;
                }
                if(last!=null) {
                    if(last.to() != e.from()) {
                        System.out.println("Cycle Not found");
                        System.out.println("cycle edges " + last + " and "  + e +   " are not incident ");
                        return false;
                    }
                }
                last = e;

            }

            if (last!=null && last.to() != first.from()) {
                System.out.println("Cycle Not found");
                System.out.println("cycle edges " + last + " and "  + first +   " are not incident ");
                return false;
            }

        }

        return true;

    }
}
