package graphs.unionFind;

import utility.GetInputFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by poorvank on 17/06/16.
 */
public class QuickFindUF {

    private int id[];  //id[i] = component identifier of i;
    private int count; //Number of connected components

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i=0;i<n;i++) {
            id[i] = i;
        }
    }


    public int getCount() {
        return count;
    }

    public int find(int p) {
        validate(p);
        return id[p];
    }

    private void validate(int p) {
        int length =  id.length;
        if(p<0 || p>=length) {
            throw new IndexOutOfBoundsException("index " + p + " is not in between " + 0 + " and " + (length-1));
        }
    }

    public boolean isConnected(int p,int q) {
        validate(p);
        validate(q);
        return find(p)==find(q);
    }


    public void union(int p,int q) {

        int pId = id[p];
        int qId = id[q];

        if(pId==qId) {
            return;
        }

        for (int i=0;i<id.length;i++) {
            if(id[i]==pId) {
                id[i] = qId;
            }
        }

        count--;

    }

    public static void main(String[] args) {

        try {

            File file = GetInputFile.getFile("largeUF.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));
            int inputCount = Integer.parseInt(br.readLine());
            QuickFindUF qf = new QuickFindUF(inputCount);
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

Proposition F. The quick-find algorithm uses one array access for each call to
find() and between N + 3 and 2N + 1 array accesses for each call to union() that
combines two components.

Proof: Immediate from the code. Each call to connected() tests two entries in the
id[] array, one for each of the two calls to find(). Each call to union() that combines
two components does so by making two calls to find(), testing each of the N
entries in the id[] array, and changing between 1 and N  1 of them.

In particular, suppose that we use quick-find for the
dynamic connectivity problem and wind up with a
single component. This requires at least N1 calls to
union(), and, consequently, at least (N+3)(N-1) ~
square(N) array accesses—we are led immediately to the hypothesis
that dynamic connectivity with quick-find
can be a quadratic-time process.




 */