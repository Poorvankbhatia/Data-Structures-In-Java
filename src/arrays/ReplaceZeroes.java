/*

Given a binary array and an integer m, find the position of zeroes flipping which creates maximum number of consecutive 1s in array.

Examples:

Input:   arr[] = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}
         m = 2
Output:  5 7
We are allowed to flip maximum 2 zeroes. If we flip
arr[5] and arr[7], we get 8 consecutive 1's which is
maximum possible under given constraints

Input:   arr[] = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}
         m = 1
Output:  7
We are allowed to flip maximum 1 zero. If we flip
arr[7], we get 5 consecutive 1's which is maximum
possible under given constraints.

Input:   arr[] = {0, 0, 0, 1}
         m = 4
Output:  0 1 2
Since m is more than number of zeroes, we can flip
all zeroes.

 */

package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 29/01/17.
 */
public class ReplaceZeroes {

    public List<Integer> findReplaceIndex(int[] arr,int m) {

        int start=0,end=0,zeroCount=0;
        int maxLength = 0,n=arr.length;
        List<Integer> indexList = new ArrayList<>();

        while (end<n) {

            if(zeroCount<=m) {
                if(arr[end]==0) {
                    zeroCount++;
                }
                end++;
            }

            if(zeroCount>m) {
                if(arr[start]==0) {
                    zeroCount--;
                }
                start++;
            }

            if(maxLength<end-start) {
                maxLength = end-start;
            }

        }

        for (int i=start;i<end;i++) {
            if(arr[i]==0) {
                indexList.add(i);
            }
        }

        return indexList;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 0, 0};
        int m=2;
        System.out.println(new ReplaceZeroes().findReplaceIndex(arr,m));

    }

}

/*

An Efficient Solution can solve the problem in O(n) time and O(1) space.
The idea is to use Sliding Window for the given array.
Let us use a window covering from index wL to index wR. Let the number of zeros inside the window be zeroCount.
We maintain the window with at most m zeros inside.

The main steps are:
– While zeroCount is no more than m: expand the window to the right (wR++) and update the count zeroCount.
– While zeroCount exceeds m, shrink the window from left (wL++), update zeroCount;
– Update the widest window along the way. The positions of output zeros are inside the best window.

 */
