/*

Count number of bits to be flipped to convert A to B

 */

package bitmanuplationPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 5/2/15.
 */
public class BitsFlipped {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number 1 ");
        int num1 = scanner.nextInt();
        System.out.println("Enter number 2 ");
        int num2 = scanner.nextInt();

        System.out.println("Number of flipped bits - " + countFlipped(num1, num2));


    }


    private static int countFlipped(int a, int b) {

        int num = a ^ b;
        return countSetBits(num);

    }


    private static int countSetBits(int n) {

        int count = 0;
        while (n > 0) {
            n = n & n - 1;
            count++;
        }

        return count;

    }
}


/*

  1. Calculate XOR of A and B.
        a_xor_b = A ^ B
  2. Count the set bits in the above calculated XOR result.
        countSetBits(a_xor_b)
XOR of two number will have set bits only at those places where A differs from B.

Example:

   A  = 1001001
   B  = 0010101
   a_xor_b = 1011100
   No of bits need to flipped = set bit count in a_xor_b i.e. 4

 */