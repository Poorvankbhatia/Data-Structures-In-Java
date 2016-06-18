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
            int inputCount = Integer.parseInt(br.readLine());
            UF uf = new UF(inputCount);
            for (int i=0;i<inputCount;i++) {
                String[] input  = (br.readLine()).split(" ");
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
