/*

Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv,
vertex u comes before
v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.

 */
package graphs;

import utility.Queue;
import utility.graphClasses.Digraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 12/03/17.
 */
public class KahnsAlgo {

    public void printTopSort(Digraph G) {

        int[] indegree = new int[G.getVertexCount()];
        for (int i=0;i<indegree.length;i++) {
            indegree[i] = G.degree(i);
        }

        Queue<Integer> queue = new Queue<>();
        for (int i=0;i<G.getVertexCount();i++) {
            if(G.degree(i)==0) {
                queue.enqueue(i);
            }
        }

        int visitCount = 0;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            for (Integer next : G.getAdj(current)) {
                if(--indegree[next]==0) {
                    queue.enqueue(next);
                }
            }
            list.add(current);
            visitCount++;
        }

        if(visitCount!=G.getVertexCount()) {
            System.out.println("No Topological order present");
        } else {
           System.out.println(list.toString());
        }

    }

    public static void main(String[] args) {

        Digraph digraph = new Digraph(6);
        //5 is dependent on 0
        digraph.addEdge(5,0);
        digraph.addEdge(5,2);
        digraph.addEdge(4,0);
        digraph.addEdge(4,1);
        digraph.addEdge(2,3);
        digraph.addEdge(3,1);

        new KahnsAlgo().printTopSort(digraph);

    }

}

/*

A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
Proof: There’s a simple proof to the above fact is that a DAG does not contain a cycle which means that all paths will
be of finite length. Now let S be the longest path from u(source) to v(destination). Since S is the longest path there
can be no incoming edge to u and no outgoing edge from v, if this situation had occurred then S would not have been the longest path
=> indegree(u) = 0 and outdegree(v) = 0

Algorithm:
Steps involved in finding the topological ordering of a DAG:

Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count
of visited nodes as 0.

Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

Step-3: Remove a vertex from the queue (Dequeue operation) and then.

Increment count of visited nodes by 1.
Decrease in-degree by 1 for all its neighboring nodes.
If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
Step 5: Repeat Step 3 until the queue is empty.

Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible
for the given graph.

How to find in-degree of each node?
There are 2 ways to calculate in-degree of every vertex:
Take an in-degree array which will keep track of
1) Traverse the array of edges and simply increase the counter of the destination node by 1.

for each node in Nodes
	indegree[node] = 0;
for each edge(src,dest) in Edges
	indegree[dest]++
Time Complexity: O(V+E)

2) Traverse the list for every node and then increment the in-degree of all the nodes connected to it by 1.

	for each node in Nodes
		If (list[node].size()!=0) then
		for each dest in list
			indegree[dest]++;
Time Complexity: The outer for loop will be executed V number of times and the inner for loop will be executed E number of times,
Thus overall time complexity is O(V+E).

The overall time complexity of the algorithm is O(V+E)

 */