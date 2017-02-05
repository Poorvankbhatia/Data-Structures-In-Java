/**
 * You have to paint N boards of length {A0, A1, A2 … AN-1}. There are K painters available and you are also
 * given how much time a painter takes to paint 1 unit of board. You have to get this job done as soon as possible under
 * the constraints that any painter will only paint continuous sections of board, say board {2, 3, 4} or only board {1} or
 * nothing but not board {2, 4, 5}.
 *
 *
 *
 */
package arrays.binarysearching;

/**
 * Created by poorvank on 17/01/17.
 */
public class PaintersProblem {

    private int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (Integer element : arr) {
            if(max<element) {
                max = element;
            }
        }
        return max;
    }

    private int getSum(int[] arr) {
        int sum = 0;
        for (Integer element : arr) {
            sum += element;
        }
        return sum;
    }

    private int getRequiredPainter(int[] arr,int mid) {

        int total = 0,numOfPainters = 1;
        for (Integer element : arr) {
            total += element;
            if (total>mid) {
                total = element;
                numOfPainters++;
            }
        }

        return numOfPainters;
    }

    public int maxSum(int[] arr,int painterCount) {

        int lo = getMax(arr);
        int hi = getSum(arr);

        while (lo<hi) {
            int mid = lo + (hi-lo)/2;
            int requiredPainters = getRequiredPainter(arr,mid);
            if(requiredPainters<=painterCount) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }

        return lo;

    }


    public static void main(String[] args) {
        int[] arr = new int[]{12, 34, 67, 90};
        System.out.println(new PaintersProblem().maxSum(arr,2));
    }

}

/**

 Given an array of non-negative integers A and a positive integer k, we want to:
 Divide A into k or fewer partitions,
 such that the maximum sum over all the partitions is minimized.

 Solution:

 Assume that you are assigning continuous section of board to each painter such that its total length must not exceed a predefined maximum,
 costmax. Then, you are able to find the number of painters that is required, x. Following are some key observations:

 The lowest possible value for costmax must be the maximum element in A (name this as lo).
 The highest possible value for costmax must be the entire sum of A, (name this as hi).
 As costmax increases, x decreases. The opposite also holds true.
 Now, the question translates directly into:

 How do we use binary search to find the minimum of costmax while satisfying the condition x = k? The search space will be the range of [lo, hi].
 Let us study using the example below to understand how this works:

 Assume that A = { 100, 200, 300, 400, 500, 600, 700, 800, 900 },
 and k = 3.
 Since k is positive, we know that the highest possible costmax must be the sum of A, 4500. (ie, assigning all boards to one painter).

 The lowest possible costmax must be the largest element in A, 900. This requires a total of six painters — The first painter paints
 {100, 200, 300}, second painter paints {400, 500} while the rest of them paints one board each).

 Below is a simple conceptual illustration of how the search space looks like, with its corresponding x value (the required number of painters)
 pointing to costmax.

 900        ...       4500
 ↑                     ↑
 x=6                   x=1
 Note that x decreases while costmax increases.
 We want to find the minimum of costmax under the constraint of x = k.

 900  ...  2700  ...  4500
 ↑          ↑          ↑
 x=6        x=2        x=1
 We choose the middle element, 2700, and find its corresponding x, which is 2.
 Since x = k (which equals to 3), we can find a better minimum (ie, the real solution) in the lower half.
 Therefore, we discard the upper half and continue searching in the lower half.

 900  ...  1800  ...  2700
 ↑          ↑          ↑
 x=6        x=3        x=2
 The middle element now is 1800 and its corresponding x is 3, which is still = k.
 We discard the upper half again and continue searching in the lower half.

 900  ...  1350  ...  1800
 ↑          ↑          ↑
 x=6        x=5        x=3
 This time, the middle element is 1350 and its corresponding x is 5, which is > k.

 We have to discard the lower half including the middle element itself, since x > k in that region (ie, no valid solution).
 Here, read the phrase in bold “including the middle element itself“ again, as this is extremely important to maintain the invariant. Why?
 Yes, that means the lower half including the value 1350 would be discarded.
 We continue searching in the upper half (ie, in the range of [1351, 1800]).

 After multiple successions of halving the search space, the final answer is 1700, and its corresponding x is 3.
 This is also the minimum of costmax while maintaining the requirement x = k.

 The complexity of this algorithm is O(N log ( ∑ Ai )), which is quite efficient.

 ==============


 DP Solution:

 http://articles.leetcode.com/the-painters-partition-problem/

 y defining M[n, k] as the optimum cost of a partition arrangement with n total blocks from the first block and k partitions,
 the recurrence relation can be stated as follow:

                       n          n-1
 M[n, k] = min { max { M[j, k-1], ∑ Ai } }
                       j=1        i=j
 The base cases are:

 M[1, k] = A[0]
            n-1
 M[n, 1] = ∑ Ai
 i=0



 */