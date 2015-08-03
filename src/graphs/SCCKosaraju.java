/*

A directed graph is strongly connected if there is a path between all pairs of vertices.
 A strongly connected component (SCC) of a directed graph is a maximal strongly connected subgraph.

 */

package graphs;

/**
 * Created by poorvank on 4/9/15.
 */
public class SCCKosaraju {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        SCC(vArray);

    }

    private static void SCC(Vertex[] vArray) {

        boolean[] visited = new boolean[vArray.length];

        Stack stack = new Stack();

        for (int i = 0; i < vArray.length; i++) {
            if (!visited[i]) {
                fillStack(vArray, stack, visited, i);
            }
        }

        Vertex[] newVArray = graphTranspose(vArray);

        for (int i = 0; i < vArray.length; i++) {
            visited[i] = false;
        }

        while (!stack.isEmpty()) {

            int vertex = ((Vertex) stack.pop()).info;


            if (!visited[vertex]) {

                dfsUtil(vertex, newVArray, visited);
                System.out.println();

            }

        }

    }

    private static void dfsUtil(int v, Vertex[] vArray, boolean[] visited) {

        visited[v] = true;
        System.out.print(vArray[v].info + " ");

        for (int i : vArray[v].adjacentVertices) {
            if (!visited[i]) {
                dfsUtil(i, vArray, visited);
            }
        }

    }

    private static void fillStack(Vertex[] vArray, Stack stack, boolean[] visited, int i) {

        visited[i] = true;

        for (int v : vArray[i].adjacentVertices) {
            if (!visited[v]) {
                fillStack(vArray, stack, visited, v);
            }
        }


        stack.push(vArray[i]);

    }

    private static Vertex[] graphTranspose(Vertex[] vArray) {

        Vertex[] vertexes = new Vertex[vArray.length];

        //creation of a new graph

        for (int i = 0; i < vArray.length; i++) {

            for (int v : vArray[i].adjacentVertices) {

                vertexes[v] = new Vertex(v);
                vertexes[v].adjacentVertices.add(i);

            }

        }

        return vertexes;

    }

}


/*

Read Notes

 */