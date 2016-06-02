/*

Consider a market for financial transactions that is based on trading commodities.
You can find a familiar example in tables that show conversion rates among
currencies, such as the one in our sample file rates.txt shown here. The first line
in the file is the number V of currencies;
then the file has one line per currency,
giving its name followed by the conversion
rates to the other currencies. For
brevity, this example includes just five
of the hundreds of currencies that are
traded on modern markets: U.S. dollars
(USD), Euros (EUR), British pounds
(GBP), Swiss francs (CHF), and Canadian
dollars (CAD). The tth number on line s represents a conversion rate: the number of
units of the currency named on row s that is needed to buy 1 unit of the currency
named on row t. For example, our table says that 1,000 U.S. dollars will buy 741 euros.
This table is equivalent to a complete edge-weighted digraph with a vertex corresponding
to each currency and an edge corresponding to each conversion rate. An edge s->t
with weight x corresponds to a conversion from s to t at exchange rate x.



 */

package graphs;

import utility.edgeClasses.DirectedEdge;
import utility.graphClasses.EdgeWeightedDigraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 24/05/16.
 */
public class Arbitrage {

    private static String[] name;

    public static void main(String[] args) {

        try {
            String filePath = new File("").getAbsolutePath();
            BufferedReader br = new BufferedReader(new FileReader(filePath + "/src/inputFiles/rates.txt"));
            Integer vertexCount = Integer.parseInt(br.readLine());
            EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(vertexCount);
            name = new String[vertexCount];
            for (int i=0;i<vertexCount;i++) {
                String[] inputSplit = (br.readLine()).split("\\s+");
                name[i] = inputSplit[0];
                for (int j=0;j<vertexCount;j++) {
                    double weight = Double.parseDouble(inputSplit[j+1]);
                    DirectedEdge e = new DirectedEdge(i,j,Math.log((1/weight)));
                    digraph.addEdge(e);
                }
            }

            System.out.println(digraph);

            BellmanFordSP spt = new BellmanFordSP(digraph, 0);
            if (spt.hasNegativeCycle()) {
                double stake = 1000.0;
                for (DirectedEdge e : spt.getCycle()) {
                    System.out.printf("%10.5f %s ", stake, name[e.from()]);
                    stake *= Math.exp(-e.getWeight());
                    System.out.printf("= %10.5f %s\n", stake, name[e.to()]);
                }
            }
            else {
                System.out.println("No arbitrage opportunity");
            }


        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }

}


/*

This problem can be interpreted as a graph problem: Each currency is a node and each possibility
of exchange between two currencies is an edge. The edges are weighted by the exchange
rate. The question is: Does there exist a cycle in the graph, such that the product of the edge
weights is greater than 1? Since the known graph algorithms are designed to minimize the sum
of the edge weights instead of maximizing their product, we use the a fact: Check Image named Arbitrage


The arbitrage problem is a negative-cycle-detection problem in
edge-weighted digraphs.
Proof: Replace each weight by its logarithm, negated. With this change, computing
path weights by multiplying edge weights in the original problem corresponds
to adding them in the transformed problem. Specifically, any product w1w2 . . . wk
corresponds to a sum ln(w1)  ln(w2)  . . .  ln(wk). The transformed edge
weights might be negative or positive, a path from v to w gives a way of converting
from currency v to currency w, and any negative cycle is an arbitrage opportunity.




 */