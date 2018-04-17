/*

Given an arbitrary unweighted rooted tree which consists of N (2 <= N <= 40000) nodes.
The goal of the problem is to find largest distance between two nodes in a tree.
Distance between two nodes is a number of edges on a path between the nodes (there will be a unique path between any pair
of nodes since it is a tree). The nodes will be numbered 0 through N - 1.

The tree is given as an array P, there is an edge between nodes P[i] and i (0 <= i < N). Exactly one of the i’s will have P[i] equal to -1,
it will be root node.

ex: If given P is [-1, 0, 0, 0, 3], then node 0 is the root and the whole tree looks like this:

          0
       /  |  \
      1   2   3
               \
                4

 One of the longest path is 1 -> 0 -> 3 -> 4 and its length is 3, thus the answer is 3. Note that there are other paths with maximal distance.

 */

package trees.tree;

import java.util.*;

/**
 * Created by poorvank.b on 09/04/18.
 */
public class LargestDistanceBetweenTwoNodes {

    private class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public int solve(ArrayList<Integer> A) {

        int startNode = -1;
        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int i=0;i<A.size();i++) {
            if(A.get(i)==-1) {
                startNode=i;
            } else {
                if(map.containsKey(A.get(i))) {
                    map.get(A.get(i)).add(i);
                } else {
                    List<Integer> child = new ArrayList<>();
                    child.add(i);
                    map.put(A.get(i),child);
                }

                if(map.containsKey(i)) {
                    map.get(i).add(A.get(i));
                } else {
                    List<Integer> child = new ArrayList<>();
                    child.add(A.get(i));
                    map.put(i,child);
                }

            }
        }

        Pair p = findFarthestNode(startNode,map,A.size());

        Pair sol = findFarthestNode(p.node,map,A.size());

        return sol.distance;

    }

    private Pair findFarthestNode(int startNode,Map<Integer,List<Integer>> map,int N) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);

        int[] distance = new int[N];
        Arrays.fill(distance,-1);
        Set<Integer> visited = new HashSet<>();
        distance[startNode]=0;

        int maxDis = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i=0;i<size;i++) {
                int pop = queue.poll();
                visited.add(pop);
                if(map.containsKey(pop)) {
                    for (int ne : map.get(pop)) {
                       if(!visited.contains(ne)) {
                           distance[ne]=distance[pop]+1;
                           queue.add(ne);
                       }
                    }
                }
            }

        }

        int nextNode = -1;

        for (int i=0;i<N;i++) {
            if(distance[i]>maxDis) {
                nextNode = i;
                maxDis = distance[i];
            }
        }

        return new Pair(nextNode,maxDis);
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(0);
        list.add(0);
        list.add(0);
        list.add(3);
        System.out.println(new LargestDistanceBetweenTwoNodes().solve(list));
    }

}

/*

We can find longest path using two BFSs. The idea is based on the following fact: If we start BFS from any node x and find a node with
 the longest distance from x, it must be an end point of the longest path. It can be proved using contradiction. So our algorithm
  reduces to simple two BFSs. First BFS to find an end point of the longest path and second BFS from this end point to find the actual longest path.



1) pick any node u
2) find the node which is farthest from u, call it x
3) find the node which is farthest from x, call it q
The answer will be the length of a path from x to q.

Proof of correctness:

The crucial step is to prove that x will be one of the endpoints of the path with maximal length (note that there might be more than one such path).
If it is, then the longest path from x will be the longest path in the tree.

Let d(v1, v2) be length of path between v1 and v2

Let’s prove it by contradiction: assume there is a strictly longer path between s and t, neither of which is x. Let h be a node which is closest to u
among the nodes on a path between s and t.

 Let u be the arbitrary vertex. We have a schematic like

    u
    |
    |
    |
    x
   / \
  /   \
 /     \
s       t ,
where x is the junction of s, t, u.

Suppose that v is a vertex maximally distant from u. If the schematic now looks like

    u
    |
    |
    |
    x   v
   / \ /
  /   *
 /     \
s       t ,
then

d(s, t) = d(s, x) + d(x, t) <= d(s, x) + d(x, v) = d(s, v),
where the inequality holds because d(u, t) = d(u, x) + d(x, t) and d(u, v) = d(u, x) + d(x, v). There is a symmetric case where v attaches between s and x instead of between x and t.

The other case looks like

    u
    |
    *---v
    |
    x
   / \
  /   \
 /     \
s       t .
Now,

d(u, s) <= d(u, v) <= d(u, x) + d(x, v)
d(u, t) <= d(u, v) <= d(u, x) + d(x, v)

d(s, t)  = d(s, x) + d(x, t)
         = d(u, s) + d(u, t) - 2 d(u, x)
        <= 2 d(x, v)

2 d(s, t) <= d(s, t) + 2 d(x, v)
           = d(s, x) + d(x, v) + d(v, x) + d(x, t)
           = d(v, s) + d(v, t),
so max(d(v, s), d(v, t)) >= d(s, t) by an averaging argument, and v belongs to a maximally distant pair.


 */
