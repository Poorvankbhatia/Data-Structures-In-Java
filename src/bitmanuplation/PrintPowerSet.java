/*

Power Set
Power Set Power set P(S) of a set S is the set of all subsets of S. 
For example S = {a, b, c} then P(s) = {{}, {a}, {b}, {c}, {a,b}, {a, c}, {b, c}, {a, b, c}}.

If S has n elements in it then P(s) will have 2^n elements

 */

package bitmanuplation;

/**
 * Created by poorvank on 8/6/15.
 */
public class PrintPowerSet {

    public static void main(String[] args) {

        char[] array = new char[]{'a', 'b', 'c'};
        powerSet(array, 3);

    }

    private static void powerSet(char[] array, int size) {

        double powerSetSize = Math.pow(2, size);

        for (int counter = 0; counter < powerSetSize; counter++) {

            for (int i = 0; i < size; i++) {

                int j = 1 << i;

                if (((counter) & (j)) != 0) {
                    System.out.print(array[i]);
                }

            }

            System.out.println();

        }

    }

}


/*

Algorithm:

Input: Set[], set_size
1. Get the size of power set
    powet_set_size = pow(2, set_size)
2  Loop for counter from 0 to pow_set_size
     (a) Loop for i = 0 to set_size
          (i) If ith bit in counter is set
               Print ith element from set for this subset
     (b) Print seperator for subsets i.e., newline
Example:

Set  = [a,b,c]
power_set_size = pow(2, 3) = 8
Run for binary counter = 000 to 111

Value of Counter            Subset
    000                    -> Empty set
    001                    -> a
    011                    -> ab
   100                     -> c
   101                     -> ac
   110                     -> bc
   111                     -> abc

 */