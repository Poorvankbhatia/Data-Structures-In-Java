/*

Count number of set bits in an integer

 */

package bitManuplationPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 5/2/15.
 */
public class CountSetBits {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");
        int n = scanner.nextInt();

        System.out.println("Number of bits are - " + count(n));

    }

    private static int count(int n) {

        int count = 0;
        while (n != 0) {

            n = n & (n - 1);
            count++;

        }

        return count;

    }

}


/*

in the worst case all the bits of n are set.
since the number of bits is logn to base 2.the loop is executed logn times

in Brian Kernighan’s Algorithm, the number of times the loop executes is equal to number of set bits in the number.

 */