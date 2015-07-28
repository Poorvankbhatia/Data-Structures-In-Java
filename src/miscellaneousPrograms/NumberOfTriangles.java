/*

Count the number of possible triangles
Given an unsorted array of positive integers. Find the number of triangles that can be formed with three 
different array elements as 
three sides of triangles. For a triangle to be possible from 3 values, the sum of any two values (or sides) 
must be greater than the third value (or third side).
For example, if the input array is {4, 6, 3, 7}, the output should be 3. There are three triangles possible 
{3, 4, 6}, {4, 6, 7} and {3, 6, 7}. Note that {3, 4, 7} is not a possible triangle.
As another example, consider the array {10, 21, 22, 100, 101, 200, 300}. There can be 6 possible triangles: 
{10, 21, 22}, {21, 100, 101}, {22, 100, 101}, {10, 100, 101}, {100, 101, 200} and {101, 200, 300}

 */

package miscellaneousPrograms;

/**
 * Created by poorvank on 7/28/15.
 */
public class NumberOfTriangles {

    public static void main(String[] args) {

        int[] arr = new int[]{10, 21, 22, 100, 101, 200, 300};

        int count = 0, n = arr.length;

        for (int i = 0; i < n - 2; i++) {

            int k = i + 2;

            for (int j = i + 1; j < n; j++) {


                // Find the rightmost element which is smaller than the sum
                // of two fixed elements
                // The important thing to note here is, we use the previous
                // value of k. If value of arr[i] + arr[j-1] was greater than arr[k],
                // then arr[i] + arr[j] must be greater than k, because the
                // array is sorted.
                while (k < n && arr[i] + arr[j] > arr[k]) {

                    if (k != j) {
                        System.out.println(arr[i] + " ," + arr[j] + " , " + arr[k]);
                    }
                    k++;
                }


                // Total number of possible triangles that can be formed
                // with the two fixed elements is k - j - 1.  The two fixed
                // elements are arr[i] and arr[j].  All elements between arr[j+1]
                // to arr[k-1] can form a triangle with arr[i] and arr[j].
                // One is subtracted from k because k is incremented one extra
                // in above while loop.
                // k will always be greater than j. If j becomes equal to k, then
                // above loop will increment k, because arr[k] + arr[i] is always
                // greater than arr[k]
                count += k - j - 1;

            }

        }

        System.out.println("Total number of triangles formed " + count);

    }

}

/*

Let a, b and c be three sides. The below condition must hold for a triangle (Sum of two sides is greater 
than the third side)
i) a + b > c
ii) b + c > a
iii) a + c > b

Following are steps to count triangle.

1. Sort the array in non-decreasing order.

2. Initialize two pointers ‘i’ and ‘j’ to first and second elements respectively, and initialize count of triangles as 0.

3. Fix ‘i’ and ‘j’ and find the rightmost index ‘k’ (or largest ‘arr[k]’) such that ‘arr[i] + arr[j] > arr[k]’. 
The number of triangles that can be formed with ‘arr[i]’ and ‘arr[j]’ as two sides is ‘k – j’. Add ‘k – j’ 
to count of triangles.

Let us consider ‘arr[i]’ as ‘a’, ‘arr[j]’ as b and all elements between ‘arr[j+1]’ and ‘arr[k]’ as ‘c’. 
The above mentioned conditions (ii) and (iii) are satisfied because ‘arr[i] < arr[j] < arr[k]'. 
And we check for condition (i) when we pick 'k'

4. Increment ‘j’ to fix the second element again.

Note that in step 3, we can use the previous value of ‘k’. The reason is simple, if we know that the value of 
‘arr[i] + arr[j-1]’ is greater than ‘arr[k]’, then we can say ‘arr[i] + arr[j]’ will also be greater than ‘arr[k]’, 
because the array is sorted in increasing order.

5. If ‘j’ has reached end, then increment ‘i’. Initialize ‘j’ as ‘i + 1′, ‘k’ as ‘i+2′ and repeat the steps 3 and 4.

 */