package graphs;

import utility.GetInputFile;
import utility.Queue;
import utility.Stack;
import utility.graphClasses.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 03/06/16.
 */
public class BreadthFirstPaths {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] vertexTo;
    private int[] distFromSource;


    public BreadthFirstPaths(Graph G, int source) {
        int size = G.getVertexCount();
        marked = new boolean[size];
        vertexTo = new int[size];
        distFromSource = new int[size];

        bfs(G,source);

    }

    private void bfs(Graph G,int source) {
        Queue<Integer> queue = new Queue<>();

        for (int i=0;i<G.getVertexCount();i++) {
            distFromSource[i] = INFINITY;
        }

        distFromSource[source] = 0;
        queue.enqueue(source);
        marked[source] = true;

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : G.getAdj(v)) {
                if(!marked[w]) {
                    marked[w] = true;
                    vertexTo[w] = v;
                    distFromSource[w] = distFromSource[v] + 1;
                    queue.enqueue(w);
                }
            }
        }

    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distanceFromSource(int v) {
        return distFromSource[v];
    }

    public Iterable<Integer> pathTo(int v) {

        Stack<Integer> path = new Stack<>();
        while (distFromSource[v]!=0) {
            path.push(v);
            v = vertexTo[v];
        }
        path.push(v);
        return path;

    }

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(GetInputFile.getFile("largeG.txt")));
            int vertexCount = Integer.parseInt(br.readLine());
            int inputCount = Integer.parseInt(br.readLine());
            Graph G = new Graph(vertexCount);
            for (int i=0;i<inputCount;i++) {
                String[] inputString = br.readLine().split(" ");
                int v = Integer.parseInt(inputString[0]);
                int w = Integer.parseInt(inputString[1]);
                G.addEdge(v,w);
            }

            int source = 0;
            BreadthFirstPaths bfsPaths = new BreadthFirstPaths(G,source);

            for (int v=0;v<vertexCount;v++) {
                if(bfsPaths.hasPathTo(v)) {
                    System.out.print(source + "->" + v + "=" + bfsPaths.distanceFromSource(v) + " : ");
                    for (int i : bfsPaths.pathTo(v)) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }
                else {
                    System.out.println(source + " -> " + v + " are not connected");
                }
            }


        } catch (Exception e) {
            System.err.println("Exception occurred");
        }


    }

}
