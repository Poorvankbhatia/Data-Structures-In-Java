package utility.graphClasses;

import utility.Bag;

/**
 * Created by poorvank on 07/05/16.
 */
public class Graph {

    private static final String NEWLINE = System.getProperty("line.separator");
    private final int vertexCount;
    private int edgeCount;
    private Bag<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int vertexCount) {
        if (vertexCount < 0) throw new IllegalArgumentException("Vertex count cannot be negative");
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        adj = (Bag<Integer>[]) new Bag[vertexCount];
        for (int v = 0; v < vertexCount; v++) {
            adj[v] = new Bag<>();
        }
    }


    public Integer getVertexCount() {
        return vertexCount;
    }

    public Integer getEdgeCount() {
        return edgeCount;
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].getSize();
    }

    public int maxDegree() {
        int maxDegree = 0;
        for (int v=0;v<vertexCount;v++) {
            int currentVertexDegree = degree(v);
            if(maxDegree<currentVertexDegree) {
                maxDegree = currentVertexDegree;
            }
        }
        return maxDegree;
    }

    public Iterable<Integer> getAdj(int v) {
        return adj[v];
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount - 1));
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        edgeCount++;
        adj[v].addToBag(w);
        adj[w].addToBag(v);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices = ").append(vertexCount).append(" edges = ").append(edgeCount).append(NEWLINE);
        for (int v = 0; v < vertexCount; v++) {
            sb.append(v).append(" : ");
            for (int adjV : adj[v]) {
                sb.append(adjV).append(" ");
            }
            sb.append(NEWLINE);
        }

        return sb.toString();
    }


}


/*

 Space usage proportional to V + E
 Constant time to add an edge
 Time proportional to the degree of v to iterate through vertices adjacent to v (constant time per adjacent vertex processed)


underlying-data-structure     space     add edgev-w  check whether w is adjacent to v    iterate through vertices adjacent to v
list of edges                   E           1           E                                        E
adjacency matrix               Sq(V)        1           1                                        V
adjacency lists                E+V          1           degree(v)                               degree(v)
adjacency sets                  E+V          log V          log V                           log V + degree(v)

 */
