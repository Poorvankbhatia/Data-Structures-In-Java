/*

Given a positive integer n, print first k digits after point in value of 1/n. 
Your program should avoid overflow and floating point arithmetic.

 */

package miscellaneous;

import java.util.Scanner;

public class DigitsAfterDecimal {

    public static void main(String[] args) {

        System.out.println("Enter the number : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("Enter number of digits after decimal : ");
        int k = sc.nextInt();

        int rem = 1;

        for (int i = 1; i <= k; i++) {

            System.out.println("digit number " + i + " is = " + (10 * rem) / n);

            rem = (rem * 10) % n;
        }

    }

}

/*


Let us consider an example n = 7, k = 3. 
The first digit of 1/7 is ‘1’, it can be obtained by doing integer value of 10/7.
 Remainder of 10/7 is 3. Next digit is 4 which can be obtained by taking integer value of 30/7. 
 Remainder of 30/7 is 2. Next digits is 2 which can be obtained by taking integer value of 20/7


 */