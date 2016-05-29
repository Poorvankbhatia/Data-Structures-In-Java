package graphs;

import utility.Stack;
import utility.UndirectedGraph;

/**
 * Created by poorvank on 4/2/15.
 */
public class DFS_itr {

    public static void main(String[] args) {

        UndirectedGraph graph = new UndirectedGraph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        boolean[] visited = new boolean[4];

        iterative(graph, 0, visited);

    }

    private static void iterative(UndirectedGraph graph, int v, boolean[] visited) {

        Stack<Integer> stack = new Stack<>();

        stack.push(v);

        while (!stack.isEmpty()) {

            Integer popped = stack.pop();

            if (!visited[popped]) {

                visited[popped] = true;

                System.out.print(popped + " ");

                for (int vertex : graph.getAdj(popped)) {
                    stack.push(vertex);
                }

            }

        }

    }

}
