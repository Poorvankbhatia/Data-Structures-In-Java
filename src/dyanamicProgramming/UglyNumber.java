/*

Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
shows the first 11 ugly numbers. By convention, 1 is included.
Write a program to find and print the 150’th ugly number.

 */

package dyanamicProgramming;

/**
 * Created by poorvank on 5/19/15.
 */
public class UglyNumber {

    public static void main(String[] args) {

        System.out.println(DPMethod(150));

    }

    private static int DPMethod(int n) {

        int[] ugly = new int[n];

        ugly[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i++) {

            int m2 = (2 * ugly[i2]);
            int m3 = (3 * ugly[i3]);
            int m5 = (5 * ugly[i5]);

            int nextUglyNo = min(m2, m3, m5);

            ugly[i] = nextUglyNo;

            if (nextUglyNo == m2) {
                i2 += 1;
            }
            if (nextUglyNo == m3) {
                i3 += 1;
            }
            if (nextUglyNo == m5) {
                i5 += 1;
            }

        }

        return ugly[n - 1];
    }

    private static int min(int a, int b, int c) {

        return a < b ? (c > a ? a : c) : (c > b ? b : c);

    }

}

/*

     Here is a time efficient solution with O(n) extra space. The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15..
     because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to 
     three groups as below:
     (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     (3) 1×5, 2×5, 3×5, 4×5, 5×5, …

     We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.
     Then we use similar merge method as merge sort, to get every ugly number from the three subsequence. 
     Every step we choose the smallest one, and move one step after.

Algorithm:

1 Declare an array for ugly numbers:  ugly[150]
2 Initialize first ugly no:  ugly[0] = 1
3 Initialize three array index variables i2, i3, i5 to point to
   1st element of the ugly array:
        i2 = i3 = i5 =0;
4 Initialize 3 choices for the next ugly no:
         next_mulitple_of_2 = ugly[i2]*2;
         next_mulitple_of_3 = ugly[i3]*3
         next_mulitple_of_5 = ugly[i5]*5;
5 Now go in a loop to fill all ugly numbers till 150:

The method is not multiplying 7*2 after 6*2. i.e. The numbers which are being multiplied with 2,3,and 5 
are those numbers which already have been generated in the past series. So after 6*2. 
the next numbers are 8*2, 5*3 and 3*5 because 8,5,3 are already generated and not 7. So the next number is 15 not 14.



 */