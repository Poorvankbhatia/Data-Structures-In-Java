package utility;

/**
 * Created by poorvank.b on 07/05/16.
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
