/*

Given a 2D array, find the maximum sum subarray in it. For example, in the following 2D array,
the maximum sum subarray has sum of this subarray is 29.

                {1, 2, -1, -4, -20}
                {-8, -3, 4, 2, 1}
                {3, 8, 10, 1, 3}
                {-4, -1, 1, 7, -6}

 */

package dyanamicprogramming;

/**
 * Created by poorvank on 28/02/17.
 */
public class LargestSubMatrixSum {

    public int maxSum(int[][] matrix) {

        int rows = matrix.length;
        if(rows==0) {
            return 0;
        }
        int cols = matrix[0].length;
        int result = Integer.MIN_VALUE;
        int startRow = 0,endRow=0;
        int startCol = 0,endCol=0;

        for (int leftCol = 0;leftCol<cols;leftCol++) {
            int[] temp = new int[rows];

            for (int rightCol = leftCol;rightCol<cols;rightCol++) {

                for (int i=0;i<rows;i++) {
                    temp[i] += matrix[i][rightCol];
                }
                int[] currentResult = kadaneAlgo(temp);
                if(currentResult[0]>result) {
                    result = currentResult[0];
                    startRow = currentResult[1];
                    endRow = currentResult[2];
                    startCol = leftCol;
                    endCol = rightCol;
                }

            }

        }

        System.out.println("FROM : " + startRow+","+startCol+ " TO : " +endRow+","+endCol);
        return result;


    }

    private int[] kadaneAlgo(int[] temp) {

        int maxTillHere=0;
        int maxEnd=Integer.MIN_VALUE;
        int start = 0;
        int end=0;
        int maxUntilNow = 0;


        for (int i=0;i<temp.length;i++) {

            maxTillHere += temp[i];

            if(maxTillHere<0) {
                maxUntilNow = i+1;
                maxTillHere = 0;
            } else if(maxEnd<maxTillHere) {
                maxEnd = maxTillHere;
                start = maxUntilNow;
                end = i;
            }

        }

        if(maxEnd<0) {
            for (int i=0;i<temp.length;i++) {
                maxEnd = Math.max(maxEnd,temp[i]);
                start=i;
                end=i;
            }
        }

        return new int[]{maxEnd,start,end};

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        System.out.println(new LargestSubMatrixSum().maxSum(arr));
    }

}

/*

Kadane’s algorithm for 1D array can be used to reduce the time complexity to O(n^3). The idea is to fix the left and right columns
one by one and find the maximum sum contiguous rows for every left and right column pair. We basically find top and bottom row numbers
(which have maximum sum) for every fixed left and right column pair. To find the top and bottom row numbers, calculate sun of elements
in every row from left to right and store these sums in an array say temp[]. So temp[i] indicates sum of elements from left to right in
row i. If we apply Kadane’s 1D algorithm on temp[], and get the maximum sum subarray of temp, this maximum sum would be the maximum
possible sum with left and right as boundary columns. To get the overall maximum sum, we compare this sum with the maximum sum so far.

 */