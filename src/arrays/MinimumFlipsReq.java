/*

Given a binary array, we can flip all the 1 are in the left part and all the 0 to the right part.Calculate the minimum flips required to make all
1s in left and all 0s in right.

Examples:

Input: 1011000
Output: 1
1 flip is required to make it 1111000.

Input : 00001
Output : 2
2 flips required to make it 10000.

 */
package arrays;

/**
 * Created by poorvank.b on 01/04/18.
 */
public class MinimumFlipsReq {

    public int minimumFlipCount(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        int flip=0;
        for (int i=0;i<n;i++) {
            if(nums[i]==0) {
                flip++;
            }
            left[i]=flip;
        }

        flip=0;
        for (int i=n-1;i>=0;i--) {
            if(nums[i]==1) {
                flip++;
            }
            right[i]=flip;
        }

        // At every point we check, value required to convert 0-1 till current point, and 1-0 from next point onwards.
        int count = Integer.MAX_VALUE;
        for (int i=0;i<n-1;i++) {
            if(count>left[i]+right[i+1]){
                count=left[i]+right[i+1];
            }
        }

        return count;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0,0,0,0,0,1};
        System.out.println(new MinimumFlipsReq().minimumFlipCount(arr));
    }

}

/*

It can be done with O(N) time complexity (where N – number of bits) and O(N) extra memory

Calculate number of flips of ‘0’ needed to be done while moving from left to right to have all ‘1’ in bits.
Calculate number of flips of ‘1’ needed to be done while moving from right to left to have all ‘0’ in bits.
Traversing through all positions between bits and find minimal sum of ‘0’-flips+’1′-flips from both arrays.


 */