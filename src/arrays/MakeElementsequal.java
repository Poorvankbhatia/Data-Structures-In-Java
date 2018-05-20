/*

Minimum number of increment-other operations to make all array elements equal.
We are given an array consisting of n elements. At each operation you can select any one element and increase rest of
 n-1 elements by 1. You have to make all elements equal performing such operation as many times you wish. Find the minimum number of operations needed for this.

Examples:

Input : arr[] = {1, 2, 3}
Output : Minimum Operation = 3
Explanation :
operation | increased elements | after increment
    1     |    1, 2            | 2, 3, 3
    2     |    1, 2            | 3, 4, 3
    3     |    1, 3            | 4, 4, 4

Input : arr[] = {4, 3, 4}
Output : Minimum Operation = 2
Explanation :
operation | increased elements | after increment
     1    |    1, 2           | 5, 4, 4
     2    |    2, 3           | 5, 5, 5

 */
package arrays;

/**
 * Created by poorvank.b on 20/05/18.
 */
public class MakeElementsEqual {

    public static int noOfOperations(int[] arr) {

        int n = arr.length;

        int sum=0;
        for(int a : arr) {
            sum+=a;
        }

        int min=Integer.MAX_VALUE;
        for(int a:  arr) {
            min = Math.min(a,min);
        }

        return sum-(n*min);

    }

}

/*

If we took a closer look at each operation as well problem statement we will find that increasing all n-1
element except the largest one is similar to decreasing the largest element only. So, the smallest elements need
 not to decrease any more and rest of elements will got decremented upto smallest one. In this way the total number of
  operation required for making all elements equal will be arraySum – n * (smallesteElement). Time complexity will
 be same as that of finding smallest elements and array sum i.e. O(n).

// find array sum
sum = arraySum (int arr[], int n);

// find the smallest element from array
small = smallest (int arr, int n);

// calculate min operation required
minOperation = sum - (n * small);

// return result
return minOperation;

 */