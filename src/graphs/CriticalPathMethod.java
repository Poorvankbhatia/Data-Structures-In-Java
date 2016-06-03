/**
 * Parallel precedence-constrained scheduling. Given a set of jobs of specified duration
 * to be completed, with precedence constraints that specify that certain jobs
 * have to be completed before certain other jobs are begun, how can we schedule
 * the jobs on identical processors (as many as needed ) such that they are all completed
 * in the minimum amount of time while still respecting the constraints?
 */


package graphs;


import utility.GetInputFile;
import utility.edgeClasses.DirectedEdge;
import utility.graphClasses.EdgeWeightedDigraph;

import java.io.*;

/**
 * Created by poorvank on 22/05/16.
 */
public class CriticalPathMethod {

    public static void main(String[] args) {

        try {
            BufferedReader br  = new BufferedReader(new FileReader(GetInputFile.getFile("CPMInput")));
            Integer n = Integer.parseInt(br.readLine());
            EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph((2*n)+2);

            int source=(2*n),sink=((2*n)+1);
            for (int i=0;i<n;i++) {
                String[] lineSplit = br.readLine().split(" ");
                int length = lineSplit.length;
                Double weight = Double.parseDouble(lineSplit[0]);
                edgeWeightedDigraph.addEdge(new DirectedEdge(source,i,0.0));
                edgeWeightedDigraph.addEdge(new DirectedEdge(i,i+n,weight));
                edgeWeightedDigraph.addEdge(new DirectedEdge(i+n,sink,0.0));
                for (int j=1;j<length;j++) {
                    edgeWeightedDigraph.addEdge(new DirectedEdge(i+n,Integer.parseInt(lineSplit[j]),0.0));
                }
            }

            AcyclicLP acyclicLP = new AcyclicLP(edgeWeightedDigraph,source);

            System.out.println("Job -- Start Time -- Finish Time");
            for (int i=0;i<n;i++) {
                System.out.println(i +" -- " + acyclicLP.distTo(i) + " -- " + acyclicLP.distTo(i+n));
            }

            System.out.println("Final Finish time = " + acyclicLP.distTo(sink));

        } catch (Exception e) {
            System.out.println("Exception occurred while reading input");
            e.printStackTrace();
        }

    }

}




/**
 * we schedule the jobs in topological
 * order and the total time required is the total duration of the jobs. Now, we
 * assume that we have sufficient processors to perform as many jobs as possible, limited
 * only by precedence constraints. Again, thousands or even millions of jobs might be
 * involved, so we require an efficient algorithm. Remarkably, a lineartime
 * algorithm is available—an approach known as the critical pathmethod demonstrates
 * that the problem is equivalent to a longestpaths
 * problem in an edge-weighted DAG.
 *
 * Definition. The critical path method for parallel scheduling is to proceed as follows:
 * Create an edge-weighted DAG with a source s, a sink t, and two vertices for each
 * job (a start vertex and an end vertex). For each job, add an edge from its start vertex
 * to its end vertex with weight equal to its duration. For each precedence constraint
 * v->w, add a zero-weight edge from the end vertex corresponding to v's to the beginning
 * vertex corresponding to w. Also add zero-weight edges from the source to each
 * job’s start vertex and from each job’s end vertex to the sink. Now, schedule each job
 * at the time given by the length of its longest path from the source.
 *
 * SEE IMAGES
 *
 * The class CPM is a straightforward implementation
 * of the critical path method. It transforms any instance of the job-scheduling
 * problem into an instance of the longest-paths problem in an edge-weighted DAG, uses
 * AcyclicLP to solve it, then prints the job start times and schedule finish time.
 *
 *
 * The critical path method solves the parallel precedence constrained
 * scheduling problem in linear time.
 * Proof: Why does the CPM approach work? The correctness of the algorithm
 * rests on two facts. First, every path in the DAG is a sequence of job
 * starts and job finishes, separated by zero-weight precedence constraints—
 * the length of any path from the source s to any vertex v in the graph is a
 * lower bound on the start/finish time represented by v, because we could
 * not do better than scheduling those jobs one after another on the same machine.
 * In particular, the length of the longest path from s to the sink t is a
 * lower bound on the finish time of all the jobs.
 * Second, all the start and finish
 * times implied by longest paths are feasible—every job starts after the finish
 * of all the jobs where it appears as a successor in a precedence constraint,
 * because the start time is the length of the longest path from the source to it.
 * In particular, the length of the longest path from s to t is an upper bound
 * on the finish time of all the jobs.
 *
 */


/*
 Dry run throughly to understand hoe vertices have been implemented
 Self Example:
 Given input :
 2
 43 1
 21

       GRAPH:
            43
          0-----2-----5(sink)
      0.0/     /0.0  /
        /0.0  /     / 0.0
(source)4-----1----3
                23
 */