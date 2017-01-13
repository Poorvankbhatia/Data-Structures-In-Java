/*

Find three closest elements from given three sorted arrays
Given three sorted arrays A[], B[] and C[], find 3 elements i, j and k from A, B and C respectively such
that max(abs(A[i] – B[j]), abs(B[j] – C[k]), abs(C[k] – A[i])) is minimized. Here abs() indicates absolute value.

Example :

Input: A[] = {1, 4, 10}
       B[] = {2, 15, 20}
       C[] = {10, 12}
Output: 10 15 10
10 from A, 15 from B and 10 from C

Input: A[] = {20, 24, 100}
       B[] = {2, 19, 22, 79, 800}
       C[] = {10, 12, 23, 24, 119}
Output: 24 22 23
24 from A, 22 from B and 23 from C

 */
package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 13/01/17.
 */
public class Find3ClosestElements {

    public void printClosest(int[] p,int[] q,int[] r) {

        int pLength = p.length;
        int qLength = q.length;
        int rLength = r.length;
        int[] resultArr = new int[3];
        int result = Integer.MAX_VALUE;

        int x=0,y=0,z=0;

        while (x<pLength && y<qLength && z<rLength) {

            int valueFromP = p[x];
            int valueFromQ = q[y];
            int valueFromR = r[z];

            int max = Math.max(valueFromP,Math.max(valueFromR,valueFromQ));
            int min = Math.min(valueFromP,Math.min(valueFromR,valueFromQ));

            if(result<max-min) {
                result = max-min;
            }

            resultArr[0] = valueFromP;
            resultArr[1] = valueFromQ;
            resultArr[2] = valueFromR;

            if(min==p[x]) {
                x++;
            } else if(min==q[y]) {
                y++;
            } else {
                z++;
            }

        }

        System.out.println(Arrays.toString(resultArr));

    }



    public static void main(String[] args) {

        int A[] = {1, 4, 10};
        int B[] = {2, 15, 20};
        int C[] = {10, 12};

        new Find3ClosestElements().printClosest(A,B,C);

    }
}

/*

A Simple Solution is to run three nested loops to consider all triplets from A, B and C. Compute the value of max(abs(A[i] – B[j]), abs(B[j] – C[k]),
 abs(C[k] – A[i]))
 for every triplet and return minimum of all values. Time complexity of this solution is O(n3)

A Better Solution is to us Binary Search.
1) Iterate over all elements of A[],
      a) Binary search for element just smaller than or equal to in B[] and C[], and note the difference.
2) Repeat step 1 for B[] and C[].
3) Return overall minimum.

Time complexity of this solution is O(nLogn)

Efficient Solution Let ‘p’ be size of A[], ‘q’ be size of B[] and ‘r’ be size of C[]

1)   Start with i=0, j=0 and k=0 (Three index variables for A,
                                  B and C respectively)

//  p, q and r are sizes of A[], B[] and C[] respectively.
2)   Do following while i < p and j < q and k < r
    a) Find min and maximum of A[i], B[j] and C[k]
    b) Compute diff = max(X, Y, Z) - min(A[i], B[j], C[k]).
    c) If new result is less than current result, change
       it to the new result.
    d) Increment the pointer of the array which contains
       the minimum.
Note that we increment the pointer of the array which has the minimum, because our goal is to decrease the difference.
Increasing the maximum pointer increases the difference. Increase the second maximum pointer can potentially increase the difference.

 */