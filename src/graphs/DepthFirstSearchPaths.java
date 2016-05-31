//Assuming that the graph is connected

package graphs;

import utility.Stack;
import utility.graphClasses.Graph;

import java.util.*;

/**
 * Created by poorvank on 03/05/16.
 */
public class DepthFirstSearchPaths {

    private int source;
    private int[] edgeTo;
    private boolean[] marked;

    public DepthFirstSearchPaths(Graph G, int source) {

        this.source = source;
        marked = new boolean[G.getVertexCount()];
        edgeTo = new int[G.getVertexCount()];
        dfs(G,source);

    }


    private void dfs(Graph G, int source) {

        marked[source] = true;

        for (Integer vertexNo : G.getAdj(source)) {

            if(!marked[vertexNo]) {
                edgeTo[vertexNo] = source;
                dfs(G,vertexNo);
            }

        }

    }

    public boolean hasPath(int w) {
        return marked[w];
    }


    public Iterable<Integer> pathTo(Graph G, int destination) {

        Stack path = new Stack();
        while (destination!=source) {
            path.push(destination);
            destination = edgeTo[destination];
        }

        path.push(source);
        return path;

    }

    public static void main(String[] args) {

        Graph G = new Graph(6);
        G.addEdge(0,5);
        G.addEdge(0,1);
        G.addEdge(0,2);
        G.addEdge(0,5);
        G.addEdge(3,5);
        G.addEdge(3,4);
        G.addEdge(2,1);
        G.addEdge(2,3);
        G.addEdge(2,4);

        DepthFirstSearchPaths paths = new DepthFirstSearchPaths(G,0);
        Iterator<Integer> iterator = paths.pathTo(G,4).iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

    }

}

/*

Instead of just keeping track of the path from the current vertex back to the start,
we remember a path from each vertex to the
start. To accomplish this, we remember the edge
v-w that takes us to each vertex w for the first
time, by setting edgeTo[w] to v. In other words,
v-w is the last edge on the known path from s
to w. The result of the search is a tree rooted at
the source; edgeTo[] is a parent-link representation
of that tree.

 */