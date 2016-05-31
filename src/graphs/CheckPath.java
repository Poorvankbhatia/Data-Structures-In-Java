package graphs;

import utility.graphClasses.Graph;

/**
 * Created by poorvank on 4/2/15.
 */
public class CheckPath {

    private static boolean flag;

    public static void main(String[] args) {

        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        boolean[] visited = new boolean[graph.getVertexCount()];
        int[] edgeTo = new int[graph.getVertexCount()];

        int a=0,b=2;
        if (pathPresent(a, b, graph, visited,edgeTo)) {
            System.out.println("Present");
            for (int v=b;v!=a;v=edgeTo[v]) {
                System.out.print(v + " ");
            }
            System.out.print(a);
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean pathPresent(int a, int b, Graph graph, boolean[] visited,int[] edgeTo) {

        visited[a] = true;

        for (Integer v : graph.getAdj(a)) {
            if (!visited[v]) {
                edgeTo[v] = a;
                if (v == b) {
                    flag = true;
                    return flag;
                }
                flag = pathPresent(v, b, graph, visited,edgeTo);
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