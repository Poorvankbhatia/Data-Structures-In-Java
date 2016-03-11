
/*

Articulation Points (or Cut Vertices) in a Graph
A vertex in an undirected connected graph is an articulation point (or cut vertex) iff removing it (and edges through it) 
disconnects the graph. Articulation points represent vulnerabilities in a connected network – single points whose failure would 
split the network into 2 or more disconnected components. They are useful for designing reliable networks.
For a disconnected undirected graph, an articulation point is a vertex removing which increases number of connected components.

How to find all articulation points in a given graph?
A simple approach is to one by one remove all vertices and see if removal of a vertex causes disconnected graph. 
Following are steps of simple approach for connected graph.

1) For every vertex v, do following
…..a) Remove v from graph
..…b) See if the graph remains connected (We can either use BFS or DFS)
…..c) Add v back to the graph

Time complexity of above method is O(V*(V+E)) for a graph represented using adjacency list. Can we do better?

A O(V+E) algorithm to find all Articulation Points (APs)
The idea is to use DFS (Depth First Search). In DFS, we follow vertices in tree form called DFS tree. In DFS tree, 
a vertex u is parent of another vertex v, if v is discovered by u (obviously v is an adjacent of u in graph). In DFS tree, 
a vertex u is articulation point if one of the following two conditions is true.
1) u is root of DFS tree and it has at least two children.
2) u is not root of DFS tree and it has a child v such that no vertex in subtree rooted with v has a back edge to one of the 
ancestors (in DFS tree) of u.

We do DFS traversal of given graph with additional code to find out Articulation Points (APs). In DFS traversal, 
we maintain a parent[] array where parent[u] stores parent of vertex u. Among the above mentioned two cases, the first case 
is simple to detect. For every vertex, count children. If currently visited vertex u is root (parent[u] is NIL) and has more 
than two children, print it.
How to handle second case? The second case is trickier. We maintain an array disc[] to store discovery time of vertices. 
For every node u, we need to find out the earliest visited vertex (the vertex with minimum discovery time) that can be reached 
from subtree rooted with u. So we maintain an additional array low[] which is defined as follows.

low[u] = min(disc[u], disc[w]) 
where w is an ancestor of u and there is a back edge from 
some descendant of u to w.



 */

package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 3/10/16.
 */

class APNode {
    int info;
    List<Integer> adjacencyList;

    public APNode(int info, List<Integer> adjacencyList) {
        this.info = info;
        this.adjacencyList = adjacencyList;
    }
}

class APGraph {

    static int time=0;
    int vertexCount;
    List<APNode> list = new ArrayList<>();
    
    public APGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        for (int i=0;i<vertexCount;i++) {
            list.add(new APNode(i,new ArrayList<Integer>()));
        }
    }
    
    
    public void addEdge(int a,int b) {
        
        list.get(a).adjacencyList.add(b);
        list.get(b).adjacencyList.add(b);
        
    }
    
    
    public void markArticulationPoints(int source,int[] discovery,boolean[] visited,boolean[] finalAp,int[] parent,int[] low) {
        
        discovery[source] = low[source] = ++time;
        visited[source] = true;
        int children = 0;

        
        for (Integer neighbour: list.get(source).adjacencyList) {
            
            if(!visited[neighbour]) {

                children++;
                parent[neighbour] = source;
                markArticulationPoints(neighbour,discovery,visited,finalAp,parent,low);
                
                //Check if subtree rooted with child has a connection to the parents of source
                low[source] = Math.min(low[source],low[neighbour]);
                
                //source will be the articulation point in the following cases
                if(parent[source]==-1 && children>1) {
                    finalAp[source] = true;
                }
                if(parent[source]!=-1 && low[neighbour] >= discovery[source]) {
                    finalAp[source] = true;
                }
                
                
            }
            //Update connected value of source to parent function calls
            else if(source!=parent[neighbour]) {
                low[source] = Math.min(low[source],discovery[neighbour]);
            }
            
        }
        
    }
    
    public void calculateArticulationPoints() {
        
        int[] discovery = new int[vertexCount];
        boolean[] finalAp = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        int[] connectValue = new int[vertexCount]; // Represents the indicates earliest visited vertex reachable from 
        // subtree rooted with v
        
        for (int i=0;i<vertexCount;i++) {
            if(!visited[i]) {
                markArticulationPoints(i,discovery,visited,finalAp,parent,connectValue);
            }
        }
        
        for (int i=0;i<vertexCount;i++) {
            if(finalAp[i]) {
                System.out.println(i);
            }
        }
        
    }
    
}

public class ArticulationPoints {
    
    public static void main(String[] args) {
        
        APGraph g1 = new APGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        
        g1.calculateArticulationPoints();
    }
    
}


/*

The above function is simple DFS with additional arrays. 
So time complexity is same as DFS which is O(V+E) for adjacency list representation of graph.

Some Dry Run:

ver   disc  low
0      1       1
1      2       1
2      3       1
——
3      4       4



0—2
| /
|/
1—3

low(2) = min(low(2),disc(0)) = min(3,1) = 1

low(1) = min(low(1),low(2)) = min(2,1) = 1


ver   disc  low
0      1       1
1      2       1
2      3       1
3      4       2



0—-2
| /|
|/ |
1—3



ver   disc  low
0      1       1
1      2       2
2      3       1
4      4       4
3      5       2



0—2
| /\
|/  \
1—3—4


 */