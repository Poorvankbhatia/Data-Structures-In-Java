/*

Count number of set bits in an integer

 */

package bitmanuplationPrograms;

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
