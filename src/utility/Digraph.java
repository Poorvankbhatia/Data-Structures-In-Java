package utility;

/**
 * Created by poorvank.b on 09/05/16.
 */
public class Digraph {

    private static final String NEWLINE = System.getProperty("line.separator");
    private  final int vertexCount;
    private int edgeCount;
    private Bag<Integer>[] adj;
    private int[] inDegree;

    @SuppressWarnings("unchecked")
    public Digraph(int vertexCount) {

        if(vertexCount<0) {
            throw new IllegalArgumentException("vertex count cannot be negative");
        }
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        inDegree = new int[vertexCount];
        adj = (Bag<Integer>[]) new Bag[vertexCount];
        for (int v=0;v<vertexCount;v++) {
            adj[v] = new Bag<>();
        }

    }


    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public Iterable<Integer> getAdj(int v) {
        validate(v);
        return adj[v];
    }

    private void validate(int v) {
        if(v<0 || v>vertexCount) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount - 1));
        }
    }

    public void addEdge(int v,int w) {
        validate(v);
        validate(w);
        adj[v].addToBag(w);
        inDegree[w]++;
        edgeCount++;

    }

    public int degree(int v) {
        validate(v);
        return inDegree[v];
    }

    public int outDegree(int v) {
        validate(v);
        return adj[v].getSize();
    }

    public Digraph reverse() {
        Digraph reverseDigraph = new Digraph(vertexCount);
        for (int i=0;i<vertexCount;i++) {
            for (Integer w : adj[i]) {
                reverseDigraph.addEdge(w,i);
            }
        }
        return reverseDigraph;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Vertices = " + vertexCount + " edges = " + edgeCount + "\n");
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
