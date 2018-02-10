/*

There are N trees in a circle. Each tree has a fruit value associated with it.

A bird has to sit on a tree for 0.5 sec to gather all the fruits present on the tree and then the bird can move to a neighboring tree.
 It takes the bird 0.5 seconds to move from one tree to another. Once all the fruits are picked from a particular tree, she can’t pick
 any more fruits from that tree. The maximum number of fruits she can gather is infinite.

We are given N and M (the total number of seconds the bird has), and the fruit values of the trees. We have to maximize the total
fruit value that the bird can gather. The bird can start from any tree.

 */

package miscellaneous;

/**
 * Created by poorvank.b on 10/02/18.
 */
public class MaxTreeSum {

    public int maxSum(int[] nums,int k) {

        if(nums==null || nums.length==0 || nums.length<k) {
            return 0;
        }

        int maxSum=0;
        for (int i=0;i<k;i++) {
            maxSum+=nums[i];
        }

        int res = 0;
        for (int i=1;i<nums.length;i++) {
            res = Math.max(res,maxSum-nums[i-1]+nums[(i+k-1)%nums.length]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,6,2,5,3,4};
        System.out.println(new MaxTreeSum().maxSum(nums,2));
    }

}

/*

First lets think for data structure to use.
Here we have a tree with fruit value. No need to think deep for tree and traversal, we can simply create a node with value.
They are in circular, hence we can think of circular queue or array or linkelist.
as Circular Array is easiest one, We can take it.
Queue is also not needed as no need for front and rear.

Now In circular array we have fruit values.
What we need to find is maximum sum of fruits which bird can traverse.
As bird takes 0.5 seconds to fetch value of fruit and 0.5 seconds to move to another Tree.
We can say is bird can traverse 1 node in 1 sec.
So if bird has X seconds, bird can traverse to X Trees.
Main Problem Statement : Hence Finally we need to get a Window of size X in Circular Array such that sum of the values of the Window is maximum.

 */