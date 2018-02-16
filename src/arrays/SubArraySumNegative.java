/*
Given an unsorted array of integers, find a subarray which adds to a given number.
If there are more than one subarrays with sum as the given number, print any of them.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {10, 2, -2, -20, 10}, sum = -10
Ouptut: Sum found between indexes 0 to 3

Input: arr[] = {-10, 0, 2, -2, -20, 10}, sum = 20
Ouptut: No subarray with given sum exists
 */
package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 11/09/17.
 */
public class SubArraySumNegative {

    public int[] subArraySum(int[] nums,int target) {

        Map<Integer,Integer> map = new HashMap<>();

        int sum=0;

        int n = nums.length;
        for (int i=0;i<n;i++) {
            sum += nums[i];

            if(sum==target) {
                return new int[]{0,i};
            }

            /*
            If we find the same sum in map it means till that point(start below) upto current sum hasn't changed.
            it means the sum from start till current should be zero.
             */
            if(map.containsKey(sum-target)) {
                int start = map.get(sum-target)+1;
                return new int[]{start,i};
            }

            map.put(sum,i);

        }

        return new int[]{-1,-1};

    }

    public static void main(String[] args) {
        int[] arr = new int[]{-10, 0, 2, -2, -20, 10};
        int target = 20;
        System.out.print(Arrays.toString(new SubArraySumNegative().subArraySum(arr,target)));
    }

}


/*
An efficient way is to use a map. The idea is to maintain sum of elements encountered so far in a variable (say curr_sum).
 Let the given number is sum. Now for each element, we check if curr_sum – sum exists in the map or not.
 If we found it in the map that means, we have a subarray present with given sum, else we insert curr_sum
 into the map and proceed to next element. If all elements of the array are processed and we didn’t find any
 subarray with given sum, then subarray doesn’t exists
 */