
/*

Count all possible walks from a source to a destination with exactly k edges
Given a directed graph and two vertices ‘u’ and ‘v’ in it, count all possible walks from ‘u’ to ‘v’
 with exactly k edges on the walk.

The graph is given as adjacency matrix representation where value of graph[i][j] as 1 
indicates that there is an edge from vertex i to vertex j and a value 0 indicates no edge from i to j.

 */

package Dyanamic_programs;

/**
 * Created by poorvank on 4/6/15.
 */
public class CountPaths {
    
    public static void main(String[] args) {

        int graph[][] = new int[][]{ {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        System.out.println(countWalks(0,3,2,graph));
        
    }
    
    private static int countWalks(int source,int destination,int edgeCount,int[][] graph) {
        
        int[][][] count = new int[4][4][edgeCount+1];
        
        for (int e = 0;e<=edgeCount;e++) {
            for (int i=0;i<4;i++) {
                for (int j=0;j<4;j++) {
                    
                    if(i==j && e ==0) {
                        count[i][j][e] = 1;
                    }
                    if(e==1 && graph[i][j]==1) {
                        count[i][j][e]=1;
                    }
                    
                    if(e>1) {
                        
                        for (int a=0;a<4;a++) {
                            if(graph[i][a]==1) {
                                count[i][j][e] += count[a][j][e-1];
                            }
                        }
                        
                    }
                    
                }
            }
        }
        
        return count[source][destination][edgeCount];
        
    }
    
}

/*

The idea is to build a 3D table where first dimension is source, second dimension is destination, 
third dimension is number of edges from source to destination, and the value is count of walks

3D table in bottom up manner.

Time complexity of the above DP based solution is O(V3K) which is much better than the naive solution.

Shortest path : 
if (graph[i][a] != INF && i != a && j!= a && sp[a][j][e-1] != INF)
  sp[i][j][e] = min(sp[i][j][e], graph[i][a] + sp[a][j][e-1]);


 */