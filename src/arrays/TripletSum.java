/*

Find a triplet that sum to a given value
Given an array and a value, find if there is a triplet in array whose sum is equal to the given value. 
If there is such a triplet present in array, then print the triplet and return true. Else return false. 
For example, if the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then there is a triplet (12, 3 and 9) 
present in array whose sum is 24

 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 8/4/15.
 */
public class TripletSum {

    public static void main(String[] args) {

        int[] array = new int[]{1, 4, 45, 6, 10, 8};

        int n = array.length, sum = 22;

        Arrays.sort(array);

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                if (array[i] + array[left] + array[right] == sum) {
                    System.out.println("Found sum - " + array[i] + " " + array[left] + " " + array[right]);
                    break;
                } else if (array[i] + array[left] + array[right] > sum) {
                    right--;
                } else {
                    left++;
                }

            }


        }

    }

}


/*

Time complexity of the method 1 is O(n^3). The complexity can be reduced to O(n^2) by sorting the array first,
 and then using method 1 of this post in a loop.
1) Sort the input array.
2) Fix the first element as A[i] where i is from 0 to array size – 2. 
After fixing the first element of triplet, find the other two elements using method 1 of this post.

 */