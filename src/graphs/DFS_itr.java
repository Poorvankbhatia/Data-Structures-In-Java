package graphs;

import utility.Stack;

/**
 * Created by poorvank on 4/2/15.
 */
public class DFS_itr {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        boolean[] visited = new boolean[vArray.length];

        iterative(vArray, 0, visited);

    }

    private static void iterative(Vertex[] vArray, int v, boolean[] visited) {

        Stack stack = new Stack<>();

        stack.push(vArray[v]);

        while (!stack.isEmpty()) {

            Vertex popped = (Vertex) stack.pop();

            if (!visited[popped.info]) {

                visited[popped.info] = true;

                System.out.print(popped.info + " ");

                for (int vertex : popped.adjacentVertices) {
                    stack.push(vArray[vertex]);
                }

            }

        }

    }

}
