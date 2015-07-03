/*

Counting sort is a sorting technique based on keys between a specific range. 
It works by counting the number of objects having distinct key values (kind of hashing). 
Then doing some arithmetic to calculate the position of each object in the output sequence.

 */

package arrayPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 7/1/15.
 */
public class CountingSort {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 4, 1, 2, 7, 5, 2};
        int[] output = new int[arr.length];
        int[] count = new int[arr.length + 1];

        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }


        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = 0; i < arr.length; i++) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < output.length; i++) {
            arr[i] = output[i];
        }

        System.out.println(Arrays.toString(arr));

    }

}

/*

For simplicity, consider the data in the range 0 to 9.
Input data: 1, 4, 1, 2, 7, 5, 2
  1) Take a count array to store the count of each unique object.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  2  0   1  1  0  1  0  0

  2) Modify the count array such that each element at each index
  stores the sum of previous counts.
  Index:     0  1  2  3  4  5  6  7  8  9
  Count:     0  2  4  4  5  6  6  7  7  7

The modified count array indicates the position of each object in
the output sequence.

  3) Output each object from the input sequence followed by
  decreasing its count by 1.
  Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
  Put data 1 at index 2 in output. Decrease count by 1 to place
  next data 1 at an index 1 smaller than this index.

Points to be noted:
1. Counting sort is efficient if the range of input data is not significantly greater than the number
 of objects to be sorted. Consider the situation where the input sequence is between range 1 to
  10K and the data is 10, 5, 10K, 5K.
2. It is not a comparison based sorting. It running time complexity is O(n) with space proportional
 to the range of data.
3. It is often used as a sub-routine to another sorting algorithm like radix sort.
4. Counting sort uses a partial hashing to count the occurrence of the data object in O(1).
5. Counting sort can be extended to work for negative inputs also.
 */