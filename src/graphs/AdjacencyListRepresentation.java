package graphs;

import java.util.LinkedList;

/**
 * Created by poorvank on 4/6/15.
 */

class AdjacentNode {

    int info;
    AdjacentNode next;

    public AdjacentNode(int v) {
        this(v, null);
    }

    public AdjacentNode(int v, AdjacentNode node) {
        info = v;
        next = node;
    }

}

class AdjacencyGraph {

    int countV;
    LinkedList<AdjacentNode> adjacentNodeList = new LinkedList<>();

    public AdjacencyGraph(int countV) {
        this.countV = countV;
        for (int i = 0; i < countV; i++) {
            AdjacentNode node = new AdjacentNode(i);
            adjacentNodeList.add(node);
        }
    }


    //Directed Graph

    public void addEdge(int source, int destination) {

        AdjacentNode node = adjacentNodeList.get(source);
        AdjacentNode nextNode = new AdjacentNode(destination);
        nextNode.next = node.next;
        node.next = nextNode;

    }

    public void printGraph() {

        for (int i = 0; i < countV; i++) {

            System.out.print("neighbours of graph node " + i + " are : ");

            AdjacentNode head = adjacentNodeList.get(i).next;

            while (head != null) {
                System.out.print(head.info + " ");
                head = head.next;
            }

            System.out.println();
        }

    }

}

public class AdjacencyListRepresentation {


    public static void main(String[] args) {

        AdjacencyGraph adjacencyGraph = new AdjacencyGraph(5);

        adjacencyGraph.addEdge(0, 1);
        adjacencyGraph.addEdge(0, 4);
        adjacencyGraph.addEdge(1, 0);
        adjacencyGraph.addEdge(1, 4);
        adjacencyGraph.addEdge(1, 2);
        adjacencyGraph.addEdge(1, 3);
        adjacencyGraph.addEdge(2, 1);
        adjacencyGraph.addEdge(2, 3);
        adjacencyGraph.addEdge(3, 1);
        adjacencyGraph.addEdge(3, 4);
        adjacencyGraph.addEdge(3, 2);
        adjacencyGraph.addEdge(4, 3);
        adjacencyGraph.addEdge(4, 0);
        adjacencyGraph.addEdge(4, 1);


        adjacencyGraph.printGraph();


    }


}
