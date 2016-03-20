/*

Like Kruskal’s algorithm, Prim’s algorithm is also a Greedy algorithm. It starts with an empty spanning tree. 
The idea is to maintain two sets of vertices. The first set contains the vertices already included in the MST, 
the other set contains the vertices not yet included. At every step, it considers all the edges that connect the 
two sets, and picks the minimum weight edge from these edges. After picking the edge, it moves the other endpoint 
of the edge to the set containing MST.
A group of edges that connects two set of vertices in a graph is called cut in graph theory. So, at every step of Prim’s algorithm, 
we find a cut (of two sets, one contains the vertices already included in MST and other contains rest of the verices),
 pick the minimum weight edge from the cut and include this vertex to MST Set (the set that contains already included vertices).

How does Prim’s Algorithm Work? The idea behind Prim’s algorithm is simple, a spanning tree means all vertices must be connected. 
So the two disjoint subsets (discussed above) of vertices must be connected to make a Spanning Tree. And they must be connected
 with the minimum weight edge to make it a Minimum Spanning Tree.

Algorithm
1) Create a set mstSet that keeps track of vertices already included in MST.
2) Assign a key value to all vertices in the input graph. Initialize all key values as INFINITE. Assign key value as 0 for the 
first vertex so that it is picked first.
3) While mstSet doesn’t include all vertices
….a) Pick a vertex u which is not there in mstSet and has minimum key value.
….b) Include u to mstSet.
….c) Update key value of all adjacent vertices of u. To update the key values, iterate through all adjacent vertices. For every 
adjacent vertex v, if weight of edge u-v is less than the previous key value of v, update the key value as weight of u-v

The idea of using key values is to pick the minimum weight edge from cut. The key values are used only for vertices which are not 
yet included in MST, the key value for these vertices indicate the minimum weight edges connecting them to the set of vertices
included in MST.

 */

package graphs;

/**
 * Created by poorvank on 2/25/16.
 */
public class PrimsMSTAdjMatrix {

    private static int INF = Integer.MAX_VALUE;

    private int vertexCount;

    public PrimsMSTAdjMatrix(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        PrimsMSTAdjMatrix prims = new PrimsMSTAdjMatrix(matrix.length);

        prims.mst(matrix);

    }

    public int minKey(int[] keyArray, boolean[] presentInMst) {

        int minValue = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < vertexCount; v++) {

            if (!presentInMst[v] && keyArray[v] < minValue) {
                minValue = keyArray[v];
                minIndex = v;
            }

        }

        return minIndex;
    }

    public void printMST(int[] parent, int[][] matrix) {

        System.out.println("Edge" + " " + "Weight");
        for (int i = 1; i < vertexCount; i++) {
            System.out.println(parent[i] + "--" + i + " " + matrix[i][parent[i]]);
        }
    }

    public void mst(int[][] matrix) {

        // Used in printing MST
        int[] parent = new int[vertexCount];

        int[] keyArray = new int[vertexCount];

        boolean[] presentInMst = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            keyArray[i] = INF;
        }

        keyArray[0] = 0;
        parent[0] = -1;

        int e = 0;

        //Number of edges is always equal to vertexCount-1 in MST
        while (e < vertexCount - 1) {

            int minIndex = minKey(keyArray, presentInMst);

            presentInMst[minIndex] = true;

            for (int i = 0; i < vertexCount; i++) {

                if (matrix[minIndex][i] != 0 && !presentInMst[i] && keyArray[i] > matrix[minIndex][i]) {

                    parent[i] = minIndex;
                    keyArray[i] = matrix[minIndex][i];

                }

            }

            e++;

        }

        printMST(parent, matrix);

    }
}

//Time Complexity of the above program is O(V^2).