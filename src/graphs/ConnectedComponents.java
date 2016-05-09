package graphs;

import utility.Bag;
import utility.UndirectedGraph;

/**
 * Created by poorvank.b on 07/05/16.
 */
public class ConnectedComponents {

    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponents(UndirectedGraph G) {

        marked = new boolean[G.getVertexCount()];
        id = new int[G.getVertexCount()];
        for (int v = 0; v < G.getVertexCount(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }

    }


    public void dfs(UndirectedGraph G, int v) {

        marked[v] = true;
        id[v] = count;
        for (Integer vertexNo : G.getAdj(v)) {
            if (!marked[vertexNo]) {
                dfs(G, vertexNo);
            }
        }

    }

    public boolean isConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int noOfConnectedComponents() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        UndirectedGraph G = new UndirectedGraph(6);
        G.addEdge(0, 1);
        G.addEdge(2, 0);
        G.addEdge(1, 5);
        G.addEdge(3, 4);

        ConnectedComponents connectedComponents = new ConnectedComponents(G);


        int cc = connectedComponents.noOfConnectedComponents();
        System.out.println("No of connected components = " + cc);

        Bag<Integer>[] component = (Bag<Integer>[]) new Bag[cc];

        for (int i = 0; i < cc; i++)
            component[i] = new Bag<>();

        for (int i = 0; i < G.getVertexCount(); i++) {
            component[connectedComponents.id[i]].addToBag(i);
        }

        for (int i = 0; i < cc; i++) {
            for (Integer v : component[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }


    }

}
