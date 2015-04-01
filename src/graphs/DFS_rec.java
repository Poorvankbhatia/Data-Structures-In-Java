package graphs;

/**
 * Created by poorvank on 4/1/15.
 */
public class DFS_rec {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        recursive(vArray, 0);

    }

    private static void recursive(Vertex[] vArray, int v) {

        boolean[] visited = new boolean[vArray.length];

        for (int i = 0; i < vArray.length; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < vArray.length; i++) {
            if (!visited[v]) {
                recurUtil(vArray, v, visited);
            }
        }

    }

    private static void recurUtil(Vertex[] vArray, int v, boolean[] visited) {

        visited[v] = true;
        System.out.print(vArray[v].info + " ");

        for (Integer vertexNo : vArray[v].adjacentVertices) {

            if (!visited[vertexNo]) {
                recurUtil(vArray, vertexNo, visited);
            }
        }

    }

}
