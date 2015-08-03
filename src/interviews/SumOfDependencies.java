/*

You were given a directed graph with n nodes. Given graph was connected. 
If there is an edge from u to v then u depends on v. Find out the sum of dependencies for every node.

 */

package interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/25/15.
 */

class DGraphNode {

    int info;
    List<Integer> childList;

    public DGraphNode(int n) {
        info = n;
        childList = new ArrayList<>();
    }


}

class DGraph {

    int count;
    DGraphNode[] array;

    public DGraph(int n) {
        count = n;
        array = new DGraphNode[n];

        for (int i = 0; i < n; i++) {
            array[i] = new DGraphNode(i);
        }
    }

    public void addEdge(int a, int b) {

        array[a].childList.add(b);

    }

    public int sumOfDependencies() {

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i].childList.size();
        }

        return sum;

    }


}

public class SumOfDependencies {

    public static void main(String[] args) {

        DGraph graph = new DGraph(4);

        graph.addEdge(0, 2);
        graph.addEdge(0, 4);
        graph.addEdge(2, 4);
        graph.addEdge(1, 4);

        System.out.println("Sum of dependencies = " + graph.sumOfDependencies());

    }

}
