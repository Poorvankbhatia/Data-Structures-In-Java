package utility.graphClasses;

import utility.Bag;
import utility.EdgeClasses.FlowEdge;

/**
 * Created by poorvank on 26/05/16.
 */
public class FlowNetwork {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int vertexCount;
    private int edgeCount;
    private Bag<FlowEdge>[] adj;

    @SuppressWarnings("unchecked")
    public FlowNetwork(int vertexCount) {
        if(vertexCount<0) throw new IllegalArgumentException("Vertex count cannot be negative");
        this.vertexCount = vertexCount;
        this.edgeCount = 0;
        adj = (Bag<FlowEdge>[]) new Bag[vertexCount];
        for (int i=0;i<vertexCount;i++) {
            adj[i] = new Bag<FlowEdge>();
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public Iterable<FlowEdge> getAdj(int v) {
        return adj[v];
    }

    private void validateVertex(int v) {
        if(v<0 && v>=vertexCount) {
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (vertexCount-1));
        }
    }

    public void addEdge(FlowEdge e) {
        int source = e.from();
        int destination = e.to();
        validateVertex(source);
        validateVertex(destination);
        adj[source].addToBag(e);
        adj[destination].addToBag(e);
        edgeCount++;
    }

    public Iterable<FlowEdge> edges() {
        Bag<FlowEdge> list = new Bag<>();
        for (int v=0;v<vertexCount;v++) {
            for (FlowEdge e : getAdj(v)) {
                if(e.to()!=v) {
                    list.addToBag(e);
                }
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertexCount + " " + edgeCount + NEWLINE);
        for (int v = 0; v < vertexCount; v++) {
            s.append(v + ":  ");
            for (FlowEdge e : adj[v]) {
                if (e.to() != v) s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {

        FlowNetwork flowNetwork = new FlowNetwork(3);
        flowNetwork.addEdge(new FlowEdge(0,1,4.5));
        flowNetwork.addEdge(new FlowEdge(1,2,2.3));
        System.out.println(flowNetwork);

    }
}
