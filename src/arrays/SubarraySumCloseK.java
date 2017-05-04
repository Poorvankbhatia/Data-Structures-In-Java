/*

Given an array, find the maximum sum of subarray close to k but not larger than k.

 */
package arrays;

import java.util.TreeSet;

/**
 * Created by poorvank.b on 04/05/17.
 */
public class SubarraySumCloseK {

    public int getLargestSumCloseToK(int[] arr, int k){
        int sum=0;
        TreeSet<Integer> set = new TreeSet<Integer>();
        int result=Integer.MIN_VALUE;
        set.add(0);

        for(int i=0; i<arr.length; i++){
            sum=sum+arr[i];

            /*
            When you are processing cum[j] what you need to retrieve from the set is the smallest number in the set
            such which is bigger than cum[j]−k.
             */
            Integer ceiling = set.ceiling(sum-k);
            if(ceiling!=null){
                result = Math.max(result, sum-ceiling);
            }

            set.add(sum);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,4,2,5,7,2,1};
        System.out.println(new SubarraySumCloseK().getLargestSumCloseToK(arr,8));
    }

}


/*

You can do this in O(nlog(n))

First thing to note is that sum of subarray (i,j] is just the sum of the first j elements less the sum of the first i elements.
Store these cumulative sums in the array cum. Then the problem reduces to finding  i,j such that i<j and cum[j]−cum[i]
is as close to kk but lower than it.

To solve this, scan from left to right. Put the cum[i]cum[i] values that you have encountered till now into a set.
When you are processing cum[j] what you need to retrieve from the set is the smallest number in the set such which is bigger than
cum[j]−k. This lookup can be done in O(logn) using upper_bound. Hence the overall complexity is O(nlog(n)).

 */