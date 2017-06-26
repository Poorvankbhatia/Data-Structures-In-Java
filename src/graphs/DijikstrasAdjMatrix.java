package graphs;

/**
 * Created by poorvank on 1/20/16.
 */
public class DijikstrasAdjMatrix {

    private static int vertexCount = 9;
    private static int INF = Integer.MAX_VALUE;

    public static int minimumDistance(boolean[] sptSet, int[] distance) {

        int minDist = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < vertexCount; v++) {

            if (!sptSet[v] && distance[v] < minDist) {
                minDist = distance[v];
                minIndex = v;
            }

        }

        return minIndex;

    }

    //See Images
    public static void printSolution(int[] distance) {

        for (int i = 0; i < distance.length; i++) {
            System.out.println(i + " " + distance[i]);
        }

    }

    public static void implementAlgorithm(int[][] graph, int src) {

        boolean[] sptSet = new boolean[vertexCount];

        int[] distance = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            distance[i] = INF;
        }
        distance[src] = 0;

        for (int count = 0; count < vertexCount - 1; count++) {

            int u = minimumDistance(sptSet, distance);

            sptSet[u] = true;

            for (int v = 0; v < vertexCount; v++) {

                if (!sptSet[v] && graph[u][v] != 0 && distance[u] != INF && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }

            }

        }

        printSolution(distance);


    }


    public static void main(String[] args) {
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                    {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                    {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                    {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                    {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                    {0, 0, 4, 0, 10, 0, 2, 0, 0},
                                    {0, 0, 0, 14, 0, 2, 0, 1, 6},
                                    {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                    {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        implementAlgorithm(graph, 0);
    }


}


/*

we generate a SPT (shortest path tree) with given source as root. We maintain two sets, 
one set contains vertices included in shortest path tree, other set includes vertices not yet included in 
shortest path tree. At every step of the algorithm, we find a vertexToConsider which is in the other set (set of not
yet included) and has minimum distance from source.

Below are the detailed steps used in Dijkstra’s algorithm to find the shortest path from a single source vertexToConsider
to all other vertices in the given graph.
Algorithm
1) Create a set sptSet (shortest path tree set) that keeps track of vertices included in shortest path tree, i.e.,
 whose minimum distance from source is calculated and finalized. Initially, this set is empty.
2) Assign a distance value to all vertices in the input graph. Initialize all distance values as INFINITE. Assign
 distance value as 0 for the source vertexToConsider so that it is picked first.
3) While sptSet doesn’t include all vertices
….a) Pick a vertexToConsider u which is not there in sptSetand has minimum distance value.
….b) Include u to sptSet.
….c) Update distance value of all adjacent vertices of u. To update the distance values, iterate through all 
adjacent vertices. For every adjacent vertexToConsider v, if sum of distance value of u (from source) and weight of edge u-v,
is less than the distance value of v, then update the distance value of v.

 Time Complexity of the implementation is O(V^2). If the input graph is represented using adjacency list,
  it can be reduced to O(E log V) with the help of binary heap

 */