/*

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example, given [100, 4, 200, 1, 3, 2], the longest consecutive elements sequence should be [1, 2, 3, 4]. 
Its length is 4.

Your algorithm should run in O(n) complexity.

 */

package arrays;

import java.util.HashSet;

/**
 * Created by poorvank on 4/24/15.
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] arr = new int[]{-1,1,2,0};

        HashSet<Integer> hashSet = new HashSet<>();

        for (int a : arr) {
            hashSet.add(a);
        }

        int max = 1;

        for (int a : arr) {

            int right = a + 1;
            int left = a - 1;
            int count = 1;

            while (hashSet.contains(right)) {
                hashSet.remove(right);
                count++;
                right++;
            }

            while (hashSet.contains(left)) {
                hashSet.remove(left);
                count++;
                left--;
            }

            max = Math.max(count, max);
        }

        System.out.println(max);

    }


}


/*

Because it requires O(n) complexity, we can not solve the problem by sorting the array first. 
Sorting takes at least O(nlogn) time.

We can use a HashSet to add and remove elements. HashSet is implemented by using a hash table. Elements are not ordered.
The add, remove and contains methods have constant time complexity O(1).


After an element is checked, it should be removed from the set. Otherwise, time complexity would be O(mn) in which 
m is the average length of all consecutive sequences.

To clearly see the time complexity, I suggest you use some simple examples and manually execute the program. 
For example, given an array {1,2,4,5,3}, the program time is m. m is the length 
of longest consecutive sequence.

We do have an extreme case here: If n is number of elements, m is average length of consecutive sequence, and m==n, 
then the time complexity is O(n^2). The reason is that in this case, no element is removed from the set each time. 
You can use this array to get the point: {1,3,5,7,9}.

 */