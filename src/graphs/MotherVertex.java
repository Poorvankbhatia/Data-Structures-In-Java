/*

A mother vertex in a graph G = (V,E) is a vertex v such that all other vertices in G can be reached by a path from v.

SEE IMAGES

 */
package graphs;

import utility.graphClasses.Digraph;

import java.util.Arrays;

/**
 * Created by poorvank.b on 14/01/18.
 */
public class MotherVertex {

    private void dfsUtil(int v , boolean[] visited,Digraph G) {
        visited[v]=true;
        for (int adjVertex : G.getAdj(v)) {
            if(!visited[adjVertex]) {
                dfsUtil(adjVertex,visited,G);
            }
        }
    }

    public int findMotherVertex(Digraph G) {

        int v=0;

        int vertexCount = G.getVertexCount();
        boolean[] visited  = new boolean[vertexCount];

        for (int i=0;i<vertexCount;i++) {
            if(!visited[i]) {
                dfsUtil(i,visited,G);
                v=i;
            }
        }

        Arrays.fill(visited,true);
        dfsUtil(v,visited,G);

        for (int i=0;i<vertexCount;i++) {
            if(!visited[i]) {
                return -1;
            }
        }
        return v;
    }

    public static void main(String[] args) {

        Digraph g = new Digraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(4, 1);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 2);
        g.addEdge(6, 0);

        System.out.println("Mother vertex - " + new MotherVertex().findMotherVertex(g));

    }

}

/*

How to find mother vertex?

Case 1:- Undirected Connected Graph : In this case, all the vertices are mother vertices as we can reach to all the other nodes in the graph.
Case 2:- Undirected/Directed Disconnected Graph : In this case, there is no mother vertices as we cannot reach to all the other nodes in the graph.
Case 3:- Directed Connected Graph : In this case, we have to find a vertex -v in the graph such that we can reach to all the other nodes in the graph
through a directed path.

A Naive approach :
A trivial approach will be to perform a DFS/BFS on all the vertices and find whether we can reach all the vertices from that vertex.
This approach takes O(V(E+V)) time, which is very inefficient for large graphs


Can we do better?
We can find a mother vertex in O(V+E) time. The idea is based on Kosaraju’s Strongly Connected Component Algorithm.
In a graph of strongly connected components, mother verices are always vertices of source component in component graph. The idea is based on below fact.

If there exist mother vertex (or vertices), then one of the mother vertices is the last finished vertex in DFS.
 (Or a mother vertex has the maximum finish time in DFS traversal).

A vertex is said to be finished in DFS if a recursive call for its DFS is over, i.e., all descendants of the vertex have been visited.

How does the above idea work?
Let the last finished vertex be v. Basically, we need to prove that there cannot be an edge from another vertex
 u to v if u is not another mother vertex (Or there cannot exist a non-mother vertex u such that u-→v is an edge). There can be two possibilities.

Recursive DFS call is made for u before v. If an edge u-→v exists,
then v must have finished before u because v is reachable through u and a vertex finishes after all its descendants.
Recursive DFS call is made for v before u. In this case also, if an edge u-→v exists, then either v must finish before u
(which contradicts our assumption that v is finished at the end) OR u should be reachable from v (which means u is another mother vertex).
Algorithm :

Do DFS traversal of the given graph. While doing traversal keep track of last finished vertex ‘v’. This step takes O(V+E) time.
If there exist mother vertex (or vertices), then v must be one (or one of them). Check if v is a mother vertex by doing DFS/BFS from v.
This step also takes O(V+E) time.

Find all other mother vertices : Just print all the vertices in the same strongly connected components in which one of the mother vertex lie.

 */