package graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 4/2/15.
 */
public class BFS_itr {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();

        boolean[] visited = new boolean[vArray.length];

        iterative(vArray, 0, visited);

    }

    private static void iterative(Vertex[] vArray, int v, boolean[] visited) {

        Queue<Vertex> queue = new LinkedList<>();

        queue.add(vArray[v]);

        while (!queue.isEmpty()) {

            Vertex vertex = queue.remove();
            visited[vertex.info] = true;
            System.out.print(vertex.info + " ");

            for (int ve : vertex.adjacentVertices) {
                if (!visited[ve]) {
                    queue.add(vArray[ve]);
                }
            }

        }

        System.out.println(Arrays.toString(visited));

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

