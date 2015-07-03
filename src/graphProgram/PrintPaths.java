package graphProgram;

import java.util.ArrayList;

/**
 * Created by poorvank on 4/2/15.
 */
public class PrintPaths {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        boolean[] visited = new boolean[vArray.length];

        for (int i = 0; i < vArray.length; i++) {
            visited[i] = false;
        }
        print(2, 3, visited, new ArrayList<Integer>(), vArray);

    }

    private static void print(int s, int d, boolean[] visited, ArrayList<Integer> arrayList, Vertex[] vArray) {

        visited[s] = true;
        arrayList.add(s);

        if (s == d) {
            System.out.println(arrayList.toString());
        } else {

            for (Integer i : vArray[s].adjacentVertices) {
                //prevent cycle
                if (!visited[i]) {
                    print(i, d, visited, arrayList, vArray);
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
 If we reach the destination vertex, print contents of path[]. 
 The important thing is to mark current vertices in path[] as visited also, so that the traversal doesn’t go in a cycle.

 */