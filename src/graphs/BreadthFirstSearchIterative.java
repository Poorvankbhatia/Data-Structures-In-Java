package graphs;

import utility.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 4/2/15.
 */
public class BreadthFirstSearchIterative {

    public static void main(String[] args) {

        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(2,3);

        boolean[] visited = new boolean[graph.getVertexCount()];

        iterative(graph, 0, visited);

    }

    private static void iterative(Graph graph, int v, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);

        while (!queue.isEmpty()) {

            Integer vertex = queue.remove();
            visited[vertex] = true;
            System.out.print(vertex + " ");

            for (int ve : graph.getAdj(vertex)) {
                if (!visited[ve]) {
                    queue.add(ve);
                }
            }

        }

       // System.out.println(Arrays.toString(visited));

    }

}


/*

Note that the above code traverses only the vertices reachable
 from a given source vertexToConsider. All the vertices may not be reachable from a given vertexToConsider

In BFS, we want to explore the
vertices in order of their distance from the source

In a breadth first search, you start at the root node, and then scan each node in the
 first level starting from the leftmost node, moving towards the right.
 Then you continue scanning the second level (starting from the left) and the third level,
 and so on until you’ve scanned all the nodes, or until you find the actual node that you
 were searching for. In a BFS, when traversing one level, we need some way of knowing which nodes
 to traverse once we get to the next level. The way this is done is by storing the pointers to a level’s
  child nodes while searching that level. The pointers are stored in FIFO (First-In-First-Out) queue.
   This, in turn, means that BFS uses a large amount of memory because we have to store the pointers.

 */

