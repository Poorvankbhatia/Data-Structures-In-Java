/*

Partition problem is to determine whether a given set can be partitioned into two subsets such that the sum of elements in both subsets is same.

Examples

arr[] = {1, 5, 11, 5}
Output: true
The array can be partitioned as {1, 5, 5} and {11}

arr[] = {1, 5, 3}
Output: false
The array cannot be partitioned into equal sum sets.

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 12/12/16.
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {

        int sum =0;
        for (Integer num : nums) {
            sum += num;
        }

        if((sum & 1)!=0) {
            return false;
        }

        sum = sum/2;

        return canPartitionDP(nums,sum,nums.length);

    }


    /*

    Time Complexity: O(2^n) In worst case, this solution tries two possibilities (whether to include or exclude) for every element.

     */
    private boolean canPartitionRecursive(int[] nums,int sum,int n) {

        if(sum<0 || n<=0) {
            return false;
        }

        if(sum==0) {
            return true;
        }

        if(nums[n-1]>sum) {
            return canPartitionRecursive(nums,sum,n-1);
        }

        return canPartitionRecursive(nums,sum-nums[n-1],n-1) || canPartitionRecursive(nums,sum,n-1);

    }

    /*

    The problem can be solved using dynamic programming when the sum of the elements is not too big.
    We can create a 2D array part[][] of size (sum/2)*(n+1). And we can construct the solution in bottom up manner such that every filled
    entry has following property
    part[i][j] = true if a subset of {arr[0], arr[1], ..arr[j-1]} has sum
             equal to i, otherwise false
     */

    private boolean canPartitionDP(int[] nums,int sum,int n) {

        boolean[][] dpTable = new boolean[sum+1][n+1];

        //Filling first row, if sum is zero than we can form 2 subsets having no element so marking it true
        for (int j=0;j<=n;j++) {
            dpTable[0][j] = true;
        }

        for (int i=1;i<=sum;i++) {
            dpTable[i][0] = false;
        }

        for (int i=1;i<=sum;i++) {
            for (int j=1;j<=n;j++) {
                //Checking if weight is greater than the current nums element
                if(i>=nums[j-1]) {
                    /*
                     1st term represents excluding the current element,
                     2nd term is including the current element
                     */
                    dpTable[i][j] = dpTable[i][j-1] || dpTable[i-nums[j-1]][j-1];
                } else {
                    dpTable[i][j] = dpTable[i][j-1];
                }
            }
        }

        return dpTable[sum][n];


    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,6};
        System.out.println(new PartitionEqualSubsetSum().canPartition(nums));
    }

}

/*

Following are the two main steps to solve this problem:
1) Calculate sum of the array. If sum is odd, there can not be two subsets with equal sum, so return false.
2) If sum of array elements is even, calculate sum/2 and find a subset of array with sum equal to sum/2.

The first step is simple. The second step is crucial, it can be solved either using recursion or Dynamic Programming.

Recursive Solution
Following is the recursive property of the second step mentioned above.

Let isSubsetSum(arr, n, sum/2) be the function that returns true if
there is a subset of arr[0..n-1] with sum equal to sum/2

The isSubsetSum problem can be divided into two subproblems
 a) isSubsetSum() without considering last element
    (reducing n to n-1)
 b) isSubsetSum considering the last element
    (reducing sum/2 by arr[n-1] and n to n-1)
If any of the above the above subproblems return true, then return true.
isSubsetSum (arr, n, sum/2) = isSubsetSum (arr, n-1, sum/2) ||
                              isSubsetSum (arr, n-1, sum/2 - arr[n-1])


Time Complexity: O(sum*n)
Auxiliary Space: O(sum*n)


 */
