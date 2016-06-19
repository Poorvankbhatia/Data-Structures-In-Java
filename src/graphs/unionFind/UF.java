/*

Dynamic connectivity We start with the following problem specification: The
input is a sequence of pairs of integers, where each integer represents an object of some
type and we are to interpret the pair p q as meaning “p is connected to q.” We assume
that “is connected to” is an equivalence relation, which means that it is
■ Reflexive : p is connected to p.
■ Symmetric : If p is connected to q, then q is connected to p.
■ Transitive : If p is connected to q and q is connected to r, then p is connected to r.
An equivalence relation partitions the objects into equivalence classes. In this case, two
objects are in the same equivalence class if and only if they are connected. Our goal is
to write a program to filter out extraneous pairs (pairs where both objects are in the
same equivalence class) from the sequence. In other words, when the program reads a
pair p q from the input, it should write the pair to the output only if the pairs it has
seen to that point do not imply that p is connected to q. If the previous pairs do imply
that p is connected to q, then the program should ignore the pair p q and proceed to
read in the next pair.

 */

package graphs.unionFind;

import utility.GetInputFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 18/06/16.
 */
public class UF {

    private int[] rank;
    private int[] parent;
    private int count;


    public UF(int n)  {
        if(n<0) {
            throw new IllegalArgumentException("n cannot be negative");
        }
        rank = new int[n];
        parent = new int[n];
        count = n;
        for (int i=0;i<n;i++) {
            rank[i] = 0;
            parent[i] = i;
        }
    }

    public int find(int p) {
        validate(p);
        while (p!=parent[p]) {
            parent[p] = parent[parent[p]]; // path compression by halving
            p = parent[p];
        }

        return p;

    }

    public boolean isConnected(int p,int q) {
        return find(p)==find(q);
    }

    public void union(int p,int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot==qRoot) {
            return;
        }

        if(rank[pRoot]<rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if(rank[pRoot]>rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] ++;
        }
        count--;
    }

    public int getCount() {
        return count;
    }

    private void validate(int p) {
        int length = rank.length;
        if(p<0 || p>=length) {
            throw new IndexOutOfBoundsException("index - " + p + " has to be in between 0 and " + (length-1));
        }
    }

    public static void main(String[] args) {

        try {

            File file = GetInputFile.getFile("largeUF.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            int vertexCount = Integer.parseInt(br.readLine());
            UF uf = new UF(vertexCount);
            String line = "";
            while ((line=br.readLine())!=null) {
                String[] input  = (line).split(" ");
                int p = Integer.parseInt(input[0]);
                int q = Integer.parseInt(input[1]);
                if(!uf.isConnected(p,q)) {
                    uf.union(p,q);
                }
            }

            System.out.println("Number of connected components are : " + uf.getCount());


        } catch (Exception e) {
            System.err.print(e.getMessage());
            e.printStackTrace();
        }

    }


}


/*

The following method,
known as path compression, is easy to implement. Ideally, we would like every node
to link directly to the root of its tree, but we do not want to pay the price of changing a
large number of links, as we did in the quick-find algorithm. We can approach the ideal
simply by making all the nodes that we do examine directly link to the root. This step
seems drastic at first blush, but it is easy to implement, and there is nothing sacrosanct
about the structure of these trees: if we can modify them to make the algorithm more
efficient, we should do so. To implement path compression, we just add another loop to
find() that sets the id[] entry corresponding to each node encountered along the way
to link directly to the root. The net result is to flatten the trees almost completely, approximating
the ideal achieved by the quick-find algorithm. The method is simple and
effective, but you are not likely to be able to discern any improvement over weighted
quick-union in a practical situation (see Exercise 1.5.24). Theoretical results about
the situation are extremely complicated and quite remarkable. Weighted quick union
with path compression is optimal but not quite constant-time per operation. That is, not
only is weighted quick-find with path compression not constant-time per operation
in the worst case ( amortized), but also there exists no algorithm that can guarantee to
perform each union-find operation in amortized constant time (under the very general
“cell probe” model of computation). Weighted quick-union with path compression is
very close to the best that we can do for this problem.

 */