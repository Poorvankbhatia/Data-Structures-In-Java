package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by poorvank on 4/1/15.
 */
class Vertex {

    public int info;
    //This list is for storing neighbours
    public List<Integer> adjacentVertices;

    public Vertex(int info) {

        this.info = info;
        adjacentVertices = new ArrayList<>();

    }


}


class Graph {

    public int noOfVertices;
    public Vertex[] vertexArray;

    public Graph(int noOfVertices) {

        this.noOfVertices = noOfVertices;
        this.vertexArray = new Vertex[noOfVertices];
        
        /*
        
        Basically vertex array consists of Vertex Objects having info same as the index of the array
        eg : index 0 has new Vertex(0)
        
         */
        for (int i = 0; i < noOfVertices; i++) {
            vertexArray[i] = new Vertex(i);
        }

    }

    public void addEdge(int v1, int v2) {
        vertexArray[v1].adjacentVertices.add(v2);
    }

}

class Input {

    public static Vertex[] graphInput() {

        int a = 0, b = 0;
        System.out.println("Enter number of vertices ");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Graph graph = new Graph(count);
        System.out.println("Enter vertex values to create an edge between them eg a,b , enter s to end input");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("s")) {
                break;
            }
            String[] inputSplit = input.split(",");
            if (inputSplit.length == 2) {
                for (String c : input.split(",")) {
                    a = Integer.parseInt(inputSplit[0]);
                    b = Integer.parseInt(inputSplit[1]);
                    if (a >= count || b >= count) {
                        System.out.println("Wrong Input");
                        continue;
                    }
                }
                graph.addEdge(a, b);
            } else {
                System.out.println("Wrong Input");
            }

        }

        return graph.vertexArray;

    }

}

/*

nextInt() does not consume the '\n', so the next call to nextLine() consumes it

sc.nextLine(); //throw away the \n not consumed by nextInt()

*/