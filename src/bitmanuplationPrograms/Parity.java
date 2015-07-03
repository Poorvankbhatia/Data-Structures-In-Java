/*

Write a C program to find the parity of an unsigned integer

 */

package bitManuplationPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 5/2/15.
 */
public class Parity {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number");

        int num = scanner.nextInt();
        System.out.println("Parity is - " + (getParity(num) ? "odd" : "even"));


    }


    private static boolean getParity(int n) {

        boolean parity = false;

        while (n > 0) {

            parity = !parity;
            n = (n & n - 1);

        }

        return parity;

    }

}


/*

Parity: Parity of a number refers to whether it contains an odd or even number of 1-bits. 
The number has “odd parity”, if it contains odd number of 1-bits and is “even parity” if it contains even number of 1-bits.
Main idea of the below solution is – Loop while n is not 0 and in loop unset one of the set bits and invert parity.

Time Complexity: The time taken by above algorithm is proportional to the number of bits set. 
Worst case complexity is O(Logn).

Uses: Parity is used in error detection and cryptography.

 */