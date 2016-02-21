package graphs;

/**
 * Created by poorvank on 11/15/15.
 */

class UFGraph {

    public int V, E;
    public Edge[] edges;

    public UFGraph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge();
        }
    }

    public int find(Subset[] subsets, int i) {

        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;

    }

    public void union(Subset[] subsets, int x, int y) {

        int xRoot = find(subsets, x);
        int yRoot = find(subsets, y);

        //Do try it out
        if (subsets[xRoot].rank > subsets[yRoot].rank) {
            subsets[yRoot].parent = xRoot;
        } else if (subsets[yRoot].rank > subsets[xRoot].rank) {
            subsets[xRoot].parent = yRoot;
        } else {
            subsets[yRoot].parent = xRoot;
            subsets[xRoot].rank++;
        }
    }

    public boolean isCycle(UFGraph graph) {

        int vertexes = graph.V;
        int edges = graph.E;

        Subset[] subsets = new Subset[vertexes];

        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        for (int e = 0; e < E; e++) {
            int x = find(subsets, graph.edges[e].source);
            int y = find(subsets, graph.edges[e].destination);

            if (x == y) {
                return true;
            }

            union(subsets, x, y);

        }

        return false;

    }

    class Edge {
        int source, destination;
    }

    class Subset {
        int rank, parent;
    }

}

public class UFByRank {

    public static void main(String[] args) {

        UFGraph graph = new UFGraph(3, 3);

        graph.edges[0].source = 0;
        graph.edges[0].destination = 1;

        // add edge 1-2
        graph.edges[1].source = 1;
        graph.edges[1].destination = 2;

        // add edge 0-2
        graph.edges[2].source = 0;
        graph.edges[2].destination = 2;

        if (graph.isCycle(graph))
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't contain cycle");

    }

}


/*

union find algorithm and used it to detect cycle in a graph. We used following union() and find() operations for subsets.

// Naive implementation of find
int find(int parent[], int i)
{
    if (parent[i] == -1)
        return i;
    return find(parent, parent[i]);
}
  
// Naive implementation of union()
void Union(int parent[], int x, int y)
{
    int xset = find(parent, x);
    int yset = find(parent, y);
    parent[xset] = yset;
}
Run on IDE
The above union() and find() are naive and the worst case time complexity is linear. The trees created to represent subsets
can be skewed and can become like a linked list. Following is an example worst case scenario.

Let there be 4 elements 0, 1, 2, 3

Initially all elements are single element subsets.
0 1 2 3 

Do Union(0, 1)
   1   2   3  
  /
 0

Do Union(1, 2)
     2   3   
    /
   1
 /
0

Do Union(2, 3)
         3    
        /
      2
     /
   1
 /
0
The above operations can be optimized to O(Log n) in worst case.
 The idea is to always attach smaller depth tree under the root of the deeper tree. 
 This technique is called union by rank. The term rank is preferred instead of height because if path compression
  technique (we have discussed it below) is used, then rank is not always equal to height. Also, size (in place of height)
   of trees can also be used as rank. Using size as rank also yields worst case time complexity as O(Logn) (See this for prrof)

Let us see the above example with union by rank
Initially all elements are single element subsets.
0 1 2 3 

Do Union(0, 1)
   1   2   3  
  /
 0

Do Union(1, 2)
   1    3
 /  \
0    2

Do Union(2, 3)
    1    
 /  |  \
0   2   3
The second optimization to naive method is Path Compression. The idea is to flatten the tree when find() is called. 
When find() is called for an element x, root of the tree is returned. The find() operation traverses up from x to find root. 
The idea of path compression is to make the found root as parent of x so that we don’t have to traverse all intermediate nodes
 again. If x is root of a subtree, then path (to root) from all nodes under x also compresses.

     
Let the subset {0, 1, .. 9} be represented as below and find() is called
for element 3.
              9
         /    |    \  
        4     5      6
     /     \        /  \
    0        3     7    8
            /  \
           1    2  

When find() is called for 3, we traverse up and find 9 as representative
of this subset. With path compression, we also make 3 as child of 9 so 
that when find() is called next time for 1, 2 or 3, the path to root is 
reduced.

               9
         /    /  \    \
        4    5    6     3 
     /           /  \   /  \
    0           7    8  1   2           

The two techniques complement each other. The time complexity of each operations becomes even smaller than O(Logn). 
In fact, amortized time complexity effectively becomes small constant.

 */