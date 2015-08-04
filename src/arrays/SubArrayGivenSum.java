/*

Find subarray with given sum
Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
Ouptut: Sum found between indexes 1 and 4

Input: arr[] = {1, 4}, sum = 0
Output: No subarray found
There may be more than one subarrays with sum as the given sum. The following solutions print first such subarray.

 */

package arrays;

/**
 * Created by poorvank on 8/4/15.
 */
public class SubArrayGivenSum {

    public static void main(String[] args) {

        int[] array = new int[]{15, 2, 4, 8, 9, 5, 10, 23};

        findSubArray(array, array.length, 23);

    }

    private static void findSubArray(int[] array, int n, int sum) {

        int currentSum = array[0];
        int start = 0;

        for (int i = 1; i < n; i++) {

            while (currentSum > sum && start < i) {
                currentSum = currentSum - array[start];
                start++;
            }
            if (currentSum == sum) {
                System.out.println("Sum found between - " + start + " " + (i - 1));
                break;
            }
            currentSum += array[i];

        }

    }

}

/*

Initialize a variable curr_sum as first element. curr_sum indicates the sum of current subarray. 
Start from the second element and add all elements one by one to the curr_sum. 
If curr_sum becomes equal to sum, then print the solution. 
If curr_sum exceeds the sum, then remove trailing elemnents while curr_sum is greater than sum.


Time complexity of method 2 looks more than O(n), but if we take a closer look at the program, then we can 
figure out the time complexity is O(n). 
We can prove it by counting the number of operations performed on every element of arr[] in worst case. 
There are at most 2 operations performed on every element: (a) the element is added to the curr_sum 
(b) the element is subtracted from curr_sum. So the upper bound on number of operations is 2n which is O(n).



 */