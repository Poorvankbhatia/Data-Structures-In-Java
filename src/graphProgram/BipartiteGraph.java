/*


A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every
 edge (u, v) either connects a vertex from U to V or a vertex from V to U. In other words, for every edge (u, v), 
 either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no edge that 
 connects vertices of same set.

 */

package graphProgram;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 4/5/15.
 */
public class BipartiteGraph {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        if (isBipartite(vArray, 0)) {
            System.out.println("Bipartite");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean isBipartite(Vertex[] vArray, int v) {

        int[] colorArray = new int[vArray.length];
        for (int i = 0; i < vArray.length; i++) {
            colorArray[i] = -1;
        }

        colorArray[v] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while (!queue.isEmpty()) {

            int vertex = queue.remove();

            for (int i : vArray[vertex].adjacentVertices) {

                if (colorArray[i] == -1) {
                    queue.add(i);
                    colorArray[i] = 1 - colorArray[vertex];
                } else if (colorArray[i] == colorArray[vertex]) {
                    return false;
                }

            }

        }

        return true;

    }

}


/*

Time Complexity of the above approach is same as that Breadth First Search.
 In above implementation is O(V^2) where V is number of vertices. 
 If graph is represented using adjacency list, then the complexity becomes O(V+E).

1.	Assign RED color to the source vertex (putting into set U).
2.	Color all the neighbors with BLUE color (putting into set V).
3.	Color all neighbor’s neighbor with RED color (putting into set U).
4.	This way, assign color to all vertices such that it satisfies all the constraints of m way coloring
 problem where m = 2.
5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, 
then the graph cannot be colored with 2 vertices (or graph is not Bipartite)

 */