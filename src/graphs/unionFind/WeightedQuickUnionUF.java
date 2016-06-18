package graphs.unionFind;

import utility.GetInputFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 18/06/16.
 */
public class WeightedQuickUnionUF {

    private int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int n) {

        parent = new int[n];
        size = new int[n];
        count = n;
        for (int i=0;i<n;i++) {
            size[i] = 1;
            parent[i] = i;
        }

    }

    public int getCount() {
        return count;
    }

    private void validate(int p) {
        int length = parent.length;
        if (p<0 || p>=length) {
            throw new IndexOutOfBoundsException("index - " + p + " has to be in between 0 and " + (length-1));
        }
    }

    public int find(int p) {
        validate(p);
        while (parent[p]!=p) {
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

        if(size[pRoot]<size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += pRoot;
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] +=qRoot;
        }

        count--;

    }

    public static void main(String[] args) {

        try {

            File file = GetInputFile.getFile("largeUF.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            int inputCount = Integer.parseInt(br.readLine());
            WeightedQuickUnionUF qf = new WeightedQuickUnionUF(inputCount);
            for (int i=0;i<inputCount;i++) {
                String[] input  = (br.readLine()).split(" ");
                int p = Integer.parseInt(input[0]);
                int q = Integer.parseInt(input[1]);
                if(!qf.isConnected(p,q)) {
                    qf.union(p,q);
                }
            }

            System.out.println("Number of connected components are : " + qf.getCount());


        } catch (Exception e) {
            System.err.print(e.getMessage());
            e.printStackTrace();
        }

    }

}


/*
Worst case for weighted quick union, when the sizes of the trees to be merged by
union() are always equal (and a power
of 2). These tree structures look complex,
but they have the simple property that
the height of a tree of 2n nodes is n. Furthermore,
when we merge two trees of 2n
nodes, we get a tree of 2n1 nodes, and we
increase the height of the tree to n1. This
observation generalizes to provide a proof
that the weighted algorithm can guarantee
logarithmic performance.


Proposition H. The depth of any node in a forest built by weighted quick-union for
N sites is at most lg N.

Proof: We prove a stronger fact by (strong) induction: The height of every tree of
size k in the forest is at most lg k. The base case follows from the fact that the tree
height is 0 when k is 1. By the inductive hypothesis, assume that the tree height of a
tree of size i is at most lg i for all i < k. When we combine a tree of size i with a tree
of size j with i <=j and i+j = k, we increase the depth of each node in the smaller set
by 1, but they are now in a tree of size i + j = k, so the property is preserved because
1+ lg i = lg(i + i ) <= lg(i + j ) = lg k.


Corollary. For weighted quick-union with N sites, the worst-case order of growth
of the cost of find(), connected(), and union() is log N.

Proof. Each operation does at most a constant number of array accesses for each
node on the path from a node to a root in the forest.

The weighted quick-union algorithm uses at most
c M lg N array accesses to process M connections among N sites for a small constant c.


 */