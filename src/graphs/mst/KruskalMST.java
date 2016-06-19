/*

What is Minimum Spanning Tree?
Given a connected and undirected graph, a spanning tree of that graph is a subgraph that 
is a tree and connects all the vertices together. A single graph can have many different spanning trees. 
A minimum spanning tree (MST) or minimum weight spanning tree for a weighted, connected and undirected graph is a 
spanning tree with weight less than or equal to the weight of every other spanning tree. The weight of a spanning tree is the 
sum of weights given to each edge of the spanning tree.

How many edges does a minimum spanning tree has?
A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.

Below are the steps for finding MST using Kruskal’s algorithm

1. Sort all the edges in non-decreasing order of their weight.

2. Pick the smallest edge. Check if it forms a cycle with the spanning tree 
formed so far. If cycle is not formed, include this edge. Else, discard it.  

3. Repeat step#2 until there are (V-1) edges in the spanning tree.

The algorithm is a Greedy Algorithm.
The Greedy Choice is to pick the smallest weight edge that does not cause a cycle in the MST constructed so far


 */

package graphs.mst;

import java.util.Arrays;

/**
 * Created by poorvank on 2/13/16.
 */



/*

O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting, we iterate through all edges and apply 
find-union algorithm. The find and union operations can take atmost O(LogV) time. So overall complexity is O(ELogE + ELogV) time. 
The value of E can be atmost V^2, so O(LogV) are O(LogE) same. Therefore, overall time complexity is O(ElogE) or O(ElogV)

 */
