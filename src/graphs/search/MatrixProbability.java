/*

A matrix probability question
Given a rectangular matrix, we can move from current cell in 4 directions with equal probability.
The 4 directions are right, left, top or bottom.
Calculate the Probability that after N moves from a given position (i, j) in the matrix, we will not cross boundaries of the matrix
at any point.

 */

package graphs.search;

/**
 * Created by poorvank on 24/07/16.
 */
public class MatrixProbability {

    private static double calculateProbability(int m,int n,int k,int i,int j) {

        double probability = 0.0;


        if (!isSafe(i, j, m, n)) {
            return 0;
        }

        if(k==0) {
            return 1;
        }

        //down
        probability += calculateProbability(m, n, k - 1, i + 1, j) * (0.25);

        //up
        probability += calculateProbability(m, n, k - 1, i - 1, j) * (0.25);

        //right
        probability += calculateProbability(m, n, k - 1, i, j + 1) * (0.25);

        //left
        probability += calculateProbability(m, n, k - 1, i, j - 1) * (0.25);



        return probability;

    }

    private static boolean isSafe(int i,int j,int m, int n) {
        return (i>=0 && j>=0 && i<m && j<n);
    }

    public static void main(String[] args) {

        int m=5,n=5;
        int i=1,j=1;
        int k =2;

        System.out.println(calculateProbability(m,n,k,i,j));

    }

}

/*

The idea is to perform something similar to DFS. We recursively traverse in each of the 4 allowed direction and for each cell encountered,
we calculate the required probability with one less move. As each direction has equal probability, each direction will contribute to 1/4 of
total probability of that cell i.e. 0.25. We return 0 if we step outside the matrix and return 1 if N steps are completed without crossing
matrix boundaries.

 */