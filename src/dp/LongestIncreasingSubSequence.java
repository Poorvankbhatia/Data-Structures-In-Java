/*

The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given 
sequence such that all elements of the subsequence are sorted in increasing order. 
For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.

 */

package dp;

/**
 * Created by poorvank on 5/20/15.
 */
public class LongestIncreasingSubSequence {

    public static void main(String[] args) {
        int[] array = new int[]{23, 10, 22, 5, 33, 8, 9, 21, 50, 41, 60, 80, 99, 22, 23, 24, 25, 26, 27};
        printLIS(array);
    }

    private static void printLIS(int[] array) {

        int[] size = new int[array.length];
        String[] lisArray = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            size[i] = 1;
            lisArray[i] = array[i] + " ";
        }

        int maxLength = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && size[j] + 1 > size[i]) {
                    size[i] = size[j] + 1;
                    lisArray[i] = lisArray[j] + " " + array[i];
                    if (size[i] > maxLength) {
                        maxLength = size[i];
                    }
                }
            }
        }

        System.out.println();


        for (int i = 0; i < array.length; i++) {
            if (size[i] == maxLength) {
                System.out.println("Longest Increasing subsequence - " + lisArray[i] + " of length = " + maxLength);
            }
        }

    }

}


/*

Optimal Substructure:
Let arr[0..n-1] be the input array and L(i) be the length of the LIS till index i such that arr[i] is part 
of LIS and arr[i] is the last element in LIS, then L(i) can be recursively written as.
L(i) = { 1 + Max ( L(j) ) } where j < i and arr[j] < arr[i] and if there is no such j then L(i) = 1
To get LIS of a given array, we need to return max(L(i)) where 0 < i < n
So the LIS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.

Considering the above implementation, following is recursion tree for an array of size 4.
lis(n) gives us the length of LIS for arr[].

    
                     lis(4)           
                 /       |      \
         lis(3)      lis(2)    lis(1)  
        /     \        /         
  lis(2)  lis(1)   lis(1) 
  /    
lis(1) 
We can see that there are many subproblems which are solved again and again. 
So this problem has Overlapping Substructure property and recomputation of same 
subproblems can be avoided by either using Memoization or Tabulation.
Following is a tabluated implementation for the LIS problem.

 */