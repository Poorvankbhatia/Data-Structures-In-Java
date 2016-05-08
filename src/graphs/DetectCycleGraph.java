package graphs;

/**
 * Created by poorvank on 11/11/15.
 */
public class DetectCycleGraph {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        System.out.println(isCyclic(vArray));

    }


    private static boolean isCyclic(Vertex[] vArray) {

        boolean[] visited = new boolean[vArray.length];
        boolean[] record = new boolean[vArray.length];

        for (int i = 0; i < vArray.length; i++) {
            if (isCyclicUtil(i, visited, record, vArray)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCyclicUtil(int v, boolean[] visited, boolean[] record, Vertex[] vArray) {

        if (!visited[v]) {

            visited[v] = true;
            record[v] = true;

            for (Integer vertex : vArray[v].adjacentVertices) {

                if (!visited[vertex] && isCyclicUtil(vertex, visited, record, vArray)) {
                    return true;
                }
                if (record[vertex]) {
                    return true;
                }

            }

        }

        record[v] = false; // remove the vertex from recursion stack
        return false;

    }

}

/*

Detect Cycle in a Directed Graph
Given a directed graph, check whether the graph contains a cycle or not. Your function should return true if the given graph
 contains at least one cycle, else return false. For example, the following graph contains three cycles 0->2->0, 0->1->2->0 and
 3->3, so your function must return true.


Solution
Depth First Traversal can be used to detect cycle in a Graph. DFS for a connected graph produces a tree. 
There is a cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is from a node 
to itself (selfloop) or one of its ancestor in the tree produced by DFS.


For a disconnected graph, we get the DFS forrest as output. To detect cycle, we can check for cycle in individual trees 
by checking back edges.

To detect a back edge, we can keep track of vertices currently in recursion stack of function for DFS traversal. 
If we reach a vertexToConsider that is already in the recursion stack, then there is a cycle in the tree. The edge that connects
current vertexToConsider to the vertexToConsider in the recursion stack is back edge. We have used recStack[] array to keep track of vertices
in the recursion stack.

visited array will treat back edge and cross edge in same way.
Cross edge doesn't make a cycle. Only back edge does. Recursion stack is used to identify back edge correctly.

Graph g(7);
g.addEdge(0,1);
g.addEdge(1,2);
g.addEdge(2,3);
g.addEdge(0,4);
g.addEdge(4,5);
g.addEdge(5,6);
g.addEdge(5,2);

Here edge 5 -- > 2 is cross edge and there is no cycle in this example.

 */