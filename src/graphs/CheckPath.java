package graphs;

/**
 * Created by poorvank on 4/2/15.
 */
public class CheckPath {

    private static boolean flag;

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        boolean[] visited = new boolean[vArray.length];

        for (int i = 0; i < vArray.length; i++) {
            visited[i] = false;
        }
        pathPresent(0, 2, vArray, visited);
        if (flag) {
            System.out.println("Present");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean pathPresent(int a, int b, Vertex[] vArray, boolean[] visited) {

        visited[a] = true;

        for (Integer v : vArray[a].adjacentVertices) {
            if (!visited[v]) {
                if (v == b) {
                    return true;
                }
                flag = pathPresent(v, b, vArray, visited);
                if (flag) {
                    break;
                }
            }
        }

        return flag;

    }

}

/*

We can either use Breadth First Search (BFS) or Depth First Search (DFS) to find path between two vertices.
 Take the first vertex as source in BFS (or DFS), follow the standard BFS (or DFS). 
 If we see the second vertex in our traversal, then return true. Else return false.

 */