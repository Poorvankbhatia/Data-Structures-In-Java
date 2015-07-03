package bitmanuplationPrograms;

import java.util.Scanner;

/**
 * Created by poorvank on 5/2/15.
 */
public class AddOne {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number ");
        int n = scanner.nextInt();

        System.out.println("next number of  - " + n + " is - " + result(n));

    }

    private static int result(int n) {

        int m = 1;

        while ((n & m) != 0) {

            n = n ^ m;
            m = m << 1;

        }

        n = n ^ m;
        return n;

    }

}


/*

To add 1 to a number x (say 0011000111), we need to flip all the bits after the rightmost 0 bit (we get 0011000000). 
Finally, flip the rightmost 0 bit also (we get 0011001000) and we are done.

//
ANOTHER METHOD

We know that the negative number is represented in 2’s complement form on most of the architectures. 
We have the following lemma hold for 2’s complement representation of signed numbers.

Say, x is numerical value of a number, then

~x = -(x+1) [ ~ is for bitwise complement ]

(x + 1) is due to addition of 1 in 2’s complement conversion

To get (x + 1) apply negation once again. So, the final expression becomes (-(~x)).

int addOne(int x)
{
   return (-(~x));
}

Example, assume the machine word length is one nibble for simplicity.
        And x = 2 (0010),
        ~x = ~2 = 1101 (13 numerical)
        -~x = -1101
        Interpreting bits 1101 in 2’s complement form yields numerical value as 
        -(2^4 – 13) = -3. Applying ‘-‘ on the result leaves 3. Same analogy holds for decrement.
        
        Proof of decrement expression :

~x + 1 = -x

Put x = ~x //since the above equation holds for every number

=> ~(~x) + 1 = -(~x)
=> (x + 1) = -(~x)

 */