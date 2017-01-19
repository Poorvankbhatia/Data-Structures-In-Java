/*

solving the
 *  single-source longest paths problem in edge-weighted directed
 *  acyclic graphs (DAGs). The edge weights can be positive, negative, or zero.

 */

package graphs;

import utility.GetInputFile;
import utility.edgeClasses.DirectedEdge;
import utility.Stack;
import utility.graphClasses.EdgeWeightedDigraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 22/05/16.
 */
public class AcyclicLP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public AcyclicLP(EdgeWeightedDigraph G, int souce) {
        int size = G.getVertexCount();
        distTo = new double[size];
        edgeTo = new DirectedEdge[size];
        for (int v=0;v<size;v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;
        }
        distTo[souce] = 0.0;

        Topological ts = new Topological(G);
        if(!ts.hasOrder()) {
            System.out.println("Graph is not acyclic");
        }
        for (Integer v : ts.getOrder()) {
            for (DirectedEdge e : G.getAdj(v)) {
                relaxOpposite(e);
            }
        }

    }

    public double distTo(int v) {
        return distTo[v];
    }

    private void relaxOpposite(DirectedEdge e) {
        int v = e.from(),w=e.to();
        if(distTo[w] < distTo[v] + e.getWeight()) {
            distTo[w] = distTo[v] + e.getWeight();
            edgeTo[w] = e;
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v]>Double.NEGATIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v)) {
            return null;
        }

        Stack<DirectedEdge> cycle = new Stack<>();
        DirectedEdge e = edgeTo[v];
        while (e!=null) {
            cycle.push(e);
            e = edgeTo[e.from()];
        }
        return cycle;
    }

    public static void main(String[] args) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(GetInputFile.getFile("newInput.txt")));
            int vertexCount = Integer.parseInt(br.readLine());
            int lineCount = Integer.parseInt(br.readLine());

            EdgeWeightedDigraph G = new EdgeWeightedDigraph(vertexCount);
            for (int i=0;i<lineCount;i++) {
                String[] input = (br.readLine()).split(" ");
                double weight = Double.parseDouble(input[2]);
                int v = Integer.parseInt(input[0]);
                int w = Integer.parseInt(input[1]);
                DirectedEdge e = new DirectedEdge(v,w,weight);
                G.addEdge(e);
            }

            int s =5;
            AcyclicLP sp = new AcyclicLP(G,s);

            for (int t = 0; t < G.getVertexCount(); t++) {
                if (sp.hasPathTo(t)) {
                    System.out.printf("%d to %d  ", s, t);
                    for (DirectedEdge e : sp.pathTo(t)) {
                        System.out.print(e + "   ");
                    }
                    System.out.println();
                }
                else {
                    System.out.printf("%d to %d no path present\n", s, t);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
