/*


A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V such that every
 edge (u, v) either connects a vertex from U to V or a vertex from V to U. In other words, for every edge (u, v), 
 either u belongs to U and v to V, or u belongs to V and v to U. We can also say that there is no edge that 
 connects vertices of same set.

 */

package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 4/5/15.
 */
public class BipartiteGraph {

    public static void main(String[] args) {

        Vertex[] vArray = Input.graphInput();
        if (isBipartite(vArray, 0)) {
            System.out.println("Bipartite");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean isBipartite(Vertex[] vArray, int v) {

        int[] colorArray = new int[vArray.length];
        for (int i = 0; i < vArray.length; i++) {
            colorArray[i] = -1;
        }

        colorArray[v] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        while (!queue.isEmpty()) {

            int vertex = queue.remove();

            for (int i : vArray[vertex].adjacentVertices) {

                if (colorArray[i] == -1) {
                    queue.add(i);
                    colorArray[i] = 1 - colorArray[vertex];
                } else if (colorArray[i] == colorArray[vertex]) {
                    return false;
                }

            }

        }

        return true;

    }

}


/*

Time Complexity of the above approach is same as that Breadth First Search.
 In above implementation is O(V^2) where V is number of vertices. 
 If graph is represented using adjacency list, then the complexity becomes O(V+E).

1.	Assign RED color to the source vertexToConsider (putting into set U).
2.	Color all the neighbors with BLUE color (putting into set V).
3.	Color all neighbor’s neighbor with RED color (putting into set U).
4.	This way, assign color to all vertices such that it satisfies all the constraints of m way coloring
 problem where m = 2.
5. While assigning colors, if we find a neighbor which is colored with same color as current vertexToConsider,
then the graph cannot be colored with 2 vertices (or graph is not Bipartite)


Bipartite Graph:

bipartite graph is an undirected graph G = (V, E) in which V can be partitioned into two sets V1 and V2 such
 that Edge  (u, v) E implies .either (u in V1 and v in V2) or (u in V2  and v in V1).
 That is, all edges go between the two sets V1 and V2 and no edge is present containing only V1 or V2 vertices



Algorithm to prove if a graph is bipartite ?

The idea is to start by placing a random node into a FIFO queue . Color it blue.
Then repeat following while there are nodes still left in the queue.

dequeue an element. Color its neighbors with a different color than the
extracted element and insert (enqueue) each neighbor into the FIFO queue.
 For example, if you dequeue (extract) an element (node) colored red, color its neighbors blue.
 If you extract a blue node, color its neighbors red.

If there are no coloring conflicts, the graph is bipartite. If you end up coloring a node with two different colors,
than it’s not bipartite.
This approach would  only work for connected graphs. However, you can apply the same algorithm on
each connected component to make it work for any graph.

Modified BFS approach :

To determine if a graph G = (V, E) is bipartite, we perform a BFS on it with a little
modification such that whenever the BFS is at a vertex u and encounters a vertex v that is already ‘gray’
 our modified BSF should check to see if the depth of both u and v are even, or if they are both odd.
 If either of these conditions holds which implies d[u] and d[v] have the same parity, then the graph
 is not bipartite. Note that this modification does not change the running time of BFS and remains O(V + E).
Simply what it means is start from any vertex of graph and color it black , and check its neighbor,
if its not yet colored color it with opposite color (neighbor of black would be colored white
and neighbor of white as black ). So if at any point en edge is seen between two identical color
 then its not a bipartite graph

Triangle is not bipartite but a quad is bipartite

Applications:

1 Job recruitment process:

Suppose there are ‘n’ companies competing to hire students from a university, and that
 ‘m’ students are available for hiring. Assume that each company has only one job opening,
 and hence can hire at most one student.

After the tests and interviews, each company shortlists certain candidates.
The company sees no distinction among its shortlisted candidates, and is willing to hire any one of them to fill its opening.

So its a problem of bipartite matching.

Let L be the set of companies, and R be the set of students.

An edge exists if r is one of the candidates shortlisted by the company.

Each company can hire at most one (either it hires a student, or goes home empty-handed.)

Obviously, each student can work in at most one company (either the student
is selected by one company; or goes home empty-handed.)

Hence, this is a case of bipartite matching between the set of companies and the set of students.

The university would certainly like to find out the maximum number of students who can get
placed through this recruitment process.

In other words, the university wishes to find out the size of the maximum bipartite matching
possible for the company-student graph.

There exist polynomial time algorithms for computing a maximum bipartite matching. Hence, the
problem can be solved by running any of those algorithms on the given instance of the company-student graph.

2. Link Prediction:

One of the most interesting challenges associated with social media is predicting which user is
expected to comment on which blog. This can be  formulated as a link prediction problem in bipartite graphs.
Recently, sparse matrix factorization became popular for link prediction.
Therefore, we can apply matrix factorization to predict which user is expected to comment which blog.


 */