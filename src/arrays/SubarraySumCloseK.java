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
