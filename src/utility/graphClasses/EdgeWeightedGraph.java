package utility.graphClasses;

import utility.Bag;
import utility.edgeClasses.Edge;

/**
 * Created by poorvank on 14/05/16.
 */
public class EdgeWeightedGraph {

    private final static String NEWLINE = System.getProperty("line.separator");
    private final int vertexCount;
    private int edgeCount;
    private Bag<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int vertexCount) {
        if(vertexCount<0) {
            throw new IllegalArgumentException("Vertex count should be non negative");
        }
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        adj = (Bag<Edge>[]) new Bag[vertexCount];
        for (int i=0;i<vertexCount;i++) {
            adj[i] = new Bag<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public void validateVertex(int v) {
        if(v<0 || v>=vertexCount) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount - 1));
        }
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].addToBag(e);
        adj[w].addToBag(e);
        edgeCount++;
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].getSize();
    }

    public Iterable<Edge> edges() {

        Bag<Edge> edgeList = new Bag<>();
        for (int v=0;v<vertexCount;v++) {
            int selfLoop = 0;
            for (Edge e : adj(v)) {
                if(e.other(v) > v) {
                    edgeList.addToBag(e);
                }
                else if(e.other(v)==v) {
                    if(selfLoop%2==0) {
                        edgeList.addToBag(e);
                        selfLoop++;
                    }
                }
            }
        }

        return edgeList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VertexCount : ").append(vertexCount).append(" edgeCount : ").append(edgeCount).append(NEWLINE);
        for (int v=0;v<vertexCount;v++) {
            sb.append(v).append(" : ");
            for (Edge e : adj(v)) {
                sb.append(e).append(" ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

}
