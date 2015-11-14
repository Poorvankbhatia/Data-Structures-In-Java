/*

Union-Find Algorithm | Set 1 (Detect Cycle in a an Undirected Graph)
A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of 
disjoint (non-overlapping) subsets. A union-find algorithm is an algorithm that performs two useful operations on such a 
data structure:

Find: Determine which subset a particular element is in. This can be used for determining if two elements are in the same subset.

Union: Join two subsets into a single subset.

In this post, we will discuss an application of Disjoint Set Data Structure. The application is to check whether a given
 graph contains a cycle or not.

Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not. Note that we have discussed an 
algorithm to detect cycle. This is another method based on Union-Find. This method assumes that graph doesn’t contain any
 self-loops.
We can keeps track of the subsets in a 1D array, lets call it parent[].

Let us consider the following graph:

        0
        |  \
        |    \
        1-----2
For each edge, make subsets using both the vertices of the edge. If both the vertices are in the same subset, a cycle is found.

Initially, all slots of parent array are initialized to -1 (means there is only one item in every subset).

0   1   2
-1 -1  -1 
Now process all edges one by one.

Edge 0-1: Find the subsets in which vertices 0 and 1 are. Since they are in different subsets, we take the union of them. 
For taking the union, either make node 0 as parent of node 1 or vice-versa.

0   1   2    <----- 1 is made parent of 0 (1 is now representative of subset {0, 1})
1  -1  -1
Edge 1-2: 1 is in subset 1 and 2 is in subset 2. So, take union.

0   1   2    <----- 2 is made parent of 1 (2 is now representative of subset {0, 1, 2})
1   2  -1
Edge 0-2: 0 is in subset 2 and 2 is also in subset 2. Hence, including this edge forms a cycle.

How subset of 0 is same as 2?
0->1->2 // 1 is parent of 0 and 2 is parent of 1
http://www.algorithmist.com/index.php/Union_Find

Note that the implementation of union() and find() is naive and takes O(n) time in worst case.

 */

package graphs;

/**
 * Created by poorvank on 11/15/15.
 */
class UnionFindGraph {

    public int V,E;
    Edge[] edges;


    class Edge {
        int source,destination;
    }

    public UnionFindGraph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i=0;i<e;i++) {
            edges[i] = new Edge();
        }
    }

    public int find(int[] parent,int edge) {

        if(parent[edge]==-1) {
            return edge;
        }
        return find(parent,parent[edge]);

    }

    public void union(int[] parent,int x,int y) {

        int xSet = find(parent,x);
        int ySet = find(parent,y);

        parent[xSet] = ySet;

    }

    public boolean isCycle(UnionFindGraph unionFindGraph) {

        int[] parent = new int[unionFindGraph.E];

        for (int i=0;i<parent.length;i++) {
            parent[i] = -1;
        }

        for (int i=0;i<unionFindGraph.E;i++) {

            int x = unionFindGraph.find(parent, unionFindGraph.edges[i].source);
            int y = unionFindGraph.find(parent, unionFindGraph.edges[i].destination);

            if(x==y) {
                return true;
            }

            unionFindGraph.union(parent,x,y);

        }

        return false;
    }

}

public class UnionFind {
    
    public static void main(String[] args) {

        UnionFindGraph graph = new UnionFindGraph(3,3);

        graph.edges[0].source = 0;
        graph.edges[0].destination = 1;

        // add edge 1-2
        graph.edges[1].source = 1;
        graph.edges[1].destination = 2;

        // add edge 0-2
        graph.edges[2].source = 0;
        graph.edges[2].destination = 2;

        if (graph.isCycle(graph))
            System.out.println( "Graph contains cycle" );
        else
            System.out.println( "Graph doesn't contain cycle" );

    }
    
}