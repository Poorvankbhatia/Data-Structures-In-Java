package graphs;

import utility.graphClasses.Graph;

import java.util.ArrayList;

/**
 * Created by poorvank on 4/2/15.
 */
public class PrintPaths {

    public static void main(String[] args) {

        Graph graph = new Graph(4);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);


        boolean[] visited = new boolean[graph.getVertexCount()];

        for (int i = 0; i < graph.getVertexCount(); i++) {
            visited[i] = false;
        }
        print(0, 2, visited, new ArrayList<Integer>(), graph);

    }

    private static void print(int s, int d, boolean[] visited, ArrayList<Integer> arrayList, Graph graph) {

        visited[s] = true;
        arrayList.add(s);

        if (s == d) {
            System.out.println(arrayList.toString());
        } else {

            for (Integer i : graph.getAdj(s)) {
                //prevent cycle
                if (!visited[i]) {
                    print(i, d, visited, arrayList, graph);
                }
            }

        }

        arrayList.remove(arrayList.size() - 1);
        visited[s] = false;

    }

}


/*

The idea is to do Depth First Traversal of given directed graph. 
Start the traversal from source. Keep storing the visited vertices in an array say ‘path[]’.
 If we reach the destination vertexToConsider, print contents of path[].
 The important thing is to mark current vertices in path[] as visited also, so that the traversal doesn’t go in a cycle.

 */