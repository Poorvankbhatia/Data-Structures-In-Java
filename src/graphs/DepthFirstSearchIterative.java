package graphs;

import utility.Stack;
import utility.graphClasses.Graph;

/**
 * Created by poorvank on 4/2/15.
 */
public class DepthFirstSearchIterative {

    public static void main(String[] args) {

        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        boolean[] visited = new boolean[4];

        iterative(graph, 0, visited);

    }

    private static void iterative(Graph graph, int v, boolean[] visited) {

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

/*

Mark-and-sweep garbage collection.
 An important
application of multiple-source reachability
is found in typical memory-management
systems, including many implementations of
Java. A digraph where each vertex represents
an object and each edge represents a reference
to an object is an appropriate model for the
memory usage of a running Java program. At
any point in the execution of a program, certain
objects are known to be directly accessible, and
any object not reachable from that set of objects
can be returned to available memory. A markand-
sweep garbage collection strategy reserves
one bit per object for the purpose of garbage
collection, then periodically marks the set of
potentially accessible objects by running a digraph
reachability algorithm like DirectedDFS
and sweeps through all objects, collecting the
unmarked ones for use for new objects.

 */
