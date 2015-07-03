/*

Check if a number is a power of 2 or not

 */

package bitmanuplationPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 5/1/15.
 */
public class PowerOfTwo {

    public static void main(String[] args) {

        System.out.println("Enter number");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if ((n & (n - 1)) == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("Nope");
        }

    }

}
/*

  If we have to check if the number is of the form 2^n then : 
  n & (n-1) == 0 gives us the answer
    
  Any power of 2 minus 1 is all ones: (2 N - 1 = 111....b)

   2 = 2^1.  2-1 = 1 (1b)
   4 = 2^2.  4-1 = 3 (11b)
   8 = 2^3.  8-1 = 7 (111b)
   Take 8 for example. 1000 & 0111 = 0000

   So that expression tests if a number is NOT a power of 2.  
   
   
   1.if the number is a power of two already, 
   then one less will result in a binary number that only has the lower-order bits set. Using & there will do nothing.

Example with 8: 0100 & (0100 - 1) --> (0100 & 0011) --> 0000
if the number is not a power of two already, then one less will do this, which returns the highest power 
of two not greater than num:

Example with 3: 0011 & (0011 - 1) --> (0011 & 0010) --> 0010
   

*/