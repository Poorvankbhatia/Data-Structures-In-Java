//Also have a  Dynamic programming approach

/*

Imagine you have a special keyboard with the following keys: 
Key 1:  Prints 'A' on screen
Key 2: (Ctrl-A): Select screen
Key 3: (Ctrl-C): Copy selection to buffer
Key 4: (Ctrl-V): Print buffer on screen appending it
                 after what has already been printed. 

If you can only press the keyboard for N times (with the above four
keys), write a program to produce maximum numbers of A's. That is to
say, the input parameter is N (No. of keys that you can press), the 
output is M (No. of As that you can produce).

 */

package miscellaneous;

public class PrintMaximumAs {
    
    public static void main(String[] args) {

       for (int i=0;i<20;i++) {
           
           System.out.println("Maximum number of A's - with " + i + " keystrokes is - " + noOfAs(i));
           
       }
        
        
    }
    
    private static int noOfAs(int input) {
        
        if(input<=6) {
            return input;
        }
        
        int max =0 ;
        
        /*
        The above function computes the same subproblems again and again.
         Recomputations of same subproblems can be avoided by storing the solutions to subproblems and solving
          problems in bottom up manner.
         */
        
        for (int i = input-3;i>=1;i--) {
            
            int currentNo = (input-i-1)*noOfAs(i);
            if(currentNo>max) {
                max = currentNo;
            }
            
        }
        
        return max;
    }
    
}

/*


The idea is to compute the optimal string length for N keystrokes by using a simple insight.
 The sequence of N keystrokes which produces an optimal string length will end with a suffix of Ctrl-A, a 
 Ctrl-C, followed by only Ctrl-V's (For N > 6).
The task is to find out the break=point after which we get the above suffix of keystrokes. 
Definition of a breakpoint is that instance after which we need to only press Ctrl-A, Ctrl-C once 
and the only Ctrl-V’s afterwards to generate the optimal length. If we loop from N-3 to 1 and choose each 
of these values for the break-point, and compute that optimal string they would produce. 
Once the loop ends, we will have the maximum of the optimal lengths for various breakpoints, 
thereby giving us the optimal length for N keystrokes


 */