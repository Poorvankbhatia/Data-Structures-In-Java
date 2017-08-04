/*

Given an array where every element occurs three times, except one element which occurs only once.
Find the element that occurs once. Expected time complexity is O(n) and O(1) extra space.
Examples:

Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
Output: 2

 */
package bitmanuplation;

/**
 * Created by poorvank.b on 20/07/17.
 */
public class AppearOnce {

    static final int INT_SIZE = 32;

    // Method to find the element that occur only once
    static int getSingle(int arr[], int n)
    {
        int result = 0;
        int x, sum;

        // Iterate through every bit
        for(int i=0; i<INT_SIZE; i++)
        {

            sum = 0;
            //Generates multiple of 2 -> 1,10,100..
            x = (1 << i);
            for(int j=0; j<n; j++)
            {
                if((arr[j] & x) == 0)
                    sum++;
            }
            // The bits with sum not multiple of 3, are the
            // bits of element with single occurrence.
            if ((sum % 3) == 0) {
                result |= x;
            }
        }
        return result;
    }

    public static void main(String args[])
    {
        int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 2, 2, 3, 7};
        int n = arr.length;
        System.out.println("The element with single occurrence is " + getSingle(arr, n));
    }

}

/*

Following is another O(n) time complexity and O(1) extra space method suggested by aj.
We can sum the bits in same positions for all the numbers and take modulo with 3. The bits for which sum is not multiple of 3,
are the bits of number with single occurrence.
Let us consider the example array {5, 5, 5, 8}. The 101, 101, 101, 1000
Sum of first bits%3 = (1 + 1 + 1 + 0)%3 = 0;
Sum of second bits%3 = (0 + 0 + 0 + 0)%0 = 0;
Sum of third bits%3 = (1 + 1 + 1 + 0)%3 = 0;
Sum of fourth bits%3 = (1)%3 = 1;
Hence number which appears once is 1000

 */
