package graphs;

/**
 * Created by poorvank on 4/6/15.
 */
public class ShortestPath {

    public static void main(String[] args) {

        int graph[][] = new int[][]{{0, 10, 3, 2},
                {-1, 0, -1, 7},
                {-1, -1, 0, 6},
                {-1, -1, -1, 0}
        };

        System.out.println("MIN WEIGHT - " + minWeightPath(0, 3, 2, graph));

    }


    private static int minWeightPath(int source, int destination, int edgeCount, int[][] graph) {


        if (edgeCount < 0) {
            return Integer.MAX_VALUE;
        }

        if (edgeCount == 1 && graph[source][destination] != -1) {
            return graph[source][destination];
        }

        if (edgeCount == 0 && source == destination) {
            return 0;
        }

        int res = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            
            /*
               If we don't put source!=i && destination!=i check
               infinite loop occurs in case of graph[source][i]==0
            
             */

            if (graph[source][i] != -1 && source != i && destination != i) {

                int rec_res = minWeightPath(i, destination, edgeCount - 1, graph);
                if (rec_res != -1) {
                    res = min(res, rec_res + graph[source][i]);
                }


            }

        }

        return res;


    }

    private static int min(int a, int b) {

        return a < b ? a : b;

    }

}
