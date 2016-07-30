package utility.graphClasses;

import utility.Bag;
import utility.edgeClasses.DirectedEdge;

/**
 * Created by poorvank on 20/05/16.
 */
@SuppressWarnings("unchecked")
public class EdgeWeightedDigraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int vertexCount;
    private int edgeCount;
    private Bag<DirectedEdge>[] adj;
    private int[] inDegree;

    public EdgeWeightedDigraph(int vertexCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Vertex count cannot be negative");
        }
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        inDegree = new int[vertexCount];
        adj = (Bag<DirectedEdge>[]) new Bag[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            adj[i] = new Bag<>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public Iterable<DirectedEdge> getAdj(int v) {
        return adj[v];
    }

    private void validateVertex(int v) {
        if (v < 0 && v >= vertexCount) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount - 1));
        }
    }

    public void addEdge(DirectedEdge e) {
        int source = e.from();
        int destination = e.to();
        validateVertex(source);
        validateVertex(destination);
        adj[source].addToBag(e);
        inDegree[destination]++;
        edgeCount++;
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < vertexCount; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.addToBag(e);
            }
        }
        return bag;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertexCount + " " + edgeCount + NEWLINE);
        for (int v = 0; v < vertexCount; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
