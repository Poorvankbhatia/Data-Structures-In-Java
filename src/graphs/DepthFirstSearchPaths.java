//Assuming that the graph is connected

package graphs;

import utility.Stack;

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
        marked = new boolean[G.noOfVertices];
        edgeTo = new int[G.noOfVertices];
        dfs(G,source);

    }


    private void dfs(Graph G,int source) {

        marked[source] = true;

        for (Integer vertexNo : G.vertexArray[source].adjacentVertices) {

            if(!marked[vertexNo]) {
                edgeTo[vertexNo] = source;
                dfs(G,vertexNo);
            }

        }

    }

    public boolean hasPath(int w) {
        return marked[w];
    }


    public Iterable<Integer> pathTo(Graph G,int destination) {

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
