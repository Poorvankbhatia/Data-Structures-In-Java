package graphs;

import utility.UndirectedGraph;

/**
 * Created by poorvank on 4/2/15.
 */
public class CheckPath {

    private static boolean flag;

    public static void main(String[] args) {

        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        boolean[] visited = new boolean[graph.getVertexCount()];

        pathPresent(1, 2, graph, visited);
        if (flag) {
            System.out.println("Present");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean pathPresent(int a, int b, UndirectedGraph graph, boolean[] visited) {

        visited[a] = true;

        for (Integer v : graph.getAdj(a)) {
            if (!visited[v]) {
                if (v == b) {
                    flag = true;
                    return flag;
                }
                flag = pathPresent(v, b, graph, visited);
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
 Take the first vertexToConsider as source in BFS (or DFS), follow the standard BFS (or DFS).
 If we see the second vertexToConsider in our traversal, then return true. Else return false.

 */