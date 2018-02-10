/*

Find 2nd degree connections ( friends’ friends), output these 2nd degree connections ranked by number of common friends (i.e 1st degree connections) with you,
 (example: if 2nd degree connection A has 10 common friends (1st degree connections) with you but 2nd degree connection B has 8 common friends
  (1st degree connections)with you, then A should be ranked first) Input is your connection graph represented by undirected graph nodes,
  output is list of 2nd degree connections represented by graph nodes

 */
package graphs;

import java.util.*;

/**
 * Created by poorvank.b on 10/02/18.
 */
public class SecondDegreeConnections {

    private class UndirectedGraphNode {

        int val;
        List<UndirectedGraphNode> friendList;

        public UndirectedGraphNode(int val, List<UndirectedGraphNode> undirectedGraphNodeList) {
            this.val = val;
            this.friendList = undirectedGraphNodeList;
        }
    }

    public List<UndirectedGraphNode> findSecDegreeConnections(UndirectedGraphNode myself){

        Set<UndirectedGraphNode> myFriends = new HashSet<>();
        List<UndirectedGraphNode> res = new ArrayList<UndirectedGraphNode>();

        for (UndirectedGraphNode friend : myself.friendList) {
            myFriends.add(friend);
        }

        int level=0;
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.add(myself);
        Set<UndirectedGraphNode> visited = new HashSet<>();
        while (!queue.isEmpty() && level<2) {
            level++;
            int size = queue.size();
            for (int i=0;i<size;i++) {
                UndirectedGraphNode current = queue.poll();
                for (UndirectedGraphNode neighbor : current.friendList) {
                    if(!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
        }

        Map<UndirectedGraphNode,Integer> countMap = new HashMap<>();
        for (UndirectedGraphNode fof : queue) {
            int count=0;
            for (UndirectedGraphNode neighbor : fof.friendList) {
                if(myFriends.contains(neighbor)) {
                    count++;
                }
            }
            countMap.put(fof,count);
        }


        Map<Integer,List<UndirectedGraphNode>> bucket = new HashMap<>();
        int maxCount=0;
        for (UndirectedGraphNode n : countMap.keySet()) {
            int count = countMap.get(n);
            maxCount = Math.max(count,maxCount);
            if (!bucket.containsKey(count)) {
                bucket.put(count, new ArrayList<>());
            }
            bucket.get(count).add(n);
        }
        for (int i = maxCount; i > 0; i--) {
            if (bucket.containsKey(i))  {
                res.addAll(bucket.get(i));
            }
        }
        return res;

    }

}
/*

Create a set of first degree friends (nodes) called myFriends. Do BFS starting from mySelf and stop when you have 2nd degree nodes in q.
 Then count common friends by checking if myFriends contains friends of 2nd degree nodes. Once you have the counts
 bucket sort the nodes and add them to res in descending order.

 */
