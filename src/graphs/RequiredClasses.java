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


class DirectedVertex {

    public int info;
    public int weight;

    public List<DirectedVertex> adjacentVertices;

    public DirectedVertex(int info) {
        this(info, 0);
    }

    public DirectedVertex(int info, int weight) {
        this.info = info;
        adjacentVertices = new ArrayList<>();
        this.weight = weight;
    }


}


class Graph {

    public int noOfVertices;
    public Vertex[] vertexArray;
    public DirectedVertex[] directedVertexArray;

    public Graph(int noOfVertices) {

        this.noOfVertices = noOfVertices;
        this.vertexArray = new Vertex[noOfVertices];
        this.directedVertexArray = new DirectedVertex[noOfVertices];
        
        /*
        
        Basically vertex array consists of Vertex Objects having info same as the index of the array
        eg : index 0 has new Vertex(0)
        
         */
        for (int i = 0; i < noOfVertices; i++) {
            vertexArray[i] = new Vertex(i);
            directedVertexArray[i] = new DirectedVertex(i);
        }

    }

    public void addEdge(int v1, int v2) {
        vertexArray[v1].adjacentVertices.add(v2);
    }

    public void addEdge(int v1, int v2, int w) {

        directedVertexArray[v1].adjacentVertices.add(new DirectedVertex(v2, w));
        System.out.println("vertex  " + v1 + " to vertex " + v2 + " with weight " + directedVertexArray[v1].adjacentVertices.get(0).weight);
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

    public static DirectedVertex[] directedGraphInput() {

        int a = 0, b = 0, c = 0;
        System.out.println("Enter number of vertices ");
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        Graph graph = new Graph(count);
        System.out.println("Enter vertex values to create an edge between them with weight c eg a,b,c , enter s to end input");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("s")) {
                break;
            }
            String[] inputSplit = input.split(",");
            if (inputSplit.length == 3) {
                // for (String c : input.split(",")) {
                a = Integer.parseInt(inputSplit[0]);
                b = Integer.parseInt(inputSplit[1]);
                c = Integer.parseInt(inputSplit[2]);
                while (a >= count || b >= count) {
                    System.out.println("Wrong Input");
                    continue;
                }
                // }
                graph.addEdge(a, b, c);
            } else {
                System.out.println("Wrong Input");
            }

        }

        return graph.directedVertexArray;

    }

}


class Stack {

    public final int MAX = 100;
    public int top = -1;
    public Object[] stack = new Object[MAX];
    //public DirectedVertex[] directedStack = new DirectedVertex[MAX];

    public void push(Object a) {

        top++;
        if (isFull()) {
            System.out.println("Stack OverFlow");
        }
        stack[top] = a;

    }

    public boolean isFull() {

        if (top == MAX - 1) {
            return true;
        }
        return false;

    }

    public boolean isEmpty() {

        if (top == -1) {
            return true;
        }
        return false;
    }

    public Object pop() {

        if (!isEmpty()) {
            Object a = stack[top];
            top--;
            return a;
        }
        System.out.println("Stack Underflow");
        return null;

    }

}

/*

nextInt() does not consume the '\n', so the next call to nextLine() consumes it

sc.nextLine(); //throw away the \n not consumed by nextInt()

*/