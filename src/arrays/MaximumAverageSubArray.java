/*

Find maximum average subarray of k length
Given an array with positive and negative numbers, find the maximum average subarray of given length.

Example:

Input:  arr[] = {1, 12, -5, -6, 50, 3}, k = 4
Output: Maximum average subarray of length 4 begins
        at index 1.
Maximum average is (12 - 5 - 6 + 50)/4 = 51/4

 */

package arrays;

/**
 * Created by poorvank on 8/28/15.
 */
public class MaximumAverageSubArray {
    
    public static void main(String[] args) {
        
        int[] arr = new int[] {1, 12, -5, -6, 50, 3};
        
        int n = arr.length;
        int sum = 0;
        int k=4;
        
        for (int i=0;i<k;i++) {
            sum += arr[i];
        }
        int maxSum=sum;
        int end = 0;
        for (int i=k;i<n;i++) {
            
            sum = sum + arr[i] - arr[i-k];
            if(sum>maxSum) {
                maxSum = sum;
                end =i;
            }
            
        }
        
        System.out.println("Maximum average starts at = " + (end-k+1));
        
    }
    
}

/*

1) Compute sum of first ‘k’ elements, i.e., elements arr[0..k-1]. Let this sum be ‘sum’. Initialize ‘max_sum’ as ‘sum’
2) Do following for every element arr[i] where i varies from ‘k’ to ‘n-1′
…….a) Remove arr[i-k] from sum and add arr[i], i.e., do sum += arr[i] – arr[i-k]
…….b) If new sum becomes more than max_sum so far, update max_sum.
3) Return ‘max_sum’

 */